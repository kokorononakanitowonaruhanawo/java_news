package util.validation;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.FloatValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.RegexValidator;
import org.apache.commons.validator.routines.UrlValidator;

import logic.AdminLogic;
import model.AdminModel;

public class ValidationUtil {

	/**
	 * 文字列が指定最大文字数以下かどうか調べる
	 * @param str 文字列
	 * @param length 文字数
	 * @return true:指定文字数以下、false:指定文字数を超えている
	 */
	public static final boolean isMaxLength(String str, int length) {
		return str.length() <= length;
	}

	/**
	 * 文字列が指定最小文字数以上かどうか調べる
	 * @param str 文字列
	 * @param length 文字数
	 * @return true:指定文字数以上、false:指定文字数より短い
	 */
	public static final boolean isMinLength(String str, int length) {
		return str.length() >= length;
	}

	/**
	 * 文字列がEmail形式として正しいかどうか調べる
	 * @param email 文字列
	 * @return true:正しい、false:正しくない
	 * @see https://blog.mailtrap.io/java-email-validation/#Simple_Email_Validation_in_Java
	 * @see https://commons.apache.org/proper/commons-validator/download_validator.cgi
	 */
	public static final boolean isEmail(String email) {
		// create the EmailValidator instance
		EmailValidator validator = EmailValidator.getInstance();
		// check for valid email addresses using isValid method
		return validator.isValid(email);
	}

	/**
	 * 文字列がパスワード（半角英数大文字小文字数字を取り混ぜて8文字以上20文字以下）として正しいかどうか調べる
	 * @param password 文字列
	 * @return true:正しい、false:正しくない
	 */
	public static final boolean isPassword(String password) {
		RegexValidator regex = new RegexValidator("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,20}$", false);
		return regex.isValid(password);
	}

	/**
	 * 文字列がURL(http://、https://)か調べる
	 * @param url
	 * @return true:正しい、false:正しくない
	 */
	public static final boolean isURL(String url) {
		RegexValidator regex = new RegexValidator(new String[]{"http://", "https://", "((localhost)(:[0-9]+))"});
		UrlValidator urlValidator = new UrlValidator(regex, 0);
		return urlValidator.isValid(url);
	}
	
	/**
	 * 文字列が日付形式（yyyy-MM-dd）かどうか調べる
	 * @param value 文字列
	 * @return true:正しい、false:正しくない
	 */
	public static final boolean isDate(String value) {
		DateValidator date = DateValidator.getInstance();
		return date.isValid(value, "yyyy-MM-dd");
	}

	/**
	 * 文字列がint型かどうか調べる
	 * @param value 文字列
	 * @return true:正しい、false:正しくない
	 */
	public static final boolean isInteger(String value ) {
		IntegerValidator integer = IntegerValidator.getInstance();
		return integer.isValid(value);
	}

	/**
	 * 文字列がfloat型かどうか調べる
	 * @param value 文字列
	 * @return true:正しい、false:正しくない
	 */
	public static final boolean isFloat(String value) {
		FloatValidator object = FloatValidator.getInstance();
		return object.isValid(value);
	}
	
	/**
	 * 文字列が一致するかどうか調べる
	 * @param value 文字列
	 * @param value 文字列
	 * @return true:正しい、false:正しくない
	 */
	public static final boolean isSame(String str1, String str2) {
		return str1.equals(str2);
                                                                          	}
	                            
	/**
	 * Emailが登録されているかどうか調べる
	 * @param email
	 * @return true:正しい、false:正しくない
	 */
	public static final boolean isRegistered(String email) {
		AdminModel model = new AdminModel();
		AdminLogic logic = new AdminLogic();
		model = logic.find(email);
		if(model == null) return false;
		return true;
	}

}
