class Item {
    String name;
    double price;
    int quantity;
    String category;

    Item(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    double getTotal() {
        return price * quantity;
    }
}