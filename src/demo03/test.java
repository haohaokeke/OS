package demo03;

import org.junit.Test;

import java.util.*;

import static demo03.main.rand;

/**
 * @测试类
 */
public class test {

    @Test
    public void test01() {

        Random rd = new Random();
        int i = rd.nextInt(2);

        System.out.println(i);

    }


    /*public void test02() {

        int[] a = new int[5];

        a[0] = 6;

        Seamphore b = new Seamphore(2, a);

        System.out.println(b.getPcq());

        B(b.getPcq());

    }*/

    public void B(int[] pcq) {

        int i = 0;

        System.out.println(pcq[i]);

    }

    @Test
    public void test03() {

        Process[] p = new Process[2];

        System.out.println(p);

        Process ps = new Process();
        ps.data = 1;

        p[0] = ps;

        System.out.println(p[0].data);
    }


    public static void main(String[] args) {

        int rand = rand();

        System.out.println(rand);


    }

    @Test
    public void test04() {

        Seamphore seamphore = new Seamphore();

        int[] a = new int[3];

        System.out.println(seamphore.getClass());

        System.out.println(a.getClass());


    }

    @Test
    public void test05() {

        int[] arr = new int[5];


    }


    @Test
    public void test06() {

        int[] arr = new int[3];

        /*for (int i = 0; i <= arr.length; i++) {
            System.out.println(arr[i]);
        }*/

        //System.out.println(arr[-1]);




    }




}
