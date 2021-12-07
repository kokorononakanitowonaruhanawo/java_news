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
import util.settings.MSSettings;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// フォームからのリクエストパラメータを取得する。
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// リクエストパラメータを元に、ユーザーを検索する。
		AdminLogic logic = new AdminLogic();
		AdminModel admin = logic.find(email, password);
		if (admin == null) {
			// ユーザーが見つからなかったときは、エラーメッセージを設定して、ログインページへフォワード
			request.setAttribute("error", MSSettings.MSG_LOGIN_FAILURE);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/login.jsp");
			dispatcher.forward(request, response);
		} else {
			// ユーザーが見つかったときは、ユーザーモデルをセッションに保存し、メインページへリダイレクト
			// ユーザーモデルがセッションに保存されていることでログイン状態を保持
			// セッションからユーザーモデルを削除することでログアウト
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			response.sendRedirect(request.getContextPath() + "/IndexServlet");
		}
	}

}
