package _BackxDaniel_A3;

/** Movie class
 *
 * @author Fei Song
 *
 * A class for representing and comparing movies.
 *
 */

public class Movie extends EClass {
	/**
	 * Create a movie with all the required fields
         *
         * @param title
         * @param kind
         * @param actors
         * @param date
         * @param price
         */
	public Movie(String title, String actors, String kind, Date date, double price) {
            super("Movie", title, actors, kind, date, price);
        }
        
}
