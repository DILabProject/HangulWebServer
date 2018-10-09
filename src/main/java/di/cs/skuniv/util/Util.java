package di.cs.skuniv.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	
	public static boolean isHangul(String word){
		Pattern pattern = Pattern.compile("^[가-힣]+$");
		Matcher matcher = pattern.matcher(word);
		return matcher.find();
	}
}
