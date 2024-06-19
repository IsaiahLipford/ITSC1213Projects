/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lipfordisaiahproject1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

         initializeData(myBookstore);

        // Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Implement a menu for user interaction
        while (true) {
            System.out.println("Bookstore Management System");
            System.out.println("1. Make a Purchase");
            System.out.println("2. Register a New Member");
            System.out.println("3. List Members" );
            System.out.println("4. Search/Filter Products By Type");
            System.out.println("5. Remove Member");
            System.out.println("6. Remove Product");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) 
            {
                case 1 -> 
                {
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

                    // Check if the member is a premium member and apply discounts if applicable
                    if (member.getType().equalsIgnoreCase("Premium")) {
                        totalCost *= 0.9; // Apply a 10% discount for premium members
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
                case 2 -> 
                {
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
                
                case 3 -> myBookstore.listMembers();
                case 4 -> 
                {
                    System.out.print("Enter search criteria type (DVD, Book, or CD): ");
                    String searchCriteria = scanner.next();
                    myBookstore.searchProducts(searchCriteria);
                }
                case 5 -> {
                    System.out.print("Enter the Member ID to be removed: ");
                    int memberIdToRemove = scanner.nextInt();
                    myBookstore.removeMember(memberIdToRemove);
                    System.out.println("This member has been successfully removed!");
                }
                case 6 -> {
                    System.out.print("Enter the Product ID to be removed: ");
                    int productIdToRemove = scanner.nextInt();
                    myBookstore.removeProductById(productIdToRemove);
                    System.out.println("This product has been removed from the inventory!");
                }
                case 7 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
       
     }
     
    private static void initializeData(Bookstore bookstore) {
        // Adding sample products
        Product book1 = new Product(1, "The Great Gatsby", "Book", 15.99, 10);
        Product book2 = new Product(2, "To Kill a Mockingbird", "Book", 12.99, 15);
        Product cd1 = new Product(3, "Classical Symphony", "CD", 9.99, 20);
        Product cd2 = new Product(4, "Utopia", "CD", 15.99, 30);
        Product dvd1 = new Product(5, "Inception", "DVD", 19.99, 5);

        bookstore.addProduct(book1);
        bookstore.addProduct(book2);
        bookstore.addProduct(cd1);
        bookstore.addProduct(cd2);
        bookstore.addProduct(dvd1);

        // Adding sample members
        Member member1 = new Member(101, "John Doe", "Regular", null, false);
        Member member2 = new Member(102, "Alice Smith", "Premium", null, false);

        bookstore.addMember(member1);
        bookstore.addMember(member2);

        // Set initial funds for premium member
        member2.setFunds(100.00);
    }

}



   
