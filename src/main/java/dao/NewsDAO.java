package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.AdminModel;
import model.GenreModel;
import model.NewsModel;
import util.settings.DBSettings;

public class NewsDAO {
	//	基本となるSQL文
	private final String BASE_SQL = "SELECT "
										+ "n.id, "
										+ "n.admin_id, "
										+ "n.genre_id,"
										+ "n.title, "
										+ "n.article, "
										+ "n.picture, "
										+ "n.url,"
										+ "n.twitter, "
										+ "n.registration_date, "
										+ "a.id, "
										+ "a.email, "
										+ "a.pass, "
										+ "a.name, "
										+ "g.id, "
										+ "g.genre "
										+ "FROM news n "
										+ "INNER JOIN admin a ON n.admin_id=a.id "
										+ "INNER JOIN genre g ON n.genre_id=g.id ";
	
	
	
	
	/**
	 * ****************************************************
	 * 管理者を全件取得
	 * @return 検索結果（ニュースモデルのリスト）
	 * ****************************************************
	 */
	public List<NewsModel> findAll() {
		List<NewsModel> list = new ArrayList<NewsModel>();
		try {
			// DBに接続
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL実行
			String sql = BASE_SQL
							+ "WHERE n.is_deleted=0 AND a.is_deleted=0 AND g.is_deleted=0 "
							+ "ORDER BY n.id DESC";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			// SQLの実行結果をArrayListに格納
			while(rs.next()) {
				//	news関連
				NewsModel news = new NewsModel();
				news.setId(rs.getInt("id"));
				news.setAdminId(rs.getInt("admin_id"));
				news.setTitle(rs.getString("title"));
				news.setArticle(rs.getString("article"));
				news.setPicture(rs.getString("picture"));
				news.setGenreId(rs.getInt("genre_id"));
				news.setURL(rs.getString("url"));
				news.setTwitter(rs.getString("twitter"));
				news.setRegistrationDate(rs.getDate("registration_date"));
				//	管理者関連
				AdminModel admin = new AdminModel();
				admin.setId(rs.getInt("admin_id"));
				admin.setEmail(rs.getString("email"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("pass"));
				news.setAdminModel(admin);
				//	ジャンル関連
				GenreModel genre = new GenreModel();
				genre.setId(rs.getInt("genre_id"));
				genre.setGenre(rs.getString("genre"));
				news.setGenreModel(genre);
				//	リストに追加
				list.add(news);
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	
	

	/**
	 * ****************************************************
	 * 指定ニュースIDからニュースを取得
	 * @param id ニュースID
	 * @return NewsModel
	 * ****************************************************
	 */
	public NewsModel findOne(int id) {
		NewsModel news = new NewsModel();
		
		// DBに接続
		try {
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL実行
			String sql = BASE_SQL + "WHERE n.id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			//	検索結果
			if(rs.next()) {
				//	news関連
				news.setId(rs.getInt("id"));
				news.setAdminId(rs.getInt("admin_id"));
				news.setTitle(rs.getString("title"));
				news.setArticle(rs.getString("article"));
				news.setPicture(rs.getString("picture"));
				news.setGenreId(rs.getInt("genre_id"));
				news.setURL(rs.getString("url"));
				news.setTwitter(rs.getString("twitter"));
				news.setRegistrationDate(rs.getDate("registration_date"));
				//	管理者関連
				AdminModel admin = new AdminModel();
				admin.setId(rs.getInt("admin_id"));
				admin.setEmail(rs.getString("email"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("pass"));
				news.setAdminModel(admin);
				//	ジャンル関連
				GenreModel genre = new GenreModel();
				genre.setId(rs.getInt("genre_id"));
				genre.setGenre(rs.getString("genre"));
				news.setGenreModel(genre);
			} else {
				news = null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return news;
	}
	
	
	
	
	/**
	 * ****************************************************
	 * ニュースを検索
	 * @param genre_id
	 * @return 検索結果（ニュースモデルのリスト）
	 * ****************************************************
	 */
	public List<NewsModel> findByGenre(int genre_id) {
		List<NewsModel> list = new ArrayList<NewsModel>();
		try {
			// DBに接続
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL実行
			String sql = BASE_SQL
							+ "WHERE n.genre_id = ? "
								+ "AND n.is_deleted=0 AND a.is_deleted=0 AND g.is_deleted=0 "
							+ "ORDER BY n.id DESC";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, genre_id);
			ResultSet rs = stmt.executeQuery();
			
			// SQLの実行結果をArrayListに格納
			while(rs.next()) {
				//	news関連
				NewsModel news = new NewsModel();
				news.setId(rs.getInt("id"));
				news.setAdminId(rs.getInt("admin_id"));
				news.setTitle(rs.getString("title"));
				news.setArticle(rs.getString("article"));
				news.setPicture(rs.getString("picture"));
				news.setGenreId(rs.getInt("genre_id"));
				news.setURL(rs.getString("url"));
				news.setTwitter(rs.getString("twitter"));
				news.setRegistrationDate(rs.getDate("registration_date"));
				//	管理者関連
				AdminModel admin = new AdminModel();
				admin.setId(rs.getInt("admin_id"));
				admin.setEmail(rs.getString("email"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("pass"));
				news.setAdminModel(admin);
				//	ジャンル関連
				GenreModel genre = new GenreModel();
				genre.setId(rs.getInt("genre_id"));
				genre.setGenre(rs.getString("genre"));
				news.setGenreModel(genre);
				//	リストに追加
				list.add(news);
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	
	
	
	/**
	 * ****************************************************
	 * ニュースを検索
	 * @param date 登録日
	 * @return 検索結果（ニュースモデルのリスト）
	 * ****************************************************
	 */
	public List<NewsModel> findByDate(Date date) {
		List<NewsModel> list = new ArrayList<NewsModel>();
		try {
			// DBに接続
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL実行
			String sql = BASE_SQL
							+ "WHERE n.registration_date = ? "
								+ "AND n.is_deleted=0 AND a.is_deleted=0 AND g.is_deleted=0 "
							+ "ORDER BY n.id DESC";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, date);
			ResultSet rs = stmt.executeQuery();
			
			// SQLの実行結果をArrayListに格納
			while(rs.next()) {
				//	news関連
				NewsModel news = new NewsModel();
				news.setId(rs.getInt("id"));
				news.setAdminId(rs.getInt("admin_id"));
				news.setTitle(rs.getString("title"));
				news.setArticle(rs.getString("article"));
				news.setPicture(rs.getString("picture"));
				news.setGenreId(rs.getInt("genre_id"));
				news.setURL(rs.getString("url"));
				news.setTwitter(rs.getString("twitter"));
				news.setRegistrationDate(rs.getDate("registration_date"));
				//	管理者関連
				AdminModel admin = new AdminModel();
				admin.setId(rs.getInt("admin_id"));
				admin.setEmail(rs.getString("email"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("pass"));
				news.setAdminModel(admin);
				//	ジャンル関連
				GenreModel genre = new GenreModel();
				genre.setId(rs.getInt("genre_id"));
				genre.setGenre(rs.getString("genre"));
				news.setGenreModel(genre);
				//	リストに追加
				list.add(news);
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	
	
	
	/**
	 * ****************************************************
	 * ニュースを検索 (searchOR や searchAND で使用)
	 * @param sql
	 * @param keyword
	 * @param genreID
	 * @param date 登録日
	 * @return 検索結果（ニュースモデルのリスト）
	 * ****************************************************
	 */
	private List<NewsModel> search(String sql, String keyword, int genreID, Date date){
		List<NewsModel> list = new ArrayList<NewsModel>();
		try {
			// DBに接続
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL実行
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString	(1, "%" +keyword + "%");
			stmt.setString	(2, "%" +keyword + "%");
			stmt.setInt		(3, genreID);
			stmt.setDate	(4, date);
			ResultSet rs = stmt.executeQuery();
			
			// SQLの実行結果をArrayListに格納
			while(rs.next()) {
				//	news関連
				NewsModel news = new NewsModel();
				news.setId(rs.getInt("id"));
				news.setAdminId(rs.getInt("admin_id"));
				news.setTitle(rs.getString("title"));
				news.setArticle(rs.getString("article"));
				news.setPicture(rs.getString("picture"));
				news.setGenreId(rs.getInt("genre_id"));
				news.setURL(rs.getString("url"));
				news.setTwitter(rs.getString("twitter"));
				news.setRegistrationDate(rs.getDate("registration_date"));
				//	管理者関連
				AdminModel admin = new AdminModel();
				admin.setId(rs.getInt("admin_id"));
				admin.setEmail(rs.getString("email"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("pass"));
				news.setAdminModel(admin);
				//	ジャンル関連
				GenreModel genre = new GenreModel();
				genre.setId(rs.getInt("genre_id"));
				genre.setGenre(rs.getString("genre"));
				news.setGenreModel(genre);
				//	リストに追加
				list.add(news);
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	
	
	/**
	 * ****************************************************
	 * ニュースを検索 (処理はsearch)
	 * @param keyword
	 * @param genreID
	 * @param date 登録日
	 * @return 検索結果（ニュースモデルのリスト）
	 * ****************************************************
	 */
	public List<NewsModel> searchOR(String keyword, int genreID, Date date){
		// SQL文を作成
		String sql = BASE_SQL
						+ "WHERE ( n.title = ? OR n.article = ? ) "
							+ "OR n.genre_id = ? "
							+ "OR n.registration_date = ? "
							+ "AND n.is_deleted=0 AND a.is_deleted=0 AND g.is_deleted=0 "
						+ "ORDER BY n.id DESC";
		
		return search(sql, keyword, genreID, date);
	}
	
	
	
	
	/**
	 * ****************************************************
	 * ニュースを検索 (処理はsearch)
	 * @param keyword
	 * @param genreID
	 * @param date 登録日
	 * @return 検索結果（ニュースモデルのリスト）
	 * ****************************************************
	 */
	public List<NewsModel> searchAND(String keyword, int genreID, Date date){
		// SQL文を作成
		String sql = BASE_SQL
						+ "WHERE ( n.title = ? OR n.article = ? ) "
							+ "AND n.genre_id = ? "
							+ "AND n.registration_date = ? "
							+ "AND n.is_deleted=0 AND a.is_deleted=0 AND g.is_deleted=0 "
						+ "ORDER BY n.id DESC";
		
		return search(sql, keyword, genreID, date);
	}
	
	
	
	
	/**
	 * ****************************************************
	 * ニュースを1件追加
	 * @param NewsModel
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 * ****************************************************
	 */
	public int create(NewsModel news) {
		try {
			// データベースに接続する
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL文
			String sql = "INSERT INTO news ( "
							+ "admin_id, "
							+ "title, "
							+ "article, "
							+ "picture, "
							+ "genre_id, "
							+ "url, "
							+ "twitter, "
							+ "registration_date "
							+ ") VALUES ( "
							+ "?, "		// admin_id
							+ "?, "		// title
							+ "?, "		// article
							+ "?, "		// picture
							+ "?, "		// genre_id
							+ "?, "		// url
							+ "?, "		// twitter
							+ "? "		// registration_date
							+ ")";
				
			// SQLを実行する準備
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// パラメータを設定
			//stmt.setInt		(1, news.getAdminId());
			stmt.setInt		(1, 1);
			stmt.setString	(2, news.getTitle());
			stmt.setString	(3, news.getArticle());
			stmt.setString	(4, news.getPicture());
			stmt.setInt		(5, news.getGenreId());
			stmt.setString	(6, news.getURL());
			stmt.setString	(7, news.getTwitter());
			stmt.setDate	(8, news.getRegistrationDate());
			
			// SQLを実行
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return DBSettings.DB_EXECUTION_FAILURE;
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getErrorCode();
		}
		return DBSettings.DB_EXECUTION_SUCCESS;
	}




	/**
	 * ****************************************************
	 * ニュースを1件更新
	 * @param NewsModel
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 * ****************************************************
	 */
	public int update(NewsModel news) {
		try {
			// データベースに接続する
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL文
			String sql = "UPDATE news SET "
							+ "admin_id = ?, "
							+ "title = ?, "
							+ "article = ?, "
							+ "picture = ?, "
							+ "genre_id = ?, "
							+ "url = ?, "
							+ "twitter = ?,"
							+ "is_deleted = ? "
							+ "WHERE id = ?";
				
			// SQLを実行する準備
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// パラメータを設定
			stmt.setInt		(1, news.getAdminId());
			stmt.setString	(2, news.getTitle());
			stmt.setString	(3, news.getArticle());
			stmt.setString	(4, news.getPicture());
			stmt.setInt		(5, news.getGenreId());
			stmt.setString	(6, news.getURL());
			stmt.setString	(7, news.getTwitter());
			stmt.setInt		(8, news.getIsDeleted());
			stmt.setInt		(9, news.getId());
			
			// SQLを実行
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return DBSettings.DB_EXECUTION_FAILURE;
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getErrorCode();
		}
		return DBSettings.DB_EXECUTION_SUCCESS;
	}
}
