import java.util.*;
import java.util.stream.Collectors;
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

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        List<String> trainConsist = new ArrayList<>();
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");
        trainConsist.remove("AC Chair");

        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101");

        LinkedList<String> orderedConsist = new LinkedList<>();
        orderedConsist.add("Engine");
        orderedConsist.add("Sleeper");
        orderedConsist.add("AC");
        orderedConsist.add("Cargo");
        orderedConsist.add("Guard");
        orderedConsist.add(2, "Pantry Car");
        orderedConsist.removeFirst();
        orderedConsist.removeLast();

        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");

        Map<String, Integer> bogieCapacity = new HashMap<>();
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 56);
        bogieCapacity.put("First Class", 24);

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        List<Bogie> bogieList = new ArrayList<>();
        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 56));
        bogieList.add(new Bogie("First Class", 24));
        bogieList.add(new Bogie("Sleeper", 70));

        int total = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Train ID (Format: TRN-1234): ");
        String trainId = scanner.nextLine();

        System.out.print("Enter Cargo Code (Format: PET-AB): ");
        String cargoCode = scanner.nextLine();

        boolean isTrainValid = Pattern.compile("TRN-\\d{4}").matcher(trainId).matches();
        boolean isCargoValid = Pattern.compile("PET-[A-Z]{2}").matcher(cargoCode).matches();

        System.out.println("Train ID Valid: " + isTrainValid);
        System.out.println("Cargo Code Valid: " + isCargoValid);

        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Coal"));

        boolean isSafe = goodsBogies.stream()
                .allMatch(g -> !g.type.equalsIgnoreCase("Cylindrical")
                        || g.cargo.equalsIgnoreCase("Petroleum"));

        System.out.println("Safety Status: " + isSafe);

        System.out.println("\n--- UC13: Performance Comparison ---");

        List<Bogie> performanceList = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            performanceList.add(new Bogie("Sleeper", i % 100));
        }

        long startLoop = System.nanoTime();

        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : performanceList) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }

        long endLoop = System.nanoTime();

        long startStream = System.nanoTime();

        List<Bogie> streamResult = performanceList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        long endStream = System.nanoTime();

        long loopTime = endLoop - startLoop;
        long streamTime = endStream - startStream;

        System.out.println("Loop Execution Time (ns): " + loopTime);
        System.out.println("Stream Execution Time (ns): " + streamTime);

        System.out.println("\nUC13 performance benchmarking completed ...");
        // ================= UC19 =================
        System.out.println("\n=======================================");
        System.out.println("UC19 - Binary Search for Bogie ID");
        System.out.println("=======================================\n");

// Create array of bogie IDs (can be unsorted initially)
        String[] bogieIdsBinary = {"BG101", "BG205", "BG309", "BG412", "BG550"};

// Ensure sorted (important precondition)
        Arrays.sort(bogieIdsBinary);

// Search key
        String key = "BG309";

// Display bogies
        System.out.println("Sorted Bogie IDs:");
        for (String id : bogieIdsBinary) {
            System.out.println(id);
        }

// ---- BINARY SEARCH LOGIC ----
        int low = 0;
        int high = bogieIdsBinary.length - 1;
        boolean foundBinary = false;

        while (low <= high) {
            int mid = (low + high) / 2;

            int comparison = key.compareTo(bogieIdsBinary[mid]);

            if (comparison == 0) {
                foundBinary = true;
                break;
            } else if (comparison > 0) {
                low = mid + 1;     // search right
            } else {
                high = mid - 1;    // search left
            }
        }

// Display result
        if (foundBinary) {
            System.out.println("\nBogie " + key + " found using Binary Search.");
        } else {
            System.out.println("\nBogie " + key + " NOT found using Binary Search.");
        }

        System.out.println("\nUC19 search completed ...");
    }
}