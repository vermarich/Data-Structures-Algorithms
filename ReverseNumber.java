import java.io.*;
import java.util.*;

class ReverseNumber {
    public static int reverse(int x) {
        int x1 = Math.abs(x);
        int newNum = 0;
        while(x1>0){
            int n = x1%10;
            int temp = newNum;
            newNum = newNum*10 + n;
            if((newNum - n)/10  != temp)
                return 0;
            x1 = x1/10;
        }
        if(x<0)
            return -newNum;
        else
            return newNum;
    }

    public static void main(String[] args){
        int x = 123;
        System.out.println("Original number => "+ x);
        System.out.println("Reversed number => " + reverse(x));
    }
}