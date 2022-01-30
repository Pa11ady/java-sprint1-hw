import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class YearlyReport {
    String name;
    private HashMap<Integer, Integer> expensesByMonths;
    private HashMap<Integer, Integer> incomesByMonths;

    public YearlyReport(String name) {
        this.name = name;
        expensesByMonths = new HashMap<>();
        incomesByMonths = new HashMap<>();
    }

    public void addExpensesByMonths(int month, int expense) {
        expensesByMonths.put(month, expense);
    }

    public void addIncomesByMonths(int month, int income) {
        incomesByMonths.put(month, income);
    }

    public ArrayList<Integer> getListMonths() {
        TreeSet<Integer> months = new TreeSet<>();
        for (Integer key : expensesByMonths.keySet()) {
            months.add(key);
        }
        for (Integer key : incomesByMonths.keySet()) {
            months.add(key);
        }
        return  new ArrayList<Integer>(months);
    }

    public void printYearStatistics() {
        System.out.println("Год " + name);
        ArrayList<Integer> months = getListMonths();
        for (int month : months) {
            
        }
    }
}
