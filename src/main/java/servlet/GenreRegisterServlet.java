package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.GenreLogic;
import model.GenreModel;
import validation.GenreValidation;

/**
 * Servlet implementation class GenreRegisterServlet
 */
@WebServlet("/GenreRegisterServlet")
public class GenreRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenreRegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 全てのジャンルを取得
		GenreLogic logic = new GenreLogic();
		List<GenreModel> genreList = new ArrayList<>();
		genreList = logic.find();

		// SessionScopeに保存
		HttpSession session = request.getSession();
		session.setAttribute("genreList", genreList);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 各パラメーターを取得
		String strGenre = request.getParameter("genre");
		
		// バリデーション
		GenreValidation validate = new GenreValidation(request);
		Map<String, String> error = validate.validation();
		if(validate.hasErrors()) {
			request.setAttribute("error", error);
			
			Map<String, String> genre = new HashMap<String, String>();
			genre.put("genre", strGenre);
			request.setAttribute("genre", strGenre);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/register.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		GenreModel genre = new GenreModel();
		genre.setGenre(strGenre);
		//登録日（本日の日付）
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		genre.setRegistrationDate(java.sql.Date.valueOf(strDate));
		
		// セッションスコープに保存
		HttpSession session = request.getSession();
		session.removeAttribute("genre");
		session.setAttribute("genre", genre);
		
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/registerConfirm.jsp");
		dispatcher.forward(request, response);
	}

}
