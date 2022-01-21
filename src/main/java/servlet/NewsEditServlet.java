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
import model.NewsModel;
import util.settings.MSSettings;
import validation.NewsValidation;

/**
 * Servlet implementation class NewsEditServlet
 */
@WebServlet("/NewsEditServlet")
public class NewsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsEditServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		// 全てのジャンルを取得
		GenreLogic logic = new GenreLogic();
		List<GenreModel> genreList = new ArrayList<>();
		genreList = logic.find();
		
		// SessionScopeに保存
		HttpSession session = request.getSession();
		session.setAttribute("genreList", genreList);
				
		// 編集するニュースを取得
		NewsModel model = new NewsModel();
		model = (NewsModel) session.getAttribute("news");
		if(model == null) {
			// エラーメッセージ
			request.setAttribute("error", MSSettings.MSG_ERROR_OCCURRED);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			dispatcher.forward(request, response);
		}
		
		// title
		request.setAttribute("title", "編集");
		
		// forward
		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news/edit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 各パラメーターを取得
		String title 	= request.getParameter("title");
		String picture	= request.getContextPath() + "/img/" + request.getParameter("picture");
		String article 	= request.getParameter("article");
		String url 		= request.getParameter("url");
		String twitter	= request.getParameter("twitter");
		int genreID = 0;
		try {
			genreID = Integer.parseInt(request.getParameter("genre"));
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		// バリデーション
		NewsValidation validate = new NewsValidation(request);
		Map<String, String> errors = validate.validation();
		if(validate.hasErrors() | genreID == 0) {
			request.setAttribute("errors", errors);
			
			Map<String, String> news = new HashMap<String, String>();
			news.put("title", title);
			news.put("picture", picture);
			news.put("article", article);
			news.put("url", url);
			news.put("twitter", twitter);
			request.setAttribute("news", news);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news/edit.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		NewsModel news = new NewsModel();
		news.setTitle(title);
		news.setPicture(picture);
		news.setArticle(article);
		news.setURL(url);
		news.setTwitter(twitter);
		news.setGenreId(genreID);
		// ジャンル
		GenreLogic logic = new GenreLogic();
		GenreModel model = new GenreModel();
		model = logic.find(genreID);
		news.setGenreModel(model);
		
		// セッションスコープに保存
		HttpSession session = request.getSession();
		session.removeAttribute("news");
		session.setAttribute("news", news);
		
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news/editConfirm.jsp");
		dispatcher.forward(request, response);
	
	}

}
