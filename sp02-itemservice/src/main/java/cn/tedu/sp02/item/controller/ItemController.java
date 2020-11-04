package cn.tedu.sp02.item.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.sp01.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sun.reflect.annotation.ExceptionProxy;

import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class ItemController {
    @Value("${server.port}")
    private int port;
    @Autowired
    private ItemService itemService;

    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId) throws Exception {
        log.info("获取订单的商品列表, orderId="+orderId);

        if(Math.random() < 0.9){
            long t = new Random().nextInt(5000);
            log.info("延时:"+t);
            Thread.sleep(t);
        }


        List<Item> items = itemService.getItems(orderId);
        return JsonResult.ok().msg("port="+port).data(items);
    }


    @PostMapping("/decreaseNumber")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items){
        System.out.println("sssssssssssssssssssssss");
        itemService.decreaseNumber(items);
        return JsonResult.ok().msg("减少商品库存成功");

    }
}
