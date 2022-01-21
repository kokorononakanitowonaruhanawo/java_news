package logic;

import java.util.List;

import dao.AdminDAO;
import model.AdminModel;

/**
 * Admin Logic
 */
public class AdminLogic {
	/**
	 * 管理者を1件追加
	 * @param model 管理者モデルクラス
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード(SQLSTATE)
	 */
	public int create(AdminModel Admin) {
		AdminDAO dao = new AdminDAO();
		return dao.create(Admin);
	}

	/**
	 * 管理者を全件取得
	 * @return 検索結果（管理者モデルのリスト）
	 */
	public List<AdminModel> find() {
		AdminDAO dao = new AdminDAO();
		return dao.findAll();
	}

	/**
	 * 指定管理者IDの管理者を取得
	 * @param adminId 管理者ID
	 * @return 検索結果（管理者モデル）
	 */
	public AdminModel find(int adminId) {
		AdminDAO dao = new AdminDAO();
		return dao.findOne(adminId);
	}

	/**
	 * 指定E-mailアドレスとパスワードの管理者を取得
	 * @param email E-mailアドレス
	 * @param password パスワード
	 * @return 検索結果（管理者モデル）
	 */
	public AdminModel find(String email, String password) {
		AdminDAO dao = new AdminDAO();
		return dao.findOne(email, password);
	}
	
	
	/**
	 * 指定E-mailアドレスとパスワードの管理者を取得
	 * @param email E-mailアドレス
	 * @return 検索結果（管理者モデル）
	 */
	public AdminModel find(String email) {
		AdminDAO dao = new AdminDAO();
		return dao.findOne(email);
	}
	
	

	/**
	 * 指定管理者IDの管理者を1件更新
	 * @param model 管理者モデル
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 */
	public int update(AdminModel Admin) {
		AdminDAO dao = new AdminDAO();
		return dao.update(Admin);
	}
}
