package _BackxDaniel_A3;

/** Music class
 *
 * @author Fei Song
 *
 * A class for representing and comparing musics.
 *
 */

public class Music extends EClass {
	/**
	 * Create a music with all the required fields
         *
         * @param title
         * @param company
         * @param kind 
         * @param date
         * @param price
         */
	public Music(String title, String company, String kind, Date date, double price) {
            super("Music", title, company, kind, date, price);
	}
}
