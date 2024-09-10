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

        // Boolean array that holds each city's hospital status (if city has hospital, hospitals[city] == true)
        boolean[] hospitals = new boolean[n + 1];

        // For each possible city connection listed in cities[][], add the 2nd node listed at the index
        // to the list of possible connections at connections[1st city], and vice versa
        for (int i = 0; i < cities.length; i++) {
            connections[cities[i][0]].add(cities[i][1]);
            connections[cities[i][1]].add(cities[i][0]);
        }

        for (int i = 1; i <= n; i++) {
            // If the given node cannot build any highways to other city, add a hospital to the city
            if (connections[i].size() == 0) {
                cost += hospitalCost;
                hospitals[i] = true;
            }
            // Otherwise look through possible routes that can be created through other cities to hospitals
            else {
                // Creates new Queue to which the cities to be checked are added
                Queue<Integer> toVisit = new LinkedList<>();
                // Creates new array where the previously visited city of each city is stored at lastCity[city number]
                int[] lastCity = new int[n + 1];
                // City number of closest city with hospital initialized to 0
                int validCity = 0;
                // City number of city currently being checked initialized to i
                int current = i;

                // While a city with a hospital hasn't been found, continue looping
                while (validCity == 0) {
                    // If there aren't any more cities to visit in the queue, build a hospital at
                    // the current city (there aren't any already-created hospitals to connect to)
                    if (toVisit.peek() == null) {
                        return -1;
                    }

                    // Current square is the next value in the toVisit queue
                    current = toVisit.remove();

                    // If city has a hospital, set validCity to the city's number and break
                    if (hospitals[current] == true) {
                        validCity = current;
                        break;
                    }

                    // Add the next possible cities to the toVisit queue (if they aren't already visited)
                    for (city : connections[current]) {
                        // Makes sure that the given city hasn't been visited yet
                        if (lastCity[city] == 0) {
                            // Add the result square to the toVisit queue
                            toVisit.add(resultSq);
                            // Set the previous city of the next city equal to the current city
                            lastCity[city] = current;
                        }
                    }

                }
            }
        }


        return 0;
    }


    // Method that finds closest city (city fewest connections apart) with a hospital
    public static int connectedHospitals(int city, boolean[] hospitals)
}
