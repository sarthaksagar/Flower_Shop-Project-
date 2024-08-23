package flowershop;

import java.util.ArrayList;
import java.util.List;

public class OrderManager 

{
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) 
    
    {
        orders.add(order);
    }

    public List<Order> getOrders() 
    
    {
        return new ArrayList<>(orders); // Return a copy to avoid external modifications
    }
}
