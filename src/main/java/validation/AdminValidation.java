package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import util.settings.MSSettings;
import util.validation.Validation;
import util.validation.ValidationUtil;

public class AdminValidation extends Validation {

	public AdminValidation(HttpServletRequest request) {
		super(request);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public Map<String, String> validation(){
		
		// name
		if(!ValidationUtil.isMaxLength(this.request.getParameter("name"), 50)) {
			this.errors.put("name", String.format(MSSettings.MSG_LENGTH_LONG, "氏名", 50));
		}
		
		// email
		if(!ValidationUtil.isEmail(this.request.getParameter("email"))) {
			this.errors.put("email", String.format(MSSettings.MSG_EMAIL_FAILURE));
		}
		
		if(ValidationUtil.isRegistered(this.request.getParameter("email"))) {
			this.errors.put("email", String.format(MSSettings.MSG_ER_DUP_KEYNAME, "email"));
		}
		
		// password
		// 登録時
		if(this.request.getParameter("action").equals("register")) {
			if(!ValidationUtil.isPassword(this.request.getParameter("password1"))) {
				this.errors.put("password1", String.format(MSSettings.MSG_PASSWORD_FAILURE));
			}
			
			if(!ValidationUtil.isSame(this.request.getParameter("password1"), this.request.getParameter("password2"))) {
				this.errors.put("password2", String.format(MSSettings.MSG_NOT_AGREE));
			}
		}
		// 編集時
		else if(this.request.getParameter("action").equals("edit")) {
			if(!ValidationUtil.isPassword(this.request.getParameter("password"))) {
				this.errors.put("password", String.format(MSSettings.MSG_PASSWORD_FAILURE));
			}
			// パスワードも変更する時
			if(!this.request.getParameter("password1").isEmpty()) {
				if(!ValidationUtil.isPassword(this.request.getParameter("password1"))) {
					this.errors.put("password1", String.format(MSSettings.MSG_PASSWORD_FAILURE));
				}
				
				if(!ValidationUtil.isSame(this.request.getParameter("password1"), this.request.getParameter("password2"))) {
					this.errors.put("password2", String.format(MSSettings.MSG_NOT_AGREE));
				}
			}
		}
		// エラー時
		else {
			this.errors.put("password1", String.format(MSSettings.MSG_PASSWORD_FAILURE));
		}
		
		return this.errors;
	}

}
