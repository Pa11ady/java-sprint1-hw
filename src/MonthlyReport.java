import java.util.*;

public class MonthlyReport {
    private static class Item implements Comparable<Item>{
        String name;
        int amount;

        public Item(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "\t" + name + "\t" + amount;
        }

        @Override
        public int compareTo(Item o){
            return amount - o.amount;
        }
    }
    private final HashMap<Integer, ArrayList<Item>> detailExpenses;
    private final HashMap<Integer, ArrayList<Item>> detailIncomes;

    public MonthlyReport() {
        detailExpenses = new HashMap<>();
        detailIncomes = new HashMap<>();
    }

    public void addDetailExpenses(int month, String itemName, int sumOfOne, int quantity) {
        //detailExpenses.put(month, income);
        int amount = sumOfOne * quantity;
        ArrayList<Item> items = detailExpenses.get(month);
        if (items == null) {
            items = new ArrayList<>();
            detailExpenses.put(month, items);
        }
        items.add(new Item(itemName, amount));
    }

    public void addDetailIncomes(int month, String itemName, int sumOfOne, int quantity) {
        int amount = sumOfOne * quantity;
        ArrayList<Item> items = detailIncomes.get(month);
        if (items == null) {
            items = new ArrayList<>();
            detailIncomes.put(month, items);
        }
        items.add(new Item(itemName, amount));
    }

    public void printMonthsStatistics() {
        ArrayList<Integer> months = getListMonths();
        for (int month : months) {
            Item maxItemIncome = getMaxItemIncome(month);
            Item maxItemExpense = getMaxItemExpense(month);
            System.out.println(MonthConvector.getMonthName(month));
            System.out.print("  Самый прибыльный товар:");
            if (maxItemIncome != null) {
                System.out.println(maxItemIncome);
            } else {
                System.out.println("\tнет");
            }
            System.out.print("  Самая большая трата:");
            if (maxItemExpense != null) {
                System.out.println(maxItemExpense);
            } else {
                System.out.println("\tнет");
            }
        }
    }

    private Item getMaxItemExpense(int month) {
        ArrayList<Item> items = detailExpenses.get(month);
        if (items == null) {
            return  null;
        }
        return Collections.max(items);
    }

    private Item getMaxItemIncome(int month) {
        ArrayList<Item>  items = detailIncomes.get(month);
        if (items == null) {
            return  null;
        }
        return Collections.max(items);
    }

    public ArrayList<Integer> getListMonths() {
        TreeSet<Integer> months = new TreeSet<>();
        months.addAll(detailExpenses.keySet());
        months.addAll(detailIncomes.keySet());
        return new ArrayList<>(months);
    }

    public int getTotalExpenseByMonth(int month) {
        ArrayList<Item> items = detailExpenses.get(month);
        return  sum(items);
    }

    public int getTotalIncomeByMonth(int month) {
        ArrayList<Item> items = detailIncomes.get(month);
        return  sum(items);
    }
    private int sum(ArrayList<Item> list) {
        if (list == null) return 0;
        int sum = 0;
        for (var el : list) {
            sum += el.amount;
        }
        return sum;
    }
}



