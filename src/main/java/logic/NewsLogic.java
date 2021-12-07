package logic;

import java.sql.Date;
import java.util.List;

import dao.NewsDAO;
import model.NewsModel;

public class NewsLogic {
	/**
	 * ニュースを1件追加
	 * @param model ニュースモデル
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 */
	public int crate(NewsModel news) {
		NewsDAO dao = new NewsDAO();
		return dao.create(news);
	}

	/**
	 * ニュースを全件取得
	 * @return ニュースモデルのArrayList
	 */
	public List<NewsModel> find() {
		NewsDAO dao = new NewsDAO();
		return dao.findAll();
	}

	/**
	 * 指定ジャンルIDのニュースを全件取得
	 * @param genreId ジャンルID
	 * @return ニュースモデルのArrayList
	 */
	public List<NewsModel> find(int genreId) {
		NewsDAO dao = new NewsDAO();
		return dao.findByGenre(genreId);
	}
	
	/**
	 * 指定日のニュースを全件取得
	 * @param date (型：sql.Date、書式:yyyy-MM-dd）
	 * @return ニュースのArrayList
	 */
	public List<NewsModel> find(Date date) {
		NewsDAO dao = new NewsDAO();
		return dao.findByDate(date);
	}

	/**
	 * 指定のニュースIDからNEWSを取得
	 * @param int (news ID)
	 * @return NewsModel
	*/
	public NewsModel findOne(int newsId) {
		NewsDAO dao = new NewsDAO();
		return dao.findOne(newsId);
	}
	
	/**
	 * ニュースをキーワード、ジャンル、登録日でOR検索
	 * @param keyWord 検索キーワード
	 * @param ジャンルID
	 * @param date 登録日
	 * @return ニュースモデルのArrayList
	 */
	public List<NewsModel> findOR(String keyword, int genreID, Date date) {
		NewsDAO dao = new NewsDAO();
		return dao.searchOR(keyword, genreID, date);
	}
	
	/**
	 * ニュースをキーワード、ジャンル、登録日でAND検索
	 * @param keyWord 検索キーワード
	 * @param ジャンルID
	 * @param date 登録日
	 * @return ニュースモデルのArrayList
	 */
	public List<NewsModel> findAND(String keyword, int genreID, Date date) {
		NewsDAO dao = new NewsDAO();
		return dao.searchAND(keyword, genreID, date);
	}

	/**
	 * ニュースを1件更新
	 * @param model ニュースモデル
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 */
	public int update(NewsModel news) {
		NewsDAO dao = new NewsDAO();
		return dao.update(news);
	}
}
