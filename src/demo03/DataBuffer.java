package demo03;

import static demo03.main.dbs;

/**
 * @缓冲区
 */
public class DataBuffer {

   public int[] buffer = new int[dbs];

   public int count;       //产品数量

    public DataBuffer() {

    }

    public DataBuffer(int[] buffer, int count) {

        this.buffer = buffer;
        this.count = count;
    }


//    获取指针
    public int[] getBuffer() {
        return buffer;
    }

    //重写方法 -->获取值
    public int getBuffer(int i) {

        return buffer[i];
    }

    public void setBuffer(int[] buffer) {
        this.buffer = buffer;
    }

    //重写方法
    public void setBuffer(int i, int b) {

        this.buffer[i] = b;

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
