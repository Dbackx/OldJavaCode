/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cis2430_backxdaniel_a1;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 *
 * @author Daniel Backx, 0679726
 * Wednesday, October, 5, 2011
 *
 */
public class dbackx_a1 {

    /****************
     * THIS is the main FUNCTION, it MAKES A LIST, ADDS, PRINTS, AND SEARCHS THROUGH A LIST IT GENERATES with a set max list size of 5.
     * @param NONE
     * @return
     */
    public static void main(String[] args) {
        Scanner Scan = new Scanner(System.in);
        String items[] = {"", "", "", "", ""};
        String userInterface;
    System.out.println("\n********************PROGRAM START********************\n");

    do {
        do {
            System.out.println("\nWhat would you like to do? \nADD new item\tSEARCH item\tPRINT all items\tQUIT");

            userInterface = Scan.next();
        } while (userInterface.equalsIgnoreCase("ADD") && userInterface.equalsIgnoreCase("SEARCH") && userInterface.equalsIgnoreCase("Quit") && userInterface.equalsIgnoreCase("a") && userInterface.equalsIgnoreCase("s") && userInterface.equalsIgnoreCase("q"));

                if (userInterface.equalsIgnoreCase("ADD") || userInterface.equalsIgnoreCase("a")) Add(items);               /* Call FUNCTION AddName */
                else if(userInterface.equalsIgnoreCase("SEARCH") || userInterface.equalsIgnoreCase("s")) MetaSearch(items);     /* Call FUNCTION SearchName */
                else if(userInterface.equalsIgnoreCase("PRINT") || userInterface.equalsIgnoreCase("p")) PrintAll(items);        /* Call FUNCTION PringAll */
                else if(userInterface.equalsIgnoreCase("QUIT") || userInterface.equalsIgnoreCase("q"));
                else System.out.println("***ERROR: there is no command by that name***");

    } while (userInterface.equalsIgnoreCase("QUIT")!=true && userInterface.equalsIgnoreCase("q")!=true);                                 /* END PROGRAM */
    System.out.println("\n********************PROGRAM END********************\n");
    }

    /**************************************************************************
     * ____                __  _____  ___   __          __
     * |    |    | |\  |  /  \   |     |   /  \  |\  | (
     * |--  |    | | \ | |       |     |  |    | | \ |  \
     * |     \__/  |  \|  \__/   |    _|_  \__/  |  \| __)
     *
    **************************************************************************/

    /****************
     * THIS FUNCTION ADDS AN ITEM TO THE LIST
     * @param REQUIRES STRING ARRAY
     * @return BOOLEAN true = correctly input name, false = name not input properly
     */
            /* FUNCTION AddName START */
        public static boolean Add (String[] items) {
            int numItems = 5;
            Scanner Scan = new Scanner(System.in);
            System.out.println("What would would you like to insert?");
            String hold = Scan.nextLine();

            boolean flag=false;

            for (int i = 0; flag!=true && i<numItems; i++) {
                if (items[i].equals("")==true) {
                    items[i]=hold;
                    flag=true;
                }                                                               /* Search for next available slot (sequentially) and put name in available */
            }
            return flag;
        }   /*  FUNCTION AddName END */

            /****************
     * THIS FUNCTION FINDS AND PRINTS ANY ITEM STRING THAT CONTAINS THE SAME WORD(S) AS INPUT SET BY USER
     * @param REQUIRES STRING ARRAY
     * @return none
     */
            /*  FUNCTION SearchName START */
        public static void MetaSearch (String items[]) {
            Scanner Scan = new Scanner(System.in);
            int numItems=5;
            System.out.println("What would you like to find?");
            String hold = Scan.nextLine();                                      /* Get User Input */
            String temp, tempSeperate;
            StringTokenizer tokenizerHold;
            StringTokenizer tokenizerSeperate;

            for (int i = 0; i < numItems; i++) {
                 tokenizerHold = new StringTokenizer(hold);
                 int numberHoldTokens=0, numberMatch=0;
                 while (tokenizerHold.hasMoreTokens()) {
                    temp = tokenizerHold.nextToken();
                    numberHoldTokens++;                                         /* Used to signify the number of words input by the user */
                    tokenizerSeperate = new StringTokenizer(items[i], ", ");    /* Seperate all strings in the items removing the commas (,) */
                    while (tokenizerSeperate.hasMoreTokens()) {
                        tempSeperate = tokenizerSeperate.nextToken();
                            if  (tempSeperate.equalsIgnoreCase(temp)) {         /* check for name (sequentially) is equal to requested name */
                                numberMatch++;                                  /* A word in the item matches a word specified by the user */
                                break;                                          /* If the word is found, continue to search for the same word */
                            }
                    }
                }
                if (numberMatch==numberHoldTokens) System.out.println(items[i] + "\n"); /* If number of words searching for is equal to the number of words found in any select item print that item */
            }
        }   /*  FUNCTION SearchName END */

          /* FUNCTION TOlKiENIZER BTICHES START */
          /* FUNCTION TOlKiENIZER BTICHES END */

                    /****************
     * THIS FUNCTION PRINTS ALL STRINGS THAT HAVE BEEN PREVIOUSLY INPUT BY THE USER
     * @param REQUIRES STRING ARRAY
     * @return none
     */
           /*  FUNCTION PrintAll START */
        public static void PrintAll (String[]names) {
            int numNames=5;

            for (int i = 0; i < numNames; i++) {
                System.out.println(i + ". " + names[i]);                        /*print all items in list*/
            }

        }   /*  FUNCTION PrintAll END */


}
