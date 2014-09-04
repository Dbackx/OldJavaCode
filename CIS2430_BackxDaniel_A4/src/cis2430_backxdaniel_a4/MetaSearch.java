package cis2430_backxdaniel_a4;
import java.io.*;
import java.util.*;
import java.lang.Integer;

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

        public void SaveToFile(EClass Save) {
            try {
                    // Create file
                    FileWriter fstream = new FileWriter("Data.txt", true);
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(Save.toString()+"\n");
                    //Close the output stream
                    out.close();
                }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            }
        }

	/** 
	 * Create an entry from the input and add it to the appropriate list
         *
         * @param input
         */
	public String add( String input ) {
		String validity = "";

                validity = Adder(input);
                
                SaveToFile(all.get(all.size()-1));
                return validity;
	}
        
        public String Adder(String line) {
                String type = "";
		String title = "";
		String field = "";
		String kind = "";
		Date date = null;
		double price = 0.0;
                String isValid = "";

                        int pos = line.indexOf(":");
			if( pos < 0 ) {
				isValid = isValid+("No entry type is provided: ");
			}
		        type = line.substring(0, pos).trim();
			if( !matchedKeyword(type, ENTRY_TYPES) ) {
				isValid = isValid+("Unknown entry type: " + type + ": ");
			}
			line = line.substring(pos + 1).trim();
			String[] fields = line.split(";");
			if( fields.length != 5 ) {
				isValid = isValid+("Incorrect number of fields provided: ");
			}
			title = fields[0].trim();
			if( title.equals("") ) {
				isValid = isValid+("Title field must be provided: ");
			}
			field = fields[1].trim();
			kind = fields[2].trim();
			date = Date.getDate( fields[3] );
			if( date == null ) {
				isValid = isValid+("Invalid date provided: ");
			}
			if( !fields[4].trim().matches("[0-9]+\\.[0-9]{1,2}") ) {
				isValid = isValid+("Invalid price provided");
			}
			price = Double.parseDouble(fields[4].trim());

                doAdd(type, title, field, kind, date, price);
                if (isValid.equals("")) isValid = ("Valid Entry: Input Successful");
                return isValid;
        }

        public void doAdd (String type, String title, String field, String kind, Date date, Double price) {
                all.add(Convert(type, title, field, kind, date, price) );
                int i = all.size();
                addHashMap(title, i);
                printAll();
        }

        public EClass Convert (String type, String title, String field, String kind, Date date, Double price) {
            EClass convert;
            
            if (type.equalsIgnoreCase("Book")) convert = new Book(title, field, kind, date, price);
            else if(type.equalsIgnoreCase("Movie")) convert = new Movie(title, field, kind, date, price);
            else if(type.equalsIgnoreCase("Music")) convert = new Music(title, field, kind, date, price);
            else return convert = new EClass("", "", "", "", new Date(), 0.0);
            
            return convert;
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
            System.out.println(eMap);
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
	private String search( String Type, String[] keywords ) {
		String found="";
                boolean flag=true;
            ArrayList<Integer> list = new ArrayList<Integer>();
            ArrayList<Integer> intersection = new ArrayList<Integer>();

            for (int i=1; i<=all.size(); i++) intersection.add(i);
            System.out.print(found);
            System.out.print(eMap);

            if (eMap.isEmpty()) {return found;}
            if (keywords.length!=0)
                for (int j=keywords.length-1; j>=0; j--) {
                    list = eMap.get(keywords[j]);
                    if (list==null) return found;
                    for (int i=0; i<intersection.size(); i++) {
                        if (!list.contains(intersection.get(i))) {
                            System.out.print(intersection.get(i));
                            intersection.remove(intersection.get(i));
                            i--;
                      }
                    }
                }

            for (int i=0; i<intersection.size(); i++) {
                found = found + "\n" + all.get(intersection.get(i)-1).toString();
            }
            for (int i=intersection.size(); i>=0; i--) {
                if (all.get(intersection.get(i)-1).geteType()!=Type) intersection.remove(intersection.get(i));
            }

            
            System.out.print(found);

            return found;
	}


	/**
	 * Gather a search request and find the matched books and journals in the related list(s)
         *
         * @param input 
         * @throws IOException
         */
	public String search( String Type, String line ) throws IOException {
            main();
            String found="";
            String[] keywords = null;
            if( !line.equals("") )
        	keywords = line.split( "[ ,\n]+" );

		// search for matched references
            found = (search(Type, keywords));
            if (!found.equals("")) return ("Matched references in current database: " +found);
            else return ("Unable to find a suitable match");

	}
	
        /**
         *
         * @param args
         * @throws IOException
         */
        public void main() throws IOException {
		Scanner input = new Scanner( System.in );
		String command;
                System.out.println("Performing Opener");
               try {
                    // Open the file that is the first
                    // command line parameter
                    FileInputStream fstream = new FileInputStream("Data.txt");
                    // Get the object of DataInputStream
                    DataInputStream in = new DataInputStream(fstream);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    //Read File Line By Line
                    while ((strLine = br.readLine()) != null)   {
                        System.out.println(strLine);// Print the content on the console
                        Adder(strLine);
                    }
                    //Close the input stream
                    in.close();
                }catch (Exception e){//Catch exception if any
                    System.err.println("Error: " + e.getMessage());
                }
	}

        /**
         *
         */
        public void printAll() {
            System.out.println(eMap.toString());
            System.out.println(all.toString());
        }

}
