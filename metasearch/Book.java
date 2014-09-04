package _backxdaniel_a3;

/** Book class
 *
 * @author Fei Song
 *
 * A class for representing and comparing books.
 *
 */

public class Book extends EClass {
	private String title;       // title of a book
	private String authors;     // one or multiple authors separated by commas
	private String kind;        // kind of a book
	private Date date;          // publication date of a book
	private double price;        // price of a book
	
	/**
	 * Create a book with all the required fields
	 */
	public Book(String title, String authors, String kind, Date date, double price) {
		if( valid(title, date, price) ) {
			this.title = title;
			this.authors = authors;
			this.kind = kind;
			this.date = new Date(date);
			this.price = price;
		} else {			
			System.out.println("Invalid values for creating a book");
			System.exit(0);
		}
	}
	
	/**
	 * Create a book with no arguments
	 */
	public Book() {
		this("No Title", "", "", new Date(), 0.00);
	}
	
	/**
	 * Create a copy of a book
	 */
	public Book(Book other) {
		if (other == null) {
			System.out.println("null value for copying a book");
			System.exit(0);
		} else {
			title = other.title;
			authors = other.authors;
			kind = other.kind;
			date = new Date(other.date);
			price = other.price;
		}
	}
	
	/**
	 * A static method for validating if the information for a book is valid
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
	 * Set a new value for authors
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
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
	 * Get the value of authors
	 */
	public String getAuthors() {
		return authors;
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
	 * Check for the equality of two books
	 */
	public boolean equals(Book other) {
		if (other == null)
			return false;
		else 
			return title.equalsIgnoreCase(other.title) &&
			       authors.equalsIgnoreCase(other.authors) &&
			       kind.equalsIgnoreCase(other.kind) &&
			       date.equals(other.date) &&
			       price == other.price;
	}

	/**
	 * Show the content of a book in a string
	 */
	public String toString() {
		return "Book: " + title + "; " + authors + "; " + kind + "; " + date + "; " + price;
	}
	
	public static void main(String[] args) {
                Book aBook = new Book( "Harry Potter", "JK Rowling", "Paper", new Date(1, 12, 2011), 66.15);
		System.out.println(aBook);
	}
}
