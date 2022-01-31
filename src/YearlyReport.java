import java.util.ArrayList;
import java.util.HashMap;
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
        int countExpenses = 0;
        int countIncomes = 0;
        int totalExpenses = 0;
        int totalIncomes = 0;
        double AverageExpenses = 0;
        double AverageIncomes = 0;
        ArrayList<Integer> months = getListMonths();

        System.out.println("Год " + name);
        for (int month : months) {
            int expense = expensesByMonths.getOrDefault(month, 0);
            int income = incomesByMonths.getOrDefault(month, 0);
            if (expense > 0) {
                countExpenses++;
                totalExpenses += expense;
            }
            if (income > 0) {
                countIncomes++;
                totalIncomes += income;
            }
            int profit = income - expense;
            System.out.println(MonthConvector.getMonthName(month));
            System.out.println("\tПрибыль " + profit);
        }

        if (countExpenses != 0) {
            AverageExpenses = (double) totalExpenses / countExpenses;
        } else {
            AverageExpenses = 0;
        }
        if (countIncomes != 0) {
            AverageIncomes = (double) totalIncomes / countIncomes;
        } else {
            AverageIncomes = 0;
        }

        System.out.println("------------------------------------------------");
        System.out.printf("Средний расход за все месяцы %.2f\n", AverageExpenses);
        System.out.printf("Средний доход за все месяцы %.2f\n", AverageIncomes);
        System.out.println("------------------------------------------------");
    }
}
