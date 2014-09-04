package _backxdaniel_a3;

import java.util.Scanner;

/** MetaSearchDemo class
 *
 * @author Fei Song
 *
 */

public class MetaSearchDemo {
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
