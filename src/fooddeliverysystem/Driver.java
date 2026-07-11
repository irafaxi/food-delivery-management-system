package fooddeliverysystem;
public class Driver extends Person implements Deliverable{
    private int driverId;
    private String vehucleType;

    public Driver(int id, String name,String phoneNumber , int driverId, String vehucleType ) {
        super(id, name, phoneNumber);
        this.driverId = driverId;
        this.vehucleType = vehucleType;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getVehucleType() {
        return vehucleType;
    }

    public void setVehucleType(String vehucleType) {
        this.vehucleType = vehucleType;
    }
    public double calculateEarning(double base ,double distance){
    
    
        return 0.0;
    }
    
    
}
