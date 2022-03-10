package io.gzy.java.base.knowledge.threadloclal;

/**
 * description: ThreadLocalTest date: 2021/5/8 7:42 下午
 *
 * @author: guizhenyu
 */
public class ThreadLocalTest {

  private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

  public static void main(String[] args) throws InterruptedException {


    for (int i = 0; i < 5; i++){
      new Thread() {

        @Override
        public void run() {
          UserInfo userInfo = new UserInfo();
          userInfo.setName("我是ThreadLocal    " + System.currentTimeMillis());
          ThreadLocalTest threadLocalTest = new ThreadLocalTest();
          threadLocalTest.handleRequest(userInfo);
        }
      }.start();
      Thread.sleep(1000);
    }


    System.out.println("dsgsdfg");

  }


  public void handleRequest(UserInfo userInfo) {
    try {
      // 1.用户信息set到线程局部变量中
      userInfoThreadLocal.set(userInfo);
      doHandle();
    } finally {
      // 3.使用完移除掉
//      userInfoThreadLocal.remove();
    }
//    doHandle();
  }

  //业务逻辑处理
  private void doHandle () {
    // 2.实际用的时候取出来
    UserInfo userInfo = userInfoThreadLocal.get();
    System.out.println(userInfo.toString());
  }
}


class UserInfo {
  private String name;

  public String getName() {
    return name;
  }



  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "UserInfo{" +
        "name='" + name + '\'' +
        '}';
  }
}
