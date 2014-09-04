package _BackxDaniel_A3;

/** Book class
 *
 * @author Fei Song
 *
 * A class for representing and comparing books.
 *
 */

public class Book extends EClass {
	/**
	 * Create a book with all the required fields
         *
         * @param title
         * @param authors
         * @param kind
         * @param date 
         * @param price
         */
	public Book(String title, String authors, String kind, Date date, double price) {
            super("Book", title, authors, kind, date, price);
	}
}
