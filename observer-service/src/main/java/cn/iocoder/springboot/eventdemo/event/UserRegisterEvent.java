package cn.iocoder.springboot.eventdemo.event;

import org.springframework.context.ApplicationEvent;

/**
 * description: UserRegisterEvent
 * date: 2021/4/9 3:57 下午
 *
 * @author: guizhenyu
 */
public class UserRegisterEvent extends ApplicationEvent {

    /**
     * 用户名
     */
    private String username;

    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
