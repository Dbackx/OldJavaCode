package metasearch;

/** Movie class
 *
 * @author Fei Song
 *
 * A class for representing and comparing movies.
 *
 */

public class Movie {
	private String title;       // title of a movie
	private String actors;     // one or multiple actors separated by commas
	private String kind;        // kind of a movie
	private Date date;          // publication date of a movie
	private double price;        // price of a movie
	
	/**
	 * Create a movie with all the required fields
	 */
	public Movie(String title, String actors, String kind, Date date, double price) {
		if( valid(title, date, price) ) {
			this.title = title;
			this.actors = actors;
			this.kind = kind;
			this.date = new Date(date);
			this.price = price;
		} else {			
			System.out.println("Invalid values for creating a movie");
			System.exit(0);
		}
	}
	
	/**
	 * Create a movie with no arguments
	 */
	public Movie() {
		this("No Title", "", "", new Date(), 0.00);
	}
	
	/**
	 * Create a copy of a movie
	 */
	public Movie(Movie other) {
		if (other == null) {
			System.out.println("null value for copying a movie");
			System.exit(0);
		} else {
			title = other.title;
			actors = other.actors;
			kind = other.kind;
			date = new Date(other.date);
			price = other.price;
		}
	}
	
	/**
	 * A static method for validating if the information for a movie is valid
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
	 * Set a new value for actors
	 */
	public void setActors(String actors) {
		this.actors = actors;
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
	 * Get the value of actors
	 */
	public String getActors() {
		return actors;
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
	 * Check for the equality of two movies
	 */
	public boolean equals(Movie other) {
		if (other == null)
			return false;
		else 
			return title.equalsIgnoreCase(other.title) &&
			       actors.equalsIgnoreCase(other.actors) &&
			       kind.equalsIgnoreCase(other.kind) &&
			       date.equals(other.date) &&
			       price == other.price;
	}

	/**
	 * Show the content of a movie in a string
	 */
	public String toString() {
		return "Movie: " + title + "; " + actors + "; " + kind + "; " + date + "; " + price;
	}
	
	public static void main(String[] args) {
                Movie aMovie = new Movie( "Harry Potter", "Daniel Radcliffe, Emma Watson, and Rupert Grint", "DVD", new Date(2011), 14.93);
		System.out.println(aMovie);
	}
}
