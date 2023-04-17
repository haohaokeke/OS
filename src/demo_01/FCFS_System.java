package demo_01;

import java.util.ArrayList;
import java.util.Scanner;

//FCFS算法系统
public class FCFS_System {

    public static void main() {

        //调用界面交互方法
        face();

    }

    //界面交互
    public static void face() {
        System.out.println();
        System.out.println("***********************************FCFS***********************************");
        System.out.println();
        System.out.print("输入进程数目:");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println();

        System.out.println("请输入各进程信息: \t\t 进程名 \t\t 到达时间 \t\t 所需运行时间 ");

        //调用输入方法
        Scanf(num);


    }

    //输入
    public static void Scanf(int num) {

        Scanner sc = new Scanner(System.in);

        //创建一个集合用于存对象
        ArrayList<Si> list = new ArrayList<>();

        //循环输入数据
        for (int i = 1; i <= num; i++) {

            System.out.println();       //用于换行防止置顶
            System.out.print("请输入进程 " + i + "的信息: ");

            //创建进程对象
            Si si = new Si();
            System.out.print(" \t\t ");

            si.setName(sc.next());
            //System.out.print(" \t\t ");
            si.setArrivalTime(sc.nextDouble());
            // System.out.print(" \t\t ");
            si.setRunTime(sc.nextDouble());

            //存入集合中
            list.add(i - 1, si);

            //System.out.println(si.getName() + si.getArrivalTime() +si.getRunTime()

        }

       /* for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }*/

        //调用算法函数
        FCFS(list);


    }

    //算法
    public static void FCFS(ArrayList<Si> list) {

        //System.out.println("wozxing了吗?");
        //判断顺序_根据到达时间
        //double tmp = list.get(0).getArrivalTime();
        //ArrayList<Si> tmp = new ArrayList<>();
        //Si tmp;


        //利用冒泡排序对原输入进行排序(根据到达时间排序)
        for (int i = 0; i < list.size(); i++) {

            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).getArrivalTime() > list.get(j + 1).getArrivalTime()) {

//                    tmp.add(0, list.get(j + 1));                //tmp =list.get(j+1);
//                    list.add(j + 1, list.get(j));              //list.get(j+1) = list.get(j);
//                    list.add(j, tmp.get(0));                   //list.get(j) = tmp;

                    //在向有元素的集合添加元素时现将其原有的元素删除
                    Si tmp = list.get(j + 1);
                    list.remove(j + 1);
                    list.add(j + 1, list.get(j));
                    list.remove(j);
                    list.add(j, tmp);


                }
            }
        }

        //打印进程调度顺序
        System.out.println("***********************************FCFS***********************************");
        System.out.print("采用FCFS算法的进程调度顺序为: ");
        for (int i = 0; i < list.size(); i++) {
            //否则直接输出进程名
            if (i == list.size() - 1) {
                System.out.print(list.get(i).getName());

            } else      //如果i与集合长度-1,则输出对应的排序顺序并打印-->
                System.out.print(list.get(i).getName() + "-->");
        }

        //创建一个集合用来装入新的数据
        ArrayList<Si> newList = new ArrayList();

        //各个信息计算
        for (int i = 0; i < list.size(); i++) {
            //创建进程对象
            Si si = new Si();
            si.setName(list.get(i).getName());
            si.setArrivalTime(list.get(i).getArrivalTime());
            si.setRunTime(list.get(i).getRunTime());

            //si.setStartTime(list.get(i + 1).getArrivalTime() >= list.get(i).getStopTime() ? list.get(i).getArrivalTime() : list.get(i).getStopTime());
            //第一个的开始时间为其到达时间
            if (i == 0) {
                si.setStartTime(0);

            } else {
                //开始时间   (到1 束0) 比较关系,取大
                //System.out.println(list.get(i).getArrivalTime() + "," + newList.get(i - 1).getStopTime());
                if (list.get(i).getArrivalTime() > newList.get(i - 1).getStopTime()) {
                    si.setStartTime(list.get(i).getArrivalTime());

                } else {
                    si.setStartTime(newList.get(i - 1).getStopTime());
                }

            }

            //结束时间      开始+服务
            si.setStopTime(si.getStartTime() + list.get(i).getRunTime());
            //周转时间   完成-到达
            si.setCycleTime(si.getStopTime() - list.get(i).getArrivalTime());
            //带权周转时间    周转/服务
            si.setjTime(si.getCycleTime() / list.get(i).getRunTime());

            //存入新集合中
            newList.add(i, si);

        }
        //调用结果方法进行打印
        resort(newList);


    }

    //结果
    public static void resort(ArrayList<Si> newList) {
        System.out.println();
        System.out.println("具体进程调度信息: ");
        System.out.println("进程名 \t\t 到达时间 \t\t 运行时间 \t\t 开始时间 \t\t 结束时间 \t\t 周转时间 \t\t 带权周转时间 ");
        for (int i = 0; i < newList.size(); i++) {
            System.out.println("  " + newList.get(i).getName() + " \t\t " + newList.get(i).getArrivalTime() + " \t\t\t " + newList.get(i).getRunTime() + " \t\t\t " + newList.get(i).getStartTime() + " \t\t\t " + newList.get(i).getStopTime() + " \t\t\t " + newList.get(i).getCycleTime() + " \t\t\t " + newList.get(i).getjTime());
        }


    }


}
