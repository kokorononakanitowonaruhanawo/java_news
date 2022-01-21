package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.GenreLogic;
import model.GenreModel;
import util.settings.MSSettings;

/**
 * Servlet implementation class GenreDeleteServlet
 */
@WebServlet("/GenreDeleteServlet")
public class GenreDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenreDeleteServlet() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/delete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		// パラメーターを取得
		int genreID = 0;
		try {
			genreID = Integer.parseInt(request.getParameter("delete"));
		} catch (Exception e) {
			// エラーメッセージ
			request.setAttribute("error", "必ず選択してください");
			//forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/delete.jsp");
			dispatcher.forward(request, response);
		}
		
		// 削除するジャンルをDBから取得
		GenreModel model = new GenreModel();
		GenreLogic logic = new GenreLogic();
		model = logic.find(genreID);
		if(model == null) {
			// エラーメッセージ
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			//forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/delete.jsp");
			dispatcher.forward(request, response);
		}
		
		// セッションスコープに保存
		HttpSession session = request.getSession();
		session.removeAttribute("genre");
		session.setAttribute("genre", model);
		
		// forward
		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/genre/deleteConfirm.jsp");
		dispatcher.forward(request, response);
		
	}

}
