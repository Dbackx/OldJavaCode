/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package a4;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/**
 *
 * @author Daniel Backx
 */
public class Account {
        public String accountHolder;
        private String accountPin;
        private int accountNum;
        private double accountBal;

        public Account (String newName, int newNum, String newPin) {
            if (!newName.equals("") && newNum!=0 && newPin.length()==4) {
                accountHolder = newName;
                accountNum = newNum;
                accountPin = newPin;
                accountBal = 0;
            }
        }
        
        public boolean equals (Account acct) {
            if (acct.accountNum == this.accountNum) return true;
            else return false;
        }

        public int getAccountNum () {
            return this.accountNum;
        }

        private boolean CheckPin (String testPin) {
            if (testPin.equals(accountPin)) return true;
            else {
                System.out.println("\nWARNING: Incorrect Pin\n");
                return false;
            }
        }

        public void SetPin (String oldPin, String newPin) {
            if (CheckPin(oldPin)) accountPin = newPin;
        }

        @Override
        public String toString () {
                return (accountHolder + " your account " + accountNum + " balance is " + accountBal);
        }

        public void Deposit (String testPin, double value) {
            if (CheckPin(testPin) && value>0) accountBal = accountBal + value;
        }

        public void Withdraw (String testPin, double value) {
            if (!CheckPin(testPin)) return;
            if (value>0 && value<accountBal) accountBal = accountBal - value;
            else System.out.println("WARNING: Incorrect Withdraw amount");
        }

        public static void main(String[] args) {
            String Pin1 = "1234", Pin2 = "4321", printthis;
            int accountNum1 = 87654321, accountNum2 = 12345678;

            HashMap<Integer,String>numPINHMap = new HashMap<Integer,String>();

            numPINHMap.put(accountNum1, Pin1);
            numPINHMap.put(accountNum2, Pin2);

            Iterator iterator = numPINHMap.keySet().iterator();

            while (iterator.hasNext()) {
                int key = Integer.parseInt(iterator.next().toString());
                String value = numPINHMap.get(key);

                System.out.println("Account number: " + key + " Related Pin: " + value);
            }
                
            SavingAccount acct1 = new SavingAccount("Bobert", accountNum1, numPINHMap.get(accountNum1), 1.2);
            ChequingAccount acct2 = new ChequingAccount("Robert", accountNum2, numPINHMap.get(accountNum2), 500.25);

           
            acct1.Deposit(numPINHMap.get(accountNum1), 100);
            acct2.Deposit(numPINHMap.get(accountNum2), 100);

            printthis = acct1.toString();
            System.out.println(printthis);
            printthis = acct2.toString();
            System.out.println(printthis);
    
            acct1.Withdraw(numPINHMap.get(accountNum1), 50);
            acct1.Withdraw(numPINHMap.get(accountNum1), 150);
            acct1.Withdraw(numPINHMap.get(accountNum2), 50);

            printthis = acct1.toString();
            System.out.println(printthis);
            printthis = acct2.toString();
            System.out.println(printthis);
     
            if (acct1.equals(acct2)) System.out.println("\nThis is the same account\n");

    }

}
