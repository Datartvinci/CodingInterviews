package dp.pack;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Datartvinci on 2017/1/31.
 * 有 N 件物品和一个容量为 V 的背包。
 * 放入第 i 件物品耗费的费用是 Ci ，得到的 价值是 Wi。求解将哪些物品装入背包可使价值总和最大。
 */
public class ZeroOnePack {
    static final int N = 5;
    static final int V = 5;

    public static void main(String[] args) {
        int[] c = getRandomArrays(N);
        int[] w = getRandomArrays(N);

        solutionOne(c, w);
        solutionTwo(c,w);

    }

    private static void solutionTwo(int[] c, int[] w) {
        int[] f=new int[V+1];
        Arrays.fill(f,0);
        for(int i=1;i<=N;++i){
            for(int v=V;v>=c[i];--v){
                f[v]=Math.max(f[v],f[v-c[i]]+w[i]);
            }
        }
        System.out.println(f[V]);
    }

    private static void solutionOne(int[] c, int[] w) {
        int[][] f = new int[N + 1][V + 1];
        //F[0,0..V]<-0 0件物品，价值统统为0
        for (int j = 0; j <= V; ++j) {
            f[0][j] = 0;
        }
        //从需要放第一件物品到需要放第N件物品
        for (int i = 1; i <= N; ++i) {
            //背包容量放不下第i件的情况
            for (int j = 0; j < c[i]; ++j) {
                f[i][j] = f[i - 1][j];
            }
            //背包容量放得下第i件的情况
            for (int j = c[i]; j <= V; ++j) {
                //不放第i件，或者放第i件，看哪个更值得
                f[i][j] = Math.max(f[i - 1][j], f[i-1][j - c[i]] + w[i]);
            }
        }
        output(c, w, f);
    }

    private static void output(int[] c, int[] w, int[][] f) {
        int v=V;
        for(int i=N;i>0&&(v-c[i])>=0;--i){
            if(f[i-1][v]<f[i-1][v-c[i]]+w[i]){
                System.out.println("index="+i+",cost="+c[i]+",value="+w[i]);
                v-=c[i];
            }
        }
        System.out.println("total value="+f[N][V]);
    }

    private static int[] getRandomArrays(int n) {
        int[] arrays = new int[n+1];
        for(int i=0;i<=n;++i){
           arrays[i]=(int) (Math.random() * (n+1));
        }
        return arrays;
    }


}
