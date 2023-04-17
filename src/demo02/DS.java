package demo02;

import java.util.Scanner;

public class DS {

    //    int Resources[M]; //各类资源总数
    //    int Available[M]; //各类资源当前可用数量
    //    int Claim[N][M]; //进程对各类资源的最大需求量
    //    int Allocation[N][M]; //进程已分配资源数量
    //    int Need[N][M]; //进程还需各类资源数量
    //    int Request[M]; //进程当前申请资源数量
    private int n = 0, m = 0;
    private int[] Resources = new int[n];       //各类资源总数
    private int[] Available = new int[n];       //各类资源当前可用数量
    private int[][] Claim = new int[m][n];      //进程对各类资源的最大需求量
    private int[][] Allocation = new int[m][n];  //进程已分配资源数量
    private int[][] Need = new int[m][n];       //进程还需各类资源数量
    private int[] Request = new int[n];         //进程当前申请资源数量


    public DS() {

    }

    public DS(int n, int m, int[] resources, int[] available, int[][] claim, int[][] allocation, int[][] need, int[] request) {

        this.n = n;
        this.m = m;
        Resources = resources;
        Available = available;
        Claim = claim;
        Allocation = allocation;
        Need = need;
        Request = request;

    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int[] getResources() {
        return Resources;
    }

    public void setResources(int[] resources) {
        Resources = resources;
    }

    public int[] getAvailable() {
        return Available;
    }

    public void setAvailable(int[] available) {
        Available = available;
    }

    public int[][] getClaim() {
        return Claim;
    }

    public void setClaim(int[][] claim) {
        Claim = claim;
    }

    public int[][] getAllocation() {
        return Allocation;
    }

    public void setAllocation(int[][] allocation) {
        Allocation = allocation;
    }

    public int[][] getNeed() {
        return Need;
    }

    public void setNeed(int[][] need) {
        Need = need;
    }

    public int[] getRequest() {
        return Request;
    }

    public void setRequest(int[] request) {
        Request = request;
    }


}
