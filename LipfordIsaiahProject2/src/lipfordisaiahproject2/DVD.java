/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lipfordisaiahproject2;

/**
 *
 * @author Isaiah Lipford
 */
public class DVD extends Product {
    private String director;

    public DVD(int productID, String title, String type, double price, int quantityInStock, String director) {
        super(productID, title, type, price, quantityInStock);
        this.director = director;
    }

    public String getDirector() {
        return director;
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

