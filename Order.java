package flowershop;
import java.util.List;
import java.util.ArrayList;

import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private List<Flower> flowers;
    private double totalAmount;
    private String orderDate;
    private double discount;

    public Order(String orderId, Customer customer, List<Flower> flowers, double totalAmount, String orderDate, double discount) {
        this.orderId = orderId;
        this.customer = customer;
        this.flowers = flowers;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.discount = discount;
    }

    public double calculateFinalAmount() {
        return totalAmount - (totalAmount * discount);
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + "\n" +
               "Customer: " + customer.getName() + "\n" +
               "Total Amount: $" + totalAmount + "\n" +
               "Discount: " + (discount * 100) + "%\n" +
               "Final Amount: $" + calculateFinalAmount() + "\n" +
               "Order Date: " + orderDate;
    }
}
