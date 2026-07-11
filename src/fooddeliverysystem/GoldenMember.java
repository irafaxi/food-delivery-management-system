package fooddeliverysystem;
public class GoldenMember extends Customer {
    private double monthlySubscriptionFee;
    private double discountRate;

    public GoldenMember(int id, String name, String phoneNumber,double monthlySubscriptionFee, double discountRate) {
        super(id, name, phoneNumber);
        this.monthlySubscriptionFee = monthlySubscriptionFee;
        this.discountRate = discountRate;
    }

    public double getMonthlySubscriptionFee() {
        return monthlySubscriptionFee;
    }

    public void setMonthlySubscriptionFee(double monthlySubscriptionFee) {
        this.monthlySubscriptionFee = monthlySubscriptionFee;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
    
}
