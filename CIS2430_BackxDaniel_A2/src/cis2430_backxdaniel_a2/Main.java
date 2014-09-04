/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cis2430_backxdaniel_a2;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Ryan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
  
    public static void main(String[] args) {
        System.out.println("\n********************PROGRAM START********************\n");

            Scanner Scan = new Scanner(System.in);
            String printthis, userInterface, Type;

            ArrayList <Music> musicList = new ArrayList <Music>();
            ArrayList <Movie> movieList = new ArrayList <Movie>();
            ArrayList <Book>  bookList  = new ArrayList <Book>();

        do {
            do {
                System.out.println("\nWhat would you like to do? \nADD new item\tSEARCH item\tPRINT all items\tQUIT");

                userInterface = Scan.next();
            } while (userInterface.equalsIgnoreCase("ADD") && userInterface.equalsIgnoreCase("SEARCH") && userInterface.equalsIgnoreCase("Quit") && userInterface.equalsIgnoreCase("a") && userInterface.equalsIgnoreCase("s") && userInterface.equalsIgnoreCase("q"));

                    if (userInterface.equalsIgnoreCase("ADD") || userInterface.equalsIgnoreCase("a")) AddItem(musicList, movieList, bookList);               /* Call FUNCTION AddName */
                    else if(userInterface.equalsIgnoreCase("SEARCH") || userInterface.equalsIgnoreCase("s")) MetaSearch(musicList, movieList, bookList);     /* Call FUNCTION SearchName */
                    else if(userInterface.equalsIgnoreCase("PRINT") || userInterface.equalsIgnoreCase("p")) {PrintAll(musicList); PrintAll(bookList); PrintAll(movieList);}        /* Call FUNCTION PringAll */
                    else if(userInterface.equalsIgnoreCase("QUIT") || userInterface.equalsIgnoreCase("q"));
                    else System.out.println("***ERROR: there is no command by that name***");

        } while (userInterface.equalsIgnoreCase("QUIT")!=true && userInterface.equalsIgnoreCase("q")!=true);                                 /* END PROGRAM */




        System.out.println("\n********************PROGRAM END********************\n");
    }

    private static String[] GetInput(){
        String[] Output = {"","","","","",""};
        Scanner Scan = new Scanner(System.in);
        
            System.out.println("Enter the Type (No ':'): ");
            Output[0] = Scan.nextLine();
        while (!ValidateType(Output[0]))
                   Output[0] = getType();
        System.out.println("Enter the Title (No ';'): ");
        Output[1] = Scan.nextLine();
        System.out.println("Enter any Other Information (No ';'): ");
        Output[2] = Scan.nextLine();
        System.out.println("Enter the SubType (No ';'): ");
        Output[3] = Scan.nextLine();
        System.out.println("Enter the Date (month,day,year) (No ';'): ");
        Output[4] = Scan.nextLine();
        System.out.println("Enter the price (No ';'): ");
        Output[5] = Scan.nextLine();

        return Output;
            }   /*  FUNCTION GetInput END */

    private static boolean ValidateType (String Type) {
        if (Type.equalsIgnoreCase("Music") || Type.equalsIgnoreCase("Movie") || Type.equalsIgnoreCase("Book")) return true;
        else return false;
    }

    private static String getType() {
        Scanner Scan = new Scanner(System.in);
        System.out.println("An invalid Type: has been entered, please re-enter the Type (no ':')\n");
        String type = Scan.nextLine();
        return type;
    }
    
    public static boolean MetaSearch (ArrayList musicList, ArrayList movieList, ArrayList bookList) {
                Scanner Scan = new Scanner(System.in);
        System.out.println("Type what you'd like to scan for: ");
        String type = Scan.nextLine();

        


     return true;
    }

    public static boolean AddItem (ArrayList musicList, ArrayList movieList, ArrayList bookList) {
        String input[] = GetInput();
        boolean flag=false;
        while (!flag){
            if (input[0].equalsIgnoreCase("Book"))
                flag = Add(bookList, input);
            else if(input[0].equalsIgnoreCase("Movie"))
                flag = Add(movieList, input);
            else if(input[0].equalsIgnoreCase("Music"))
                flag = Add(musicList, input);
            else input[0]=getType();
        }
        return true;
    }

    public static boolean Add(ArrayList List, String[]userInput) {
        Scanner Scan = new Scanner(System.in);
        String type="", itemTitle="", itemOther="", itemSubType="", itemPrice="", itemMonth="";
        int itemYear=0, itemDay=0;
        for (int i=0; i<6; i++)
            System.out.println(userInput[i]);

            type = userInput[0];
            itemTitle = userInput[1];
            itemOther = userInput[2];
            itemSubType = userInput[3];
            itemPrice = userInput[5];

            StringTokenizer tokenizerSeperate;
            tokenizerSeperate = new StringTokenizer(userInput[4], ",");
            itemMonth = tokenizerSeperate.nextToken();
            itemDay = Integer.parseInt(tokenizerSeperate.nextToken());
            itemYear = Integer.parseInt(tokenizerSeperate.nextToken());

            if (type.equalsIgnoreCase("Music")) {
                while (!Music.CheckTitle(itemTitle)) {
                    System.out.println("Invalid Title, Please Re-enter: ");
                    String newInput = Scan.nextLine();
                    itemTitle = newInput;
                }
                while (!Date.ValidatorYear(itemYear)) {
                    System.out.println("Invalid Year, Please Re-enter: ");
                    String newInput = Scan.nextLine();
                    itemTitle = newInput;
                }
                while (!Date.ValidatorDateOfYear(itemMonth, itemDay)) {
                    System.out.println("Invalid Month and/or Day, Please Re-enter: ");
                    String newInput = Scan.nextLine();
                    itemTitle = newInput;
                }
                Music newMusicItem = new Music();
                newMusicItem.Set(itemTitle, itemOther, itemSubType, itemYear, itemMonth, itemDay, itemPrice);
                List.add(newMusicItem);
           }

            else if (type.equalsIgnoreCase("Movie")) {
                while (!Movie.CheckTitle(itemTitle)) {
                    System.out.println("Invalid Title, Please Re-enter: ");
                    String newInput = Scan.nextLine();
                    itemTitle = newInput;
                }
                while (!Date.ValidatorYear(itemYear)) {
                    System.out.println("Invalid Year, Please Re-enter: ");
                    String newInput = Scan.nextLine();
                    itemTitle = newInput;
                }
                while (!Date.ValidatorDateOfYear(itemMonth, itemDay)) {
                    System.out.println("Invalid Month and/or Day, Please Re-enter: ");
                    String newInput = Scan.nextLine();
                    itemTitle = newInput;
                }
                 Movie newMovieItem = new Movie();
                 newMovieItem.Set(itemTitle, itemOther, itemSubType, itemYear, itemMonth, itemDay, itemPrice);
                 List.add(newMovieItem);
            }
            else if (type.equalsIgnoreCase("Book")) {
                while (!Book.CheckTitle(itemTitle)) {
                    System.out.println("Invalid Title, Please Re-enter: ");
                    String newInput = Scan.nextLine();
                    itemTitle = newInput;
                }
                while (!Date.ValidatorYear(itemYear)) {
                    System.out.println("Invalid Year, Please Re-enter: ");
                    String newInput = Scan.nextLine();
                    itemTitle = newInput;
                }
                while (!Date.ValidatorDateOfYear(itemMonth, itemDay)) {
                    System.out.println("Invalid Month and/or Day, Please Re-enter: ");
                    String newInput = Scan.nextLine();
                    itemTitle = newInput;
                }
                 Book newBookItem = new Book();
                 newBookItem.Set(itemTitle, itemOther, itemSubType, itemYear, itemMonth, itemDay, itemPrice);
                 List.add(newBookItem);
            }
            return true;
    }

            public static void PrintAll(ArrayList List) {
            for (int index=0; index<List.size(); index++) {
                System.out.println(List.get(0).toString());
                }
            }

            /*
            for (int index=0; index<musicList.size(); index++) {
                System.out.println(musicList.get(0).toString());
                System.out.println("0");
            }
            for (int index=0; index<movieList.size(); index++) {
                System.out.println(movieList.get(0).toString());
                System.out.println("1");
            }
            for (int index=0; index<bookList.size();  index++) {
                System.out.println (bookList.get(0).toString());
            }

Type: Rise Against; Other; SubType; 2011; $14.66
             *               System.out.println(Input[i] + i + userInput);

*/
    }
