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

        // Cost of building hospital in every node, with no highways
        long baseMax = (long)hospitalCost * n;

        // If hospitals don't cost more than roads, minimum cost is equal to the cost of building hospital in every city
        if (hospitalCost <= highwayCost) {
            // Return the cost of building hospital in every city
            return baseMax;
        }

        // Minimum cost to be returned
        long cost = 0;

        // Boolean array that holds each city's hospital status (if city has hospital/is connected to a city with a hospital, hospitalAccess[city] == true)
        boolean[] hospitalAccess = new boolean[n + 1];

        // Array of Arraylists to keep track of what other cities each city can have a highway to
        // Each city's possible connections to be listed at connections[city]
        ArrayList<Integer>[] connections = new ArrayList[n + 1];

        // Array to keep track of what cluster each city belongs to at cityCluster[city]
        int[] cityCluster = new int[n + 1];

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


        for (int i = 1; i <= n; i++) {
            for (int city : connections[i]) {
                if (cityCluster[city] != 0) {
                    cityCluster[i] = cityCluster[city];
                    break;
                }
            }
            if (clusters == 0) {
                // Set the city cluster to one more than the current number of clusters
                cityCluster[i] = clusters.size() + 1;
                // Create new cluster
                cityCluster.add++;
            }
        }

        cost = (long)numClusters * hospitalCost;
        for ()

        // Return the minimum cost of the constructed roads and hospitals
        return cost;
    }

}
