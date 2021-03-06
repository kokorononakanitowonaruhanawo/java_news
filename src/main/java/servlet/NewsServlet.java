package servlet;

import java.io.IOException;

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
 * Servlet implementation class NewsServlet
 */
@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメーターを取得
		int newsId = 0;
		try {
			newsId = Integer.parseInt((String) request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect("/java_news/IndexServlet");
		}

		// 取得したIDに基づいてnews情報を取得
		NewsModel news = new NewsModel();
		NewsLogic logic = new NewsLogic();
		news = logic.findOne(newsId);
		if(news == null) {
			response.sendRedirect("/java_news/IndexServlet");
		}
		
		// スコープに保存
		HttpSession session = request.getSession();
		session.removeAttribute("news");
		session.setAttribute("news", news);
		
		// forward
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news.jsp");
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
