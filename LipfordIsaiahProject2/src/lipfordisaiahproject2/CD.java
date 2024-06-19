/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lipfordisaiahproject2;

/**
 *
 * @author Isaiah Lipford
 */
public class CD extends Product {
    private String artist;

    public CD(int productID, String title, String type, double price, int quantityInStock, String artist) {
        super(productID, title, type, price, quantityInStock);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
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

