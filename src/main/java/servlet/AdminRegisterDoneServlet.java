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
 * Servlet implementation class AdminRegisterDoneServlet
 */
@WebServlet("/AdminRegisterDoneServlet")
public class AdminRegisterDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRegisterDoneServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		// パラメーターを取得
		HttpSession session = request.getSession();
		AdminModel model = (AdminModel) session.getAttribute("newAdmin");
		if(model == null) {
			// エラーメッセージ
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/register.jsp");
			dispatcher.forward(request, response);
		}
		
		// DBに登録
		AdminLogic logic = new AdminLogic();
		int result = logic.create(model);
		
		// 登録処理に成功
		if(result == DBSettings.DB_EXECUTION_SUCCESS) {
			// リクエストスコープを設定
			request.setAttribute("title", MSSettings.MSG_TITLE_RESISTER);
			request.setAttribute("legend", MSSettings.MSG_TITLE_RESISTER);
			request.setAttribute("message", MSSettings.MSG_RESISTER_COMPLETE);
			// sessionを削除
			session.removeAttribute("newAdmin");
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/done.jsp");
			dispatcher.forward(request, response);
		}
		// 登録処理に失敗
		else {
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/register.jsp");
			dispatcher.forward(request, response);
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
