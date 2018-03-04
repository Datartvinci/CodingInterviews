package leetcode;

import java.util.Stack;

/**
 * Created by Datartvinci on 2018/2/25.
 */
public class BipartiteGraph {
    public static void main(String[] args) {
        BipartiteGraph graph = new BipartiteGraph();
        boolean ans;
        ans = graph.isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}});
        System.out.println(ans);
        ans = graph.isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}});
        System.out.println(ans);
    }

    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Stack<Integer> stack = new Stack<>();
        int blue = 1;
        int red = 2;
        color[0] = blue;
        for (int i = 0; i < graph.length; i++) {
            int[] connectedNodes = graph[i];
            for (int connectNode : connectedNodes) {
                stack.add(connectNode);
            }
            while (!stack.isEmpty()) {
                int connectNode = stack.pop();
                if(connectNode==i){
                    continue;
                }
                System.out.println(connectNode);
                for (int nextConnectNode : graph[connectNode]) {
                    stack.add(nextConnectNode);
                }
                if (color[connectNode] != 0) {
                    return false;
                } else {
                    color[connectNode] = color[i] == blue ? red : blue;
                }
            }
        }
        return true;
    }

}
