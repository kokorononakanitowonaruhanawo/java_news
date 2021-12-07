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
 * Servlet implementation class NewsRegisterDoneServlet
 */
@WebServlet("/NewsRegisterDoneServlet")
public class NewsRegisterDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsRegisterDoneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MSSettings settings = new MSSettings();
		RequestDispatcher dispatcher = null;
		// session情報を取得
		HttpSession session = request.getSession();
		NewsModel news = (NewsModel) session.getAttribute("news");
		
		// 取得できていない場合
		if(news == null) {
			// エラーメッセージ
			request.setAttribute("error", settings.MSG_ERROR_DERIVERY);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news/register.jsp");
			dispatcher.forward(request, response);
		}
		
		// DBに登録
		NewsLogic logic = new NewsLogic();
		int result = logic.crate(news);
		
		// 登録処理に成功
		if(result == DBSettings.DB_EXECUTION_SUCCESS) {
			// リクエストスコープを設定
			request.setAttribute("title", settings.MSG_TITLE_RESISTER);
			request.setAttribute("legend", settings.MSG_TITLE_RESISTER);
			request.setAttribute("message", settings.MSG_RESISTER_COMPLETE);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/done.jsp");
			dispatcher.forward(request, response);
		}
		// 登録処理に失敗
		else {
			request.setAttribute("error", settings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news/register.jsp");
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
