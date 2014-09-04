/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package a4;

/**
 *
 * @author Ryan
 */
public class ChequingAccount extends Account{
    double cashLimit;

    public ChequingAccount (String newName, int newNum, String newPin, double cashLimit) {
            super(newName, newNum, newPin);
            this.cashLimit = cashLimit;
    }

    public double getRate () {
        return this.cashLimit;
    }

    @Override
    public String toString () {
        return (super.toString() + " with an cash limit of " + this.cashLimit);
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
