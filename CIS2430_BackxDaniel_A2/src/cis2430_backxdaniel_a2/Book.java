/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cis2430_backxdaniel_a2;

/**
 *
 * @author Ryan
 */
public class Book {
            private Date pubDate;
            private String Title, Author, SubType, Price;

            public Book () {
                    Title = "";
                    SubType = "";
                    Author = "";
                    Price = "";
                    pubDate = new Date();
                    System.out.println("\n Book  Item has been created!");
              
            }                      /* Initialize new Item  */

            public void Set (String newTitle, String newAuthor, String newSubType, int year, String month, int day, String newPrice) {
                Title = newTitle;
                pubDate = new Date();
                pubDate.setDate(year, month, day);
                Author = newAuthor;
                SubType = newSubType;
                Price = newPrice;
                System.out.println("\n Book  Item has been altered!");
                System.out.println(toString());
            }   /* Set new Item values */

            public static boolean CheckTitle(String title) {
                if (title.equals(""))return false;
                else return true;
            }
            
            @Override
            public String toString () {
                    String Date;
                    Date = pubDate.toString();
                    return ("Book: " + Title + "; " + Author + "; " + SubType + "; " + Date + "; " + Price);
            }                          /* Return Entire Item as 1 string */
}                                                   /* The Datatype/Class for Music */
