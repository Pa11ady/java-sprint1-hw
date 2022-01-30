import java.util.HashMap;

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
