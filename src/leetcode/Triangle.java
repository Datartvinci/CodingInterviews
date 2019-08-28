package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {
    int min=Integer.MAX_VALUE;
    @Test
    public void deadLock() throws InterruptedException {
        byte[] lockA=new byte[0];
        byte[] lockB=new byte[0];
        Thread a=new Thread(()->{
            synchronized (lockA) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread a getting lock B");
                synchronized (lockB){
                    System.out.println("thread a got lock B");
                }
            }
        });
        Thread b=new Thread(()->{
            synchronized (lockB) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread b getting lock A");
                synchronized (lockA){
                    System.out.println("thread b got lock A");
                }
            }
        });
        a.start();
        b.start();
        b.join();
        a.join();
    }
    @Test
    public void run(){
        List<List<Integer>> a=new ArrayList<>();
        a.add(Arrays.asList(2));
        a.add(Arrays.asList(3,4));
        a.add(Arrays.asList(6,5,7));
        a.add(Arrays.asList(4,1,8,3));
        System.out.println(minimumTotal(a));
    }
    public int minimumTotal(List<List<Integer>> a) {
        find(a,0,0,0);
        return min;
    }
    void find(List<List<Integer>> a,int level,int index,int sum){
        System.out.println("find("+level+","+index+","+sum+")");
        if(level==a.size()){
            if(min>sum){
                min=sum;
            }
            return;
        }
        List<Integer>list=a.get(level);
        for(int i=index;i<list.size()&&i<=index+1;i++){
            find(a,level+1,i,sum+list.get(i));
        }
    }
}
