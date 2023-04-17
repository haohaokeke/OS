package demo03;

import java.util.Arrays;

/**
 * @信号量
 */
public class Seamphore {

    public int value;      //值
    public int[] pcq;

    public Seamphore() {

    }

    public Seamphore(int value, int[] pcq) {
        this.value = value;
        this.pcq = pcq;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int[] getPcq() {
        return pcq;
    }

    public void setPcq(int[] pcq) {
        this.pcq = pcq;
    }

    @Override
    public String toString() {
        return "Seamphore{" +
                "value=" + value +
                ", pcq=" + Arrays.toString(pcq) +
                '}';
    }

}
