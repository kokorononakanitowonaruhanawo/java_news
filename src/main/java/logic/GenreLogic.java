package logic;

import java.util.List;

import dao.GenreDAO;
import model.GenreModel;

/**
 * Genre logic
 */
public class GenreLogic {
	/**
	 * ジャンルを1件追加
	 * @param model ジャンルモデルクラス
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード(SQLSTATE)
	 */
	public int create(GenreModel genre) {
		GenreDAO dao = new GenreDAO();
		return dao.create(genre);
	}

	/**
	 * ジャンルを全件取得
	 * @return 検索結果（ジャンルモデルのリスト）
	 */
	public List<GenreModel> find() {
		GenreDAO dao = new GenreDAO();
		List<GenreModel> list = dao.findAll();
		return list;
	}

	/**
	 * 指定ジャンルIDのジャンルを取得
	 * @param genreId ジャンルID
	 * @return 検索結果（ジャンルモデル）
	 */
	public GenreModel find(int genreId) {
		GenreDAO dao = new GenreDAO();
		return dao.findOne(genreId);
	}

	/**
	 * 指定ジャンルIDのジャンルを1件更新
	 * @param model ジャンルモデル
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 */
	public int update(GenreModel model) {
		GenreDAO dao = new GenreDAO();
		return dao.update(model);
	}
}
