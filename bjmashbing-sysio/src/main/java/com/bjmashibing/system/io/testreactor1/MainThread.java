package com.bjmashibing.system.io.testreactor1;

/**
 * description: MainThread date: 2021/4/21 9:49 上午
 *
 * @author: guizhenyu
 */
public class MainThread {

  public static void main(String[] args) {
    //1,创建 IO Thread  （一个或者多个）

    //2，我应该把 监听（9999）的  server  注册到某一个 selector上
    SelectorThreadGroup stg = new SelectorThreadGroup(1);

    stg.bind(9999);
  }

}
