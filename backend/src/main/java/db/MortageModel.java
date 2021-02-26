package db;

// used just for serializing purposes
public class MortageModel {
    public String name;
    public int years;
    public  double loanSize;
    public double interest;
    public double monthlyPayment;

    public MortageModel(String name, int years, double loanSize, double interest, double monthlyPayment) {
        this.name = name;
        this.years = years;
        this.loanSize = loanSize;
        this.interest = interest;
        this.monthlyPayment = monthlyPayment;
    }
}
