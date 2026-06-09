import java.util.Scanner;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Phone number input
        System.out.print("Enter customer phone number: ");
        String phone = sc.next();

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Bill bill = new Bill(n);

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter item details:");

            System.out.print("Name: ");
            String name = sc.next();

            System.out.print("Price: ");
            double price = sc.nextDouble();

            System.out.print("Quantity: ");
            int qty = sc.nextInt();

            // Auto category
            String category = bill.getCategory(name);
            System.out.println("Auto Category: " + category);

            Item item = new Item(name, price, qty, category);
            bill.addItem(item);
        }

        bill.calculateTotal();
        bill.printBill();

        // Save file silently
        bill.saveToFile(phone);

        sc.close();
    }
}