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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int size = util.settings.OthersSettings.DISPLAY_NUMBER;
		
		// 全てのニュースを取得
		NewsLogic logic = new NewsLogic();
		List<NewsModel> newsList = new ArrayList<>();
		newsList = logic.find();
		
		if(newsList == null) {
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			dispatcher.forward(request, response);
		}
		
		// 何ページ目を表示するか
		String strNum = request.getParameter("page");
		int num = 0;
		if(strNum != null) {
			num = Integer.parseInt(strNum);
		}
		
		// 表示するページを取得
		List<NewsModel> list = new ArrayList<NewsModel>();
		int start = num * size;
		int end = Math.min(start + size, newsList.size());
		list = newsList.subList(start, end);
		
		// 全部で何ページあるか
		int block = (newsList.size() + (size - 1)) / size;
		
		// Scopeに保存
		HttpSession session = request.getSession();
		session.removeAttribute("list");
		session.setAttribute("list", list);
		request.setAttribute("page", num + 1);
		request.setAttribute("block", block);
		
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
