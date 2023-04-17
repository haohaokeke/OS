package demo03;

/**
 * @PCB进程
 */
public class Process {

    public String name;    //进程名

    public int roleFlag;   //进程类型(1, 生产者  0, 消费者)

    public int currentState;   //进程状态(1: 就绪  0, 阻塞)

    public int currentStep;    //断点

    public int data;           //临时数据

    public int code;           //编号

    public Process() {

    }

    public Process(String name, int roleFlag, int currentState, int currentStep, int data, int code) {
        this.name = name;
        this.roleFlag = roleFlag;
        this.currentState = currentState;
        this.currentStep = currentStep;
        this.data = data;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(int roleFlag) {
        this.roleFlag = roleFlag;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", roleFlag=" + roleFlag +
                ", currentState=" + currentState +
                ", currentStep=" + currentStep +
                ", data=" + data +
                ", code=" + code +
                '}';
    }

}
