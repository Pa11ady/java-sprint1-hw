import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

enum Command {
    EXIT,
    READ_MONTH_REPORTS,
    READ_YEAR_REPORT,
    CHECK_REPORTS,
    PRINT_MONTHLY_REPORTS,
    PRINT_YEAR_REPORT
}

public class Main {
    public static  final String DIRECTORY = "resources";
    public static final String YEAR_FILE_NAME = "y.2021";
    public static final String MONTH_FILE_NAME = "m.2021";
    public static final int MONTH_COUNT = 3;
    //Константа только ссылка, но массив к сожалению не константный
    public static final String[] MONTH = {
            "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
            "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = null;
        YearlyReport yearlyReport = null;

        while (true) {
            printMenu();
            Command command =  inputCommandOrNull(scanner);
            if (command == null) {
                System.out.println("Извините, такой команды нет.");
                continue;
            }
            switch (command) {
                case EXIT:
                    System.out.println(command);
                    scanner.close();
                    return;
                case READ_MONTH_REPORTS:
                    System.out.println(command);
                    for (int i = 1; i <= MONTH_COUNT; i++) {
                        String text = readFileContentsOrNull(GetMonthPath(i));
                        if (text != null) {
                            String[][] monthTable = splitText(text, ",");
                            monthlyReport = createMonthlyReport(monthTable, i);
                        }
                    }
                    break;
                case READ_YEAR_REPORT:
                    System.out.println(command);
                    String path = DIRECTORY + File.separator + YEAR_FILE_NAME + ".csv";
                    String text = readFileContentsOrNull(path);
                    if (text != null) {
                        String[][] yearTable = splitText(text, ",");
                        String nameYear = YEAR_FILE_NAME.replaceFirst("y.","");
                        yearlyReport = createYearlyReport(yearTable, nameYear);
                        yearlyReport.print();
                        System.out.println("======");
                    }
                    break;
                case CHECK_REPORTS:
                    System.out.println(command);
                    if  (monthlyReport != null && yearlyReport != null) {

                    } else {
                        System.out.println("Считайте, пожалуйста, отчёты");
                    }
                    break;
                case PRINT_MONTHLY_REPORTS:
                    System.out.println(command);
                    if  (monthlyReport != null) {

                    } else {
                        System.out.println("Считайте, пожалуйста, месячные отчеты");
                    }
                    break;
                case PRINT_YEAR_REPORT:
                    System.out.println(command);
                    if  (yearlyReport != null) {

                    } else {
                        System.out.println("Считайте, пожалуйста, годовой отчёт");
                    }
                    break;
            }
        }
    }

    static void printMenu() {
        System.out.println("================================================");
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
        System.out.println("================================================");
    }

    static Command inputCommandOrNull(Scanner scanner) {
        if(!scanner.hasNextInt()) {
            scanner.nextLine();
            return null;
        }
        int value = scanner.nextInt();
        if (0 <= value && value <= 5) {
            return Command.values()[value];
        }
        return null;
    }

    static String GetMonthPath(int index) {
        if (index < 1 || index > 12) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DIRECTORY).append(File.separator).append(MONTH_FILE_NAME);
        if (index <= 9) {
            stringBuilder.append("0");
        }
        stringBuilder.append(index);
        stringBuilder.append(".csv");
        return stringBuilder.toString();
    }

    static String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл " + path);
            return null;
        }
    }

    static String[][] splitText(String text, String regex) {
        String[] textLines = text.split("\n");
        int rowsCount = textLines.length;
        String[][] table = new String[rowsCount][];
        for (int i = 0; i < rowsCount; i++) {
            String[] cols = textLines[i].split(regex);
            table[i] = cols;
        }
        return table;
    }

    static  MonthlyReport createMonthlyReport(String[][] monthTable, int month) {
        MonthlyReport monthlyReport = new MonthlyReport();
        //0-я чтрока отладочная с заголовками
        for (int i = 1; i < monthTable.length; i++) {
            String itemName = monthTable[i][0];
            boolean isExpense = Boolean.parseBoolean(monthTable[i][1]);
            int quantity = Integer.parseInt(monthTable[i][2]);
            int sumOfOne = Integer.parseInt(monthTable[i][3]);
            if (isExpense) {
                monthlyReport.addDetailExpenses(month, itemName, sumOfOne, quantity);
            } else {
                monthlyReport.addDetailIncomes(month, itemName, sumOfOne, quantity);
            }
        }

        monthlyReport.print();
        return monthlyReport;
    }

    static YearlyReport createYearlyReport(String[][] yearTable, String nameYear) {
        YearlyReport yearlyReport = new YearlyReport(nameYear);
        //0-я чтрока отладочная с заголовками
        for (int i = 1; i < yearTable.length; i++) {
            int month = Integer.parseInt(yearTable[i][0]);
            int amount = Integer.parseInt(yearTable[i][1]);
            boolean isExpense = Boolean.parseBoolean(yearTable[i][2]);
            if (isExpense) {
                yearlyReport.addExpensesByMonths(month, amount);
            } else {
                yearlyReport.addIncomesByMonths(month, amount);
            }
        }
        return yearlyReport;
    }
}

