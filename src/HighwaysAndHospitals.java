import java.util.ArrayList;

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
        int baseMax = hospitalCost * n;

        // If hospitals don't cost more than roads, minimum cost is equal to the cost of building hospital in every city
        if (hospitalCost <= highwayCost) {
            // Return the cost of building hospital in every city
            return baseMax;
        }

        // Minimum cost to be returned
        int cost = 0;

        // Arraylist to keep track of what other cities each city can have a highway to
        // Each city's possible connections to be listed at connections[city]
        ArrayList<Integer>[] connections = new ArrayList[n + 1];

        // Arraylist to keep track of what other cities each city has a highway to
        // Each city's  connections to be listed at roads[city]
        ArrayList<Integer>[] roads = new ArrayList[n + 1];

        // For each possible city connection listed in cities[][], add the 2nd node listed at the index
        // to the list of possible connections at connections[1st city], and vice versa
        for (int i = 0; i < cities.length; i++) {
            connections[cities[i][0]].add(cities[i][1]);
            connections[cities[i][1]].add(cities[i][0]);
        }

        for (int i = 0; i < n; i++) {
            // If the given node cannot build any highways to other city, add a hospital to the city
            if (connections[i + 1].size() == 0) {
                cost += hospitalCost;
            }
            // Otherwise look through possible cities to connect to
            else {
                for (int j = 0; j < roads[i + 1].size(); j++) {

                }
            }
        }


        return 0;
    }


    public static int connectedHospitals(int city, )
}
