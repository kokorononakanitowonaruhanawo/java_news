package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminModel;
import validation.AdminValidation;

/**
 * Servlet implementation class AdminRegisterServlet
 */
@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// forward
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 各パラメーターを取得
		String name 		= request.getParameter("name");
		String email		= request.getParameter("email");
		String password1 	= request.getParameter("password1");
		String password2 	= request.getParameter("password2");
		
		AdminModel admin = new AdminModel();
		admin.setName(name);
		admin.setEmail(email);
		
		// バリデーション
		AdminValidation validate = new AdminValidation(request);
		Map<String, String> errors = validate.validation();
		if(validate.hasErrors()) {
			request.setAttribute("errors", errors);
			
			request.setAttribute("newAdmin", admin);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/register.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		admin.setPassword(password1);
		
		//登録日（本日の日付）
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		admin.setRegistrationDate(java.sql.Date.valueOf(strDate));
		
		// セッションスコープに保存
		HttpSession session = request.getSession();
		session.removeAttribute("newAdmin");
		session.setAttribute("newAdmin", admin);
		
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/registerConfirm.jsp");
		dispatcher.forward(request, response);
	}

}
