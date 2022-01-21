package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import util.settings.MSSettings;
import util.validation.Validation;
import util.validation.ValidationUtil;

public class GenreValidation extends Validation {

	public GenreValidation(HttpServletRequest request) {
		super(request);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public Map<String, String> validation(){
		
		// genre
		if(!ValidationUtil.isMaxLength(this.request.getParameter("genre"), 20)) {
			this.errors.put("genre", String.format(MSSettings.MSG_LENGTH_LONG, "新規登録ジャンル", 20));
		}
		
		return this.errors;
	}

}
