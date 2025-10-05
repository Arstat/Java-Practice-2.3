import java.util.*;
import java.util.stream.*;

class Product {
    private String name;
    private double price;
    private String category;
    
    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getCategory() {
        return category;
    }
    
    @Override
    public String toString() {
        return String.format("Product{name='%s', price=$%.2f, category='%s'}", 
                           name, price, category);
    }
}

public class C_ProductStreamOperations {
    public static void main(String[] args) {
        // Create a large dataset of products
        List<Product> products = Arrays.asList(
            // Electronics
            new Product("Laptop Pro", 1299.99, "Electronics"),
            new Product("Smartphone X", 899.99, "Electronics"),
            new Product("Wireless Mouse", 29.99, "Electronics"),
            new Product("4K Monitor", 449.99, "Electronics"),
            new Product("Bluetooth Speaker", 79.99, "Electronics"),
            new Product("Gaming Keyboard", 129.99, "Electronics"),
            
            // Clothing
            new Product("Leather Jacket", 199.99, "Clothing"),
            new Product("Running Shoes", 89.99, "Clothing"),
            new Product("Denim Jeans", 59.99, "Clothing"),
            new Product("Cotton T-Shirt", 19.99, "Clothing"),
            new Product("Winter Coat", 249.99, "Clothing"),
            new Product("Sneakers", 74.99, "Clothing"),
            
            // Books
            new Product("Java Programming Guide", 49.99, "Books"),
            new Product("Data Structures Handbook", 39.99, "Books"),
            new Product("Web Development Bible", 44.99, "Books"),
            new Product("Algorithm Design", 54.99, "Books"),
            new Product("Python Mastery", 34.99, "Books"),
            
            // Home & Kitchen
            new Product("Coffee Maker", 89.99, "Home & Kitchen"),
            new Product("Blender Pro", 129.99, "Home & Kitchen"),
            new Product("Air Fryer", 149.99, "Home & Kitchen"),
            new Product("Cookware Set", 199.99, "Home & Kitchen"),
            new Product("Toaster", 39.99, "Home & Kitchen"),
            
            // Sports
            new Product("Yoga Mat", 29.99, "Sports"),
            new Product("Dumbbells Set", 149.99, "Sports"),
            new Product("Tennis Racket", 89.99, "Sports"),
            new Product("Basketball", 34.99, "Sports"),
            new Product("Treadmill", 799.99, "Sports")
        );
        
        System.out.println("Total Products: " + products.size());
        System.out.println("\n" + "=".repeat(80));
        
        // 1. Group products by category
        System.out.println("\n--- PRODUCTS GROUPED BY CATEGORY ---\n");
        
        Map<String, List<Product>> productsByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));
        
        productsByCategory.forEach((category, productList) -> {
            System.out.println("Category: " + category + " (" + productList.size() + " products)");
            productList.forEach(p -> System.out.println("  - " + p));
            System.out.println();
        });
        
        // 2. Find the most expensive product in each category
        System.out.println("=".repeat(80));
        System.out.println("\n--- MOST EXPENSIVE PRODUCT IN EACH CATEGORY ---\n");
        
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.maxBy(Comparator.comparing(Product::getPrice))
            ));
        
        mostExpensiveByCategory.forEach((category, product) -> {
            product.ifPresent(p -> 
                System.out.printf("%-20s: %s - $%.2f%n", 
                    category, p.getName(), p.getPrice())
            );
        });
        
        // Alternative: Without Optional wrapper
        System.out.println("\n--- MOST EXPENSIVE PRODUCTS (Alternative Method) ---\n");
        
        Map<String, Product> maxPriceProducts = products.stream()
            .collect(Collectors.toMap(
                Product::getCategory,
                p -> p,
                (p1, p2) -> p1.getPrice() > p2.getPrice() ? p1 : p2
            ));
        
        maxPriceProducts.forEach((category, product) -> 
            System.out.printf("%-20s: %s - $%.2f%n", 
                category, product.getName(), product.getPrice())
        );
        
        // 3. Calculate the average price of all products
        System.out.println("\n" + "=".repeat(80));
        System.out.println("\n--- PRICE STATISTICS ---\n");
        
        double averagePrice = products.stream()
            .collect(Collectors.averagingDouble(Product::getPrice));
        
        System.out.printf("Average Price of All Products: $%.2f%n", averagePrice);
        
        // Additional: Average price by category
        System.out.println("\n--- AVERAGE PRICE BY CATEGORY ---\n");
        
        Map<String, Double> avgPriceByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.averagingDouble(Product::getPrice)
            ));
        
        avgPriceByCategory.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEach(entry -> 
                System.out.printf("%-20s: $%.2f%n", entry.getKey(), entry.getValue())
            );
        
        // Additional statistics using DoubleSummaryStatistics
        System.out.println("\n--- COMPREHENSIVE PRICE STATISTICS ---\n");
        
        DoubleSummaryStatistics priceStats = products.stream()
            .collect(Collectors.summarizingDouble(Product::getPrice));
        
        System.out.printf("Total Products: %d%n", priceStats.getCount());
        System.out.printf("Total Value: $%.2f%n", priceStats.getSum());
        System.out.printf("Average Price: $%.2f%n", priceStats.getAverage());
        System.out.printf("Minimum Price: $%.2f%n", priceStats.getMin());
        System.out.printf("Maximum Price: $%.2f%n", priceStats.getMax());
        
        // Bonus: Find products above average price
        System.out.println("\n--- PRODUCTS ABOVE AVERAGE PRICE ---\n");
        
        List<Product> aboveAverage = products.stream()
            .filter(p -> p.getPrice() > averagePrice)
            .sorted(Comparator.comparing(Product::getPrice).reversed())
            .collect(Collectors.toList());
        
        System.out.printf("Products above average ($%.2f): %d%n%n", 
                         averagePrice, aboveAverage.size());
        aboveAverage.forEach(System.out::println);
    }
}