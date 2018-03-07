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
        int[] colors = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if(colors[i]==0&&!validColor(graph,colors,i,1)){
                return false;
            }
        }
        return true;
    }

    private boolean validColor(int[][] graph, int[] colors, int node, int colorToDraw) {
        if(colors[node]!=0){
            return colorToDraw==colors[node];
        }
        colors[node]=colorToDraw;
        for(int nextNode:graph[node]){
            if(!validColor(graph,colors,nextNode,colorToDraw==1?2:1)){
                return false;
            }
        }
        return true;
    }

}
