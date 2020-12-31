package io.gzy.java.concurrency.entity;

/**
 * description: MyTask
 * date: 2020/12/29 6:16 下午
 * author: guizhenyu
 */
public class MyTask implements Runnable{

    private int taskId;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public MyTask(int taskId){
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "taskId=" + taskId +
                '}';
    }

    @Override
    public void run() {
        System.out.println("正在启动的taskId : " + this.taskId+",执行该任务的线程id:" + Thread.currentThread().getId());

        try {
            Thread.sleep((long)(Math.random()*3000));
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("任务 ID :" + this.taskId+ "执行结束");
        }
    }
}
