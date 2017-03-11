/**
* Моделийн нэр             ValidationFunctions.java
*
* Функцын нэр          【Бүртгэх үед орж ирж байгаа утгууд стандардын дагуу байгаа эсэхийг шалгах】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2010.08.25  С.Бауырбек		Үүсгэх
* 01.00.02				2010.08.27  С.Бауырбек		Засах
*/

package org.golomt.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.*;

/**
 * Ексэлээс орж ирэх утгуудыг шалгах класс - овог, нэр, имейл, регистр, текст, тоо гэ мэт
 * @author Bauirbek
 * 
 */
public class ValidationFunctions
{
	/**
	 * Орж ирж байгаа хувьсагч нэр Unicode мөн үү эсэхийг шалгах фүнкц
	 * @param name - Нэр дамжиж орж ирэх
	 * @param minLimit - Нэр байж болох тэмдэгтийн хамгийн бага утга
	 * @param minLimit - Нэр байж болох тэмдэгтийн хамгийн их утга
	 * @return - Хэрэв нэр нь нөхцөлийг хангаж байвал үнэн, эсрэг тохиолдолд худал утга буцах
	 */
	public static boolean isName(String name, int minLimit, int maxLimit)
	{
		// Жижиг, болон том үсгүүд байна уу эсэхийг шалгах тэмдэгтүүд
		//Pattern pattern = Pattern.compile("[а-яА-ЯӨҮЁөүё\\-]{" + minLimit + "," + maxLimit + "}");
		return Pattern.matches("^[а-яА-ЯӨҮЁөүё\\- \\.\\,\\n\\r\\ \\-]{" + minLimit + "," + maxLimit + "}$", name);
	}

	/**
	 * Орж ирж байгаа регистрын дугаарыг шалгах фүнкц
	 * @param register - Нэр дамжиж орж ирэх
	 * @return - Хэрэв регистр нь нөхцөлийг хангаж байвал үнэн, эсрэг тохиолдолд худал утга буцах
	 */
	public static boolean isRegister(String register)
	{
		// df
		// Орж ирж байгаа регистрын дугаарыг шалгах 
		//Pattern pattern = Pattern.compile("[А-ЯӨҮЁ]{2}+([0-9]{2})+([0-1]{1})+([0-9]{1})+([0-3]{1})+([0-9]{3})");
		
		if (register.length() > 10)
		{
			return false ;
		}
		
		String regex = "[а-яА-ЯӨҮөүЁёà-ÿÀ-ßª¯¨ÿº¿¸]{2}+([0-9]{2})+([0-1]{1})+([0-9]{1})+([0-3]{1})+([0-9]{3})";

		Calendar now = Calendar.getInstance(); 

	    int y = now.get(now.YEAR) / 100;		
		
	    if(register.length() > 3){
	    	if (("" + register.charAt(2) + register.charAt(3)).compareTo(String.valueOf(y)) <= 0)
			{
				regex = "[а-яА-ЯӨҮөүЁёà-ÿÀ-ßª¯¨ÿº¿¸]{2}+([0-9]{2})+([0-3]{1})+([0-9]{1})+([0-3]{1})+([0-9]{3})";
			}
			return Pattern.matches(regex, register) ;
	    }else{
	    	return false;
	    }
	}
	
	/**
	 * Орж ирж байгаа иргэний үнэмлэхний дугаарыг шалгах фүнкц
	 * @param idNumber - Иргэний дугаар дамжиж орж ирэх
	 * @return - Хэрэв регистр нь нөхцөлийг хангаж байвал үнэн, эсрэг тохиолдолд худал утга буцах
	 */
	public static boolean isIdNumber(String idNumber)
	{
		// Орж ирж байгаа иргэний үнэмлэхний дугаарыг шалгах 
		//Pattern pattern = Pattern.compile("[А-ЯӨҮЁ]{2}+([0-9]{7})");
		return Pattern.matches("^[А-ЯӨҮЁ]{2}+([0-9]{7})$", idNumber);
	}
	
	/**
	 * Гараас орж ирж байгаа тэмдэгт мөр имейл хаягийг дагуу бичигдсэн эсэхийг шалгах
	 * @param email - имейл хаяг
	 * @return - Хэрэв гараас орж ирж байгаа тэмдэгт мөр өгөгдсөн шаардалагыг хангаж байвал үнэн утга буцна.
	 */
	public static boolean isEmail(String email)
	{
		//Pattern pattern = Pattern.compile("[a-z0-9\\-_\\.]++@[a-z0-9\\-]++(\\.[a-z0-9\\-]++)++");
		
		return Pattern.matches("[a-zA-Z0-9\\-_\\.]++@[a-zA-Z0-9\\-]++(\\.[a-zA-Z0-9\\-]++)++", email);
	}
	
	/**
	 * @param text
	 * @return
	 */
	
	/**
	 * Эксэл файл архивлаж хуулахад нэр шалгах
	 * @param filename - файл нэр
	 * @return - Хэрэв гараас орж ирж байгаа тэмдэгт мөр өгөгдсөн шаардалагыг хангаж байвал үнэн утга буцна.
	 */
	public static boolean isFilename(String filename)
	{

		return Pattern.matches(".*.zip", filename);

	}
	
