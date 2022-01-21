package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.GenreLogic;
import model.GenreModel;
import util.settings.DBSettings;
import util.settings.MSSettings;

/**
 * Servlet implementation class GenreDeleteDoneServlet
 */
@WebServlet("/GenreDeleteDoneServlet")
public class GenreDeleteDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenreDeleteDoneServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		// セッションパラメーターから値を取得
		GenreModel model = new GenreModel();
		model =	(GenreModel) session.getAttribute("genre");
		if(model == null) {
			// エラーメッセージ
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/delete.jsp");
			dispatcher.forward(request, response);
		}
		
		model.setIsDeleted(1);
		
		// DBから削除（is_deleted = 1 にする）
		GenreLogic logic = new GenreLogic();
		int result = logic.update(model);
		
		// 削除処理に成功
		if(result == DBSettings.DB_EXECUTION_SUCCESS) {
			// リクエストスコープを設定
			request.setAttribute("title", MSSettings.MSG_TITLE_DELETE);
			request.setAttribute("legend", MSSettings.MSG_TITLE_DELETE);
			request.setAttribute("message", MSSettings.MSG_DELETE_COMPLETE);
			// sessionを削除
			session.removeAttribute("genre");
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/done.jsp");
			dispatcher.forward(request, response);
		}
		// 削除処理に失敗
		else {
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/delete.jsp");
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
