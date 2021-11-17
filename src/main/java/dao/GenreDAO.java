package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.GenreModel;
import settings.DBSettings;

public class GenreDAO {

	/**
	 * ****************************************************
	 * ジャンルを全件取得
	 * @return 検索結果（ジャンルモデルのリスト）
	 * ****************************************************
	 */
	public List<GenreModel> findAll(){
		List<GenreModel> list = new ArrayList<GenreModel>();
		try {
			// DBに接続
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			// SQL実行
			String sql = "SELECT * FROM genre WHERE is_deleted = 0 ORDER BY id";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			// SQLの実行結果をArrayListに格納
			while(rs.next()) {
				GenreModel genre = new GenreModel();
				genre.setId(rs.getInt("id"));
				genre.setGenre(rs.getString("genre"));
				list.add(genre);
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	
	
	/**
	 * ****************************************************
	 * 指定ジャンルIDのジャンルを1件検索する
	 * @param id ジャンルID
	 * @return 検索結果（ジャンルモデル）
	 * ****************************************************
	 */
	public GenreModel findOne(int id) {
		GenreModel genre = new GenreModel();
		try {
			// DBに接続
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL実行
			String sql = "SELECT * FROM genre WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			// SQLの実行結果を取得
			if(rs.next()) {
				genre.setId(rs.getInt("id"));
				genre.setGenre(rs.getString("genre"));
			} else {
				genre = null;
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return genre;
	}
	
	
	
	
	/**
	 * ****************************************************
	 * ジャンルを1件追加
	 * @param GenreModel
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 * ****************************************************
	 */
	public int create(GenreModel genre) {
		try {
			// データベースに接続する
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL文
			String sql = "INSERT INTO genre ( "
							+ "genre, "
							+ "registration_date "
							+ " ) VALUES ( ?, ? )";
				
			// SQLを実行する準備
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// パラメータを設定
			stmt.setString	(1, genre.getGenre());
			stmt.setDate	(2, genre.getRegistrationDate());
			
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
	 * レコードの更新
	 * @param GenreModel
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 * ****************************************************
	 */
	public int update(GenreModel genre) {
		try {
			// データベースに接続する
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL文
			String sql = "UPDATE genre SET "
							+ "genre = ?, "
							+ "is_deleted = ? "
							+ "WHERE id = ?";
				
			// SQLを実行する準備
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// パラメータを設定
			stmt.setString	(1, genre.getGenre());
			stmt.setInt		(2, genre.getIsDeleted());
			stmt.setInt		(3, genre.getId());
			
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
