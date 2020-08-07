package com.lucky.nacos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lucky.nacos.entity.ModelPage;
import com.lucky.nacos.service.ModelPageService;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

@SpringBootTest
class LuckyNacosClientApplicationTests {

    @Autowired
    private StatefulRedisConnection connection;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelPageService modelPageService;

    @Test
    void contextLoads() {
        RedisCommands sync = connection.sync();
        sync.set("123","呵呵呵");
        System.out.println(sync.get("123"));
        redisTemplate.opsForValue().set("12311","呵呵");
        System.out.println(redisTemplate.opsForValue().get("哈哈"));
    }

    @Test
    void model(){
        
    }

}
