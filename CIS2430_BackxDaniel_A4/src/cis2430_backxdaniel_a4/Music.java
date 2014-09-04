package cis2430_backxdaniel_a4;

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

        @Override
        public String toString() {
            return super.toString();
        }


}
