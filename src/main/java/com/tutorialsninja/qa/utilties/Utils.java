package com.tutorialsninja.qa.utilties;

import java.util.Date;

public class Utils {
	
	public static String emailWithDateTimeStamp() {
		Date date = new Date();
		String  emailDateTimeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "seleniumpanda" + emailDateTimeStamp + "@gmail.com";
	}
	
	public static final  int IMPLICIT_WAIT = 100;
	public static final  int PAGELOAD_TIME = 100;
	public static final  int SCRIPT_TIME = 1000;

}
