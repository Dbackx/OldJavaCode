package _BackxDaniel_A3;

import java.util.*;
import java.lang.Integer;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.InputStream;

/** MetaSearch class
 *
 * @author Fei Song edited by Daniel Backx
 *
 * A class that adds and searches for books, musics, and movies both within the
 * list and online using Chapters.ca.
 *
 */

public class MetaSearch {

	private ArrayList<EClass> all;      // A list for maintaining books
	private HashMap<String, ArrayList> eMap;         // A map for all titles

        /**
         *
         */
        public static final String[] ENTRY_TYPES = {"Book", "Music", "Movie"};

	/**
	 * Create an instance of MetaSearch
	 */
	public MetaSearch() {
		all = new ArrayList<EClass>();
                eMap = new HashMap<String, ArrayList>();
	}

	/** 
	 * Create an entry from the input and add it to the appropriate list
         *
         * @param input
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

                all.add( new Book(title, field, kind, date, price) );
                int i = all.size();
                addHashMap(all.get(i).getTitle(), i);
	}

        
        /*
	 * Add to HashMap
	 */
        private boolean addHashMap (String title, int arrayPosition) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(arrayPosition);
            String[] keywords = null;
            if( !title.equals("") )
                keywords = title.split( "[ ,\n_]+" );

            if (eMap.isEmpty()) {eMap.put(keywords[0], list);}
            for (int j=keywords.length-1; j>=0; j--) {
                list = new ArrayList<Integer>();
                    if (eMap.containsKey(keywords[j]) && !eMap.get(keywords[j]).contains(arrayPosition)) {
                        list = (eMap.get(keywords[j]));
                        list.add(arrayPosition);
                        eMap.put(keywords[j], list);
                    }
                    else if (!eMap.containsKey(keywords[j])){list.add(arrayPosition);eMap.put(keywords[j], list);
                    }
            }
            return true;
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
	 * Search for all entries that satisfy a title search request
	 */
	private boolean search( String[] keywords ) {
		boolean found=false, flag=true;
            ArrayList<Integer> list = new ArrayList<Integer>();
            ArrayList<Integer> intersection = new ArrayList<Integer>();

            for (int i=0; i<all.size(); i++) intersection.add(i);

            if (eMap.isEmpty()) {return found;}
            for (int j=keywords.length-1; j>=0; j--) {
                list = eMap.get(keywords[j]);
                if (list==null) return found;
                for (int i=0; i<intersection.size(); i++) {
                        if (!list.contains(intersection.get(i))) {
                        intersection.remove(intersection.get(i));
                        i--;
                    }
                }
            }

            for (int i=0; i<intersection.size(); i++) {
                System.out.println(all.get(intersection.get(i)).toString());
                found = true;
            }

            return found;
	}

        /*
	 * Search for all things about the keyword from internet
	 */
	private boolean searchChapters( String[] keywords ) throws IOException {
            String inputString="", nextLine="", keyword = "";
            String Type="", Artist="", Title="", Company="", Kind="", Dates="", Price="";
            
            for (int i=0; i<keywords.length; i++) keyword = keyword + "%20" + keywords[i];

            System.setProperty("http.agent", "Mozilla/5.0 (Windows; U; MTSE 9.01; Windows NT 9.0; en-US)");

            try {
                    URL chaptersIndigo = new URL ("http://www.chapters.indigo.ca/home/search/?keywords="+keyword+"&pageSize=12");
                    Scanner scan = new Scanner (chaptersIndigo.openStream());
                    nextLine = scan.nextLine();
                    
                    do {
                        inputString += nextLine;
                        try {
                            nextLine = scan.nextLine();
                        }catch(NoSuchElementException ex){
                            break;
                        }
                    }while(2>1);
                scan.close();
                }
                catch (MalformedURLException e){
                    System.out.println(e.getMessage());
                }

            int start=0, end=0;
            for (int i=0; i<7; i++) {
                start = end;
                start = inputString.indexOf("<h3",start);
                start = inputString.indexOf("<a",start);
                start = inputString.indexOf("\">",start)+2;
                end = inputString.indexOf(":",start);
                Type = inputString.substring(start, end);
                start = end;
                end = inputString.indexOf("</a>",end+1);
                Title = inputString.substring(start+2, end);

                start = inputString.indexOf("<h4",start);
                start = inputString.indexOf("'>",start);
                end = inputString.indexOf("</a>",start+1);
                Artist = inputString.substring(start+2, end);

                start = inputString.indexOf("<h5",start);
                start = inputString.indexOf("\">",start);
                start = inputString.indexOf("\">",start)+14;
                end = inputString.indexOf("</h5>",start);
                Company = inputString.substring(start, end);

                String[] parse = Company.trim().split("[|]");

                if (parse.length==3) {
                    Company = parse[0];
                    Dates = parse[1];
                    Kind = parse[2];
                }
                if (parse.length==2) {
                    Company = parse[0];
                    Dates = parse[1];
                    Kind = "";
                }
                if (parse.length==1) {
                    Company = "";
                    Dates = parse[0];
                    Kind = "";
                }

                    start = inputString.indexOf("/h6",start);
                    start = inputString.indexOf("'>",start);
                    end = inputString.indexOf("</",start);
                    Price = inputString.substring(start+3, end);
 
                if (Type.equalsIgnoreCase("DVD")) Type = "Movie";
                if (Type.equalsIgnoreCase("Book") || Type.equalsIgnoreCase("Movie") || Type.equalsIgnoreCase("Music")) {

                    int month=0, day=0, year=0;
                    String[] fields = Dates.trim().split("[., ]+");
                    if( fields.length == 1 && fields[0].matches("[0-9]+") ) {
                        year = Integer.parseInt(fields[0]);
                    }
                    else if( fields.length == 3 && Date.getMonth(fields[0]) >= 0 && fields[1].matches("[0-9]+") && fields[2].matches("[0-9]+") ) {
                        month = Date.getMonth(fields[0]);
                        day = Integer.parseInt(fields[1]);
                        year = Integer.parseInt(fields[2]);
                    }
                    Date date = new Date(month,day,year);
                    double price = Double.parseDouble(Price);

                    if (Type.equalsIgnoreCase("Book")) all.add( new Book(Title, Artist, Kind, date, price) );
                    else if(Type.equalsIgnoreCase("Movie")) all.add(new Movie(Title, Artist, Kind, date, price));
                    else if(Type.equalsIgnoreCase("Music")) all.add(new Music(Title, Artist, Kind, date, price));
                    else return false;

               System.out.println("Type: " + Type + "\tTitle: " + Title + "\nArtist: " + Artist + "\tDate: " + Dates + "\tKind: " + Kind + "\nCompany: " + Company + "\tPrice: $" + Price + "\n");
                    addHashMap(all.get(all.size()-1).getTitle(), all.size()-1 );
                }
            }
        return true;
	}

