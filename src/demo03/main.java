package demo03;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @主程序
 */
public class main {

    //缓冲区数量
    public static final int dbs = 2;

    //进程数量(生产者 + 消费者)
    public static final int processNum = 4;


    //等待信号量empty的阻塞队列 --> 生产者
    protected static int[] producerCongestionQueue = new int[processNum];

    //等待信号量full的阻塞队列 --> 消费者
    protected static int[] consumerCongestionQueue = new int[processNum];

    //等待信号量mutex的阻塞队列 --> 共享
    protected static int[] shareCongestionQueue = new int[processNum];

    //定义全局变量
    public static Seamphore empty = new Seamphore(dbs, producerCongestionQueue);          //空缓冲区数
    public static Seamphore full = new Seamphore(0, consumerCongestionQueue);       //缓冲区内可用产品
    public static Seamphore mutex = new Seamphore(1, shareCongestionQueue);         //互斥信号量

    public static Process[] process = new Process[processNum];                            //进程集合

    static DataBuffer dataBuffer = new DataBuffer();


    public static void main(String[] args) {



        //调用方法
        deal();

    }

    public static void deal() {

        System.out.println("-----------------------------------------------");
        System.out.printf("\t\t  生产者-->消费者算法模拟 \n");
        System.out.println("-----------------------------------------------");

        //调用方法
        initProcess();

        rr();


    }

    //初始化进程集合
    public static void initProcess() {

        String digitTemp;
        Random rd = new Random(System.currentTimeMillis());

        for (int i = 0; i < processNum; i++) {

            //Random rd = new Random();

            Process p = new Process();



            //process[i].setRoleFlag(rd.nextInt(2));
            p.roleFlag = rd.nextInt(2);

            if(p.roleFlag == 1) {

                p.name = "生产者";

            } else {

                p.name = "消费者";

            }

            //拼接 --> 转换
            digitTemp = p.name + (i + 1);

            p.name = digitTemp;

            p.currentState = 1;
            p.currentStep = 1;
            p.code = i + 1;

            producerCongestionQueue[i] = 0;
            consumerCongestionQueue[i] = 0;
            shareCongestionQueue[i] = 0;


            //随机指定进程 --> 生产者 or 消费者
            process[i] = p;

        }

    }

