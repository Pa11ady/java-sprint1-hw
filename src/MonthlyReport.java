import java.util.*;

public class MonthlyReport {
    private HashMap<Integer, ArrayList<Item>> detailExpenses;
    private HashMap<Integer, ArrayList<Item>> detailIncomes;

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
        print();
        TreeSet<Integer> months = new TreeSet<>();
        for (Integer key : detailExpenses.keySet()) {
            months.add(key);
        }
        for (Integer key : detailIncomes.keySet()) {
            months.add(key);
        }

        return new ArrayList<Integer>(months);
    }

    public void print() {
        System.out.println("----");
        System.out.println("ex=");
        for (var entry : detailExpenses.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        }
        System.out.println("in=");
        for (var entry : detailIncomes.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        }
        System.out.println("----");
    }
}

/*Вспомогательный локальный класс, можно было бы сделать внутренним.
В задании в тестовых данных целые числа и потому будем использовать int
Хотя реальных задачах это вещественные числа
Для упрощения не буду писать геттеры и сеттеры это чисто как структура в Си
Конструкто для удобства
*/
class Item implements Comparable<Item>{
    String name;
    int amount;

    public Item(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String toString() {
        return "\t" + name + "\t" + amount;
    }

    @Override
    public int compareTo(Item o){
        return amount - o.amount;
    }
}

