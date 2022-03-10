package io.gzy.java.concurrency;

/**
 * description: InterruptedTest date: 2022/2/25 11:44 上午
 *
 * @author: guizhenyu
 */
public class InterruptedTest {

  public static class InterruptedTestThread extends Thread{

    @Override
    public void run() {
      System.out.println("thread started");
      try {
        Thread.sleep(20000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("i am wake up");
    }
  }


  public static void main(String[] args) {

    InterruptedTestThread interruptedTestThread = new InterruptedTestThread();
    interruptedTestThread.start();
//    interruptedTestThread.interrupt();
  }

}
