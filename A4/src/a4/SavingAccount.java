/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package a4;

/**
 *
 * @author Ryan
 */
public class SavingAccount extends Account {
    private double intrestRate;

    public SavingAccount (String newName, int newNum, String newPin, double intrestRate) {
            super(newName, newNum, newPin);
            this.intrestRate = intrestRate;
    }

    public double getRate () {
        return this.intrestRate;
    }

    @Override
    public String toString () {
        return (super.toString() + " with an interest rate of " + this.intrestRate);
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) return false;
        else if (getClass( ) != obj.getClass()) return false;
        else {
            Account otherAccount = (Account)obj;
            return (getAccountNum()==otherAccount.getAccountNum());
        }
    }
}
