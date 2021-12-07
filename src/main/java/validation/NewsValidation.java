package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import util.settings.MSSettings;
import util.validation.Validation;
import util.validation.ValidationUtil;

public class NewsValidation extends Validation {

	public NewsValidation(HttpServletRequest request) {
		super(request);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public Map<String, String> validation(){
		
		// title
		if(!ValidationUtil.isMaxLength(this.request.getParameter("title"), 100)) {
			this.errors.put("title", String.format(MSSettings.MSG_LENGTH_LONG, "title", 100));
		}
		
		// article
		if(!ValidationUtil.isMaxLength(this.request.getParameter("article"), 5000)) {
			this.errors.put("article", String.format(MSSettings.MSG_LENGTH_LONG, "本文", 5000));
		}
		
		// url
		if(!ValidationUtil.isURL(this.request.getParameter("url"))) {
			this.errors.put("url", String.format(MSSettings.MSG_INVALID_FORMAT, "URL"));
		}
		if(!ValidationUtil.isMaxLength(this.request.getParameter("url"), 2000)) {
			this.errors.put("url", String.format(MSSettings.MSG_LENGTH_LONG, "URL", 2000));
		}
		
		// twitter
		if(!ValidationUtil.isMaxLength(this.request.getParameter("twitter"), 1000)) {
			this.errors.put("twitter", String.format(MSSettings.MSG_LENGTH_LONG, "TWITTER", 1000));
		}
		
		return this.errors;
	}

}
