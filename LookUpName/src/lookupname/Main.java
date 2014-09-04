/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lookupname;
import java.util.Scanner;


/**
 *
 * @author Daniel
 *
 */
public class Main {

        int numNames=6;
    /****************
     * THIS FUNCTION MAKES A LIST, ADDS, REMOVES, PRINTS, AND SEARCHS THROUGH A LIST IT GENERATES. 
     * @param NONE
     * @return
     */
    public static void main(String[] args) {
        Scanner Scan = new Scanner(System.in);


    String[]names = {"Sam", "Kyle", "Nick", "Sonya", "", "", ""};
    int userInterface;

    do {

        do {
            userInterface = 0;

            System.out.println("What would you like to do? \n1. ADD new name\t2. DELETE position\t3. SEARCH name\t4. PRINT position\t5. PRINT all\t6. EXIT");

            userInterface  = Scan.nextInt();
        } while (userInterface>7 || userInterface<0);

                if (userInterface==1) AddName(names);           /* Call FUNCTION AddName */
                if (userInterface==2) DeletePosition(names);    /* Call FUNCTION DeletePosition */
                if (userInterface==3) SearchName(names);        /* Call FUNCTION SearchName */
                if (userInterface==4) PrintSlot(names);         /* Call FUNCTION PrintSlot */
                if (userInterface==5) PrintAll(names);          /* Call FUNCTION PrintAll */

    } while (userInterface!=6);                                 /* END PROGRAM */

    }

    /**************************************************************************
     * ____                __  _____  ___   __          __
     * |    |    | |\  |  /  \   |     |   /  \  |\  | (
     * |--  |    | | \ | |       |     |  |    | | \ |  \
     * |     \__/  |  \|  \__/   |    _|_  \__/  |  \| __)
     *
    **************************************************************************/

    /****************
     * THIS FUNCTION ADDS A NAME TO THE SET IN A SPECIFIED SLOT
     * @param REQUIRES STRING ARRAY
     * @return
     */
            /* FUNCTION AddName START */
        public static boolean AddName (String[] names) {
            int numNames = 6;
            Scanner Scan = new Scanner(System.in);
            System.out.println("What name would you like to insert?");
            String hold = Scan.next();
            boolean flag=false;

            for (int i = 0; flag!=true && i<numNames; i++) {
                if (names[i].equals("")==true) {
                    names[i]=hold;
                    flag=true;
                }   /* Search for next available slot (sequentially) and put name in available */
            }
            return flag;
        }   /*  FUNCTION AddName END */


    /****************
     * THIS FUNCTION DELETES A NAME TO THE SET IN A SPECIFIED SLOT
     * @param REQUIRES STRING ARRAY
     * @return
     */
        /*  FUNCTION DeletePosition START */
        public static void DeletePosition (String[] names) {
            Scanner Scan = new Scanner(System.in);
            System.out.println("Which position would you like to delete? or press 6 to exit");
            int delete_position = Scan.nextInt();

            if  (delete_position!=6 && names[delete_position].equals("")!=true) { /* Make sure slot requested exists, ensure there is a name in requested slot */
                names[delete_position]="";
                System.out.println("SUCCESSFUL: Name in slot " + delete_position + " was successfully removed.");
            }
            else System.out.println("ERROR: No name in selected position");
        }   /*  FUNCTION DeletePosition END */


            /****************
     * THIS FUNCTION FINDS A NAME AND THE SPECIFIED SLOT
     * @param REQUIRES STRING ARRAY
     * @return
     */
            /*  FUNCTION SearchName START */
        public static void SearchName (String[]names) {
            Scanner Scan = new Scanner(System.in);
            int numNames=6;
            boolean flag=false;

            System.out.println("What name would you like to find?");
            String hold = Scan.next();

            for (int i = 0; flag==true || i < numNames; i++)
                if  (hold.equals(names[i])==true) { /* check for name (sequentially) is equal to requested name */
                    System.out.println("Position " + i + " Contains name " + names[i]);
                    flag=true;
                    break;
                }

            if (flag!=true) System.out.println("Name does not exist in list.");

        }   /*  FUNCTION SearchName END */

            /****************
     * THIS FUNCTION PRINTS A NAME TO THE SET IN A SPECIFIED SLOT
     * @param REQUIRES STRING ARRAY
     * @return
     */
        /*  FUNCTION PrintSlot START */
        public static void PrintSlot (String[]names) {
            Scanner Scan = new Scanner(System.in);
            int numNames=6, slot;

            System.out.println("Which position would you like to veiw?");
            slot = Scan.nextInt();

            System.out.println(slot + ". " + names[slot]); /*print only selected slot*/

        }   /*  FUNCTION PrintSlot END */

            /****************
     * THIS FUNCTION PRINTS ALL  NAMES
     * @param REQUIRES STRING ARRAY
     * @return
     */
            /*  FUNCTION PrintAll START */
        public static void PrintAll (String[]names) {
            int numNames=6;

            for (int i = 0; i < numNames; i++) {
                System.out.println(i + ". " + names[i]); /*print all from 1-numeber of names in list*/
            }

        }   /*  FUNCTION PrintAll END */

}
