/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lipfordisaiahproject3;

/**
 *
 * @author Isaiah Lipford
 */
public class Book extends Product {
    private final String author;

    public Book(int productID, String title, String type, double price, int quantityInStock, String author) {
        super(productID, title, type, price, quantityInStock);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
    
    @Override
    public String toString() {
        return "Product ID: " + getProductID() +
               "\nTitle: " + getTitle() +
               "\nType: " + getType() +
               "\nPrice: $" + getPrice() +
               "\nQuantity in Stock: " + getQuantityInStock() +
               "\n--------------";
    }

}