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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.GenreLogic;
import model.GenreModel;
import model.NewsModel;
import validation.NewsValidation;

/**
 * Servlet implementation class NewsRegisterServlet
 */
@WebServlet("/NewsRegisterServlet")
@MultipartConfig
public class NewsRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 各パラメーターを取得
		String title 	= request.getParameter("title");
		String picture	= request.getParameter("picture");
		String article 	= request.getParameter("article");
		String url 		= request.getParameter("url");
		String twitter	= request.getParameter("twitter");
		int genreID		= Integer.parseInt(request.getParameter("genre"));
		
		// バリデーション
		NewsValidation validate = new NewsValidation(request);
		Map<String, String> errors = validate.validation();
		if(validate.hasErrors()) {
			request.setAttribute("errors", errors);
			
			Map<String, String> news = new HashMap<String, String>();
			news.put("title", title);
			news.put("picture", picture);
			news.put("article", article);
			news.put("url", url);
			news.put("twitter", twitter);
			request.setAttribute("news", news);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news/register.jsp");
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
		//登録日（本日の日付）
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		news.setRegistrationDate(java.sql.Date.valueOf(strDate));
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news/newsRegisterConfirm.jsp");
		dispatcher.forward(request, response);
	}

}
