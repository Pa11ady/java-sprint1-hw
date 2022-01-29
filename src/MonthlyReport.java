import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    private HashMap<String, ArrayList<Integer>> expensesByItems;
    private HashMap<String, ArrayList<Integer>> incomesByItems;

    public MonthlyReport() {
        expensesByItems = new HashMap<>();
        incomesByItems = new HashMap<>();
    }
}
