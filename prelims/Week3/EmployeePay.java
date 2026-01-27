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
                """





                        ---------------------------
                                  Summary
                        ---------------------------
                        First name:       %s
                        Middle name:      %s
                        Last name:        %s
                        Employee number:  %d
                        Hourly rate:      %.2f
                        Hours worked:     %.2f
                        Overtime pay:     %.2f
                        Total deductions: %.2f

                        ===========================
                        %s
                        Your net pay is %5.2f
                        ===========================
                        """,
                firstName, midName, lastName, employeeNum, hourlyRate, hoursWorked, overtimePay, totalDeductions,
                fullName, netPay);
    }
}