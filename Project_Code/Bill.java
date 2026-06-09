import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Bill {
    Item[] items;
    int count;
    double total;
    double tax;

    Bill(int size) {
        items = new Item[size];
        count = 0;
    }

    void addItem(Item item) {
        items[count++] = item;
    }

    // Auto Category Detection
    String getCategory(String name) {
        name = name.toLowerCase();

        if (name.contains("milk") || name.contains("curd") || name.contains("butter")) {
            return "Dairy";
        } else if (name.contains("rice") || name.contains("biscuit") || name.contains("chips")) {
            return "Packaged";
        } else if (name.contains("chocolate") || name.contains("perfume")) {
            return "Luxury";
        } else {
            return "Packaged";
        }
    }

    void calculateTotal() {
        total = 0;
        tax = 0;

        for (int i = 0; i < count; i++) {
            double itemTotal = items[i].getTotal();
            total += itemTotal;

            if (items[i].category.equalsIgnoreCase("Dairy")) {
                tax += 0;
            } else if (items[i].category.equalsIgnoreCase("Packaged")) {
                tax += itemTotal * 0.05;
            } else if (items[i].category.equalsIgnoreCase("Luxury")) {
                tax += itemTotal * 0.18;
            }
        }
    }

    void printBill() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("\nDate & Time: " + now.format(format));
        System.out.println("----- Grocery Bill -----");
        System.out.println("Item\tPrice\tQty\tTotal");

        for (int i = 0; i < count; i++) {
            System.out.println(items[i].name + "\t" + items[i].price + "\t" +
                               items[i].quantity + "\t" + items[i].getTotal());
        }

        System.out.println("\nSubtotal: " + total);
        System.out.println("Tax: " + tax);
        System.out.println("Grand Total: " + (total + tax));
    }

    // Save silently (no print)
    @SuppressWarnings("ConvertToTryWithResources")
    void saveToFile(String phone) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            FileWriter fw = new FileWriter(phone + ".txt");

            fw.write("Customer Phone: " + phone + "\n");
            fw.write("Date & Time: " + now.format(format) + "\n");
            fw.write("----- Grocery Bill -----\n");
            fw.write("Item\tPrice\tQty\tTotal\n");

            for (int i = 0; i < count; i++) {
                fw.write(items[i].name + "\t" + items[i].price + "\t" +
                         items[i].quantity + "\t" + items[i].getTotal() + "\n");
            }

            fw.write("\nSubtotal: " + total);
            fw.write("\nTax: " + tax);
            fw.write("\nGrand Total: " + (total + tax));

            fw.close();

        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }
}