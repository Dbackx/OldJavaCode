package _backxdaniel_a3;

/** Music class
 *
 * @author Fei Song
 *
 * A class for representing and comparing musics.
 *
 */

public class Music extends EClass{
	private String title;       // title of a music
	private String company;     // one or multiple company separated by commas
	private String kind;        // kind of a music
	private Date date;          // publication date of a music
	private double price;        // price of a music
	
	/**
	 * Create a music with all the required fields
	 */
	public Music(String title, String company, String kind, Date date, double price) {
		if( valid(title, date, price) ) {
			this.title = title;
			this.company = company;
			this.kind = kind;
			this.date = new Date(date);
			this.price = price;
		} else {			
			System.out.println("Invalid values for creating a music");
			System.exit(0);
		}
	}
	
	/**
	 * Create a music with no arguments
	 */
	public Music() {
		this("No Title", "", "", new Date(), 0.00);
	}
	
	/**
	 * Create a copy of a music
	 */
	public Music(Music other) {
		if (other == null) {
			System.out.println("null value for copying a music");
			System.exit(0);
		} else {
			title = other.title;
			company = other.company;
			kind = other.kind;
			date = new Date(other.date);
			price = other.price;
		}
	}
	
	/**
	 * A static method for validating if the information for a music is valid
	 */
	public static boolean valid(String title, Date date, double price) {
		return !title.equals("") && date != null && price >= 0.00;
	}

	/**
	 * Set a new value for title
	 */
	public void setTitle(String title) {
		if( title.equals("") ) {
			System.out.println("Empty value for a title");
			System.exit(0);
		} else
			this.title = title;
	}
	
	/**
	 * Set a new value for company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * Set a new value for kind
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * Set a new value for date
	 */
	public void setDate(Date date) {
		if( date == null ) {
			System.out.println("null value for a date");
			System.exit(0);
		} else
			this.date = new Date(date);
	}

	/**
	 * Set a new value for price
	 */
	public void setPrice(int price) {
		if( price < 0.00 ) {
			System.out.println("Invalid value for price: " + price);
			System.exit(0);
		} else
			this.price = price;
	}
	
	/**
	 * Get the value of title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Get the value of company
	 */
	public String getCompany() {
		return company;
	}
	
	/**
	 * Get the value of kind
	 */
	public String getKind() {
		return kind;
	}
	
	/**
	 * Get the value of date
	 */
	public Date getDate() {
		return new Date(date);
	}
	
	/**
	 * Get the value of price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Check for the equality of two musics
	 */
	public boolean equals(Music other) {
		if (other == null)
			return false;
		else 
			return title.equalsIgnoreCase(other.title) &&
			       company.equalsIgnoreCase(other.company) &&
			       kind.equalsIgnoreCase(other.kind) &&
			       date.equals(other.date) &&
			       price == other.price;
	}

	/**
	 * Show the content of a music in a string
	 */
	public String toString() {
		return "Music: " + title + "; " + company + "; " + kind + "; " + date + "; " + price;
	}
	
	public static void main(String[] args) {
                Music aMusic = new Music( "Harry Potter", "Motion Pictures", "Audio", new Date(2011), 17.99);
		System.out.println(aMusic);
	}
}
