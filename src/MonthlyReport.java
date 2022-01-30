import java.util.ArrayList;
import java.util.HashMap;

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
class Item {
    String name;
    int amount;

    public Item(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    /*@Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }*/
    public String toString() {return name + "\t" + amount;}
}