    //进程调度
    public static void rr() {

        Process p;

        while (true) {

            //随机选取进程集合内某一进程
            Random rd = new Random();
            int i = rd.nextInt(processNum);     // 0 , 1, 2, 3

            p = process[i];

            //判断 --> 进程为阻塞态,
            if(p.currentState == 0) {

                //重新生成进程
                continue;

            }

            if(p.roleFlag == 1) {                                       //生产者

                produce(p);

            } else if (p.roleFlag == 0){                                //消费者

                consume(p);

            } else
                System.out.println("aa");

            //等待
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }

    //生产者进程
    public static void produce(Process p) {

        //续断点执行
        switch (p.currentStep) {

            //生产产品
            case 1:
                Random r = new Random();
                int n = r.nextInt(1000);

                p.data = n;

                System.out.println("\t\t" + p.getName() + ": 生产一个产品 " + p.getData() + " !");

                //断点后移
                p.currentStep++;

                break;

            //申请空缓冲区
            case 2:
                P(empty, p);

                break;

            //申请访问缓冲区
            case 3:
                P(mutex, p);

                break;

           //将产品送入缓冲区
            case 4:
                push(p.data);

                System.out.println("\t\t" + p.getName() + ": 将产品" + p.getData() + "正送入缓冲区 !");

                p.currentStep++;

                break;

            //释放缓冲区权限
            case 5:
                V(mutex, p);

                break;

            //将产品送入缓冲区, 数量+1
            case 6:
                V(full, p);

                p.currentStep = 1;

                break;

        }


    }


    //消费者进程
    public static void consume(Process p) {

        switch (p.currentStep) {

            //申请从缓冲区取产品
            case 1 :
                P(full, p);

                break;

            //申请访问缓冲区
            case 2 :
                P(mutex, p);

                break;

            //从缓冲区中
            case  3 :
                p.data = pop();

                System.out.println("\t\t" + p.getName() + ": 从缓冲区中正取出产品 " + p.getData() + " !");

                p.currentStep++;

                break;

            //释放缓冲区权限
            case 4 :
                V(mutex, p);

                break;

            //已经从缓冲区取出产品, 空缓冲区数量 +1
            case 5 :
                V(empty, p);

                break;

            //消费产品
            case 6 :
                System.out.println("\t\t" + p.getName() + ": 消费产品" + p.getData() + "!");

               p.currentStep = 1;

                break;

        }



    }

    //P操作
    public static void P(Seamphore s, Process p) {

        s.value -= 1;

        if (s.getValue() >= 0) {

            if (p.roleFlag == 1) {              //生产者

                if (p.currentStep == 2) {

                    System.out.println("\t\t" + p.getName() + ": 申请空缓冲区成功! ");


                } else if(p.currentStep == 3) {

                    System.out.println("\t\t" + p.getName() + ": 申请访问缓冲区成功! ");

                }

            } else if (p.roleFlag == 0) {        //消费者

                if (p.currentStep == 1) {

                    System.out.println("\t\t" + p.getName() + ": 申请取出产品成功! ");

                } else if (p.currentStep == 2) {

                    System.out.println("\t\t" + p.getName() + ": 申请访问缓冲区成功! ");

                }

            }

            //下一步
            p.currentStep++;

        } else if (s.value < 0) {

            if (p.roleFlag == 1) {            //生产者

                if (p.currentStep == 2) {

                    System.out.println("\t\t" + p.getName() + ": 无空缓冲区, 该进程被阻塞! ");

                } else if(p.currentStep == 3) {

                    System.out.println("\t\t" + p.getName() + ": 其他进程正在访问缓冲区, 该进程被阻塞! ");

                }

            } else if (p.roleFlag == 0) {     //消费者

                if (p.currentStep == 1) {

                    System.out.println("\t\t" + p.getName() + ": 无产品可取, 该进程被阻塞! ");

                } else if (p.currentStep == 2) {

                    System.out.println("\t\t" + p.getName() + ": 其他进程正在访问缓冲区, 该进程被阻塞! ");

                }

            }


            sleep(s.pcq, p.code);
            

        }


    }

    //V操作
    public static void V(Seamphore s, Process p) {

        s.value += 1;

        //生产者
        if (p.roleFlag == 1) {
            if(p.getCurrentStep() == 5) {

                System.out.println("\t\t" + p.getName() + ": 释放缓冲区权限! ");

            } else if (p.getCurrentStep() == 6) {

                System.out.println("\t\t" + p.getName() + ": 产品已送入缓冲区, 产品数量增加! ");

            }

            //消费者
        } else if (p.roleFlag == 0) {

            if (p.getCurrentStep() == 4) {

                System.out.println("\t\t" + p.getName() + ": 释放缓冲区权限! ");

            } else if (p.getCurrentStep() == 5) {

                System.out.println("\t\t" + p.getName() + ": 产品已取出, 空缓冲区数量增加! ");

            }

        }

        if (s.value <= 0) {

            wakeup(s.pcq);

        }


       p.currentStep++;


    }

    //阻塞进程
    public static void sleep(int[] pcq, int code) {

        //进程设为阻塞态
        process[code - 1].currentState = 0;

        for (int i = 0; i < processNum; i++) {

            if (pcq[i] == 0) {

                pcq[i] = code;

                break;

            }

        }

    }

    //唤醒进程
    public static void wakeup(int[] pcq) {

        //取出队首进程
        int code = pcq[0] - 1;

        //设置进程为就绪态
        process[code].currentState = 1;

        //当进程被唤醒后继续执行任务
            //生产者
        if (process[code].roleFlag == 1) {

            if (process[code].getCurrentStep() == 2) {

                System.out.println("\t\t" + process[code].getName() + ": 该进程被唤醒! 申请空缓冲区成功! ");


            } else if (process[code].currentStep == 3) {

                System.out.println("\t\t" + process[code].name + ": 该进程被唤醒!申请访问缓冲区成功!");

            }

            //消费者
        }else if (process[code].roleFlag == 0) {

                if (process[code].getCurrentStep() == 1) {

                    process[code].data = pop();

                    System.out.println("\t\t" + process[code].getName() + ": 该进程被唤醒! 申请取产品"+ process[code].getData() +"成功!");

                } else if (process[code].getCurrentStep() == 2) {

                    System.out.println("\t\t" + process[code].getName() + ": 该进程被唤醒! 申请访问缓冲区成功! ");

                }

            }

            process[code].currentStep++;

            //删除队首进程
            for (int i = 1; (i < processNum) && (pcq[i] != 0); i++) {

                pcq[i - 1] = pcq[i];

                if (pcq[i -1] > processNum) {

                    pcq[i - 1] = 0;

                }

            }



    }

    //从缓冲区取出产品
    public static int pop() {

        int data = dataBuffer.buffer[0];

        if (dataBuffer.count > 0) {

            dataBuffer.count--;

        }
        moveDataFroward();

        return data;

    }

    public static void moveDataFroward() {

        for (int i = 0; i < dataBuffer.count; i++) {

            dataBuffer.buffer[i] = dataBuffer.buffer[i + 1];

        }

    }

    //产品送入缓冲区
    public static void push(int data) {

            dataBuffer.buffer[dataBuffer.count++] = data;

    }

    //@Test
    //生成随机不重复数
    public  static int rand() {

        // 种子你可以随意生成，但不能重复
        int[] seed = {0, 1, 2, 3};

        int[] ranArr = new int[4];

        Random ran = new Random();


        // 数量你可以自己定义。
        for (int i = 0; i < seed.length; i++) {

            // 得到一个位置
            int j = ran.nextInt(seed.length - i);

            // 得到那个位置的数值
            ranArr[i] = seed[j];

            // 将最后一个未用的数字放到这里
            seed[j] = seed[seed.length - 1 - i];

            //System.out.println(ranArr[i]);

            return ranArr[i];

        }

        //System.out.println("ranArr:" + Arrays.toString(ranArr));

        return 0;

    }



}
