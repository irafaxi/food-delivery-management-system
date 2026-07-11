package fooddeliverysystem;

import java.util.ArrayList;

public class FoodDeliverySystm {

    // List containing all people in the system (customers and drivers)
    private ArrayList<Person> persons;
    // List containing all the executors (Drivers and Drones)
    private ArrayList<Deliverable> deliverers;
    // List of all registered restaurants
    private ArrayList<Restaurant> restaurants;
    // List containing all created requests
    private ArrayList<Order> orders;

    public FoodDeliverySystm() {
        persons = new ArrayList<>();
        deliverers = new ArrayList<>();
        restaurants = new ArrayList<>();
        orders = new ArrayList<>();
    }

    // Methods for adding regular or gold customers
    public String addRegularCustomer(int id, String name, String phoneNumber) {
        RegularCustomer regular = new RegularCustomer(id, name, phoneNumber);
        persons.add(regular);
        return "Regular Customer " + regular.getName() + " added successfully.\n\n";
    }

    public String addGoldenCustomer(int id, String name, String phoneNumber, double monthlyFee, double discountRate) {
        GoldenMember golden = new GoldenMember(id, name, phoneNumber, monthlyFee, discountRate);
        persons.add(golden);
        return "Golden Customer " + name + " added successfully.\n\n";
    }

    //Methods for adding the type of connector, whether it is a driver or a drone
    public String addDriver(int id, String name, String phoneNumber, int driverId, String vehicleType) {
        Driver driver = new Driver(id, name, phoneNumber, driverId, vehicleType);
        persons.add(driver);
        deliverers.add(driver);
        return "Driver " + name + " added successfully.\n\n";
    }

    public String addDrone(int droneId, double maxPayload, int batteryLevel) {
        Drone drone = new Drone(droneId, maxPayload, batteryLevel);
        deliverers.add(drone);
        return "Drone " + droneId + " added successfully.\n\n";
    }

    //Method for adding restaurants
    public String addRestaurant(int restaurantId, String name, String address) {
        Restaurant restaurant = new Restaurant(restaurantId, name, address);
        restaurants.add(restaurant);
        return "Restaurant " + name + " added successfully.\n\n";
    }

    //Method for adding items
    public String addMenuItems(int restaurantId, String menuItem) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRestaurantId() == restaurantId) {
                String MenuItem = "";
                String[] splitingMenu = menuItem.split("#");
                for (int i = 0; i < splitingMenu.length; i++) {
                    String Single_item = splitingMenu[i].trim();
                    restaurant.addMenuItems(Single_item);
                    MenuItem += "Menu item \'" + Single_item + "\' added to restaurant " + restaurant.getName() + "\n\n";
                }
                return MenuItem;
            }
        }
        return "Restaurant with ID " + restaurantId + " not found.\n\n";
    }

    //The restaurant is searched based on its ID.
    //If the restaurant is not found, an error message is returned.
    public String createOrder(Customer customer, int restaurantId, String[] items, double deliveryCost) {
        Restaurant restaurant = null;
        for (Restaurant res : restaurants) {
            if (res.getRestaurantId() == restaurantId) {
                restaurant = res;
                break;
            }
        }
      if (restaurant == null) {
            return "Restaurant with ID " + restaurantId + " not found.\n\n";}
        Order order = new Order(0, customer, restaurant);
        for (String FoodItem : items) {
            order.addFoodItem(FoodItem.trim());
        }
        order.setDeliveryCost(deliveryCost);
        orders.add(order);
        return "Order " + order.getOrderId() + " created successfully for " + customer.getName() + "\n\n";
    }
// Assigns a deliverer (Driver or Drone) to a specific order.
    public String assignDeliverer(int orderId, Deliverable deliverer) {
        Order order = null;
        for (Order orderSearsh : orders) {
            if (orderSearsh.getOrderId() == orderId) {
                order = orderSearsh;
                break;
            }
        }
        if (order == null) {
        return "Order with ID " + orderId + " not found.\n\n";}
        order.setDeliverer(deliverer);
        if (deliverer instanceof Driver) {
            Driver driver = (Driver) deliverer;
            return "Driver " + driver.getName() + " assigned to Order " + orderId + "\n\n";
        } else if (deliverer instanceof Drone) {
            Drone drone = (Drone) deliverer;
            return "Drone " + drone.getDroneId() + " assigned to Order " + orderId + "\n\n";
        }
        return "Unknown deliverer \n\n";
    }
