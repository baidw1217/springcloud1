package cn.tedu.sp04.feign;

import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.web.util.JsonResult;
import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class UserFeignClientFB implements UserFeignClient {

    @Override
    public JsonResult<User> getUser(Integer userId) {
        if (Math.random() < 0.5){
            User user = new User(userId, "缓存用户"+userId,"缓存密码"+userId);
            return JsonResult.ok().msg("获取用户信息成功").data(user);
        }

        return JsonResult.err().msg("获取用户信息失败");
    }

    @Override
    public JsonResult<?> addScore(Integer userId, Integer score) {
        return JsonResult.err().msg("增加用户积分失败");
    }
}
