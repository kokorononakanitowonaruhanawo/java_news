package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.AdminModel;
import util.settings.DBSettings;

/**
 * Admin DAO
 */

public class AdminDAO {

	/**
	 * ****************************************************
	 * 管理者を全件取得
	 * @return 検索結果（管理者モデルのリスト）
	 * ****************************************************
	 */
	public List<AdminModel> findAll() {
		List<AdminModel> list = new ArrayList<AdminModel>();
		try {
			// DBに接続
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			// SQL実行
			String sql = "SELECT * FROM admin WHERE is_deleted = 0 ORDER BY id";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			// SQLの実行結果をArrayListに格納
			while(rs.next()) {
				AdminModel admin = new AdminModel();
				admin.setId(rs.getInt("id"));
				admin.setEmail(rs.getString("email"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("pass"));
				list.add(admin);
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	
	
	
	/**
	 * ****************************************************
	 * 指定の管理者を取得
	 * @param id 管理者ID
	 * @return 検索結果（AdminModel）
	 * ****************************************************
	 */
	public AdminModel findOne(int id) {
		AdminModel admin = new AdminModel();
		try {
			// DBに接続
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			// SQL文を作成
			String sql = "SELECT * FROM admin WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			// パラメーターを設定
			stmt.setInt(1, id);
			// SQL文を実行
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				admin.setId(rs.getInt("id"));
				admin.setEmail(rs.getString("email"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("pass"));
			} else {
				admin = null;
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return admin;
	}
	
	
	
	
	/**
	 * ****************************************************
	 * 指定の管理者を取得
	 * @param email
	 * @param password
	 * @return 検索結果（AdminModel）
	 * ****************************************************
	 */
	public AdminModel findOne(String email, String password) {
		AdminModel admin = new AdminModel();
		try {
			// DBに接続
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			// SQL文を作成
			String sql = "SELECT * FROM admin WHERE email = ? AND pass = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			// パラメーターを設定
			stmt.setString(1, email);
			stmt.setString(2, password);
			// SQL文を実行
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				admin.setId(rs.getInt("id"));
				admin.setEmail(rs.getString("email"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("pass"));
			} else {
				admin = null;
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return admin;
	}
	
	
	

	/**
	 * ****************************************************
	 * 指定の管理者を取得
	 * @param email
	 * @return 検索結果（AdminModel）
	 * ****************************************************
	 */
	public AdminModel findOne(String email) {
		AdminModel admin = new AdminModel();
		try {
			// DBに接続
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			// SQL文を作成
			String sql = "SELECT * FROM admin WHERE email = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			// パラメーターを設定
			stmt.setString(1, email);
			// SQL文を実行
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				admin.setId(rs.getInt("id"));
				admin.setEmail(rs.getString("email"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("pass"));
			} else {
				admin = null;
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return admin;
	}
	
	
	
	/**
	 * ****************************************************
	 * データを1件追加
	 * @param AdminModel
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 * ****************************************************
	 */
	public int create(AdminModel admin) {
		try {
			// データベースに接続する
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL文
			String sql = "INSERT INTO admin ( "
							+ "email, "
							+ "pass, "
							+ "name, "
							+ "registration_date "
							+ ") VALUES ( "
							+ "?, "	// email
							+ "?, "	// pass
							+ "?, "	// name
							+ "? "	// registration_date
							+ ")";
				
			// SQLを実行する準備
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// パラメータを設定
			stmt.setString	(1, admin.getEmail());
			stmt.setString	(2, admin.getPassword());
			stmt.setString	(3, admin.getName());
			stmt.setDate	(4, admin.getRegistrationDate());
			
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
	 * @param AdminModel
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 * ****************************************************
	 */
	public int update(AdminModel admin) {
		try {
			// データベースに接続する
			DBConnection db = new DBConnection();
			Connection connection = db.getConnection();
			
			// SQL文
			String sql = "UPDATE admin SET "
							+ "email = ?, "
							+ "pass = ?, "
							+ "name = ?,"
							+ "is_deleted = ? "
							+ "WHERE id = ?";
				
			// SQLを実行する準備
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// パラメータを設定
			stmt.setString	(1, admin.getEmail());
			stmt.setString	(2, admin.getPassword());
			stmt.setString	(3, admin.getName());
			if(admin.getIsDeleted() == 0) {
				stmt.setInt	(4, 0);
			} else {
				stmt.setInt	(4, admin.getIsDeleted());
			}
			stmt.setInt		(5, admin.getId());
			
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
