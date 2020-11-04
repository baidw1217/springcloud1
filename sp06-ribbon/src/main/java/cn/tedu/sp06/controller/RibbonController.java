package cn.tedu.sp06.controller;

//import com.netflix.discovery.converters.Auto;
import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.web.util.JsonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@Slf4j
public class RibbonController {
    @Autowired
    private RestTemplate rt;

    @GetMapping("/item-service/{orderId}")
    @HystrixCommand(fallbackMethod = "getItemsFB")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId){
        return rt.getForObject("http://item-service/{1}",JsonResult.class,orderId);


    }




    @PostMapping("/item-service/decreaseNumber")
    @HystrixCommand(fallbackMethod = "decreaseNumberFB")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items){
        return rt.postForObject("http://item-service/decreaseNumber",
                items,
                JsonResult.class);
    }

    @GetMapping("/user-service/{userId}")
    @HystrixCommand(fallbackMethod = "getUserFB")
    public JsonResult<User> getUser(@PathVariable Integer userId){
        return rt.getForObject("http://user-service/{1}",JsonResult.class,userId);
    }

    @GetMapping("/user-service/{userId}/score")
    @HystrixCommand(fallbackMethod = "addScoreFB")
    public JsonResult<?> addScore(@PathVariable Integer userId, Integer score){
        return rt.getForObject("http://user-service/{1}/score?score={2}",JsonResult.class,userId,score);
    }


    @GetMapping("/order-service/{orderId}")
    @HystrixCommand(fallbackMethod = "getOrderFB")
    public JsonResult<Order> getOrder(@PathVariable String orderId){
        return rt.getForObject("http://order-service/{1}",JsonResult.class,orderId);
    }

    @GetMapping("/order-service/")
    @HystrixCommand(fallbackMethod = "addOrderFB")
    public JsonResult<?> addOrder(){

        return rt.getForObject("http://order-service/",JsonResult.class);
    }


    public JsonResult<List<Item>> getItemsFB(String orderId){
        return JsonResult.err().msg("获取订单的商品列表失败");


    }





    public JsonResult<?> decreaseNumberFB(List<Item> items){
        return JsonResult.err().msg("获取订单的商品列表失败");
    }


    public JsonResult<User> getUserFB(@PathVariable Integer userId){
        return JsonResult.err().msg("获取订单的商品列表失败");
    }


    public JsonResult<?> addScoreFB(@PathVariable Integer userId, Integer score){
        return JsonResult.err().msg("获取订单的商品列表失败");
    }



    public JsonResult<Order> getOrderFB(@PathVariable String orderId){
        return JsonResult.err().msg("获取订单的商品列表失败");
    }


    public JsonResult<?> addOrderFB(){
        return JsonResult.err().msg("获取订单的商品列表失败");
    }

}
