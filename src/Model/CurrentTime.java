package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
 
public class CurrentTime {	
	public static String currentDate() {
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		String ret = date.format(today);
		return ret;
	}
	
	public static String currentTime() {
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("a hΩ√ m∫– s√ ");
		String ret = date.format(today);
		return ret;
	}
}