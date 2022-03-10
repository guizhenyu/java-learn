package cn.iocoder.springboot.eventdemo.controller;

import cn.iocoder.springboot.eventdemo.event.CostomerSerializer;
import cn.iocoder.springboot.eventdemo.event.UserRegisterEvent;
import cn.iocoder.springboot.eventdemo.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: DemoController
 * date: 2021/4/9 3:55 下午
 *
 * @author: guizhenyu
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(String username) {

        UserRegisterEvent userRegisterEvent = new UserRegisterEvent(new Object());
        for(int i = 0; i < 200; i++) {
            SerializeConfig serializeConfig = new SerializeConfig();
            serializeConfig.put(UserRegisterEvent.class, new CostomerSerializer());
            JSON.toJSONString(userRegisterEvent, serializeConfig);
        }
//        userService.register(username);
        return "success";
    }

}
