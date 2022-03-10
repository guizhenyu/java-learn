package cn.iocoder.springboot.eventdemo.service;

import cn.iocoder.springboot.eventdemo.event.UserRegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CouponService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    public String addCoupon(UserRegisterEvent event) {
        logger.info("[addCoupon][给用户({}) 发放优惠劵]", event.getUsername());
        return "";

    }

}
