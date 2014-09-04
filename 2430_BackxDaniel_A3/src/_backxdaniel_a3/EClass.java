/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package _BackxDaniel_A3;

/**
 *
 * @author Ryan
 */
public class EClass {

    /**
     * @param args the command line arguments
     */

/** Everything class
 *
 * @author Daniel Backx
 *
 * A class for representing and comparing everything.
 *
 */
        private String eType;
	private String eTitle;       // title of a item
	private String eCompany;     // publshing company
	private String eKind;        // kind of a item
	private Date eDate;          // publication date of item
	private double ePrice;        // price of item
	
	/**
	 * Create a item with all the required fields
         *
         * @param type
         * @param title
         * @param date
         * @param company
         * @param kind
         * @param price
         */
	public EClass(String type, String title, String company, String kind, Date date, double price) {
		if( valid(title, date, price) ) {
			this.eType = type;
                        this.eTitle = title;
			this.eCompany = company;
			this.eKind = kind;
			this.eDate = new Date(date);
			this.ePrice = price;
		} else {
			System.out.println("Invalid values for creating a music");
			System.exit(0);
		}
	}

	/**
	 * A static method for validating if the information for a music is valid
         *
         * @param title
         * @param date
         * @param price
         * @return
         */
	public static boolean valid(String title, Date date, double price) {
		return !title.equals("") && date != null && price >= 0.00;
	}

	/**
	 * Set a new value for title
         *
         * @param title
         */
	public void setTitle(String title) {
		if( title.equals("") ) {
			System.out.println("Empty value for a title");
			System.exit(0);
		} else
			this.eTitle = title;
	}

	/**
	 * Set a new value for company
         *
         * @param company
         */
	public void setCompany(String company) {
		this.eCompany = company;
	}

	/**
	 * Set a new value for kind
         *
         * @param kind
         */
	public void setKind(String kind) {
		this.eKind = kind;
	}

	/**
	 * Set a new value for date
         *
         * @param date
         */
	public void setDate(Date date) {
		if( date == null ) {
			System.out.println("null value for a date");
			System.exit(0);
		} else
			this.eDate = new Date(date);
	}

	/**
	 * Set a new value for price
         *
         * @param price
         */
	public void setPrice(int price) {
		if( price < 0.00 ) {
			System.out.println("Invalid value for price: " + price);
			System.exit(0);
		} else
			this.ePrice = price;
	}

	/**
	 * Get the value of type
         *
         * @return
         */
        public String geteType() {
            return eType;
        }

	/**
	 * Get the value of title
         *
         * @return
         */
	public String getTitle() {
		return eTitle;
	}

	/**
	 * Get the value of company
         *
         * @return
         */
	public String getCompany() {
		return eCompany;
	}

	/**
	 * Get the value of kind
         *
         * @return
         */
	public String getKind() {
		return eKind;
	}

	/**
	 * Get the value of date
         *
         * @return 
         */
	public Date getDate() {
		return new Date(eDate);
	}

	/**
	 * Get the value of price
         *
         * @return
         */
	public double getPrice() {
		return ePrice;
	}

	/**
	 * Show the content of a music in a string
	 */
        @Override
	public String toString() {
		return eType + ": " + eTitle + "; " + eCompany + "; " + eKind + "; " + eDate + "; " + ePrice;
	}

}