//Prints all orders delivered by the specified deliverer
    public String printOrderByDeliverer(Deliverable deliverer) {
        StringBuilder FORdriverOrDrone = new StringBuilder();
        FORdriverOrDrone.append("-----Orders delivered by ");
        if (deliverer instanceof Driver) {
            Driver driver = (Driver) deliverer;
            FORdriverOrDrone.append("Driver ").append(driver.getName()).append(" (ID: ").append(driver.getDriverId()).append(")").append("--------\n");
        } else if (deliverer instanceof Drone) {
            Drone drone = (Drone) deliverer;
            FORdriverOrDrone.append("Drone with ID ").append(drone.getDroneId());
        }

        for (Order order : orders) {
            if (order.getDeliverer().equals(deliverer)) {
                FORdriverOrDrone.append("===============================================================\n")
                        .append(String.format("Order ID   Customer        Restaurant           Delivery Cost  \n"))
                        .append(String.format("%-10d %-15s %-20s %.2f\n", order.getOrderId(), order.getCustomer().getName(), order.getRestaurant().getName(), order.getDeliveryCost()))
                        .append("-----------------------------Items---------------------------\n");
                int ItemsNamesNumber = 0;
                for (String ItemsNames : order.getFoodItems()) {
                    FORdriverOrDrone.append(++ItemsNamesNumber).append("-").append(ItemsNames).append("\n");
                }
                FORdriverOrDrone.append("==============================End==============================\n");
            }
        }
        FORdriverOrDrone.append("\n");
        return FORdriverOrDrone.toString();
    }
//The total is the sum of delivery costs for all orders
    public String getTotalByDeliverer(int delivererId) {
        double total = 0;
        String type = "";
        Deliverable deliverer = getDelivererById(delivererId);
        if (deliverer instanceof Driver) {
            type = "Driver";
        } else if (deliverer instanceof Drone) {
            type = "Drone";
        }
        for (Order order : orders) {
            if (order.getDeliverer() == deliverer) {
                total += order.getDeliveryCost();
            }
        }
        String total_With_Format = String.format("%.2f", total);
        return "\nThe " + type + " with ID " + delivererId + " has earned $" + total_With_Format + " for deliveries.\n";
    }
// Prints all orders placed by a specific customer
    public String printOrderByCustomer(int customerId) {
        StringBuilder OrderByCustomer = new StringBuilder();
        Customer customer = getCustomerById(customerId);
        OrderByCustomer.append("\n------------- Orders for Customer: ").append(customer.getName()).append(" (ID: ").append(customer.getId()).append(")").append("------------\n");
        for (Order order : orders) {
            if (order.getCustomer().getId() == customerId) {
                OrderByCustomer.append("-------------------------------------------------------------\n").append("Order ID      : ").append(order.getOrderId()).append("\n").append("Customer Type : ");
                String type = "";
                double Rate = 0;
                if (customer instanceof GoldenMember) {
                    type = "Golden\n";
                } else if (customer instanceof RegularCustomer) {
                    type = "Regular\n";
                }
                OrderByCustomer.append(type).append("Restaurant    : ").append(order.getRestaurant().getName()).append("\n").append("Discount Rate : ");

                if (customer instanceof GoldenMember) {
                    Rate = ((GoldenMember) customer).getDiscountRate();
                } else {
                    Rate = 0.0;
                }

                OrderByCustomer.append(Rate).append("\n");
                OrderByCustomer.append("Delivery Cost : ").append(String.format("%.2f", order.getDeliveryCost())).append("\n");
                OrderByCustomer.append("Items:\n");

                int numberOfItems = 0;
                for (String item : order.getFoodItems()) {
                    OrderByCustomer.append("   ").append(++numberOfItems).append(". ").append(item).append("\n");
                }
                OrderByCustomer.append("Delivered by  : ");

                if (order.getDeliverer() instanceof Driver) {
                    Driver driver = (Driver) order.getDeliverer();
                    OrderByCustomer.append("Driver ").append(driver.getName()).append(" (ID: ").append(driver.getDriverId()).append(")\n");
                } else if (order.getDeliverer() instanceof Drone) {
                    Drone drone = (Drone) order.getDeliverer();
                    OrderByCustomer.append("Drone (ID: ").append(drone.getDroneId()).append(")\n");
                }
                OrderByCustomer.append("-------------------------------------------------------------\n");
            }
        }

        if (customer == null) {
            return "Customer not found.\n";
        }
        return OrderByCustomer.toString();
    }
// Searches for and returns a Customer object by its ID.
    public Customer getCustomerById(int customerId) {
        for (Person person : persons) {
            if (person instanceof Customer) {
                Customer customer = (Customer) person;
                if (person.getId() == customerId) {
                    return customer;
                }
            }
        }
        return null;
    }
//Searches for and returns a Deliverable object
    public Deliverable getDelivererById(int DliverId) {
        for (Deliverable Dlever : deliverers) {
            if (Dlever instanceof Driver) {
                Driver driver = ((Driver) Dlever);
                if (driver.getDriverId() == DliverId) {
                    return Dlever;
                }
            } else if (Dlever instanceof Drone) {
                Drone drone = ((Drone) Dlever);
                if (drone.getDroneId() == DliverId) {
                    return Dlever;
                }
            }
        }
        return null;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public ArrayList<Deliverable> getDeliverers() {
        return deliverers;
    }
}
