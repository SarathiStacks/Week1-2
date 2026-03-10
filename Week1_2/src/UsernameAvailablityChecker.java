import java.util.*;

public class UsernameAvailablityChecker {

    // Stores username -> userId
    private HashMap<String, Integer> usernameMap = new HashMap<>();

    // Stores username -> number of attempts
    private HashMap<String, Integer> attemptFrequency = new HashMap<>();

    private int userIdCounter = 1;

    // Register a username
    public void registerUser(String username) {
        usernameMap.put(username, userIdCounter++);
    }

    // Check if username is available
    public boolean checkAvailability(String username) {

        // Track attempt frequency
        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username, 0) + 1);

        return !usernameMap.containsKey(username);
    }

    // Suggest alternatives
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;
            if (!usernameMap.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        String modified = username.replace("_", ".");
        if (!usernameMap.containsKey(modified)) {
            suggestions.add(modified);
        }

        return suggestions;
    }

    // Get most attempted username
    public String getMostAttempted() {

        String mostAttempted = null;
        int max = 0;

        for (String user : attemptFrequency.keySet()) {
            if (attemptFrequency.get(user) > max) {
                max = attemptFrequency.get(user);
                mostAttempted = user;
            }
        }

        return mostAttempted + " (" + max + " attempts)";
    }

    // Demo
    public static void main(String[] args) {

        UsernameAvailablityChecker system = new UsernameAvailablityChecker();

        system.registerUser("john_doe");
        system.registerUser("admin");

        System.out.println(system.checkAvailability("john_doe")); // false
        System.out.println(system.checkAvailability("jane_smith")); // true

        System.out.println(system.suggestAlternatives("john_doe"));

        system.checkAvailability("admin");
        system.checkAvailability("admin");
        system.checkAvailability("admin");

        System.out.println(system.getMostAttempted());
    }
}