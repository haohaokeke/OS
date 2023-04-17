package demo_01;

import java.util.Scanner;

//HPS算法系统
public class HPF_System {

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***********************************HPF***********************************");
        System.out.print("输入进程数目: ");
        int num = scanner.nextInt();

        //创建进程数组对象
        Si[] p = new Si[num];

        System.out.println("请输入各进程信息: \t\t 进程名 \t\t 到达时间 \t\t 所需运行时间 \t\t 优先级");

        for (int i = 0; i < p.length; i++) {

            System.out.println();       //用于换行防止置顶
            System.out.print("请输入进程 " + i + "的信息: ");
            System.out.print(" \t\t ");
            p[i] = new Si(scanner.next(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextInt());

        }

        //调用进程优先级算法
        OS_HPF(p);

    }


    //进程优先级算法
    private static void OS_HPF(Si[] p) {

        sort(p); //排序
        run(p); //执行该进程
        print(p); //显示界面

    }

    //排序算法(冒泡排序法)
    public static void sort(Si[] p) {

        //  1.对HPF型数组中的元素进行一个简单的排序,找到优先级最高的进程,并且把其他进程进行简单排序,方便后续工作
        //冒泡排序:N次循环,每次找到从 i到n-1中优先级最好的进程,放到p[i]
        for (int i = 0; i < p.length; i++) {

            for (int j = i + 1; j < p.length; j++) {

                if (p[i].getArrivalTime() > p[j].getArrivalTime()) {
                    Si temp;
                    temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;

                }
            }
        }
        // 2.每个进程运行完成之后,找到当前时刻已经到达的优先级最大进程
        for (int m = 0; m < p.length; m++) {
            if (m == 0)  //p[0]的优先级最高
                p[m].setStopTime(p[m].getArrivalTime() + p[m].getRunTime());
            else
                p[m].setStopTime(p[m - 1].getStopTime() + p[m].getStopTime());

            // 2.1 找到p[m].finishtime时刻哪些进程已经到达,从下一个进程p[m+1]开始寻找
            int i = 0;
            for (int n = m + 1; n < p.length; n++) {
                if (p[n].getArrivalTime() <= p[m].getArrivalTime()) {

                    i++;
                }
                //2.2 找到p[m].finishtime时刻已经到达的最短进程
                int next = m + 1;
                double min = p[m + 1].getRunTime(); //next进程服务时间为p[m+1].servicetime

                //在 p[m+1]~p[m+i-1]这 i个已经到达的进程中找到最短进程
                for (int k = m + 2; k <= m + i; k++) {
                    if (p[k].getRunTime() < min) {
                        min = p[k].getRunTime();
                        next = k;

                        //2.3 把最短的进程 放在p[m+1]进程处
                        Si temp;
                        temp = p[m + 1];
                        p[m + 1] = p[next];
                        p[next] = temp;

                    }

                }
            }
        }
    }

    //进程执行
    private static void run(Si[] p) {
        for (int k = 0; k < p.length; k++) {
            if (k == 0) {
                p[k].setStartTime(p[k].getArrivalTime());
                p[k].setStopTime(p[k].getArrivalTime() + p[k].getRunTime());

            } else {

                p[k].setStartTime(p[k - 1].getStopTime());
                p[k].setStopTime(p[k - 1].getStopTime() + p[k].getRunTime());

            }
        }
        for (int k = 0; k < p.length; k++) {

            p[k].setCycleTime(p[k].getStopTime() - p[k].getArrivalTime());      //计算该进程周转时间
            p[k].setjTime(p[k].getCycleTime() / p[k].getRunTime());             //计算该进程带权周转时间

        }
    }


    //结果回显
    private static void print(Si[] p) {

        System.out.println("***********************************FCFS***********************************");
        System.out.print("采用HPF算法的进程调度顺序为: ");

        for (int i = 0; i < p.length; i++) {
            //否则直接输出进程名
            if (i == p.length - 1) {
                System.out.print(p[i].getName());

            } else      //如果i与集合长度-1,则输出对应的排序顺序并打印-->
                System.out.print(p[i].getName() + "-->");

        }
        System.out.println("\n");
        System.out.println("具体进程调度信息: ");
        System.out.println("进程名 \t\t 优先级 \t\t 到达时间 \t\t 运行时间 \t\t 开始时间 \t\t 结束时间 \t\t 周转时间 \t\t 带权周转时间 ");

        for (int k = 0; k < p.length; k++) {
            System.out.printf("%3s", p[k].getName());
            System.out.printf(" \t %7d", p[k].getPriority());
            System.out.printf(" \t %10.3f", p[k].getArrivalTime());
            System.out.printf(" \t %10.3f", p[k].getRunTime());
            System.out.printf(" \t %10.3f", p[k].getStartTime());
            System.out.printf(" \t %10.3f", p[k].getStopTime());
            System.out.printf(" \t %10.3f", p[k].getCycleTime());
            System.out.printf(" \t %10.3f\n", p[k].getjTime());
        }
    }
}
