package leetcode;

import java.util.*;

/**
 * Created by Datartvinci on 2018/2/22.
 * There are n cities connected by m flights.
 * Each fight starts from city u and arrives at v with a price w.
 * Now given all the cities and fights,
 * together with starting city src and the destination dst,
 * your task is to find the cheapest price from src to dst with up to k stops.
 * If there is no such route, output -1.
 */
public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        CheapestFlightsWithinKStops stops = new CheapestFlightsWithinKStops();
        int result = stops.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1);
        System.out.println(result);
        result = stops.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0);
        System.out.println(result);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            result = stops.findCheapestPrice(5, new int[][]{{0, 1, 5}, {1, 2, 5}, {0, 3, 2}, {3, 1, 2}, {1, 4, 1}, {4, 2, 1}}, 0, 2, 2);
        }
        System.out.println(result + "-----" + String.valueOf(System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            result = stops.dp(5, new int[][]{{0, 1, 5}, {1, 2, 5}, {0, 3, 2}, {3, 1, 2}, {1, 4, 1}, {4, 2, 1}}, 0, 2, 2);
        }
        System.out.println(result + "-----" + String.valueOf(System.currentTimeMillis() - start));
    }

    /**
     * bfs
     *
     * @param n       城市个数
     * @param flights 航班信息
     * @param src     出发地
     * @param dst     目的地
     * @param K       中转次数
     * @return 费用
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //使用邻接矩阵来存储图，adjacencyMatrix[i][j]=price 意味着从i飞到j需要price花费
        int[][] adjacencyMatrix = new int[n][n];
        for (int i = 0; i < flights.length; i++) {
            adjacencyMatrix[flights[i][0]][flights[i][1]] = flights[i][2];
        }
        //存储每个src到每个城市的花费的map
        Map<Integer, Integer> priceFromSrc = new HashMap<>();

        Queue<City> queue = new LinkedList<>();
        queue.offer(new City(src, 0, 0));
        while (!queue.isEmpty()) {
            City currentCity = queue.poll();
            if (currentCity.step > K) {
                continue;
            }
            int[] connectCities = adjacencyMatrix[currentCity.node];
            for (int i = 0; i < connectCities.length; i++) {
                int connectCityPrice = connectCities[i];
                if (connectCityPrice == 0) {
                    continue;
                }
                int newCost = currentCity.price + connectCityPrice;
                if (priceFromSrc.containsKey(i)) {
                    if (newCost < priceFromSrc.get(i)) {
                        priceFromSrc.put(i, newCost);
                        queue.offer(new City(i, newCost, currentCity.step + 1));
                    }
                } else {
                    priceFromSrc.put(i, newCost);
                    queue.offer(new City(i, newCost, currentCity.step + 1));
                }
            }
        }
        return priceFromSrc.getOrDefault(dst, -1);
    }

    class City {
        int node;
        int price;
        int step;

        City(int node, int price, int step) {
            this.node = node;
            this.price = price;
            this.step = step;
        }
    }

    public int dp(int n, int[][] flights, int src, int dst, int K) {
// distance is a dp[][] array
        // distance[x][k] means: city x's minimal distance from city src, with the restriction of maximal k stops in the route

        if (src == dst) {
            return 0;
        }

        // because a -> b is considered 0 stops, we need to consider one more times
        int[][] distance = new int[n][K + 1];

        for (int[] arr : distance) {
            Arrays.fill(arr, 150000000);
            // make all of them to be a far away distance
        }

        distance[src][0] = 0;
        // direct route, no stop
        for (int[] f : flights) {
            if (f[0] == src) {
                distance[f[1]][0] = Math.min(distance[f[1]][0], f[2]);
            }
        }

        // how many stops we can have in the way from src to dst
        for (int i = 1; i <= K; i++) {
            // traverse all cities
            // update the minimal distance from last loop's result, used for the below flights for loop
            for (int k = 0; k < n; k++) {
                distance[k][i] = distance[k][i - 1];
            }

            // greedily update all routes
            for (int[] f : flights) {
                distance[f[1]][i] = Math.min(distance[f[1]][i], distance[f[0]][i - 1] + f[2]);
            }
        }

        return (distance[dst][K] == 150000000) ? -1 : distance[dst][K];
    }
}