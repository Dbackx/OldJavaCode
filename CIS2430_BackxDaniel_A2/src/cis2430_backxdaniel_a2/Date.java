/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cis2430_backxdaniel_a2;

/**
 *
 * @author Ryan
 */
public class Date {
     /**
     * @param args the command line arguments
     */
            private int Year, Month, Day;
            private static String[]MonthsLong = {"","January","February","March","April","May","June","July","August","September","October","Novermber","December"};
            private static String[]MonthsShort = {"","Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
            private static int[]MonthDays = {0,31,28,31,30,31,30,31,31,30,31,30,31};
            
                public void Date () {
                    Year = 1000;
                    Month = 0;
                    Day = 0;
                }   /* Constructor */

                public void setDate (int newYear, String newMonth, int newDay) {
                    Year = newYear;
                    Month = InitializeMonth(newMonth);
                    Day = newDay;
                }   /* Initializer */

                private static int InitializeMonth (String month) {
                    int monthNum=0;
                    if (!month.equals("")) {
                        for(int i=0; i<=12; i++)
                           if (month.equalsIgnoreCase(MonthsLong[i]) || month.equalsIgnoreCase(MonthsShort[i]) || i==Integer.parseInt(month))
                               monthNum = i;
                    }
                    return monthNum;
                }   /* Convert Month string to integer */

                private String MonthtoString (int month) {
                    String monthString="";
                    for (int i=0; i<=12; i++)
                        if (month==i) monthString = MonthsLong[i];

                    return monthString;
                }       /* Convert Month number to the string of the month */

                public int TimeOfYear () {
                    int TimeOfYear=0;
                    TimeOfYear = Month*100;
                    TimeOfYear = TimeOfYear + Day;                                  /* Add Day specifier to Month Specifier */
                    return TimeOfYear;
                }   /* Give a Time of year Approxmiate created from months 101-1231 and days 1-31) */

                public boolean equals(Date early, Date late) {
                    if (late.Year==0) if (Year==early.Year) return true;
                    else if (early.Year<=Year && Year<=late.Year)
                        if (early.TimeOfYear()==0 && late.TimeOfYear()==0)
                    return true;
                    return false;
                }             /* Date Class */

                public static boolean ValidatorYear(int year) {
                    if (year>=1000 && year<10000)
                        return true;
                    else return false;
                }

                public static boolean ValidatorDateOfYear(String monthString, int day) {
                    int month = InitializeMonth(monthString);
                    if (month<=0 || month>12) return false;
                    if (day<=0 && day>=MonthDays[month]) return false;
                    return true;
                }

                @Override
                public String toString() {
                    String date;
                    date = "" + Year;
                    if (Day!=0) 
                        date = Day + ", " + date;
                    if (Month!=0) {
                        String month = MonthtoString(Month);
                        date = month + ", " + date;
                    }
                    return date;
                }   /* Return String of date in form "Month, Day, Year" */

}
