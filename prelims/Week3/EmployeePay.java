import java.util.Scanner;

public class EmployeePay {
    public static void main(String[] args) {
        String firstName, midName, lastName, fullName;
        int employeeNum;
        double hoursWorked, hourlyRate, overtimePay, totalDeductions;

        firstName = inputString("Input your first name: ");
        midName = inputString("Input your middle name: ");
        lastName = inputString("Input your last name: ");
        fullName = firstName + " " + midName + " " + lastName;

        employeeNum = inputInt("Input employee number: ");

        hourlyRate = inputDouble("Input hourly rate: ");
        hoursWorked = inputDouble("Input hours worked: ");
        overtimePay = inputDouble("Input overtime pay: ");
        totalDeductions = inputDouble("Input total deductions: ");

        displaySummary(firstName, midName, lastName, employeeNum, hourlyRate, hoursWorked, overtimePay, totalDeductions,
                fullName, computeSalary(hoursWorked, hourlyRate, overtimePay, totalDeductions));

        input.close();
    }

    public static Scanner input = new Scanner(System.in);

    public static String inputString(String message) {
        String s;
        while (true) {
            System.out.print(message);
            if (input.hasNextLine()) {
                s = input.nextLine().trim();
                if (!s.isEmpty()) {
                    break;
                }
            }
            System.out.println("\nInvalid Input! Retry\n");
        }
        return s;
    }

    public static int inputInt(String message) {
        int n;
        while (true) {
            System.out.print(message);
            if (input.hasNextInt()) {
                n = input.nextInt();
                input.nextLine();
                if (n >= 0)
                    break;
                else {
                    System.out.println("\nInvalid Input! Input must be positive\n");
                }
            } else {
                System.out.println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
        return n;
    }

    public static double inputDouble(String message) {
        double d;
        while (true) {
            System.out.print(message);
            if (input.hasNextDouble()) {
                d = input.nextDouble();
                input.nextLine();
                if (d >= 0)
                    break;
                else {
                    System.out.println("\nInvalid Input! Input must be positive\n");
                }
            } else {
                System.out.println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
        return d;
    }

    public static double computeSalary(double hoursWorked, double hourlyRate, double overtimePay,
            double totalDeductions) {
        return ((hourlyRate * hoursWorked) + overtimePay) - totalDeductions;
    }

    public static void displaySummary(String firstName, String midName, String lastName, int employeeNum,
            double hourlyRate, double hoursWorked, double overtimePay, double totalDeductions, String fullName,
            double netPay) {
        System.out.printf(
                "\n\n\n\n\n\n---------------------------\n%10sSummary%10s\n---------------------------\nFirst name: %-5s\nMiddle name: %-5s\nLast name: %-5s\nEmployee number: %-5d\nHourly rate: %-5.2f\nHours worked: %-5.2f\nOvertime pay: %-5.2f\nTotal deductions: %-5.2f\n\n===========================\n%s\nYour net pay is %5.2f\n===========================\n",
                "", "", firstName, midName, lastName, employeeNum, hourlyRate, hoursWorked, overtimePay,
                totalDeductions, fullName, netPay);
    }
}