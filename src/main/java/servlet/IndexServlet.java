package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.NewsLogic;
import model.NewsModel;
import util.settings.OthersSettings;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = null;
		
		// 何ページ目を表示するか
		int offset = 0;
		
		// 検索
		NewsLogic logic = new NewsLogic();
		List<NewsModel> searchList = new ArrayList<NewsModel>();
		int limit = OthersSettings.DISPLAY_NUMBER;
		
		searchList = logic.find(0, null, null, null, limit, 0);
		
		if(searchList == null) {
			// フォワード
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// 全部で何ページあるか
		int block = logic.count(0, null, null, null) / OthersSettings.DISPLAY_NUMBER;
		
		int isEnd = 0;
		if(block == offset + 1)	{isEnd =1;}
		
		// Scopeに保存
		HttpSession session = request.getSession();
		session = request.getSession();
		session.removeAttribute("list");
		session.setAttribute("list", searchList);
		request.setAttribute("page", offset + 1);
		request.setAttribute("block", block);
		request.setAttribute("isEnd", isEnd);
		
		// フォワード
		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
