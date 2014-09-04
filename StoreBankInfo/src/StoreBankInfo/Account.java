/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package StoreBankInfo;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Daniel Backx
 */
public class Account {
        public String accountType, accountHolder;
        private String accountPin;
        private int accountNum;
        private double accountBal;

        public Account (String newName, int newNum, String newType) {
            if (newType.equalsIgnoreCase("Chequing") || newType.equalsIgnoreCase("Saving") || newType.equalsIgnoreCase("TaxFree") && !newName.equals("") && newNum!=0) {
                accountHolder = newName;
                accountNum = newNum;
                accountType = newType;
                accountPin = "0000";
                accountBal = 0;
                System.out.println("\n Your " + newType + " has been created!");
            }
        }

        public boolean equals (Account acct) {
            if (acct.accountNum == this.accountNum) return true;
            else return false;
        }


        private boolean CheckPin (String testPin) {
            if (testPin.equals(accountPin)) return true;
            else {
                System.out.println("\n WARNING: Incorrect Pin\n");
                return false;
            }
        }

        public void SetPin (String oldPin, String newPin) {
            if (CheckPin(oldPin)) accountPin = newPin;
        }

        public String toString (String testPin) {
            if (CheckPin(testPin)) {
                return (accountHolder + " your " + accountType + " account " + accountNum + " balance is " + accountBal);
            }
            else return "";
        }

        public void Deposit (String testPin, double value) {
            if (CheckPin(testPin) && value>0) accountBal = accountBal + value;
        }

        public void Withdraw (String testPin, double value) {
            if (CheckPin(testPin) && value>0 && value<accountBal) accountBal = accountBal - value;
            else System.out.println("WARNING: Incorrect Withdraw amount");
        }

        public static void main(String[] args) {
            Scanner Scan = new Scanner(System.in);
            String printthis;

            ArrayList <Account> accounts = new ArrayList <Account>();

            Account acct1 = new Account("Bobert", 12345678, "Chequing");
            Account acct2 = new Account("Robert", 12345678, "Saving");
            Account acct3 = new Account("Bert", 78123456, "TaxFree");

            accounts.add(acct1);
            accounts.add(acct2);
            accounts.add(acct3);

            acct1.SetPin("0000", "1234");
            acct2.SetPin("0000", "4321");
            acct3.SetPin("0000", "3412");

            acct1.Deposit("1234", 100);
            acct2.Deposit("4321", 100);
            acct3.Deposit("3412", 100);

            printthis = acct1.toString("1234");
            System.out.println(printthis);
            printthis = acct2.toString("4321");
            System.out.println(printthis);
            printthis = acct3.toString("3412");
            System.out.println(printthis);

            acct1.Withdraw("1234", 50);
            acct1.Withdraw("4321", 150);
            acct1.Withdraw("1234", 50);

            printthis = acct1.toString("1234");
            System.out.println(printthis);
            printthis = acct2.toString("4321");
            System.out.println(printthis);
            printthis = acct3.toString("3412");
            System.out.println(printthis);

            if (acct1.equals(acct2)) System.out.println("\nThis is the same account\n");

    }

}
