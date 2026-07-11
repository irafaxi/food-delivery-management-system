package fooddeliverysystem;
import java.util.ArrayList;
public class Order {
    private static int currentOrderIdNumber = 1; 
    private int orderId;
    private Customer customer;
    private Restaurant restaurant;
    private ArrayList<String> foodItems=new ArrayList<>();
    private Double deliveryCost;
    private Deliverable deliverer;

    public Order(int orderId, Customer customer, Restaurant restaurant) {
        this.orderId = currentOrderIdNumber++;
        this.customer = customer;
        this.restaurant = restaurant;

    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<String> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<String> foodItems) {
        this.foodItems = foodItems;
    }

    public Double getDeliveryCost() {
        
        return deliveryCost;
    }
// Checks if the customer is a Golden Member
// If so, the discount percentage is applied to the delivery cost
// Otherwise, the cost is assigned directly without a discount
    public void setDeliveryCost(Double deliveryCost) {
        if (customer instanceof GoldenMember ){
          double disRate=((GoldenMember)customer).getDiscountRate();
         this.deliveryCost =deliveryCost * (1 - disRate);}
        
        else 
         this.deliveryCost = deliveryCost;

    }

    public Deliverable getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(Deliverable deliverer) {
        this.deliverer = deliverer;
    }
     // Adds a single food item
   public void addFoodItem(String foodItems){
   this.foodItems.add(foodItems);
   }
}
