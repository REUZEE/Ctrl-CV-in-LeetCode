package BFS;

import java.util.*;

public class BusRoutes {

    public static void main(String[] args) {
        int[][] routes = new int[][]{{1,2,7}, {3,6,7}};
        int source = 1;
        int target = 6;
        System.out.println(numBusesToDestination(routes, source, target));

    }

    static public int numBusesToDestination(int[][] routes, int source, int target) {

        int res = 0;
        HashMap<Integer, Set<Integer>> station2route = new HashMap<>();
        HashMap<Integer, Set<Integer>> route2station = new HashMap<>();
        Deque<Integer> queue = new LinkedList<>();
        HashSet<Integer> taken = new HashSet<>();

        /* init hashmap */
        for (int i = 0; i < routes.length; i++) {
            Set<Integer> stations = new HashSet<>();
            route2station.put(i, stations);
            for (int station : routes[i]) {
                stations.add(station); // add station to route2station

                Set<Integer> route = station2route.getOrDefault(station, new HashSet<Integer>());
                route.add(i); // add route to station2route
                station2route.put(station, route);
            }
        }

        System.out.println(station2route);
        System.out.println(route2station);

        /* BFS */
        for (int route : station2route.get(source))
            queue.push(route);
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int route = queue.poll();
                taken.add(route);
                for (int station : route2station.get(route)) {

                    if (station == target)
                        return res;

                    Set<Integer> nextRoutes = station2route.get(station);
                    for (int nextRoute : nextRoutes) {
                        if (!taken.add(nextRoute))
                            continue;
                        queue.push(nextRoute);
                    }
                }
            }
        }

        return -1;
    }
}
