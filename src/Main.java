import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static void main(String[] args) {
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
                    System.out.println(command);
                    scanner.close();
                    return;
                case READ_MONTH_REPORTS:
                    System.out.println(command);
                    for (int i = 1; i <= MONTH_COUNT; i++) {
                        String text = readFileContentsOrNull(GetMonthPath(i));
                        System.out.println(text);
                        System.out.println("======");
                    }
                    break;
                case READ_YEAR_REPORT:
                    System.out.println(command);
                    String path = DIRECTORY + File.separator + YEAR_FILE_NAME + ".csv";
                    String text = readFileContentsOrNull(path);
                    System.out.println(text);
                    System.out.println("======");
                    break;
                case CHECK_REPORTS:
                    System.out.println(command);
                    break;
                case PRINT_MONTHLY_REPORTS:
                    System.out.println(command);
                    break;
                case PRINT_YEAR_REPORT:
                    System.out.println(command);
                    break;
            }
        }
    }

    static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
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

}

