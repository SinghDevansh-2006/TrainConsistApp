import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

public class TrainConsistApp {

    // ================= UC7–UC10 Bogie =================
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    // ================= UC12 & UC15 Goods Bogie =================
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        GoodsBogie(String type) {
            this.type = type;
        }

        // UC15: Safe Cargo Assignment
        void assignCargo(String cargo) {
            try {
                if (type.equalsIgnoreCase("Rectangular") &&
                        cargo.equalsIgnoreCase("Petroleum")) {
                    throw new CargoSafetyException("Unsafe cargo assignment!");
                }

                this.cargo = cargo;
                System.out.println("Cargo assigned successfully -> " + cargo);

            } catch (CargoSafetyException e) {
                System.out.println("Error: " + e.getMessage());

            } finally {
                System.out.println("Cargo validation completed for " + type + " bogie");
            }
        }
    }

    // ================= UC14 Custom Exception =================
    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String msg) {
            super(msg);
        }
    }

    // ================= UC15 Runtime Exception =================
    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String msg) {
            super(msg);
        }
    }

    // ================= UC14 Passenger Bogie =================
    static class PassengerBogie {
        String name;
        int capacity;

        PassengerBogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.name = name;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) {

        // ================= UC7 =================
        System.out.println("\n=== UC7 Sorting ===");
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        bogies.sort(Comparator.comparingInt(b -> b.capacity));
        bogies.forEach(b -> System.out.println(b.name + " -> " + b.capacity));

        // ================= UC8 =================
        System.out.println("\n=== UC8 Filtering (>60) ===");
        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .toList();
        filtered.forEach(b -> System.out.println(b.name + " -> " + b.capacity));

        // ================= UC9 =================
        System.out.println("\n=== UC9 Grouping ===");
        Map<String, List<Bogie>> grouped =
                bogies.stream().collect(Collectors.groupingBy(b -> b.name));
        grouped.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(b -> System.out.println(" -> " + b.capacity));
        });

        // ================= UC10 =================
        System.out.println("\n=== UC10 Total Seats ===");
        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);
        System.out.println("Total: " + total);

        // ================= UC11 =================
        System.out.println("\n=== UC11 Regex Validation ===");
        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        boolean validTrain = Pattern.matches("TRN-\\d{4}", trainId);
        boolean validCargo = Pattern.matches("PET-[A-Z]{2}", cargoCode);

        System.out.println("Train Valid: " + validTrain);
        System.out.println("Cargo Valid: " + validCargo);

        // ================= UC12 =================
        System.out.println("\n=== UC12 Safety Check ===");
        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goods.add(new GoodsBogie("Open", "Coal"));
        goods.add(new GoodsBogie("Cylindrical", "Coal"));

        boolean safe = goods.stream().allMatch(g ->
                !g.type.equalsIgnoreCase("Cylindrical") ||
                        g.cargo.equalsIgnoreCase("Petroleum"));

        System.out.println("Safe: " + safe);

        // ================= UC13 =================
        System.out.println("\n=== UC13 Performance ===");

        long start1 = System.nanoTime();
        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.capacity > 60) loopResult.add(b);
        }
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        List<Bogie> streamResult = bogies.stream()
                .filter(b -> b.capacity > 60)
                .toList();
        long end2 = System.nanoTime();

        System.out.println("Loop Time: " + (end1 - start1));
        System.out.println("Stream Time: " + (end2 - start2));

        // ================= UC14 =================
        System.out.println("\n=== UC14 Exception Handling ===");
        try {
            PassengerBogie p1 = new PassengerBogie("Sleeper", 72);
            System.out.println("Created: " + p1.name);

            PassengerBogie p2 = new PassengerBogie("Invalid", 0);
        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ================= UC15 =================
        System.out.println("\n=== UC15 Safe Cargo Assignment ===");

        GoodsBogie g1 = new GoodsBogie("Cylindrical");
        GoodsBogie g2 = new GoodsBogie("Rectangular");

        g1.assignCargo("Petroleum"); // valid
        System.out.println();
        g2.assignCargo("Petroleum"); // invalid
    }
}