import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    private enum Command {
        EXIT,
        READ_MONTH_REPORTS,
        READ_YEAR_REPORT,
        CHECK_REPORTS,
        PRINT_MONTHLY_REPORTS,
        PRINT_YEAR_REPORT
    }

    public static void main(String[] args) {
        MonthlyReport monthlyReport = null;
        YearlyReport yearlyReport = null;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            Command command =  inputCommandOrNull(scanner);
            if (command == null) {
                System.out.println("Извините, такой команды нет.");
                continue;
            }
            switch (command) {
                case EXIT:
                    scanner.close();
                    return;
                case READ_MONTH_REPORTS:
                    monthlyReport = readMonthReports();
                    break;
                case READ_YEAR_REPORT:
                    yearlyReport = readYearReport();
                    break;
                case CHECK_REPORTS:
                    checkReports(yearlyReport, monthlyReport);
                    break;
                case PRINT_MONTHLY_REPORTS:
                    printMonthlyReports(monthlyReport);
                    break;
                case PRINT_YEAR_REPORT:
                    printYearReport(yearlyReport);
                    break;
            }
        }
    }

    private static void printMenu() {
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

    private static Command inputCommandOrNull(Scanner scanner) {
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

    private static MonthlyReport readMonthReports() {
        return ReportReader.readMonthlyReportFromFile();
    }

    private static YearlyReport readYearReport() {
        return ReportReader.readYearlyReportFromFile();
    }

    private static void checkReports(YearlyReport yearlyReport, MonthlyReport monthlyReport ) {
        if  (monthlyReport == null || yearlyReport == null) {
            System.out.println("Считайте, пожалуйста, отчёты");
            return;
        }
        boolean isCorrect = true;
        //на тот случай если в годовом и месячном отчётах разные месяцы
        TreeSet<Integer> months = new TreeSet<>();
        months.addAll(yearlyReport.getListMonths());
        months.addAll(monthlyReport.getListMonths());
        int errorCnt = 0;
        for (int month : months) {
            if (yearlyReport.getTotalExpenseByMonth(month) !=
                    monthlyReport.getTotalExpenseByMonth(month) ||
                    yearlyReport.getTotalIncomeByMonth(month) !=
                            monthlyReport.getTotalIncomeByMonth(month)) {
                errorCnt++;
                isCorrect = false;
                System.out.println("Ошибка " + errorCnt + ": " + MonthConvector.getMonthName(month));
            }
        }

        if (isCorrect) {
            System.out.println("Сверка данных успешно завершена!");
        }
    }

    private static void printMonthlyReports(MonthlyReport monthlyReport) {
        if (monthlyReport != null) {
            monthlyReport.printMonthsStatistics();
        } else {
            System.out.println("Считайте, пожалуйста, месячные отчеты");
        }
    }

    private static void printYearReport(YearlyReport yearlyReport) {
        if  (yearlyReport != null) {
            yearlyReport.printYearStatistics();
        } else {
            System.out.println("Считайте, пожалуйста, годовой отчёт");
        }
    }
}

