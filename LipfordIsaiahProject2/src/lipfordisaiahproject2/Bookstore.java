/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lipfordisaiahproject2;

import java.util.ArrayList;
import java.util.List;

/**
 * The Bookstore class represents a bookstore management system that stores information
 * about members and the inventory of products available in the bookstore. It provides
 * methods for adding and removing members, managing the inventory, searching for
 * products, processing purchases, and listing members and inventory.
 * 
 * @author Isaiah Lipford
 * @version 1.0
 */
   public class Bookstore implements BookstoreSpecification
   {
    private final List<Member> members;
    private final List<Product> inventory;
    
    /**
     * Constructs a new Bookstore object with empty member and inventory lists.
     */
    public Bookstore() 
    {
        members = new ArrayList<>();
        inventory = new ArrayList<>();
    }
    
    /**
     * Gets the inventory of products available in the bookstore.
     * 
     * @return The list of products in the inventory.
     */
    public List<Product> getInventory() 
    {
        return inventory;
    }
    
    /**
     * Adds a new member to the bookstore's list of members.
     * 
     * @param member The member to be added.
     */
    public void addMember(Member member) 
    {
        members.add(member);
    }
    
    /**
     * Adds a new member to the bookstore's list of members.
     * 
     * @param member The member to be added.
     * @param initialFunds The initial funds to be added if registered as a premium member.
     */
    public void addMember(Member member, double initialFunds) 
    {
        if (member.getType().equalsIgnoreCase("Premium")) 
        {
            member.setFunds(initialFunds);
        }
        members.add(member);
    }
    
    /**
     * Removes a member from the bookstore's list of members based on their
     * member ID.
     * 
     * @param memberId The ID of the member to be removed.
     */
    public void removeMember(int memberId) 
    {
    // Use the memberId to find the member in the list
    Member memberToRemove = null;

    for (Member member : members) 
    {
        if (member.getMemberID() == memberId) 
        {
            memberToRemove = member;
            break; // Exit the loop once the member is found
        }
    }

    if (memberToRemove != null) 
    {
        members.remove(memberToRemove);
        System.out.println("Member with ID " + memberId + " has been removed.");
    } 
    else 
    {
        System.out.println("Member with ID " + memberId + " not found.");
    }
}
    
    /**
     * Adds a new product to the bookstore's inventory.
     * 
     * @param product The product to be added to the inventory.
     */
    public void addProduct(Product product) 
    {
        inventory.add(product);
    }
    
    /**
     * Removes a product from the bookstore's inventory based on its product ID.
     * 
     * @param productId The ID of the product to be removed from inventory.
     */
    public void removeProductById(int productId) 
    {
    Product productToRemove = null;

    for (Product product : inventory) 
    {
        if (product.getProductID() == productId) 
        {
            productToRemove = product;
            break; // Exit the loop once the product is found
        }
    }

    if (productToRemove != null) 
    {
        inventory.remove(productToRemove);
        System.out.println("Product with ID " + productId + " has been removed.");
    } 
    else 
    {
        System.out.println("Product with ID " + productId + " not found.");
    }
}
    
    /**
     * Searches for products in the inventory based on the specified search criteria
     * (e.g.type) and displays the search results.
     * 
     * @param searchCriteria The search criteria to match against product types.
     */
    public void searchProducts(String searchCriteria) 
    {
    System.out.println("Search results for \"" + searchCriteria + "\":");

    for (Product product : inventory) 
    {
        // Check if the product's title or type contains the search criteria
        if (product.getTitle().toLowerCase().contains(searchCriteria.toLowerCase()) ||
            product.getType().toLowerCase().contains(searchCriteria.toLowerCase())) 
        {

            System.out.println("Product ID: " + product.getProductID());
            System.out.println("Title: " + product.getTitle());
            System.out.println("Type: " + product.getType());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Quantity in Stock: " + product.getQuantityInStock());
            System.out.println("--------------");
        }
    }

         if (inventory.stream().noneMatch(product -> 
            product.getTitle().toLowerCase().contains(searchCriteria.toLowerCase()) ||
            product.getType().toLowerCase().contains(searchCriteria.toLowerCase()))) 
         {
            System.out.println("No matching products found.");
        }
}
    
    /**
     * Processes a purchase for a member by deducting the total cost from the member's
     * funds, updating the member's total amount spent, and reducing the product quantity
     * in the inventory.
     * 
     * @param member The member making the purchase.
     * @param products The list of products to be purchased.
     */
    public void purchase(Member member, List<Product> products) 
    {
    double totalCost = 0.0;

    // Calculate the total cost of the purchased products
    for (Product product : products) {
        totalCost += product.getPrice();
    }

    // Check if the member is a premium member and apply discounts if applicable
    if (member.getType().equalsIgnoreCase("Premium")) 
    {
        totalCost *= 0.9; // Apply a 10% discount for premium members
    }

    // Check if the member has enough funds to make the purchase
    if (totalCost <= member.getFunds()) 
    {
        // Deduct the products from the inventory
        for (Product product : products) {
            if (inventory.contains(product)) {
                Product inventoryProduct = inventory.get(inventory.indexOf(product));
                inventoryProduct.decreaseStock(1); // Deduct one unit from stock
            }
        }

        // Update the member's total amount spent and deduct funds
        member.addToTotalAmountSpent(totalCost);
        member.deductFunds(totalCost);

        System.out.println("Purchase successful!");
    }
    else 
    {
        System.out.println("Insufficient funds to make the purchase.");
    }
}

    /**
     * Lists all members in the bookstore, displaying their member IDs, names, types,
     * and total amount spent.
     */
    public void listMembers() 
    {
        System.out.println("List of Members:");
        for (Member member : members) {
            System.out.println("Member ID: " + member.getMemberID());
            System.out.println("Name: " + member.getName());
            System.out.println("Type: " + member.getType());
            System.out.println("Total Amount Spent: $" + member.getTotalAmountSpent());
            System.out.println("--------------");
        }
    }

    /**
     * Lists all products in the bookstore's inventory, displaying their product IDs,
     * titles, types, prices, and quantities in stock.
     */
    public void listInventory() 
    {
        System.out.println("Inventory List:");
        System.out.println("--------------");
        for (Product product : inventory) 
        {
            System.out.println("Product ID: " + product.getProductID());
            System.out.println("Title: " + product.getTitle());
            System.out.println("Type: " + product.getType());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Quantity in Stock: " + product.getQuantityInStock());
            System.out.println("--------------");
        }
    }
    
    /**
     * Finds a member in the bookstore's list of members by their member ID.
     * 
     * @param memberId The ID of the member to find.
     * @return The Member object if found, or null if not found.
     */
    public Member findMemberById(int memberId) 
    {
        for (Member member : members) 
        {
            if (member.getMemberID() == memberId) {
                return member;
            }
        }
        return null; // Member not found
    }
    
    /**
     * Finds a product in the bookstore's inventory by its product ID.
     * 
     * @param productId The ID of the product to find.
     * @return The Product object if found, or null if not found.
     */
    public Product findProductById(int productId) 
    {
        for (Product product : inventory) 
        {
            if (product.getProductID() == productId) 
            {
                return product;
            }
        }
        return null; // Product not found
    }
    
    // Implement restockProduct from BookStoreSpecification
    @Override
    public int restockProduct(int productId, int quantity) {
        for (Product product : inventory) {
            if (product.getProductID() == productId) {
                product.restock(quantity);
                return product.getQuantityInStock(); // Return the updated quantity
            }
        }
        return -1; // Return -1 to indicate product not found
    }

    // Implement inventoryValue from BookStoreSpecification
    @Override
    public double inventoryValue() {
        double totalValue = 0.0;
        for (Product product : inventory) {
            totalValue += product.getPrice() * product.getQuantityInStock();
        }
        return totalValue;
    }
}




