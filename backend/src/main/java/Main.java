import db.MortageModel;
import util.Mortage;
import db.MortageDatabaseHandler;
import static spark.Spark.*;
import spark.Filter;
import com.google.gson.Gson;

import java.util.List;

// REST API styled application
// I use Java Spark as my web framework and Gson for serialization
// I'm first time user of both of them so excuse my bad practices

public class Main {
    public static void main(String[] args) {

        // allowing CORS
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "*");
        });

        // specifying content type
        before((request, response) -> response.type("application/json"));

        MortageDatabaseHandler mortageDBhandler = new MortageDatabaseHandler();

        // endpoint that returns info about the loan in json form and saves loan into the db
        post("/api/mortagecalculator", (req, res) -> {
                int years;
                double loanSize;
                double interest;
                String name = req.queryParams("name");
                try {
                    years = Integer.parseInt(req.queryParams("years"));
                    loanSize = Double.parseDouble(req.queryParams("loanSize"));
                    interest = Double.parseDouble(req.queryParams("interest"));
                } catch (Exception e) {
                    res.status(400);
                    return "Error: request must contain parameters years, loanSize and interest and they must in numeric form";
                }
                Gson gson = new Gson(); // used to convert object to json
                Mortage mortage = new Mortage(name, years, loanSize, interest);
                mortageDBhandler.addMoratge(mortage);
                return gson.toJson(mortage);
            }
        );
        // endpoint that returns all mortages in the database in json form
        get("/api/mortages", (req, res) -> {
            List<MortageModel> mortages = mortageDBhandler.getAllMortages();
            Gson gson = new Gson();
            return gson.toJson(mortages);
            }
        );
    }
}