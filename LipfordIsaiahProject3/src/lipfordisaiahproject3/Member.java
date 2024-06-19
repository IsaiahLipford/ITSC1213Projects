/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lipfordisaiahproject3;

/**
 * The Member class represents a bookstore member with attributes such as
 * member ID, name, type (Regular or Premium), total amount spent, and available funds.
 * It provides methods to interact with and manipulate member data.
 * 
 * @author Isaiah Lipford
 * @version 1.0
 */
public class Member 
{
    private int memberID;
    private String name;
    private String type;
    private double totalAmountSpent;
    private double funds;
    private String paymentMethod = null;
    private boolean isMonthlyFeeDue = false;
    
    /**
     * Constructs a new Member object with the specified member ID, name, and type.Initializes totalAmountSpent and funds to zero.
     * 
     * @param memberID The unique identifier for the member.
     * @param name The name of the member.
     * @param type The type of the member (Regular or Premium).
     * @param paymentMethod
     * @param isMonthlyPaymentDue
     */
    public Member(int memberID, String name, String type, String paymentMethod, boolean isMonthlyPaymentDue) 
    {
        this.memberID = memberID;
        this.name = name;
        this.type = type;
        this.totalAmountSpent = 0.00;
        this.funds = 0.00;
        this.paymentMethod = paymentMethod;
    }
    /**
     * Gets the member's unique identifier.
     * 
     * @return The member's unique identifier.
     */
    public int getMemberID() 
    {
        return memberID;
    }
    /**
     * Gets the name of the member.
     * 
     * @return The name of the member.
     */
    public String getName() 
    {
        return name;
    }
    /**
     * Gets the type of the member (Regular or Premium).
     * 
     * @return The type of the member.
     */
    public String getType() 
    {
        return type;
    }
    /**
     * Deducts funds from the member's available funds if the amount is valid
     * and the member has sufficient funds.
     * 
     * @param amount The amount to deduct from the member's funds.
     */
    public void deductFunds(double amount) 
    {
        if (amount > 0 && funds >= amount) 
        {
            funds -= amount;
            System.out.println("Deducted $" + amount + " from your funds.");
        } 
        else
        {
            System.out.println("Insufficient funds to deduct $" + amount + ".");
        }
    }
    /**
     * Gets the available funds of the member.
     * 
     * @return The available funds of the member.
     */
    public double getFunds() 
    {
        return funds;
    }
    /**
     * Gets the total amount spent by the member.
     * 
     * @return The total amount spent by the member.
     */
    public double getTotalAmountSpent() 
    {
        return totalAmountSpent;
    }
    /**
     * Adds an amount to the member's total amount spent.
     * 
     * @param amount The amount to add to the total amount spent.
     */
    public void addToTotalAmountSpent(double amount) 
    {
        totalAmountSpent += amount;
    }
    
    /**
    * Gets the payment method associated with the premium member.
    *
    * @return The payment method (e.g., credit card, PayPal).
    */
    public String getPaymentMethod() 
    {
        return paymentMethod;
    }
    
    /**
    * Checks if the monthly fee is due for the premium member.
    *
    * @return {@code true} if the monthly fee is due, {@code false} otherwise.
    */
    public boolean isMonthlyFeeDue() 
    {
        return isMonthlyFeeDue;
    }
    
    /**
     * Sets the name of the member if the new name is valid.
     * 
     * @param newName The new name to set for the member.
     */
   public void setName(String newName) 
   {
        if (isValidName(newName)) 
        {
            name = newName;
        }
        else
        {
            System.out.println("Invalid name. Please provide a valid name.");
        }
    }
    
    // Helper method to validate member names
    private boolean isValidName(String nameToCheck) {
        // For simplicity, we assume any non-empty name is valid.
        return !nameToCheck.trim().isEmpty();
    }
    /**
     * Sets the type of the member (Regular or Premium) if the new type is valid.
     * 
     * @param newType The new type to set for the member.
     */
    public void setType(String newType) 
    {
        if (isValidType(newType)) 
        {
            type = newType;
        } 
        else 
        {
            System.out.println("Invalid member type. Type must be 'Regular' or 'Premium'.");
        }
    }
    
    // Helper method to validate member types
    private boolean isValidType(String typeToCheck) {
        return typeToCheck.equalsIgnoreCase("Regular") || typeToCheck.equalsIgnoreCase("Premium");
    }
    /**
     * Sets the available funds of the member, ensuring the amount is non-negative.
     * 
     * @param amount The new funds amount to set for the member.
     */
    public void setFunds(double amount) 
    {
        if (amount >= 0) {
            funds = amount;
        }
        else 
        {
            System.out.println("Invalid funds amount. Funds cannot be negative.");
        }
    }
    /**
     * Sets the member's unique identifier if the new member ID is valid (greater than zero).
     * 
     * @param newMemberID The new member ID to set for the member.
     */
    public void setMemberID(int newMemberID) 
    {
        if (newMemberID > 0) 
        {
            memberID = newMemberID;
        } 
        else 
        {
            System.out.println("Invalid member ID. Please provide a valid ID.");
        }
    }
    
    /**
 * Sets the payment method for the premium member.
 *
 * @param paymentMethod The payment method to set (e.g., credit card, PayPal).
 */
    public void setPaymentMethod(String paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }
    
 /**
 * Sets the monthly fee due status for the premium member.
 *
 * @param isMonthlyFeeDue {@code true} if the monthly fee is due, {@code false} otherwise.
 */
    public void setMonthlyFeeDue(boolean isMonthlyFeeDue) 
    {
        this.isMonthlyFeeDue = isMonthlyFeeDue;
    }
    
}

