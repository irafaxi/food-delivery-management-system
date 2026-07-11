package fooddeliverysystem;
public class Drone implements Deliverable {
    private int droneId;
    private double maxPayload;
    private int batteryLevel;

    public Drone(int droneId, double maxPayload, int batteryLevel) {
        this.droneId = droneId;
        this.maxPayload = maxPayload;
        this.batteryLevel = batteryLevel;
    }

    public int getDroneId() {
        return droneId;
    }

    public void setDroneId(int droneId) {
        this.droneId = droneId;
    }

    public double getMaxPayload() {
        return maxPayload;
    }

    public void setMaxPayload(double maxPayload) {
        this.maxPayload = maxPayload;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
    
    
    
}
