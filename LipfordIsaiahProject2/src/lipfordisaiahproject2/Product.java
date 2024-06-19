/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lipfordisaiahproject2;

/**
 * The Product class represents a product available in the bookstore inventory
 * with attributes such as product ID, title, type (e.g., DVD, Book, CD), price,
 * and quantity in stock. It provides methods to interact with and manipulate product data.
 * 
 * @author Isaiah Lipford
 * @version 1.0
 */
public abstract class Product implements Comparable<Product>
{
    private int productID;
    private String title;
    private String type;
    private double price;
    private int quantityInStock;
    
    /**
     * Constructs a new Product object with the specified product ID, title, type,
     * price, and initial quantity in stock.
     * 
     * @param productID The unique identifier for the product.
     * @param title The title of the product.
     * @param type The type of the product (e.g., DVD, Book, CD).
     * @param price The price of the product.
     * @param quantityInStock The initial quantity of the product in stock.
     */
    public Product(int productID, String title, String type, double price, int quantityInStock) 
    {
        this.productID = productID;
        this.title = title;
        this.type = type;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }
    
    /**
     * Gets the product's unique identifier.
     * 
     * @return The product's unique identifier.
     */
    public int getProductID() 
    {
        return productID;
    }

    /**
     * Gets the title of the product.
     * 
     * @return The title of the product.
     */
    public String getTitle() 
    {
        return title;
    }
    
    /**
     * Gets the type of the product (e.g., DVD, Book, CD).
     * 
     * @return The type of the product.
     */
    public String getType() 
    {
        return type;
    }
    
    /**
     * Gets the price of the product.
     * 
     * @return The price of the product.
     */
    public double getPrice() 
    {
        return price;
    }
    
    /**
     * Gets the quantity of the product in stock.
     * 
     * @return The quantity of the product in stock.
     */
    public int getQuantityInStock() 
    {
        return quantityInStock;
    }

    /**
     * Decreases the stock quantity of the product by the specified quantity.
     * 
     * @param quantity The quantity to decrease from the product's stock.
     */
    public void decreaseStock(int quantity) 
    {
        if (quantityInStock >= quantity) 
        {
            quantityInStock -= quantity;
        } 
        else 
        {
            System.out.println("Insufficient stock.");
        }
    }
    
    /**
     * Sets the product's unique identifier if the new product ID is valid (greater than zero).
     * 
     * @param newProductID The new product ID to set for the product.
     */
    public void setProductID(int newProductID) 
    {
        if (newProductID > 0) 
        {
            productID = newProductID;
        } 
        else 
        {
            System.out.println("Invalid product ID. Please provide a valid ID.");
        }
    }
  
    /**
     * Sets the title of the product if the new title is valid.
     * 
     * @param newTitle The new title to set for the product.
     */
    public void setTitle(String newTitle) {
        if (isValidTitle(newTitle)) {
            title = newTitle;
        } 
        else 
        {
            System.out.println("Invalid title. Please provide a valid title.");
        }
    }
  
  // Helper method to validate member names
    private boolean isValidTitle(String titleToCheck) {
        // For simplicity, we assume any non-empty name is valid.
        return !titleToCheck.trim().isEmpty();

    }
    
    /**
     * Sets the type of the product if the new type is valid.
     * 
     * @param newType The new type to set for the product.
     */
    public void setType(String newType) {
        if (isValidType(newType)) {
            type = newType;
        } 
        else 
        {
            System.out.println("Invalid product type. Please provide a valid type.");
        }
    }
    
    // Helper method to validate product types
    private boolean isValidType(String typeToCheck) 
    {
        return !typeToCheck.trim().isEmpty();
    }
    
    /**
     * Sets the price of the product, ensuring the price is non-negative.
     * 
     * @param newPrice The new price to set for the product.
     */
    public void setPrice(double newPrice) 
    {
        if (newPrice >= 0) 
        {
            price = newPrice;
        } 
        else 
        {
            System.out.println("Invalid product price. Please provide a valid price.");
        }
    }
    
    public void restock(int quantityToAdd) {
        this.quantityInStock += quantityToAdd;
    }
    
    /**
     *
     * @param otherProduct
     * @return
     */
    @Override
    public int compareTo(Product otherProduct) {
        double priceDifference = (this.price) - (otherProduct.price);
        if (priceDifference < 0) {
            return -1; // This product is cheaper
        } else if (priceDifference > 0) {
            return 1; // This product is more expensive
        } else {
            return 0; // Prices are equal
        }
    }
}