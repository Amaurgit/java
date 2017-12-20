package fr.eni.clinique.TOOLS;

import java.sql.Timestamp;
import java.util.Calendar;

public class Date {

	
	
	public static Timestamp getTimeStamp()
	{
	    // Calendar information
	    Calendar calendar       = Calendar.getInstance();
	    java.util.Date now      = calendar.getTime();
	    Timestamp dbStamp       = new Timestamp(now.getTime());
	    return dbStamp;
	}   
}
