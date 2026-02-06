import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Current conversion rates:");
        Bank.printRates();
        System.out.print("How many users to generate? ");
        int n = scanner.nextInt();
        scanner.nextLine();

      
        User[] users    = Generator.generateUsers(n);
        User[] original = Arrays.copyOf(users, users.length);

   
        Comparator<User> idComparator    = Comparator.comparing(User::getId);
        Comparator<User> amountComparator = Comparator.comparingDouble(
            User::getTotalAmountInCommonCurrency
        );

   
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. List users");
            System.out.println("2. Sort by ID");
            System.out.println("3. Sort by Total Amount");
            System.out.println("4. Reset order");
            System.out.println("5. Lookup user by ID");
            System.out.println("6. Change conversion rate");
            System.out.println("7. Print conversion rates");
            System.out.println("8. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    for (User u : users) {
                        System.out.println(u);
                    }
                    break;
                case 2:
                    long startTime = System.nanoTime();
                    SortingSystem.hybridSort(users, idComparator);
                    long endTime = System.nanoTime();
                    System.out.printf("Sorted by ID in %.3f ms%n",
                        (endTime - startTime) / 1_000_000.0);
                    break;
                case 3:
                    startTime = System.nanoTime();
                    SortingSystem.hybridSort(users, amountComparator);
                    endTime = System.nanoTime();
                    System.out.printf("Sorted by Total in %.3f ms%n",
                        (endTime - startTime) / 1_000_000.0);
                    break;
                case 4:
                    users = Arrays.copyOf(original, original.length);
                    System.out.println("Order reset.");
                    break;
                case 5:
                    System.out.print("Enter ID to lookup: ");
                    String id = scanner.nextLine();
                    boolean found = false;
                    for (User u : users) {
                        if (u.getId().equals(id)) {
                            System.out.println("Found: " + u);
                            System.out.println("Accounts:");
                            for (Account a : u.getAccounts()) {
                                System.out.printf("  %c %.4f → common: %.4f%n",
                                    a.getCurrency(), a.getAmount(), a.toCommon());
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("User with ID " + id + " not found.");
                    }
                    break;
                case 6:
                    System.out.print("Currency (A–D): ");
                    char c = scanner.nextLine().charAt(0);
                    System.out.print("New rate: ");
                    double r = scanner.nextDouble();
                    scanner.nextLine();
                    Bank.setRate(c, r);
                    System.out.println("Rate updated.");
                    break;
                case 7:
                    Bank.printRates();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
