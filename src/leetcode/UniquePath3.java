package leetcode;

public class UniquePath3 {
    public static void main(String[] args) {
         new UniquePath3().uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}});
    }

    int sx = 0,sy = 0,ex = 0,ey = 0,empty = 1,sum=0,m = 0,n = 0;
    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0){
                    empty++;
                }else if(grid[i][j]==1){
                    sx=i;
                    sy=j;
                }else if(grid[i][j]==2){
                    ex=i;
                    ey=j;
                }
            }
        }
        dfs(grid,sx,sy);
        return sum;

    }
    void dfs(int[][]grid,int x,int y){
        if(invalid(grid,x,y)){
            return;
        }
        if(x==ex&&y==ey){
            if(empty==0){
                sum++;
            }
            return;
        }
        empty--;
        grid[x][y]=-1;//防止往回走
        dfs(grid,x+1,y);
        dfs(grid,x-1,y);
        dfs(grid,x,y+1);
        dfs(grid,x,y-1);
        grid[x][y]=0;
        empty++;
    }

    boolean invalid(int[][]grid,int x,int y){
        return x>=m||x<0||y>=n||y<0||grid[x][y]<0;
    }
}
