package demo_01;

import java.security.PublicKey;

//进程调度信息类__标准javaBean
public class Si {

    //定义成员变量
    private String name;        //进程名
    private double arrivalTime;     //到达时间
    private double runTime;         //运行时间
    private int priority;           //优先级
    private double startTime;         //开始时间       (到1 束0) 比较关系,取大
    private double stopTime;         //结束时间         开始+服务
    private double cycleTime;         //周转时间        完成-到达
    private double jTime;         //带权时间            周转/服务

    //构造方法

    //空参构造
    public Si() {
    }

    //部分参数
    public Si(String name, double arrivalTime, double runTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.runTime = runTime;
        this.priority = priority;

    }

    //全参构造
    public Si(String name, double arrivalTime, double runTime, int priority, double startTime, double stopTime, double cycleTime, double jTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.runTime = runTime;
        this.priority = priority;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.cycleTime = cycleTime;
        this.jTime = jTime;
    }

    //提供get和set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getRunTime() {
        return runTime;
    }

    public void setRunTime(double runTime) {
        this.runTime = runTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getStopTime() {
        return stopTime;
    }

    public void setStopTime(double stopTime) {
        this.stopTime = stopTime;
    }

    public double getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(double cycleTime) {
        this.cycleTime = cycleTime;
    }

    public double getjTime() {
        return jTime;
    }

    public void setjTime(double jTime) {
        this.jTime = jTime;
    }
}
