package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.AdminLogic;
import model.AdminModel;
import util.settings.DBSettings;
import util.settings.MSSettings;

/**
 * Servlet implementation class AdminEditDoneServlet
 */
@WebServlet("/AdminEditDoneServlet")
public class AdminEditDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditDoneServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		// セッションパラメーターを取得
		AdminModel model = new AdminModel();
		HttpSession session = request.getSession();
		model = (AdminModel) session.getAttribute("editAdmin");
		if(model == null) {
			// エラーメッセージ
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin/edit.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// DBを更新
		AdminLogic logic = new AdminLogic();
		int result = logic.update(model);
		if(result == DBSettings.DB_EXECUTION_SUCCESS) {
			// リクエストスコープを設定
			request.setAttribute("title", MSSettings.MSG_TITLE_EDIT);
			request.setAttribute("legend", MSSettings.MSG_TITLE_EDIT);
			request.setAttribute("message", MSSettings.MSG_EDIT_COMPLETE);
			// sessionを削除
			session.removeAttribute("editAdmin");
			session.removeAttribute("admin");	// ログインをやり直す
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/done.jsp");
			dispatcher.forward(request, response);
			return;
		}
		// 登録処理に失敗
		else {
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/edit.jsp");
			dispatcher.forward(request, response);
			return;
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
