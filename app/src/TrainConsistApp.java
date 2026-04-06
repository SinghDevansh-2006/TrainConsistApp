import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TrainConsistApp {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("=== Train Consist Management App ===");
        System.out.println("=================================");

        List<String> trainConsist = new ArrayList<>();

        System.out.println("Train initialized successfully ...");
        System.out.println("Initial Bogie Count : " + trainConsist.size());
        System.out.println("Current Train Consist : " + trainConsist);

        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("\nAfter Adding Bogies:");
        System.out.println("Train Consist : " + trainConsist);

        trainConsist.remove("AC Chair");

        System.out.println("\nAfter Removing AC Chair:");
        System.out.println("Train Consist : " + trainConsist);

        boolean exists = trainConsist.contains("Sleeper");

        System.out.println("\nChecking if Sleeper exists:");
        System.out.println("Sleeper Present : " + exists);

        System.out.println("\nFinal Train Consist : " + trainConsist);

        System.out.println("\n--- UC3: Unique Bogie ID Tracking ---");

        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101");
        bogieIds.add("BG102");

        System.out.println("Bogie IDs After Insertion:");
        System.out.println(bogieIds);

        System.out.println("\n--- UC4: Maintain Ordered Bogie Consist ---");

        LinkedList<String> orderedConsist = new LinkedList<>();
        orderedConsist.add("Engine");
        orderedConsist.add("Sleeper");
        orderedConsist.add("AC");
        orderedConsist.add("Cargo");
        orderedConsist.add("Guard");

        System.out.println("Initial Train Consist:");
        System.out.println(orderedConsist);

        orderedConsist.add(2, "Pantry Car");

        System.out.println("\nAfter Inserting Pantry Car at position 2:");
        System.out.println(orderedConsist);

        orderedConsist.removeFirst();
        orderedConsist.removeLast();

        System.out.println("\nAfter Removing First and Last Bogie:");
        System.out.println(orderedConsist);

        System.out.println("\n--- UC5: Preserve Insertion Order of Bogies ---");

        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println("Final Train Formation:");
        System.out.println(formation);

        System.out.println("\n--- UC6: Map Bogie to Capacity (HashMap) ---");

        Map<String, Integer> bogieCapacity = new HashMap<>();
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 56);
        bogieCapacity.put("First Class", 24);

        System.out.println("Bogie Capacity Details:");
        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\n--- UC7: Sort Bogies by Capacity (Comparator) ---");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        System.out.println("Before Sorting:");
        for (Bogie b : bogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("\nAfter Sorting by Capacity:");
        for (Bogie b : bogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        System.out.println("\n--- UC8: Filter Passenger Bogies Using Streams ---");

        List<Bogie> filteredBogies = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("Filtered Bogies (Capacity > 60):");
        for (Bogie b : filteredBogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        System.out.println("\n--- UC9: Group Bogies by Type ---");

        List<Bogie> bogieList = new ArrayList<>();
        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 56));
        bogieList.add(new Bogie("First Class", 24));
        bogieList.add(new Bogie("Sleeper", 70));
        bogieList.add(new Bogie("AC Chair", 60));

        Map<String, List<Bogie>> groupedBogies =
                bogieList.stream()
                        .collect(Collectors.groupingBy(b -> b.name));

        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println("\nBogie Type: " + entry.getKey());
            for (Bogie b : entry.getValue()) {
                System.out.println("Capacity -> " + b.capacity);
            }
        }

        System.out.println("\n--- UC10: Count Total Seats in Train ---");

        List<Bogie> capacityList = new ArrayList<>();
        capacityList.add(new Bogie("Sleeper", 72));
        capacityList.add(new Bogie("AC Chair", 56));
        capacityList.add(new Bogie("First Class", 24));
        capacityList.add(new Bogie("Sleeper", 70));

        System.out.println("Bogies in Train:");
        for (Bogie b : capacityList) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        int totalCapacity = capacityList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Seating Capacity of Train: " + totalCapacity);

        System.out.println("\nUC10 aggregation completed ...");
    }
}