package demo_01;

import java.util.Scanner;

//用于选择的类
public class OS {
    public static void main(String[] args) {

        while (true) {

            //选择要用的调度算法
            System.out.println("*********************算法选择********************");
            System.out.println(" \t\t\t\t\t 1.FCFS");
            System.out.println(" \t\t\t\t\t 2.HPF");
            System.out.println("************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入要选择算法的序号: ");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    FCFS_System.main();
                    break;

                case 2:
                    HPF_System.main();
                    break;

                default:
                    System.out.println("无该选项,请重新输入: ");
            }
        }
    }

}
