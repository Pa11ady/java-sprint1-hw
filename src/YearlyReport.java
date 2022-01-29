import java.util.HashMap;

public class YearlyReport {
    String name;
    private HashMap<String, Integer> expensesByMonths;
    private HashMap<String, Integer> incomesByMonths;

    public YearlyReport(String name) {
        this.name = name;
        expensesByMonths = new HashMap<>();
        incomesByMonths = new HashMap<>();
    }

    public void addExpensesByMonths(String nameMonth, int income) {
        expensesByMonths.put(nameMonth, income);
    }

    public void addIncomesByMonths(String nameMonth, int income) {
        incomesByMonths.put(nameMonth, income);
    }

    public void print() {
        System.out.println("----");
        System.out.println(name);
        System.out.println("ex=");
        for (var entry : expensesByMonths.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("in=");
        for (var entry : incomesByMonths.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("----");
    }
}
