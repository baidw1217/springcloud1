package cn.tedu.sp04.order.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.sp04.feign.ItemFeignClient;
import cn.tedu.sp04.feign.UserFeignClient;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ItemFeignClient itemFeignClient;
    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Order getOrder(String orderId) {
        Order order=new Order();
        order.setId(orderId);

        List<Item> items = itemFeignClient.getItems(orderId).getData();
        User user = userFeignClient.getUser(1).getData();
        order.setItems(items);
        order.setUser(user);


        return order;
    }

    @Override
    public void addOrder(Order order) {
        log.info("添加订单:"+order);

        itemFeignClient.decreaseNumber(order.getItems());
        userFeignClient.addScore(2,100);

    }
}
