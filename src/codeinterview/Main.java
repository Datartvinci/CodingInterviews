package codeinterview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Datartvinci on 2017/4/3.
 */
public class Main {

    private class Contents{
        private int i =1;

        public int value(){return i;}

    }
    public Contents contents(){
        return  new Contents(){
            private int i =2;
            public int value(){return 3;}
        };
    }
    public static void main(String[] args) {
        Main main=new Main();
        int value=main.contents().value();
        System.out.println(value);
        List<? super Child> list= new ArrayList<>();
    }
    static class Parent{

    }
    static class Child extends Parent{

    }
}
