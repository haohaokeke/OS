package demo_01;

//随机打乱输入的字符串

import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        int b = 0;
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        System.out.println("请输入字符串:");
        String str = sc.next();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int a = r.nextInt(str.length());
            if (i == 0) {
                sb.append(str.charAt(a));
                b = a;
            } else {
                if (a == b) {
                    i--;
                } else {
                    sb.append(str.charAt(a));
                    b = a;
                }
            }
        }
        System.out.println(sb);
    }
}