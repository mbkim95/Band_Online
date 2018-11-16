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
		SimpleDateFormat date = new SimpleDateFormat("a h:mm:ss");
		String ret = date.format(today);
		return ret;
	} 
}