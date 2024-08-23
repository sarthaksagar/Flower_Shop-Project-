package flowershop;

import java.security.SecureRandom;

public class OTPUtils 

{
    private static final int OTP_LENGTH = 6; // Define the OTP length
   
    private static final SecureRandom RANDOM = new SecureRandom();

    // Method to generate a random numeric OTP
    public static String generateOTP1() 
    
    {
        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) 
        {
            otp.append(RANDOM.nextInt(10)); // Append a random digit between 0 and 9
        }
        return otp.toString();
    }

    // Method to validate the OTP
    public static boolean validateOTP(String inputOTP, String generatedOTP) 
    
    {
        return inputOTP.equals(generatedOTP);
    }
}
