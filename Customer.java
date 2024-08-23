//This specifies that the Customer class is part of the flowershop package
package flowershop;

//This defines the Customer class as public meaning it can be accessed from other classes
public class Customer 

{
//(These private  store the name address and phone number of the customer)
    private String name;
    private String address;
    private String phoneNumber;

    //The constructor initializes the set with the values provided when a new Customer object is created
  
    public Customer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

//These methods provide access to the private fields of the class
    public String getName() 
    {
        return name;
    }

    public String getAddress() 
    {
        return address;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    //Override
    public String toString() 
    {
     // Return a string representation of the Customer object
    	return "Customer{" + 
     
    	"name='" + name + "', " + // Append the name field with single quotes
     
     "address='" + address + "', " +  // Append the address field with single quotes
     
     "phoneNumber='" + phoneNumber + "'" +  // Append the phoneNumber field with single quotes
               "}";
    }

}
