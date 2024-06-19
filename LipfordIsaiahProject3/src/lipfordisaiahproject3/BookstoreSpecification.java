/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lipfordisaiahproject3;

/**
 *
 * @author Isaiah Lipford
 */
public interface BookstoreSpecification {
    /**
     * Given a product ID and a product quantity, update stock by adding the amount
     * to the existing product quantity.
     *
     * @param productId The ID of the product to be restocked.
     * @param amount    The quantity to be added to the existing product quantity.
     * @return The updated product quantity after restocking.
     */
    public int restockProduct(int productId, int amount);

    /**
     * Calculate and return the dollar amount for the current inventory of products
     * that are in stock.
     *
     * @return The total value of products in stock.
     */
    public double inventoryValue();
}
