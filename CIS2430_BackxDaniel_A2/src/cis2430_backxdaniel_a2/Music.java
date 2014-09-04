/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cis2430_backxdaniel_a2;

/**
 *
 * @author Ryan
 */
public class Music {
    /**
     * @param args the command line arguments
     */

            private Date pubDate;
            private String Title, Company, SubType, Price;

            public Music() {
                    Title = "";
                    SubType = "";
                    Company = "";
                    Price = "";
                    pubDate = new Date();
                   System.out.println("\n Music Item has been created!");
              
          }                      /* Initialize new Item  */

            public void Set (String newTitle, String newCompany, String newSubType, int year, String month, int day, String newPrice) {
                Title = newTitle;
                pubDate = new Date();
                pubDate.setDate(year, month, day);
                Company = newCompany;
                SubType = newSubType;
                Price = newPrice;
                   System.out.println("\n Music Item has been altered!");
                System.out.println(toString());
            }   /* Set new Item values */

            public static boolean CheckTitle(String title) {
                if (title.equals(""))return false;
                else return true;
            }

            @Override
            public String toString() {
                    String Date;
                    Date = pubDate.toString();
                   return ("Music: " + Title + "; " + Company + "; " + SubType + "; " + Date + "; " + Price);
            }                          /* Return Entire Item as 1 string */
}                                                   /* The Datatype/Class for Music */
  
