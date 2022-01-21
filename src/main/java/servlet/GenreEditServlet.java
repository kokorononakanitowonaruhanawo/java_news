package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class GenreEditServlet
 */
@WebServlet("/GenreEditServlet")
public class GenreEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenreEditServlet() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/edit.jsp");
		dispatcher.forward(request, response);
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		// パラメーターを取得
		int genreID = 0;
		try {
			genreID = Integer.parseInt(request.getParameter("old"));
		} catch (Exception e) {
			//フォワード
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/edit.jsp");
			dispatcher.forward(request, response);
			return;
		}
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
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/edit.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// 選択したジャンルをDBより取得
		GenreModel model = new GenreModel();
		GenreLogic logic = new GenreLogic();
		model = logic.find(genreID);
		
		// sessionスコープに保存
		HttpSession session = request.getSession();
		session.removeAttribute("odlGenre");
		session.removeAttribute("newGenre");
		session.setAttribute("oldGenre", model);
		session.setAttribute("newGenre", strGenre);
		
		// forward
		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/editConfirm.jsp");
		dispatcher.forward(request, response);
		
		return;
	}
	
}
