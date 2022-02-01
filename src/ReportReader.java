import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReportReader {
    public static  final String DIRECTORY = "resources";
    public static final String YEAR_FILE_NAME = "y.2021";
    public static final String MONTH_FILE_NAME = "m.2021";
    public static final int MONTH_COUNT = 3;

    public static MonthlyReport readMonthlyReportFromFile() {
        MonthlyReport  monthlyReport = null;
        for (int i = 1; i <= MONTH_COUNT; i++) {
            String text = readFileContentsOrNull(getMonthPath(i));
            if (text != null) {
                String[][] monthTable = splitText(text);
                monthlyReport = createMonthlyReport(monthlyReport, monthTable, i);
            }
        }
        return monthlyReport;
    }

    public static YearlyReport readYearlyReportFromFile() {
        YearlyReport yearlyReport = null;
        String path = DIRECTORY + File.separator + YEAR_FILE_NAME + ".csv";
        String text = readFileContentsOrNull(path);
        if (text != null) {
            String[][] yearTable = splitText(text);
            String nameYear = YEAR_FILE_NAME.replaceFirst("y.","");
            yearlyReport = createYearlyReport(yearTable, nameYear);
        }
        return yearlyReport;
    }

    private static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл " + path);
            return null;
        }
    }

    private static String[][] splitText(String text) {
        String[] textLines = text.split("\n");
        int rowsCount = textLines.length;
        String[][] table = new String[rowsCount][];
        for (int i = 0; i < rowsCount; i++) {
            String[] cols = textLines[i].split(",");
            table[i] = cols;
        }
        return table;
    }

    private static  MonthlyReport createMonthlyReport(MonthlyReport monthlyReport,
                                                      String[][] monthTable, int month) {
        if (monthlyReport == null) {
            monthlyReport = new MonthlyReport();
        }
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
        return monthlyReport;
    }

    private static YearlyReport createYearlyReport(String[][] yearTable, String nameYear) {
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

    private static String getMonthPath(int index) {
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
}
