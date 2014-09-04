package _backxdaniel_a3;

import java.util.ArrayList;
import java.util.Scanner;

/** MetaSearch class
 *
 * @author Fei Song
 *
 * A class that adds and searches for books, musics, and movies.
 *
 */

public class MetaSearch {

	private ArrayList<Book> books;       // A list for maintaining books
	private ArrayList<Music> musics;     // A list for maintaining musics
	private ArrayList<Movie> movies;     // A list for maintaining movies
	
	public static final String[] ENTRY_TYPES = {"Book", "Music", "Movie"};

	/**
	 * Create an instance of MetaSearch
	 */
	public MetaSearch() {
		books = new ArrayList<Book>();
		musics = new ArrayList<Music>();
		movies = new ArrayList<Movie>();
	}

	/** 
	 * Create an entry from the input and add it to the appropriate list
	 */
	public void add( Scanner input ) {
		String type = "";
		String title = "";
		String field = "";
		String kind = "";
		Date date = null;
		double price = 0.0; 

		boolean valid;
		do {
			valid = true;
			System.out.println( "Enter a new entry for book, music, or movie> " );
			String line = input.nextLine();
			int pos = line.indexOf(":");
			if( pos < 0 ) {
				System.out.println("No entry type is provided");
				valid = false;
				continue;
			}
		        type = line.substring(0, pos).trim();
			if( !matchedKeyword(type, ENTRY_TYPES) ) {
				System.out.println("Unknown entry type: " + type);
				valid = false;
				continue;
			}				
			line = line.substring(pos + 1).trim();
			String[] fields = line.split(";");
			if( fields.length != 5 ) {
				System.out.println("Incorrect number of fields provided");
				valid = false;
				continue;
			}
			title = fields[0].trim();
			if( title.equals("") ) {
				System.out.println("Title field must be provided");
				valid = false;
				continue;
			}	
			field = fields[1].trim();
			kind = fields[2].trim();
			date = Date.getDate( fields[3] );
			if( date == null ) {
				System.out.println("Invalid date provided");
				valid = false;
				continue;
			}
			if( !fields[4].trim().matches("[0-9]+\\.[0-9]{1,2}") ) {
				System.out.println("Invalid price provided");
				valid = false;
				continue;	
			} 
			price = Double.parseDouble(fields[4].trim());
		} while( !valid );
		
		if( type.equalsIgnoreCase("Book") )
			books.add( new Book(title, field, kind, date, price) );
		else if( type.equalsIgnoreCase("Music") )
			musics.add( new Music(title, field, kind, date, price) );
		else
			movies.add( new Movie(title, field, kind, date, price) );
	}

	/* 
	 * Check if a keyword is on a list of tokens
	 */
	private boolean matchedKeyword( String keyword, String[] tokens ) {
		for( int i = 0; i < tokens.length; i++ ) 
			if( keyword.equalsIgnoreCase(tokens[i]) )
				return true;
		return false;
	}

	/*
	 * Check if all keywords are in a string 
	 */
	private boolean matchedKeywords( String[] keywords, String title ) {
		String[] tokens = title.split( "[ ,\n]+" );
		for( int i = 0; i < keywords.length; i++ ) 
			if( !matchedKeyword(keywords[i], tokens) )
				return false;
		return true;
	}

	/*
	 * Search for all books that satisfy a search request
	 */
	private void searchBooks( String[] keywords, Date startDate, Date endDate ) {
		for( int i = 0; i < books.size(); i++ )  {
			Date bookDate = books.get(i).getDate();
			if( (keywords == null || matchedKeywords(keywords, books.get(i).getTitle())) &&
			    (startDate == null || startDate.precedes(bookDate) || startDate.equals(bookDate)) &&
			    (endDate == null || bookDate.precedes(endDate) || bookDate.equals(endDate)) )
				System.out.println( books.get(i) ); 		
		}
	}

	/*
	 * Search for all musics that satisfy a search request
	 */
	private void searchMusics( String[] keywords, Date startDate, Date endDate ) {
		for( int i = 0; i < musics.size(); i++ )  {
			Date musicDate = musics.get(i).getDate();
			if( (keywords == null || matchedKeywords(keywords, musics.get(i).getTitle())) &&
			    (startDate == null || startDate.precedes(musicDate) || startDate.equals(musicDate)) &&
			    (endDate == null || musicDate.precedes(endDate) || musicDate.equals(endDate)) )
				System.out.println( musics.get(i) ); 		
		}
	}

	/*
	 * Search for all movies that satisfy a search request
	 */
	private void searchMovies( String[] keywords, Date startDate, Date endDate ) {
		for( int i = 0; i < movies.size(); i++ )  {
			Date movieDate = movies.get(i).getDate();
			if( (keywords == null || matchedKeywords(keywords, movies.get(i).getTitle())) &&
			    (startDate == null || startDate.precedes(movieDate) || startDate.equals(movieDate)) &&
			    (endDate == null || movieDate.precedes(endDate) || movieDate.equals(endDate)) )
				System.out.println( movies.get(i) ); 		
		}
	}

	/**
	 * Gather a search request and find the matched books and journals in the related list(s)
	 */ 
	public void search( Scanner input ) {
		
		String type = "";
		boolean valid;
		do {
			valid = true;
			System.out.print( "Enter an entry type (book, music, or movie)> " );
			type = input.nextLine();
			if( !type.equals("") && !matchedKeyword(type, ENTRY_TYPES) ) {
				System.out.println("Unknown entry type: " + type);
				valid = false;
			}
  		} while( !valid );

		System.out.print( "Enter title keywords> " );
		String[] keywords = null;
		String line = input.nextLine();
		if( !line.equals("") )
			keywords = line.split( "[ ,\n]+" );

		Date startDate = null;
		do {
			valid = true;
			System.out.print("Enter a start date (month day, year)> ");
			line = input.nextLine();
			startDate = Date.getDate( line );
			if( !line.equals("") && startDate == null ) {
				System.out.println( "Invalid start date.  Try again" );
				valid = false;
			}
		} while( !valid );

		Date endDate = null;
		do {
			valid = true;
			System.out.print("Enter an end date (month day, year)> ");
			line = input.nextLine();
			endDate = Date.getDate( line );
			if( !line.equals("") && endDate == null ) {
				System.out.println( "Invalid end date.  Try again" );
				valid = false;
			}
		} while( !valid );

		// search for matched references
		System.out.println( "Matched references: " );
		if( type.equals("") || type.equalsIgnoreCase("book") )
			searchBooks( keywords, startDate, endDate );
		if( type.equals("") || type.equalsIgnoreCase("music") )
			searchMusics( keywords, startDate, endDate );
		if( type.equals("") || type.equalsIgnoreCase("movie") )
			searchMovies( keywords, startDate, endDate );
	}
	
	public static void main( String[] args ) {
		Scanner input = new Scanner( System.in );
		MetaSearch engine = new MetaSearch();
		String command;
		do {
			System.out.print( "Enter add, search, or quit> " );
			command = input.nextLine();
			if( command.equalsIgnoreCase("add") || command.equalsIgnoreCase("a") )
				engine.add( input );
			else if( command.equalsIgnoreCase("search") || command.equalsIgnoreCase("s") )
				engine.search( input );			
		} while( !command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q") );
	}

}
