package util;

import static util.MathHelpers.raiseToPower;
import static util.MathHelpers.nthRoot;

public class Mortage {
    public String name;
    public int years;
    public  double loanSize;
    public int payments;
    public double interest;
    public double monthlyInterest;
    public double monthlyPayment;

    public Mortage(String name, int years, double loanSize, double interest) {
        this.name = name;
        this.years = years;
        this.loanSize = loanSize;
        this.payments = years * 12;
        this.interest = interest;
        this.monthlyInterest = nthRoot(1 + this.interest, 12, 0.00000001) - 1;

        double mi = this.monthlyInterest;
        int p = this.payments;
        this.monthlyPayment = monthlyInterest < 0.000001 ?
                this.loanSize / this.payments :
                loanSize * (mi * raiseToPower((1 + mi), p)/(raiseToPower((1 + mi), p) - 1));
    }
}
