package demo02;

import java.util.Scanner;

public class system {
    public static void main(String[] args) {
        //进程初始化
        Scanner sc = new Scanner(System.in);
        //定义一个数组用来存储各进程
        DS[] arr = new DS[sc.nextInt()];    //进程个数
        DS[] arr1 = new DS[sc.nextInt()];   //进程类别数
        int resource = sc.nextInt();        //各类资源总数
        int claim = sc.nextInt();           //各资源的最大需求量
        int allocation = sc.nextInt();      //已分配的量

        for (int i = 0; i < arr.length; i++) {
            //创建对象
            DS ds = new DS();
            //ds.setResources();
        }


    }
}
