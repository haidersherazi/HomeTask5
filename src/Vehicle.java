
public class Vehicle {
	
	private int id;
	private long arrivalTime;

    public Vehicle(int id) {
        this.arrivalTime = System.currentTimeMillis();
        this.id = id;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }
    
    public int getId() {
        return id;
    }

}