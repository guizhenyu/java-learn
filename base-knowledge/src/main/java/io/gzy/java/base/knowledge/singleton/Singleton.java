package io.gzy.java.base.knowledge.singleton;

/**
 * description: SingleTon
 * date: 2021/3/11 1:20 上午
 * @author: guizhenyu
 */
public class Singleton {

    public volatile static Singleton obj;

    private int a;

    private Singleton(){
      a = 3;
    }

    public static Singleton getObj(){

      if (obj == null){

        synchronized (Singleton.class){
          if (obj == null){
            obj = new Singleton();
          }
        }
      }

      return obj;
    }

  public static void main(String[] args) {
    getObj();
  }

}
