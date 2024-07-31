package sai;


import java.util.HashMap;
import java.util.Map;

 class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters for each field
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}


public class InventoryManagementSystem {
    private Map<String, Product> inventory;

    public InventoryManagementSystem() {
        this.inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public void updateProduct(String productId, int quantity, double price) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setQuantity(quantity);
            product.setPrice(price);
        }
    }

    public void deleteProduct(String productId) {
        inventory.remove(productId);
    }

    public Product getProduct(String productId) {
        return inventory.get(productId);
    }


    private static void displayProduct(InventoryManagementSystem ims, String productId) {
        Product product = ims.getProduct(productId);
        if (product != null) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println("Price: " + product.getPrice());
            System.out.println("----------------------------");
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
}
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        // Adding products to the inventory
        ims.addProduct(new Product("P001", "Laptop", 10, 999.99));
        ims.addProduct(new Product("P002", "Smartphone", 25, 499.99));
        ims.addProduct(new Product("P003", "Tablet", 15, 299.99));
        ims.addProduct(new Product("P004", "Monitor", 20, 199.99));

        // Displaying products
        displayProduct(ims, "P001");
        displayProduct(ims, "P002");

        // Updating a product's quantity and price
        ims.updateProduct("P001", 8, 979.99);
        ims.updateProduct("P003", 10, 279.99);

        // Displaying products after update
        displayProduct(ims, "P001");
        displayProduct(ims, "P003");

        // Deleting a product from the inventory
        ims.deleteProduct("P004");

        // Trying to display a deleted product
        displayProduct(ims, "P004");
    }
}
    


