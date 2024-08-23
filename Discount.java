package flowershop;

public class Discount {
    private String discountCode;
    private double discountAmount;

    public Discount(String discountCode, double discountAmount) {
        this.discountCode = discountCode;
        this.discountAmount = discountAmount;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountCode='" + discountCode + '\'' +
                ", discountAmount=" + discountAmount +
                '}';
    }
}
