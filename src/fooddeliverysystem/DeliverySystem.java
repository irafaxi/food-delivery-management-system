// The main CLASS 
/*
Name : Rafa Almramhi
Class : GCS 
ID : 2405664 
 */
package fooddeliverysystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DeliverySystem {

    private static final StringBuilder filePrint = new StringBuilder();
    static PrintWriter fWrite;

    public static void main(String[] args) throws FileNotFoundException {
        File f1 = new File("input.txt");
        Scanner s1 = new Scanner(f1);
        File f2 = new File("output.txt");
        fWrite = new PrintWriter(f2);
        FoodDeliverySystm Class = new FoodDeliverySystm();
        s1.useDelimiter("[,|\n]");
        while (s1.hasNextLine()) {
            String comand = s1.next();
            switch (comand) {
                case "addRegularCustomer":
                    int id = s1.nextInt();
                    String name = s1.next();
                    String phoneNumber = s1.next();
                    filePrint.append(Class.addRegularCustomer(id, name, phoneNumber));
                    break;
                case "addGoldenCustomer":
                    int id1 = s1.nextInt();
                    String name1 = s1.next();
                    String phoneNumber1 = s1.next();
                    double monthlyFee = Double.parseDouble(s1.next());
                    double discountRate = Double.parseDouble(s1.next());
                    filePrint.append(Class.addGoldenCustomer(id1, name1, phoneNumber1, monthlyFee, discountRate));
                    break;
                case "addDriver":
                    int id2 = s1.nextInt();
                    String name2 = s1.next();
                    String phoneNumber2 = s1.next();
                    int driverId = s1.nextInt();
                    String vehicleType = s1.next();
                    filePrint.append(Class.addDriver(id2, name2, phoneNumber2, driverId, vehicleType));
                    break;
                case "addDrone":
                    int droneId = s1.nextInt();
                    double maxPayload = s1.nextDouble();
                    int batteryLevel = Integer.parseInt(s1.next().trim());
                    filePrint.append(Class.addDrone(droneId, maxPayload, batteryLevel));
                    break;
                case "addRestaurant":
                    int restaurantId = s1.nextInt();
                    String name3 = s1.next();
                    String address = s1.next();
                    filePrint.append(Class.addRestaurant(restaurantId, name3, address));
                    break;
                case "addMenuItem":
                    int restaurantId1 = s1.nextInt();
                    String menuItem = s1.next();
                    filePrint.append(Class.addMenuItems(restaurantId1, menuItem));
                    break;
                case "createOrder":
                    int customerId = s1.nextInt();
                    int restaurantId2 = s1.nextInt();
                    String[] items = s1.next().split("#");
                    double deliveryCost = Double.parseDouble(s1.next());
                    Customer customer = Class.getCustomerById(customerId);
                    filePrint.append(Class.createOrder(customer, restaurantId2, items, deliveryCost));
                    break;
                case "assignDeliverer":
                    int iD = s1.nextInt();
                    int DliverId = Integer.parseInt(s1.next().trim());
                    Deliverable deliverer = Class.getDelivererById(DliverId);
                    filePrint.append(Class.assignDeliverer(iD, deliverer));
                    break;
                case "printOrdersByDeliverer":
                    int delivId = Integer.parseInt(s1.next().trim());
                    Deliverable deliv = Class.getDelivererById(delivId);
                    filePrint.append(Class.printOrderByDeliverer(deliv));
                    break;
                case "getTotalCostByDelivererId":
                    int delivererID = Integer.parseInt(s1.next().trim());
                    filePrint.append(Class.getTotalByDeliverer(delivererID));
                    break;
                case "printOrdersByCustomer":
                    int customerID = Integer.parseInt(s1.next().trim());
                    filePrint.append(Class.printOrderByCustomer(customerID));
                    break;
            }
        }
        fWrite.print(filePrint);
        fWrite.close();
    }
}
