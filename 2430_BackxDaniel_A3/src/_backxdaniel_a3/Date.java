package _BackxDaniel_A3;

import java.util.Scanner;

/**
 * Date class - for creating and accessing date objects
 *
 * Most of the code is taken from the textbook "Absolute Java" by Water Savitch  
 * unless noted otherwise at the method level.
 */

public class Date {
	private int month;      // 0 to 12
	private int day;        // 0 to 31
	private int year;	// 1000 to 9999

	/**
	 * create a date object with all the required parameters
         *
         * @param month
         * @param year
         * @param day
         */
	public Date(int month, int day, int year) {
		if (dateOK(month, day, year)) {
			this.month = month;
			this.day = day;
			this.year = year;
		} else {
			System.out.println("Fatal Error for Date");
			System.exit(0);
		}
	}
	
	/**
	 * create a default date object with no arguments
	 */
        public Date() {
		this(0, 0, 1000);
	}

	/**
	 * create a date object with the year only
         *
         * @param year
         */
        public Date(int year) {
		this(0, 0, year);
        }

	/**
	 * copy an existing date object
         *
         * @param otherDate
         */
	public Date(Date otherDate) {
		if (otherDate == null) {
			System.out.println("Fatal Error");
		} else {
			month = otherDate.month;
			day = otherDate.day;
			year = otherDate.year;
		}
	}

	/**
	 * Create a date object for a valid string value 
         * @param value
         * @return
         * @author Fei Song
	 */
	public static Date getDate(String value) {
		String[] fields = value.trim().split("[., ]+");
		if( fields.length == 1 && fields[0].matches("[0-9]+") ) {
			int year = Integer.parseInt(fields[0]);
			if( Date.dateOK(0, 0, year) )
				return new Date(0, 0, year);
			else
				return null;
		} else if( fields.length == 3 && Date.getMonth(fields[0]) >= 0 &&
			   fields[1].matches("[0-9]+") && fields[2].matches("[0-9]+") ) {
			int month = Date.getMonth(fields[0]);
			int day = Integer.parseInt(fields[1]);
			int year = Integer.parseInt(fields[2]);
			if( Date.dateOK(month, day, year) ) 
				return new Date(month, day, year);
			else
				return null;
		}
		return null;
 	}

	/**
	 * set a month value
         *
         * @param month
         */
	public void setMonth(int month)
	{
		if ((month < 0) || (month > 12)) {
			System.out.println("Fatal Error");
			System.exit(0);
		} else
			this.month = month;
	}
	
	/**
	 * set a day value
         *
         * @param day
         */
	public void setDay(int day) {
		if ((day < 0) || (day > 31)) {
			System.out.println("Fatal Error");
			System.exit(0);
		} else
			this.day = day;
	}
	
	/**
	 * set a year value
         *
         * @param year
         */
	public void setYear(int year) {
		if ((year < 1000) || (year > 9999)) {
			System.out.println("Fatal Error");
			System.exit(0);
		} else
			this.year = year;
	}
	
	/**
	 * compare if two date objects are the same
         *
         * @param other
         * @return
         */
	public boolean equals(Date other) {
		if (other == null)
			return false;
		else 
			return month == other.month && day == other.day && year == other.year;
	}
	
	/**
	 * compare if the current date object precedes the other date object
         *
         * @param other
         * @return
         */
	public boolean precedes(Date other) {
		return ((year < other.year) || 
			(year == other.year && (month < other.month ||
						month == other.month && day < other.day)));
	}
	
	/**
	 * get a day value
         *
         * @return
         */
	public int getDay() {
		return day;
	}
	
	/**
	 * get a year value
         *
         * @return
         */
	public int getYear() {
		return year;
	}
	
	/**
	 * get a month value
         *
         * @return
         */
	public int getMonth() {
		return month;
	}
	
	/**
	 * display a date object in string
	 */
	public String toString() {
		String output = "";
		if (month == 0)
			output += year;
		else {
			output += getMonth(month) + " ";
			if (day > 0 )
				output += day + ", ";
			output += year;
		}
		return output;
	} 
	
	/**
	 * validate all component values for a potential date object
         *
         * @param month
         * @param day 
         * @param year
         * @return
         */
	public static boolean dateOK(int month, int day, int year) {
		return (((month == 0 && day == 0) ||
			(month > 0 && month <= 12 && day >= 0 && day <= 31)) &&
			(year >= 1000 && year <= 9999));
	}
	
	/**
	 * convert an integer month value to a string value
         *
         * @param month
         * @return
         */
	public static String getMonth(int month) {
		switch (month) {
		case 0: 
			return "";
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8: 
			return "Auguest";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			System.out.println("Unknown month " + month + ".  Returning an empty string.");
			return ""; 
		}
	}
	
	/**
	 * convert a string month value to an integer value
         *
         * @param month
         * @return
         */
	public static int getMonth(String month) {
		if (month.equals(""))
			return 0;
		else if (month.equalsIgnoreCase("January") || month.equalsIgnoreCase("Jan"))
			return 1;
		else if (month.equalsIgnoreCase("February") || month.equalsIgnoreCase("Feb"))
			return 2;
		else if (month.equalsIgnoreCase("March") || month.equalsIgnoreCase("Mar"))
			return 3;
		else if (month.equalsIgnoreCase("April") || month.equalsIgnoreCase("Apr"))
			return 4;
		else if (month.equalsIgnoreCase("May"))
			return 5;
		else if (month.equalsIgnoreCase("June") || month.equalsIgnoreCase("Jun"))
			return 6;
		else if (month.equalsIgnoreCase("July") || month.equalsIgnoreCase("Jul"))
			return 7;
		else if (month.equalsIgnoreCase("August") || month.equalsIgnoreCase("Aug"))
			return 8;
		else if (month.equalsIgnoreCase("September") || month.equalsIgnoreCase("Sep"))
			return 9;
		else if (month.equalsIgnoreCase("October") || month.equalsIgnoreCase("Oct"))
			return 10;
		else if (month.equalsIgnoreCase("November") || month.equalsIgnoreCase("Nov"))
			return 11;
		else if (month.equalsIgnoreCase("December") || month.equalsIgnoreCase("Dec"))
			return 12;
		else {
			System.out.println("Unknown month " + month + ".  Returning -1.");
			return -1; 
		}
	}
	
}
