package cn.tedu.sp04.order.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.sp01.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId){
        Order order = orderService.getOrder(orderId);
        return JsonResult.ok().data(order);
    }


    @GetMapping("/")
    public JsonResult<?> addOrder(){
        Order order = new Order("1111111111111",
                new User(8,"用户6","密码8"),
                Arrays.asList(new Item[]{
                        new Item(1,"goods 1",1),
                        new Item(2,"goods 2",2),
                        new Item(3,"goods 3",3),
                        new Item(4,"goods 4",4),
                        new Item(5,"goods 5",5)
                }));
        orderService.addOrder(order);
        return JsonResult.ok().msg("添加订单成功");

    }
}
