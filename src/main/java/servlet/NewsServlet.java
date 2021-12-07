package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメーターを取得
		int newsId = 0;
/**		newsId = Integer.parseInt((String)request.getParameter("id"));
		if(newsId == 0) {
			response.sendRedirect("/java_news/IndexServlet");
		}
*/
		newsId = 3;
		// 取得したIDに基づいてnews情報を取得
		NewsModel news = new NewsModel();
		NewsLogic logic = new NewsLogic();
		news = logic.findOne(newsId);
		if(news == null) {
			response.sendRedirect("/java_news/IndexServlet");
		}
		
		// sessionに保存
		request.removeAttribute("news");
		request.setAttribute("news", news);
		
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