	/**
	 * @param text
	 * @return
	 */
	
	
	public static boolean isEngText(String text, int minLen, int maxLen)
	{
		//Pattern pattern = Pattern.compile("[a-zA-Z\\-_\\.]++");
		return Pattern.matches("[a-zA-Z\\- _\\.]{" + minLen + "," + maxLen + "}", text);
	}

	/**
	 * @param text
	 * @return
	 */
	public static boolean isCarNumber(String text)
	{
		//Pattern pattern = Pattern.compile("[а-яА-ЯӨҮөү\\- _\\.]++");
		return Pattern.matches("^[а-яА-ЯӨҮЁяөүё]{2,3}+([0-9]{4})$", text);
	}
	
	/**
	 * @param text
	 * @return
	 */
	public static boolean isMglText(String text, int minLen, int maxLen)
	{
		//Pattern pattern = Pattern.compile("[а-яА-ЯӨҮөү\\- _\\.]++");
		return Pattern.matches("^[а-яА-ЯӨҮөүЁё\\- __\\.\\,\\%\\/\"\'\\n\\r]{" + minLen + "," + maxLen + "}$", text);
	} 
	
	/**
	 * @param text
	 * @return
	 */
	public static boolean isMglText(String text, int length)
	{
		return isMglText(text, length, length);
	}
	
	/**
	 * @param text
	 * @return
	 */
	public static boolean isText(String text, int minLen, int maxLen)
	{
		//Pattern pattern = Pattern.compile("[а-яА-ЯӨҮa-zA-Z0-9Ёёөү\\- _\\.\\,]++");
		return Pattern.matches("[а-яА-ЯӨҮa-zA-Z0-9Ёёөү\\- _\\.\\,\\n\\r]{" + minLen + "," + maxLen + "}", text);
	}
	
	public static boolean isCyrillicText(String text, int minLen, int maxLen)
	{
		return Pattern.matches("^[а-яА-ЯӨҮөүЁё0-9\\- _\\.\\,\\%\\/\"\'\\n\\r]{" + minLen + "," + maxLen + "}$", text);
	}
	
	/**
	 * @param text
	 * @return
	 */
	public static boolean isPassword(String text, int minLen, int maxLen)
	{
		//Pattern pattern = Pattern.compile("[а-яА-ЯӨҮa-zA-Z0-9Ёёөү\\- _\\.\\,]++");
		return Pattern.matches("[a-zA-Z0-9\\- _]{" + minLen + "," + maxLen + "}", text);
	}
	
	/**
	 * Сери, сертификат, гэрчилгээний дугаар
	 * @param text
	 * @return
	 */
	public static boolean isCertification(String text, int minLen, int maxLen)
	{
		//Pattern pattern = Pattern.compile("[а-яА-ЯӨҮa-zA-Z0-9Ёёөү\\-_]++");
		return Pattern.matches("^[а-яА-ЯӨҮa-zA-Z0-9Ёёөү\\- __\\.\\,\\%\\/\"\'\\n\\r]{" + minLen + "," + maxLen + "}$", text);
	}
	/**
	 * Өгөгдсөн тэмдэгт мөр тоо мөн үү эсэхийг шалгах
	 * @param value - тэмдэг мөр
	 * @param limit - таслалаас хойшхи тооны хязгаар
	 * @return - Хэрэв гараас орж ирж байгаа тэмдэгт мөр үнэ байвал үнэн утга буцна.
	 */
	public static boolean isDouble(String value, int digit)
	{

		return Pattern.matches("^[\\d]{1,"+digit+"}([\\.]{0,1}[\\d]{1,2}){0,1}$", value);
	}
	/**
	 * Өгөгдсөн тэмдэгт мөр тоо мөн үү эсэхийг шалгах
	 * @param too - тэмдэг мөр
	 * @param minLimit - уртын доод хязгаар
	 * @param maxLimit - уртын дээд хязгаар
	 * @return - Хэрэв гараас орж ирж байгаа тэмдэгт мөр тоо байвал үнэн утга буцна.
	 */
	public static boolean isToo(String too, int minLimit, int maxLimit)
	{
		//Pattern pattern = Pattern.compile("[0-9\\.]{" + minLimit +"," + maxLimit + "}");
		// ^[-+]?[0-9]*\\.?[0-9]+$
		return Pattern.matches("^[0-9]{" + minLimit +"," + maxLimit + "}$", too);
	}
	
	/**
	 * Өгөгдсөн тэмдэгт мөр тоо мөн үү эсэхийг шалгах
	 * @param too - тэмдэг мөр
	 * @param minLimit - урт
	 * @return - Хэрэв гараас орж ирж байгаа тэмдэгт мөр тоо байвал үнэн утга буцна.
	 */
	public static boolean isToo(String too, int length)
	{
		return isToo(too, length, length);
	}
	
	/**
	 * Өгөгдсөн стандардыг хангах огноог гараас оруулж байна уу эсэхийг шалгах
	 * @param date - гараас орж ирэх огноо
	 * @return - Хэрэв гараас орж ирж байгаа огноо өгөгдсөн шаардалагыг хангаж байвал үнэн утга буцна.
	 */
	public static boolean isDate(String date)
	{
		//Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
		//return Pattern.matches("^(\\d{4})-(\\d{2})-(\\d{2})$", date);

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    
	    try
	    {
	        dateFormat.parse(date.toString().trim());
	    }
	      catch (ParseException pe)
	    {
	    	  return false;
	    }
	    
	    return true; 
	}
}