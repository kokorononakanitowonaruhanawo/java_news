
package servlet;

import java.io.IOException;
import java.sql.Date;
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
import logic.NewsLogic;
import model.GenreModel;
import model.NewsModel;
import util.settings.MSSettings;
import util.settings.OthersSettings;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		
		// forward
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		// パラメーターを取得
		String keyword = request.getParameter("keyword");
		int genreID = 0;
		try {
			genreID = Integer.parseInt(request.getParameter("genre"));
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
		}
		
		String strDate = request.getParameter("date");
		Date date = null;
		if(strDate != null && !strDate.isEmpty()) {
			date = java.sql.Date.valueOf(strDate);
		}
		
		String search = request.getParameter("search");
		if((keyword == null | keyword.isEmpty()) && genreID == 0 && date == null) {
			// エラーメッセージ
			request.setAttribute("error", MSSettings.MSG_NO_INPUT);
			// forward
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// 何ページ目を表示するか
		int offset = 0;
		String strOffset = request.getParameter("offset");
		try {
			offset = Integer.parseInt(strOffset);
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		// 検索
		NewsLogic logic = new NewsLogic();
		List<NewsModel> searchList = new ArrayList<NewsModel>();
		int limit = OthersSettings.DISPLAY_NUMBER;
		// keywordが設定されている時
		if(keyword != null && !keyword.isEmpty()) {
			// Genreが設定されている時
			if(genreID != 0) {
				// 日付か設定されている時
				if(date != null) {
					if(search.equals("or"))	{searchList = logic.findOR(keyword, genreID, date, limit, offset);}
					else					{searchList = logic.findAND(keyword, genreID, date, limit, offset);}
				}
				// 日付か設定されていない時
				else {
					if(search.equals("or"))	{searchList = logic.findOR(keyword, genreID, limit, offset);}
					else					{searchList = logic.findAND(keyword, genreID, limit, offset);}
				}
			} 
			// Genreが設定されていない時
			else {
				// 日付か設定されている時
				if(date != null) {
					if(search.equals("or"))	{searchList = logic.findOR(keyword, date, limit, offset);}
					else					{searchList = logic.findAND(keyword, date, limit, offset);}
				}
				// 日付か設定されていない時
				else {searchList = logic.find(keyword, limit, offset);}
			}
		} 
		// keywordが設定されていない時
		else {
			// Genreが設定されている時
			if(genreID != 0) {
				// 日付か設定されている時
				if(date != null) {
					if(search.equals("or"))	{searchList = logic.findOR(genreID, date, limit, offset);}
					else					{searchList = logic.findAND(genreID, date, limit, offset);}
				}
				// 日付か設定されていない時
				else	{searchList = logic.find(genreID, limit, offset);}
			}
			// Genreが設定されていない時
			else {
				// 日付か設定されている時
				if(date != null)	{searchList = logic.find(date, limit, offset);}
			}
		}
		
		if(searchList == null) {
			// フォワード
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// 全部で何ページあるか
		int block = logic.calculateNumOfPage();
		
		int isEnd = 0;
		if(block == offset + 1)	isEnd =1;
		// Scopeに保存
		HttpSession session = request.getSession();
		session = request.getSession();
		session.removeAttribute("list");
		session.setAttribute("list", searchList);
		request.setAttribute("page", offset + 1);
		request.setAttribute("block", block);
		request.setAttribute("isEnd", isEnd);
		
		// フォワード
		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}


}
