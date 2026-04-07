import java.util.stream.Collectors;
import java.util.*;
import java.util.regex.Pattern;

public class TrainConsistApp {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }
    }

    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

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

        System.out.println("=====================================");
        System.out.println(" Train Consist Management App ");
        System.out.println("=====================================");

        List<String> trainConsist = new ArrayList<>();

        System.out.println("Train initialized successfully ...");
        System.out.println("Initial Bogie Count : " + trainConsist.size());
        System.out.println("Current Train Consist : " + trainConsist);

        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("\nAfter Adding Bogies:");
        System.out.println(trainConsist);

        trainConsist.remove("AC Chair");

        System.out.println("\nAfter Removing AC Chair:");
        System.out.println(trainConsist);

        System.out.println("\nContains Sleeper? " + trainConsist.contains("Sleeper"));

        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101");

        System.out.println("\nUnique Bogie IDs:");
        System.out.println(bogieIds);

        LinkedList<String> linkedTrain = new LinkedList<>();
        linkedTrain.add("Engine");
        linkedTrain.add("Sleeper");
        linkedTrain.add("AC");
        linkedTrain.add("Cargo");
        linkedTrain.add("Guard");

        linkedTrain.add(2, "Pantry Car");
        linkedTrain.removeFirst();
        linkedTrain.removeLast();

        System.out.println("\nLinked Train Consist:");
        System.out.println(linkedTrain);

        LinkedHashSet<String> orderedSet = new LinkedHashSet<>();
        orderedSet.add("Engine");
        orderedSet.add("Sleeper");
        orderedSet.add("Cargo");
        orderedSet.add("Guard");
        orderedSet.add("Sleeper");

        System.out.println("\nOrdered Unique Bogies:");
        System.out.println(orderedSet);

        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 56);
        capacityMap.put("First Class", 24);

        System.out.println("\nBogie Capacities:");
        for (Map.Entry<String, Integer> entry : capacityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("\nSorted Bogies:");
        for (Bogie b : bogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .toList();

        System.out.println("\nFiltered Bogies (>60):");
        filtered.forEach(b -> System.out.println(b.name + " -> " + b.capacity));

        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        System.out.println("\nGrouped Bogies:");
        for (Map.Entry<String, List<Bogie>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().forEach(b -> System.out.println(" -> " + b.capacity));
        }

        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Capacity: " + total);

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Train ID (TRN-1234): ");
        String trainId = scanner.nextLine();

        System.out.print("Enter Cargo Code (PET-AB): ");
        String cargoCode = scanner.nextLine();

        boolean trainValid = Pattern.matches("TRN-\\d{4}", trainId);
        boolean cargoValid = Pattern.matches("PET-[A-Z]{2}", cargoCode);

        System.out.println("Train ID Valid: " + trainValid);
        System.out.println("Cargo Code Valid: " + cargoValid);

        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goods.add(new GoodsBogie("Open", "Coal"));
        goods.add(new GoodsBogie("Box", "Grain"));
        goods.add(new GoodsBogie("Cylindrical", "Coal"));

        boolean safe = goods.stream()
                .allMatch(g -> !g.type.equalsIgnoreCase("Cylindrical") ||
                        g.cargo.equalsIgnoreCase("Petroleum"));

        System.out.println("\nSafety Status: " + safe);

        long startLoop = System.nanoTime();
        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }
        long endLoop = System.nanoTime();

        long startStream = System.nanoTime();
        List<Bogie> streamResult = bogies.stream()
                .filter(b -> b.capacity > 60)
                .toList();
        long endStream = System.nanoTime();

        System.out.println("\nLoop Time: " + (endLoop - startLoop));
        System.out.println("Stream Time: " + (endStream - startStream));

        try {
            PassengerBogie valid = new PassengerBogie("Sleeper", 72);
            System.out.println("\nCreated Bogie: " + valid.name + " -> " + valid.capacity);

            PassengerBogie invalid = new PassengerBogie("AC Chair", 0);
            System.out.println(invalid.name);

        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nSystem ready for operations ...");
    }
}