	/**
	 * Gather a search request and find the matched books and journals in the related list(s)
         *
         * @param input 
         * @throws IOException
         */
	public void search( Scanner input ) throws IOException {
            boolean found=false;
            System.out.print( "Enter title keywords> " );
            String[] keywords = null;
            String line = input.nextLine();
            if( !line.equals("") )
        	keywords = line.split( "[ ,\n]+" );

		// search for matched references
            System.out.println( "\nMatched references in current database: " );
            found = (search(keywords));
            if (!found) {
                System.out.println( "No references were found, Will attempt search on Chapter.ca\nMatched Online references: " );
                found = searchChapters(keywords);
            }
            if (!found) System.out.println("Unable to find a suitable match");

	}
	
        /**
         *
         * @param args
         * @throws IOException
         */
        public static void main( String[] args ) throws IOException {
		Scanner input = new Scanner( System.in );
		MetaSearch engine = new MetaSearch();
		String command;
		do {
			System.out.print( "\nEnter add, search, TestCases, print or quit> " );
			command = input.nextLine();
			if( command.equalsIgnoreCase("add") || command.equalsIgnoreCase("a") )
				engine.add( input );
			else if( command.equalsIgnoreCase("search") || command.equalsIgnoreCase("s") )
				engine.search( input );
			else if( command.equalsIgnoreCase("print") || command.equalsIgnoreCase("p") )
				engine.printAll( );
                        else if ( command.equalsIgnoreCase("Testcases") || command.equalsIgnoreCase("t"))
                                engine.addTest();
                         
		} while( !command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q") );
	}

        /**
         *
         */
        public void printAll() {
            System.out.println(eMap.toString());
        }

        /**
         *
         */
        public void addTest() {
            Date date = new Date(12, 1, 1999);
            all.add( new Movie("Star Wars: Episode 1: The Phantom Menace", "Movie", "Motion Picture", date, 23.42) );
            addHashMap(all.get(0).getTitle(), 0 );
            date = new Date(1, 12, 2000);
            all.add( new Movie("Star Wars: Episode 2: Attack of the Clones", "Movie", "Motion Picture", date, 23.42) );
            addHashMap(all.get(1).getTitle(), 1 );
            date = new Date(1, 12, 2005);
            all.add( new Movie("Star Wars: Episode 3: Revenge of the Sith", "Movie", "Motion Picture", date, 23.42) );
            addHashMap(all.get(2).getTitle(), 2 );
            date = new Date(1, 12, 2009);
            all.add( new Movie("Star Wars: Episode 4: A New Hope", "Movie", "Motion Picture", date, 23.42) );
            addHashMap(all.get(3).getTitle(), 3 );
            date = new Date(1, 12, 2000);
            all.add( new Book("Harry Potter and the Philosophers Stone", "Book", "Paperback Novel", date, 23.42) );
            addHashMap(all.get(4).getTitle(), 4 );
            date = new Date(1, 12, 2001);
            all.add( new Book("Harry Potter and the Chamber of Secrets", "Book", "Paperback Novel", date, 23.42) );
            addHashMap(all.get(5).getTitle(), 5 );
            System.out.println(eMap);

        }

}
