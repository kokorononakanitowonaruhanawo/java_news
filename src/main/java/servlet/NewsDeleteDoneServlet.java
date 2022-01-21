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
import util.settings.DBSettings;
import util.settings.MSSettings;

/**
 * Servlet implementation class NewsDeleteDoneServlet
 */
@WebServlet("/NewsDeleteDoneServlet")
public class NewsDeleteDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsDeleteDoneServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		// session情報を取得
		HttpSession session = request.getSession();
		NewsModel news = (NewsModel) session.getAttribute("news");
		
		// 取得できていない場合
		if(news == null) {
			// エラーメッセージ
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news/edit.jsp");
			dispatcher.forward(request, response);
		}
		
		news.setIsDeleted(1);
		
		// DBに登録
		NewsLogic logic = new NewsLogic();
		int result = logic.update(news);
		
		// 登録処理に成功
		if(result == DBSettings.DB_EXECUTION_SUCCESS) {
			// リクエストスコープを設定
			request.setAttribute("title", MSSettings.MSG_TITLE_EDIT);
			request.setAttribute("legend", MSSettings.MSG_TITLE_EDIT);
			request.setAttribute("message", MSSettings.MSG_EDIT_COMPLETE);
			// sessionを削除
			session.removeAttribute("news");
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/done.jsp");
			dispatcher.forward(request, response);
		}
		// 登録処理に失敗
		else {
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news/edit.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
