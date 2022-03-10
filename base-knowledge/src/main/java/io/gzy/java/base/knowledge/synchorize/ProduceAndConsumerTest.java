package io.gzy.java.base.knowledge.synchorize;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: ProduceAndConsumerTest
 * date: 2021/3/31 7:30 下午
 *
 * @author: guizhenyu
 */
public class ProduceAndConsumerTest {

    private static Map<String,String> map = new ConcurrentHashMap();

//    private

    public void produce(){
        synchronized (map){

        }
    }
}
