package fooddeliverysystem;
import java.util.ArrayList;
public class Restaurant {
    private int restaurantId;
    private String name;
    private String address;
    private ArrayList<String> menultems;

    public Restaurant(int restaurantId, String name, String address) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        menultems=new ArrayList<>();
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getMenultems() {
        return menultems;
    }

    public void setMenultems(ArrayList<String> menultems) {
        this.menultems = menultems;
    }
    public void addMenuItems(String menultems){
     this.menultems.add(menultems);
    }
    }
    

