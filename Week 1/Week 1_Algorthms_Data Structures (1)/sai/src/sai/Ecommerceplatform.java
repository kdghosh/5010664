package sai;
import java.util.Arrays;

class Products {
    private String productId;
    private String productName;
    private String category;

    public Products(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
}

public class Ecommerceplatform 
{
    public static Products linearSearch(Products[] products, String productName) 
    {
        for (Products product : products)
        {
            if (product.getProductName().equals(productName)) 
            {
                return product;
            }
        }
        return null;
    }
    public static Products binarySearch(Products[] products, String productName) {
        int left = 0;
        int right = products.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductName().compareTo(productName);
            
            if (comparison == 0) {
                return products[mid];
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Products[] products = {
            new Products("P001", "Laptop", "Electronics"),
            new Products("P002", "Smartphone", "Electronics"),
            new Products("P003", "Tablet", "Electronics"),
            new Products("P004", "Monitor", "Electronics")
        };

        // Sort products by name for binary search
        Arrays.sort(products, (p1, p2) -> p1.getProductName().compareTo(p2.getProductName()));

        // Linear search test
        Products result = linearSearch(products, "Smartphone");
        System.out.println(result != null ? "Found: " + result.getProductName() : "Not Found");

        // Binary search test
        result = binarySearch(products, "Tablet");
        System.out.println(result != null ? "Found: " + result.getProductName() : "Not Found");
    }
}

