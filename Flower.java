package flowershop;

import java.util.*;
public class Flower 

{
    private String name;
    private String color;
    private double price;
    private int quantity;

    public Flower(String name, String color, double price, int quantity) 
    
    {
        this.name = name;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getName() 
    
    {
        return name;
    }

    public String getColor() 
    
    {
        return color;
    }

    public double getPrice() 
    
    {
        return price;
    }

    public int getQuantity() 
    
    {
        return quantity;
    }

    public void setQuantity(int quantity) 
    
    {
        this.quantity = quantity;
    }

    @Override
    public String toString() 
    
    {
        return "Flower name='" + name + "', "
        		+ "color='" + color + "', "
        				+ "price=" + price + ", "
        						+ "quantity=" + quantity + "...";
    }
}
