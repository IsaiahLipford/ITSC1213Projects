/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lipfordisaiahproject3;

/**
 *
 * @author iklip
 */

import java.util.*;
import java.io.*;

/**
 * The TestHarness class serves as the entry point for the Bookstore Management System.
 * It provides a menu-based interface for users to interact with the system, including options
 * for making purchases, registering new members, listing inventory, listing members,
 * searching products, removing members, removing products, and exiting the system.
 * 
 * @author Isaiah Lipford
 * 
 */
public class TestHarness 
{
    
    /**
     * Main method to start the Bookstore Management System.
     * 
     * @param args The command line arguments (not used).
     */
     public static void main(String[] args) {
        // Create an instance of the Bookstore class
        Bookstore myBookstore = new Bookstore();
        try {
        myBookstore.loadInitialInventory("resources/ProductInventory.csv");
         

        // Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Implement a menu for user interaction
        while (true) {
            System.out.println("Bookstore Management System");
            System.out.println("1. Make a Purchase");
            System.out.println("2. Register a New Member");
            System.out.println("3. List Members" );
            System.out.println("4. Search/Filter Products By Characters Entered");
            System.out.println("5. Remove Member");
            System.out.println("6. Remove Product");
            System.out.println("7. Compare Products/Sort Inventory By Price");
            System.out.println("8. Restock a Product");
            System.out.println("9. Display Inventory Total Value");
            System.out.println("10. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) 
            {
                case 1 -> 
                {
                try {
                    double totalCost = 0.0;
                    System.out.print("Enter Member ID: ");
                    int memberId = scanner.nextInt();
                    
                    Member member = myBookstore.findMemberById(memberId);
                    if (member == null) 
                    {
                        System.out.println("Member not found.");
                    }
                    
                    System.out.println("Here are the items we have in stock! Please take reference of the Product ID number to enter to continue with your purchase!");
                    myBookstore.listInventory();
                    
                    List<Product> selectedProducts = new ArrayList<>();
                    while (true) 
                    {
                        System.out.print("Enter Product ID (Enter 0 to finish): ");
                        int productId = scanner.nextInt();

                        if (productId == 0) 
                        {
                            break;
                        }

                        Product product = myBookstore.findProductById(productId);
                        if (product != null) {
                            selectedProducts.add(product);
                            totalCost += product.getPrice();
                        } else 
                        {
                            System.out.println("Product not found.");
                        }
                    }

                    // Check if the member has enough funds to make the purchase
                    if (totalCost <= member.getFunds()) {
                        // Deduct the products from the inventory
                        for (Product product : selectedProducts) {
                            if (myBookstore.getInventory().contains(product)) {
                                Product inventoryProduct = myBookstore.getInventory()
                                .get(myBookstore.getInventory().indexOf(product));
                                inventoryProduct.decreaseStock(1); // Deduct one unit from stock
                            }
                        }

                        // Update the member's total amount spent and deduct funds
                        member.addToTotalAmountSpent(totalCost);
                        member.deductFunds(totalCost);

                        System.out.println("Purchase successful!");
                    } else 
                    {
                        System.out.println("Insufficient funds to make the purchase.");
                    }
                    } 
                catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid numeric values.");
                scanner.nextLine(); // Consume the invalid input
            } 
                catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
                
                case 2 -> 
                {
                try {
                    System.out.print("Enter a Member ID (Make sure you write this down!): ");
                    int newMemberId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Member Name: ");
                    String newMemberName = scanner.nextLine();
                    System.out.print("Enter Member Type (Regular/Premium): ");
                    String newMemberType = scanner.nextLine();
                    
                    if (newMemberType.equalsIgnoreCase("Premium")) {
                        // For premium members, collect payment method and fee status
                        System.out.print("Enter Payment Method for Premium Membership: ");
                        String paymentMethod = scanner.nextLine();
                        
                        
                        // Create a premium member with payment method and fee status
                        Member newMember = new Member(newMemberId, newMemberName, newMemberType, paymentMethod, false);
                        myBookstore.addMember(newMember);
                        
                        // Set initial funds for premium members as a bonus
                        double initialFunds = 100.00;
                        newMember.setFunds(initialFunds);
                        System.out.println("You have an inital bonus amount of $100 dollars because you registered as a premium member.");
                        System.out.println("You have a monthly payment due next month!");
                    } 
                    else 
                    {
                        // For regular members, create without payment method and fee status
                        Member newMember = new Member(newMemberId, newMemberName, newMemberType, null, false);
                        myBookstore.addMember(newMember);
                    }
                    System.out.println("New member registered successfully.");
                }
                catch(InputMismatchException e) {
                    System.out.println("Error " + e.getMessage());
                }
                }
                
                case 3 -> myBookstore.listMembers();
                case 4 -> 
                {
                    try {
                    System.out.print("Enter search criteria type (DVD, Book, CD, The Title, etc): ");
                    String searchCriteria = scanner.next();
                    myBookstore.searchProducts(searchCriteria);
                    }
                    catch(InputMismatchException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 5 -> 
                {
                    try {
                    System.out.print("Enter the Member ID to be removed: ");
                    int memberIdToRemove = scanner.nextInt();
                    myBookstore.removeMember(memberIdToRemove);
                    System.out.println("This member has been successfully removed!");
                    
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 6 -> 
                {
                try{
                    System.out.print("Enter the Product ID to be removed: ");
                    int productIdToRemove = scanner.nextInt();
                    myBookstore.removeProductById(productIdToRemove);
                    System.out.println("This product has been removed from the inventory!");
                }
                catch(InputMismatchException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                }
                
                case 7 -> 
                {
                    Product product1 = new Book(1, "The Great Gatsby", "Book", 15.99, 10, "F Scott Fitzgerald");
                    Product product2 = new Book(2, "To Kill a Mockingbird", "Book", 12.99, 15, "Harper Lee");
                    
                    List<Product> inventory = myBookstore.getInventory();
                    Collections.sort(inventory);
                    myBookstore.listInventory(); // List the sorted products
                    
                    //Test For CompareTo Method (Returns 1, -1, or 0)
                    int comparison1 = product1.compareTo(product2);  // Should return 1 because product1's price is higher.
                    int comparison2 = product2.compareTo(product1);  // Should return -1 because product2's price is lower.
                    int comparison3 = product1.compareTo(product1);  // Should return 0 because they have the same price.

                    System.out.println("Comparison 1: " + comparison1);
                    System.out.println("Comparison 2: " + comparison2);
                    System.out.println("Comparison 3: " + comparison3);
                    System.out.println("---------------------");
            }
                case 8 -> 
                {
                try {
                    // Implement restocking a product
                    System.out.print("Enter Product ID to restock: ");
                    int productIDToRestock = scanner.nextInt();
                    System.out.print("Enter the quantity to restock: ");
                    int quantityToRestock = scanner.nextInt();
                    myBookstore.restockProduct(productIDToRestock, quantityToRestock);
                    System.out.println("Product restocked successfully.");
                } 
                catch (InputMismatchException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                }
                case 9 -> 
                {
                    // Implement displaying inventory total (Part B)
                    double inventoryValue = myBookstore.inventoryValue();
                    System.out.println("Inventory Total Value: $" + inventoryValue);
                }
                case 10 -> 
                {
                    System.out.println("Exiting...");
                    myBookstore.updateInventoryFile("resources/ProductInventoryDay2.csv");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
     }
        catch(Exception e) {
            System.out.println("An error occurred! " + "Not a Valid Input!");
        }
     }
}
     

     





   

