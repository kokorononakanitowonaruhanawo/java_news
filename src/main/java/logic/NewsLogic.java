package logic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dao.NewsDAO;
import model.NewsModel;

/**
 * News Logic
 */
public class NewsLogic {
	/**
	 * ****************************************************
	 * ニュースを1件追加
	 * @param model ニュースモデル
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 * ****************************************************
	 */
	public int create(NewsModel news) {
		NewsDAO dao = new NewsDAO();
		return dao.create(news);
	}



	/**
	 * ****************************************************
	 * 指定のニュースIDからNEWSを取得
	 * @param int (news ID)
	 * @return NewsModel
	 * ****************************************************
	*/
	public NewsModel findOne(int newsId) {
		NewsDAO dao = new NewsDAO();
		return dao.findOne(newsId);
	}
	

	/*
	 * ****************************************************
	 * ニュースを取得
	 * @param genreID : ジャンルID
	 * @param date : 登録日
	 * @param keyword : キーワード
	 * @param search AND検索かOR検索か
	 * @param limit 取得するデータの数を指定
	 * @param offset 開始位置
	 * @return ニュースモデルのArrayList
	 * ****************************************************
	 */
	public List<NewsModel> find(int genreID, Date date, String keyword, String search, int limit, int offset) {
		List<NewsModel> searchList = new ArrayList<NewsModel>();
		NewsDAO dao = new NewsDAO();
		
		// TOP画面や検索結果で操作した時
		if(search == null || search.isEmpty()) {
			// ジャンルを押した時
			if(genreID != 0)		{searchList = dao.findByGenre(genreID, limit, offset);}
			// 日付を押した時
			else if(date != null)	{searchList = dao.findByDate(date, limit, offset);}
			// 全件取得
			else					{searchList = dao.findAll(limit, offset);}
		}
		
		// 検索画面での操作
		else if(keyword != null && !keyword.isEmpty()) {
			// Genreが設定されている時
			if(genreID != 0) {
				// 日付か設定されている時
				if(date != null) {
					if(search.equals("or"))	{searchList = dao.searchOR(keyword, genreID, date, limit, offset);}
					else					{searchList = dao.searchAND(keyword, genreID, date, limit, offset);}
				}
				// 日付か設定されていない時
				else {
					if(search.equals("or"))	{searchList = dao.searchOR(keyword, genreID, limit, offset);}
					else					{searchList = dao.searchAND(keyword, genreID, limit, offset);}
				}
			} 
			// Genreが設定されていない時
			else {
				// 日付か設定されている時
				if(date != null) {
					if(search.equals("or"))	{searchList = dao.searchOR(keyword, date, limit, offset);}
					else					{searchList = dao.searchAND(keyword, date, limit, offset);}
				}
				// 日付か設定されていない時
				else {searchList = dao.findByKeyword(keyword, limit, offset);}
			}
		} 
		// keywordが設定されていない時
		else {
			// Genreが設定されている時
			if(genreID != 0) {
				// 日付か設定されている時
				if(date != null) {
					if(search.equals("or"))	{searchList = dao.searchOR(genreID, date, limit, offset);}
					else					{searchList = dao.searchAND(genreID, date, limit, offset);}
				}
				// 日付か設定されていない時
				else	{searchList = dao.findByGenre(genreID, limit, offset);}
			}
			// Genreが設定されていない時
			else {
				// 日付か設定されている時
				if(date != null)	{searchList = dao.findByDate(date, limit, offset);}
				else				{searchList = dao.findAll(limit, offset);}
			}
		}
		return searchList;
	}
	
		
	
	/**
	 * ****************************************************
	 * ニュースを1件更新
	 * @param model ニュースモデル
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 * ****************************************************
	 */
	public int update(NewsModel news) {
		NewsDAO dao = new NewsDAO();
		return dao.update(news);
	}
	
	/**
	 * ****************************************************
	 * ニュースの件数を取得
	 * @param genreID : ジャンルID
	 * @param date : 登録日
	 * @param keyword : キーワード
	 * @param search AND検索かOR検索か
	 * @return 件数
	 * ****************************************************
	 */
	public int count(int genreID, Date date, String keyword, String search) {
		NewsDAO dao = new NewsDAO();
		
		// TOP画面や検索結果で操作した時
		if(search == null || search.isEmpty()) {
			// ジャンルを押した時
			if(genreID != 0)		{return dao.searchCount(genreID);}
			// 日付を押した時
			else if(date != null)	{return dao.searchCount(date);}
			// 全件取得
			else					{return dao.countAll();}
		}
		
		// 検索画面での操作
		else if(keyword != null && !keyword.isEmpty()) {
			// Genreが設定されている時
			if(genreID != 0) {
				// 日付か設定されている時
				if(date != null) {
					if(search.equals("or"))	{return dao.searchORCount(keyword, genreID, date);}
					else					{return dao.searchANDCount(keyword, genreID, date);}
				}
				// 日付か設定されていない時
				else {
					if(search.equals("or"))	{return dao.searchORCount(keyword, genreID);}
					else					{return dao.searchANDCount(keyword, genreID);}
				}
			} 
			// Genreが設定されていない時
			else {
				// 日付か設定されている時
				if(date != null) {
					if(search.equals("or"))	{return dao.searchORCount(keyword, date);}
					else					{return dao.searchANDCount(keyword, date);}
				}
				// 日付か設定されていない時
				else {return  dao.searchCount(keyword);}
			}
		} 
		// keywordが設定されていない時
		else {
			// Genreが設定されている時
			if(genreID != 0) {
				// 日付か設定されている時
				if(date != null) {
					if(search.equals("or"))	{return dao.searchORCount(genreID, date);}
					else					{return dao.searchANDCount(genreID, date);}
				}
				// 日付か設定されていない時
				else	{return dao.searchCount(genreID);}
			}
			// Genreが設定されていない時
			else {
				// 日付か設定されている時
				if(date != null)	{return dao.searchCount(date);}
				else				{return dao.countAll();}
			}
		}
	}
	
	
	
	
	
	
	
}
