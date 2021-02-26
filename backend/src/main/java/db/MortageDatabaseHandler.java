package db;

import util.Mortage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// I used SQLite for the db, I thought that it was suitable for small demo app like this
// queries are done in raw sql and that makes the code look a bit messy
// but I thought that an ORM would be an overkill for app this small

public class MortageDatabaseHandler {
    private Connection connection = null;
    private Statement statement;
    private int currentId = 0; // I had trouble getting the auto increment to work so decided to keep track of indices manually

    public MortageDatabaseHandler() {

        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            this.statement = connection.createStatement();
            this.statement.setQueryTimeout(20);
            this.statement.executeUpdate("drop table if exists mortage");
            this.statement.executeUpdate("create table mortage (id integer, name string, loansize double, years integer, interest double, monthlypayment double)");
        }
        catch(SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    // adds an mortage to the database and returns nothing
    public void addMoratge(Mortage m) {
        this.currentId++;
        String query = "insert into mortage values(" + this.currentId + "," + "'" + m.name + "'" + "," + m.loanSize + "," + m.years + "," + m.interest + "," + m.monthlyPayment + ")";
        try {
            this.statement.executeUpdate(query);
        }
        catch (SQLException e) {
            System.err.println(e);
        }
    }

    // returns a list containing all mortages in the db
    public List<MortageModel> getAllMortages() {
        try {
            ResultSet rs = statement.executeQuery("select * from mortage");
            List<MortageModel> mortages = new ArrayList<>();

            while(rs.next()) {
                int years = rs.getInt("years");
                String name = rs.getString("name");
                double loanSize = rs.getDouble("loansize");
                double interest = rs.getDouble("interest");
                double monthlyPayment = rs.getDouble("monthlypayment");
                mortages.add(new MortageModel(name, years, loanSize, interest, monthlyPayment));
            }
            return mortages;
        }
        catch (SQLException e) {
            System.err.println(e);
            return Collections.emptyList();
        }
    }
}