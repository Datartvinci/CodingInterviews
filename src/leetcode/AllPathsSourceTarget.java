package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 *
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
 *
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 */
public class AllPathsSourceTarget {
    List<List<Integer>> list;
    LinkedList<Integer> path;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        list = new LinkedList<>();
        path = new LinkedList<>();
        path.add(0);
        dfs(graph, 0);
        return list;
    }

    void dfs(int[][] graph, int v) {
        //有向无环图，不需要使用visited来记录
        if (v == graph.length - 1) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int adj : graph[v]) {
            path.add(adj);
            dfs(graph, adj);
            path.removeLast();
        }
    }
}
