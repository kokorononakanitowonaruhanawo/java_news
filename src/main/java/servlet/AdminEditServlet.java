package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.AdminLogic;
import model.AdminModel;
import util.settings.MSSettings;
import validation.AdminValidation;

/**
 * Servlet implementation class AdminEditServlet
 */
@WebServlet("/AdminEditServlet")
public class AdminEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		// ログインしている管理者を取得
		AdminModel model = new AdminModel();
		HttpSession session = request.getSession();
		model = (AdminModel) session.getAttribute("admin");
		if(model == null) {
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			response.sendRedirect("/java_news/LoginServlet");
			return;
		}
		
		// ログインしている管理者を詳しく取得
		AdminLogic logic = new AdminLogic();
		AdminModel loginAdmin = new AdminModel();
		loginAdmin = logic.find(model.getEmail(), model.getPassword());
		
		if(loginAdmin == null) {
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			response.sendRedirect("/java_news/IndexServlet");
			return;
		}
		
		request.removeAttribute("editAdmin");
		request.setAttribute("editAdmin", loginAdmin);
		
		// forward
		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/edit.jsp");
		dispatcher.forward(request, response);
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		// 各パラメーターを取得
		String name 		= request.getParameter("name");
		String email		= request.getParameter("email");
		String password1 	= request.getParameter("password1");
		
		AdminModel editAdmin = new AdminModel();
		editAdmin.setName(name);
		editAdmin.setEmail(email);
		
		// バリデーション
		AdminValidation validate = new AdminValidation(request);
		Map<String, String> errors = validate.validation();
		if(validate.hasErrors()) {
			// 各パラメーターをリクエストスコープに保存
			request.setAttribute("errors", errors);
			request.setAttribute("editAdmin", editAdmin);
			
			//フォワード
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/edit.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// 入力データをセット
		HttpSession session = request.getSession();
		editAdmin = (AdminModel) session.getAttribute("admin");
		editAdmin.setName(name);
		editAdmin.setEmail(email);
		if(!password1.isEmpty()) {
			editAdmin.setPassword(password1);
		}
		
		// セッションスコープに保存
		session.removeAttribute("editAdmin");
		session.setAttribute("editAdmin", editAdmin);
		
		// フォワード
		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/editConfirm.jsp");
		dispatcher.forward(request, response);
		
		return;
	}

}
