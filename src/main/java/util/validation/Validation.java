package util.validation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Validation {
	
	// リクエスト
	public HttpServletRequest request;
	
	// エラーが発生した項目とエラー内容を格納するMAP
	public Map<String, String> errors;
	
	/**
	 * validation 基底クラス
	 * @param request
	 */
	public Validation(HttpServletRequest request) {
		this.request = request;
		this.errors = new HashMap<String, String>();
	}
	/**
	 * validation エラーの有無を判定
	 * @return true：エラーがある、false：エラーなし
	 */
	public boolean hasErrors() {
		if(this.errors.size() > 0) {return true;}
		else	return false;
	}
}
