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
 * Servlet implementation class GenreEditDoneServlet
 */
@WebServlet("/GenreEditDoneServlet")
public class GenreEditDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenreEditDoneServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		// 編集内容を取得
		HttpSession session = request.getSession();
		GenreModel model = (GenreModel) session.getAttribute("oldGenre");
		String strGenre = (String) session.getAttribute("newGenre");
		if(model == null | strGenre == null) {
			// エラーメッセージ
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/edit.jsp");
			dispatcher.forward(request, response);
		}
		model.setGenre(strGenre);
		
		// DBに登録
		GenreLogic logic = new GenreLogic();
		int result = logic.update(model);
		
		// 登録処理に成功
		if(result == DBSettings.DB_EXECUTION_SUCCESS) {
			// リクエストスコープを設定
			request.setAttribute("title", MSSettings.MSG_TITLE_EDIT);
			request.setAttribute("legend", MSSettings.MSG_TITLE_EDIT);
			request.setAttribute("message", MSSettings.MSG_EDIT_COMPLETE);
			// sessionを削除
			session.removeAttribute("genre");
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/done.jsp");
			dispatcher.forward(request, response);
		}
		// 登録処理に失敗
		else {
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/edit.jsp");
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
