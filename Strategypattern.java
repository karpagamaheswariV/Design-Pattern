import java.util.*;

class Vehicle {
    String number;
    String type; 

    Vehicle(String number, String type) {
        this.number = number;
        this.type = type;
    }
}

class ParkingSpot {
    String id;
    String type;  
    boolean free = true;

    ParkingSpot(String id, String type) {
        this.id = id;
        this.type = type;
    }
}

class ParkingSpotFactory {
    private static int count = 1;

    public static ParkingSpot createSpot(String type) {
        return new ParkingSpot("S" + count++, type);
    }
}

interface SpotAssignmentStrategy {
    ParkingSpot getAvailableSpot(List<ParkingSpot> spots, Vehicle v);
}

class NearestSpotStrategy implements SpotAssignmentStrategy {
    public ParkingSpot getAvailableSpot(List<ParkingSpot> spots, Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.free && isCompatible(s, v)) return s;
        }
        return null;
    }

    private boolean isCompatible(ParkingSpot s, Vehicle v) {
        if (v.type.equals("Bike")) return true;                       
        if (v.type.equals("Car")) return s.type.equals("Medium") || s.type.equals("Large");
        return s.type.equals("Large");                                
    }
}

class ParkingLot {
    private static ParkingLot instance;

    private List<ParkingSpot> spots = new ArrayList<>();
    private SpotAssignmentStrategy strategy = new NearestSpotStrategy();
    private Map<String, ParkingSpot> ticketMap = new HashMap<>();

    private ParkingLot() {}  

    public static ParkingLot getInstance() {
        if (instance == null) instance = new ParkingLot();
        return instance;
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public String park(Vehicle v) {
        ParkingSpot spot = strategy.getAvailableSpot(spots, v);
        if (spot == null) return "No spot available!";

        spot.free = false;
        ticketMap.put(v.number, spot);

        return "Parked at " + spot.id;
    }

    public String unpark(String vehicleNo) {
        if (!ticketMap.containsKey(vehicleNo)) return "Invalid vehicle";

        ParkingSpot spot = ticketMap.get(vehicleNo);
        spot.free = true;
        ticketMap.remove(vehicleNo);

        return "Vehicle removed from " + spot.id;
    }

    public void showStatus() {
        System.out.println("---- Parking Status ----");
        for (ParkingSpot s : spots) {
            System.out.println(s.id + " (" + s.type + ") : " + (s.free ? "Free" : "Occupied"));
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getInstance();

        lot.addSpot(ParkingSpotFactory.createSpot("Small"));
        lot.addSpot(ParkingSpotFactory.createSpot("Medium"));
        lot.addSpot(ParkingSpotFactory.createSpot("Large"));

        Vehicle v1 = new Vehicle("KA01", "Car");
        Vehicle v2 = new Vehicle("KA02", "Bike");
        Vehicle v3 = new Vehicle("KA03", "Truck");

        System.out.println(lot.park(v1));
        System.out.println(lot.park(v2));
        System.out.println(lot.park(v3));

        lot.showStatus();

        System.out.println(lot.unpark("KA02"));

        lot.showStatus();
    }
}
