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
        // Array to keep track of number of kids each node has
        int[] numKids = new int[n + 1];


        // Loop through every city, add its root city to rootCity[city]
        for (int i = 0; i < cities.length; i++) {
            // Ints to hold 1st and 2nd listed nodes respectively
            int city1 = cities[i][0];
            int city2 = cities[i][1];

            // Int to hold root of first listed node in city
            int parent1 = city1;
            // Int to hold root of second node listed in city
            int parent2 = city2;
            // Iterate through to find root of first node
            while (rootCity[parent1] != 0) {
                parent1 = rootCity[parent1];
            }
            // Iterate through to find root of second node
            while (rootCity[parent2] != 0) {
                parent2 = rootCity[parent2];
            }

            int x = city1;
            int y = city2;
            // Int to temporarily hold values
            int t;

            // Sets parent node of all nodes in path from first listed node to root to the root
            // Compresses tree
            while (rootCity[rootCity[x]] > 0) {
                t = x;
                // Sets x to the parent node
                x = rootCity[x];
                // Subtracts the kids of the kid node from the parent, as well as the kid node
                numKids[x] = numKids[x] - numKids[t] - 1;
                // Sets the parent of the child node to the root
                rootCity[t] = parent1;
            }

            // Sets parent node of all nodes in path from second listed node to root to the root
            // Compresses tree
            while (rootCity[rootCity[y]] > 0) {
                t = y;
                // Sets y to the parent node
                y = rootCity[y];
                // Subtracts the kids of the kid node from the parent, as well as the kid node
                numKids[y] = numKids[y] - numKids[t] - 1;
                // Sets the parent of the child node to the root
                rootCity[t] = parent2;
            }

            // If nodes don't share the same root, make the parent of node with less kids the other node
            if (parent1 != parent2) {
                // Ints to hold city with more children and fewer children respectively
                int biggerCity = 0;
                int smallerCity = 0;

                // Set biggerCity to the root of the component with more kids, and set smallerCity to the root of the
                // component with fewer kids
                if (numKids[parent1] > numKids[parent2]) {
                    biggerCity = parent1;
                    smallerCity = parent2;
                }
                else {
                    biggerCity = parent2;
                    smallerCity = parent1;
                }

                // Make root of the component with more kids the root of the component with less kids
                // Weight ordering
                if (rootCity[smallerCity] == 0) {
                    rootCity[smallerCity] = biggerCity;
                    // Add the number of kids of the new child city + 1 to the number of kids of the bigger city
                    numKids[biggerCity] = numKids[biggerCity] + 1 + numKids[smallerCity];
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
