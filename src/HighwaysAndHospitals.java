import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Stevie Halprin
 *
 */

public class HighwaysAndHospitals {

    /**
     * TODO: Complete this function, cost(), to return the minimum cost to provide
     *  hospital access for all citizens in Menlo County.
     */
    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {

        // If hospitals don't cost more than roads, minimum cost is equal to the cost of building hospital in every city
        if (hospitalCost <= highwayCost) {
            // Return the cost of building hospital in every city
            return (long)hospitalCost * n;
        }

        // Minimum cost to be returned
        long cost = 0;

        // Array of Arraylists to keep track of what other cities each city can have a highway to
        // Each city's possible connections to be listed at connections[city]
        ArrayList<Integer>[] connections = new ArrayList[n + 1];

        // Array to keep track of whether city has been assigned a cluster at cityCluster[city]
        boolean[] assigned = new boolean[n + 1];

        // Arraylist of integers to keep track of number of clusters, how many cities are in each cluster
        ArrayList<Integer> clusters = new ArrayList<>();

        // Initialize all ArrayLists in connections array
        for (int i = 1; i <= n; i++) {
            connections[i] = new ArrayList<>();
        }

        // For each possible city connection listed in cities[][], add the 2nd node listed at the index
        // to the list of possible connections at connections[1st city], and vice versa
        for (int i = 0; i < cities.length; i++) {
            connections[cities[i][0]].add(cities[i][1]);
            connections[cities[i][1]].add(cities[i][0]);
        }

        // Loop through every city, add it to any preexisting cluster it can be a part of, make a new one if none exist
        for (int i = 1; i <= n; i++) {
            // If the current city has already been assigned a cluster, skip to the next city
            if (assigned[i] == false) {
                // Int to hold number of cities in cluster
                int numCities = 1;
                // Set the cluster status of the city to true
                assigned[i] = true;
                // Queue to hold cities to be added to cluster
                Queue<Integer> toAdd = new LinkedList<>();
                // Add immediate connections of current city to the Queue
                for (int city : connections[i]) {
                    toAdd.add(city);
                    // Set the cluster status of the city to true
                    assigned[city] = true;
                }
                // While there are more cities to be added to the cluster, continue looping
                while (!toAdd.isEmpty()) {
                    // Current city is next item in Queue
                    int current = toAdd.remove();
                    // Increment the number of cities in the cluster by 1
                    numCities++;
                    // Add the connections of the given city to the Queue if they haven't already been added
                    for (int city : connections[current]) {
                        if (assigned[city] == false) {
                            toAdd.add(city);
                            // Set the cluster status of the city to true
                            assigned[city] = true;
                        }
                    }
                }
                // Add another cluster to clusters ArrayList
                clusters.add(numCities);
            }
        }

        // Add the cost of 1 hospital for each cluster to the total cost
        cost = (long)clusters.size() * hospitalCost;
        // For each cluster, add the cost of the highways needed to connect the cluster
        // Highway cost for each cluster is (# cities in cluster - 1) * highwayCost
        for (int numCities : clusters) {
            cost += (long)(numCities - 1) * highwayCost;
        }

        // Return the minimum cost of the constructed roads and hospitals
        return cost;
    }

}
