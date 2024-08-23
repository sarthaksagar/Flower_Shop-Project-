package flowershop;

import java.util.*;

public class FlowerShopApp 

{
    private static List<Customer> customers = new ArrayList<>(); // List to store registered customers

    public static void main(String[] args) 
    
    {
        
    	Inventory inventory = new Inventory();
        
    	addDefaultFlowers(inventory); // Add default flowers to the inventory
        
    	OrderManager orderManager = new OrderManager();
        
    	Scanner scanner = new Scanner(System.in);

        while (true) 
        {
            System.out.println("Flower Shop Management System:");
            
            System.out.println("=============================");
            
            System.out.println("1. Add Flower");
            
            System.out.println("2. Register Customer");
            
            System.out.println("3. Place Order");
            
            System.out.println("4. Display Flowers");
            
            System.out.println("5. Exit");
           
            System.out.print("Choose an option: ");

            int choice;
            try 
            
            {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) 
            
            {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
                continue; // Return to the start of the loop
            }

            switch (choice) 
            
            {
                case 1:
                    // Add Flower
                    System.out.print("Enter flower name: ");
                    String flowerName = scanner.nextLine();

                    System.out.print("Enter flower color: ");
                    String color = scanner.nextLine();

                    System.out.print("Enter flower price: ");
                    double price;
                    try 
                    
                    {
                        price = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                    } catch (InputMismatchException e) 
                    
                    {
                        System.out.println("Invalid price. Please enter a number.");
                        scanner.nextLine(); // Clear invalid input
                        continue;
                    }

                    System.out.print("Enter flower quantity: ");
                    int quantity;
                    try 
                    
                    {
                        quantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (InputMismatchException e) 
                    
                    {
                        System.out.println("Invalid quantity. Please enter a number.");
                        scanner.nextLine(); // Clear invalid input
                        continue;
                    }

                    Flower flower = new Flower(flowerName, color, price, quantity);
                    inventory.addFlower(flower);
                    System.out.println("Flower added: " + flower);
                    break;

                case 2:
                    // Register Customer
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();

                    System.out.print("Enter customer address: ");
                    String address = scanner.nextLine();

                    System.out.print("Enter customer phone number: ");
                    String phoneNumber = scanner.nextLine();

                    // Generate and display OTP
                    String otp = OTPUtils.generateOTP1();
                    System.out.println("Your OTP is: " + otp);

                    // Prompt user to enter OTP
                    System.out.print("Enter the OTP to complete registration: ");
                    String inputOTP = scanner.nextLine();

                    // Validate OTP
                    if (OTPUtils.validateOTP(inputOTP, otp)) 
                    
                    {
                        Customer customer = new Customer(customerName, address, phoneNumber);
                        customers.add(customer); // Add the new customer to the list

                        System.out.println("Customer registered successfully.");
                       
                        System.out.println("Registered Customer Details:");
                        
                        System.out.println(customer); // Print the registered customer details
                    } else 
                    
                    {
                        System.out.println("Invalid OTP. Registration failed.");
                    }
                   
                    break;

                case 3:
                    // Place Order
                    System.out.print("Enter customer phone number: ");
                    String phoneNumberForOrder = scanner.nextLine();

                    Customer customerForOrder = findCustomerByPhoneNumber(phoneNumberForOrder);

                    if (customerForOrder == null) {
                        System.out.println("Customer not registered. Please register before placing an order.");
                        break;
                    }

                    System.out.print("Enter order ID: ");
                    String orderId = scanner.nextLine();

                    List<Flower> orderFlowers = new ArrayList<>();
                    while (true) 
                    
                    {
                        System.out.println("Enter flower name:-  ");
                        System.out.println("done' to finish...): ");
                        String flowerForOrder = scanner.nextLine();
                        if (flowerForOrder.equalsIgnoreCase("done")) 
                        
                        {
                            break;
                        }

                        Flower flowerToOrder = inventory.findFlowerByName(flowerForOrder);
                        if (flowerToOrder != null) 
                        
                        {
                            System.out.print("Enter quantity: ");
                            int orderQuantity = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            if (orderQuantity > flowerToOrder.getQuantity()) 
                            
                            {
                                System.out.println("Not enough quantity available. Available: " + flowerToOrder.getQuantity());
                                continue;
                            }

                            flowerToOrder.setQuantity(flowerToOrder.getQuantity() - orderQuantity);
                            orderFlowers.add(new Flower(flowerToOrder.getName(), flowerToOrder.getColor(), flowerToOrder.getPrice(), orderQuantity));
                        } 
                        
                        else 
                        
                        {
                            System.out.println("Flower not found.");
                        }
                    }

                    double totalAmount = orderFlowers.stream().mapToDouble(f -> f.getPrice() * f.getQuantity()).sum();
                    double discount = 0.10; // 10% discount
                    Order order = new Order(orderId, customerForOrder, orderFlowers, totalAmount, "2024-07-21", discount);

                    orderManager.addOrder(order);

                    System.out.println(order.toString());

                    // Payment method selection
                    System.out.println("Choose a payment method:");
                    System.out.println("1. Cash");
                    System.out.println("2. Online (GPay or PhonePe)");
                    System.out.print("Enter your choice: ");
                    int paymentChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (paymentChoice) 
                    
                    {
                        case 1:
                            System.out.println("Please pay $" + order.calculateFinalAmount() + " in cash upon delivery.");
                            break;
                        case 2:
                            System.out.println("Please complete the payment of $" + order.calculateFinalAmount() + " via GPay or PhonePe.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                    


                    case 4:
                    // Display Flowers
                    List<Flower> availableFlowers = inventory.getFlowers();
                    if (availableFlowers.isEmpty()) 
                    
                    {
                        System.out.println("No flowers available in the inventory.");
                    } 
                    
                    else 
                    
                    
                    {
                        boolean flowersAvailable = false;
                        System.out.println("Available flowers:");
                        for (Flower f : availableFlowers) 
                        
                        
                        {
                            if (f.getQuantity() > 0) 
                            
                            { // Display only flowers with quantity greater than 0
                                System.out.println(f);
                                flowersAvailable = true;
                            }
                        }
                        
                        if (!flowersAvailable) 
                        
                        {
                            System.out.println("No flowers available in the inventory.");
                        }
                    }
                   
                    
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    // Method to add default flowers to the inventory
    private static void addDefaultFlowers(Inventory inventory) 
    {
    	// Add total flowers  100
    	Flower[] defaultFlowers = 
    		{
            new Flower("Rose", 
            	
            "Red", 15, 100),
            
            new Flower("Tulip", 
            		
            "Yellow", 15, 80),
            
            new Flower("Daisy", 
            		
            "White", 10, 150),
            
            new Flower("Lily", 
            		
            "Pink", 30, 50),
            
            new Flower("Orchid", 
            		
            "Purple", 40, 40),
            
            new Flower("Sunflower", 
            		
            "Yellow", 20, 60),
          
            new Flower("Aster", 
            		
            "Blue", 10, 70),
          
            new Flower("Begonia", 
            		
            "Red", 20, 30),
            
            new Flower("Carnation", 
            		
            "Pink", 13, 45),
            
            new Flower("Dahlia", 
            		
            "Orange", 20, 25),
            
            new Flower("Edelweiss", 
            		
            "White", 13, 15),
            
            new Flower("Freesia", 
            		
            "Yellow", 17, 55),
            
            new Flower("Gardenia", 
            		
            "White", 20, 35),
            
            new Flower("Hyacinth", 
            		
            "Purple",20, 40),
            
            new Flower("Iris", 
            		
            "Blue", 10, 50),
            
            new Flower("Jasmine", 
            		
            "White", 20, 60),
            
            new Flower("Kalanchoe", 
            		
            "Red", 15, 80),
            
            new Flower("Lavender", 
            		
            "Purple", 20, 100),
            
            new Flower("Marigold", 
            		
            "Orange", 10, 110),
            
            new Flower("Narcissus", 
            		
            "Yellow",20, 90),
            
            new Flower("Oleander", 
            		
            "Pink", 20, 75),
            
            new Flower("Petunia", 
            		
            "Purple", 10, 130),
            
            new Flower("Quince", 
            		
            "Pink", 20, 20),
            
            new Flower("Rhododendron", 
            		
            "Red", 30, 65),
             
            new Flower("Snapdragon", 
            		
            "Yellow", 10, 85),
           
            new Flower("Tuberose", 
            		
            "White", 20, 30),
            
            new Flower("Umbrella Plant", 
            		
            "Green", 10, 100),
            
            new Flower("Violet", 
            "Blue", 15, 55),
            
            new Flower("Water Lily", 
            		
            "Pink", 5, 25),
            
            new Flower("Xeranthemum", 
            		
            "Purple", 12, 45),
            
            new Flower("Yarrow", 
            		
            "Yellow", 10, 70),
            
            new Flower("Zinnia", 
            		
            "Red", 10, 90),
            
            new Flower("Acacia", 
            		
            "Yellow",20, 50),
            
            new Flower("Baby's Breath", 
            		
            "White",18, 60),
            
            new Flower("Calendula", 
            		
            "Orange", 20, 70),
            
            new Flower("Dianthus", 
            		
            "Pink", 16, 40),
            
            new Flower("Echinacea", 
            		
            "Purple", 20, 55),
            
            new Flower("Forget-Me-Not", 
            		
            "Blue", 10, 100),
            
            new Flower("Geranium", 
            		
            "Red", 20, 30),
            
            new Flower("Hollyhock", 
            		
            "Pink",10, 25),
            
            new Flower("Ixora", 
            		
            "Red",10, 60),
            
            new Flower("Jade Plant", 
            		
            "Green", 5, 50),
            
            new Flower("Kaffir Lily", 
            		
            "Orange", 20, 45),
            
            new Flower("Lantana", 
            		
            "Yellow", 10, 70),
            
            new Flower("Magnolia", 
            		
            "White", 30, 35),
            
            new Flower("Nasturtium", 
            		
            "Orange", 16, 80),
            
            new Flower("Ornamental Kale", 
            		
            "Purple",10, 40),
            
            new Flower("Poppy", 
            		
            "Red", 10, 75),
            
            new Flower("Quamash", 
            		
            "Blue", 20, 50),
            
            new Flower("Rosemary", 
            		
            "Blue", 10, 90),
            
            new Flower("Snapdragon", 
            		
            "Pink", 15, 65),
            
            new Flower("Tulip Tree", 
            		
            "Yellow", 2.90, 20),
            
            new Flower("Umbrella Tree", 
            		
            "Green", 10, 110),
            
            new Flower("Viola", 
            		
            "Purple", 10, 85),
            
            new Flower("Wisteria", 
            		
            "Purple", 20, 45),
            
            new Flower("Xylosma", 
            		
            "Yellow", 10, 50),
            
            new Flower("Yucca", 
            		
            "White", 20, 60),
            
            new Flower("Zephyranthes", 
            		
            "Pink", 10, 35)
        };

        for (Flower flower : defaultFlowers) 
        {
            inventory.addFlower(flower);
        }
    }

    // Method to find a customer by phone number
    private static Customer findCustomerByPhoneNumber(String phoneNumber)
    
    {
        for (Customer customer : customers) 
        
        {
            if (customer.getPhoneNumber().equals(phoneNumber)) 
            
            {
                return customer;
            }
        }
        return null;
    }
}
