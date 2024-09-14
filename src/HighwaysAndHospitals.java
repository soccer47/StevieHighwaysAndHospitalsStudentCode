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
        long cost;

        // Array to keep track of root city of city at cityCluster[city]
        int[] rootCity = new int[n + 1];


        // Loop through every city, add its root city to rootCity[city]
        for (int i = 0; i < cities.length; i++) {
            // Ints to hold 1st and 2nd listed nodes respectively
            int leafCity = cities[i][1];
            int ogCity = cities[i][0];

            // Int to hold root of first listed node in city
            int parent1 = ogCity;
            // Int to hold root of second node listed in city
            int parent2 = leafCity;
            // Iterate through to find root of first node
            while (rootCity[parent1] != 0) {
                parent1 = rootCity[parent1];
            }
            // Iterate through to find root of second node
            while (rootCity[parent2] != 0) {
                parent2 = rootCity[parent2];
            }

            int x = ogCity;
            int y = leafCity;
            // Int to temporarily hold values
            int t;

            // Sets parent node of all nodes in path from first listed node to root to the root
            // Compresses tree
            while (rootCity[x] > 0) {
                t = x;
                x = rootCity[x];
                rootCity[t] = parent1;
            }

            // Sets parent node of all nodes in path from second listed node to root to the root
            // Compresses tree
            while (rootCity[y] > 0) {
                t = y;
                y = rootCity[y];
                rootCity[t] = parent2;
            }

            // If nodes don't share the same root, make the parent of second node the first node
            if (parent1 != parent2) {
                // If second listed node currently has no parent city, make first node the parent city
                if (rootCity[leafCity] == 0) {
                    rootCity[leafCity] = ogCity;
                }
                // Otherwise make the first listed node the parent of the root of second listed node
                else {
                    rootCity[rootCity[leafCity]] = ogCity;
                }

            }
        }

        // Int to hold number of clusters
        int numClusters = 0;
        // Loop through the root of every city, increment numClusters by 1 for every city that doesn't have a root
        for (int i = 1; i <= n; i++) {
            if (rootCity[i] == 0) {
                numClusters++;
            }
        }

        // Add the cost of 1 hospital for each cluster to the total cost, + total cost of roads
        cost = (long)numClusters * hospitalCost + (long)(n - numClusters) * highwayCost;

        // Return the minimum cost of the constructed roads and hospitals
        return cost;
    }
}
