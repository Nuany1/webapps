package com.lucky.nacos.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
@RefreshScope
public class RedisConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int  port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private int  database;
    @Value("${spring.redis.timeout}")
    private int  timeout;
    @Value("${spring.redis.pool.max-idle}")
    private int  maxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private long  maxWaitMillis;
    @Value("${spring.redis.pool.max-active}")
    private int  maxTotal;



    @Bean
        public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        /*Jackson2JsonRedisSerializer jackson = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson.setObjectMapper(om);
        template.setValueSerializer(jackson); //value 序列化采用jackson*/
        FastJsonRedisSerializer fastjson = new FastJsonRedisSerializer(Object.class);
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        template.setValueSerializer(redisSerializer);
        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        template.setHashValueSerializer(redisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StatefulRedisConnection redisPole(){
        RedisURI redisURI = RedisURI.builder()
                .withHost(host)
                .withPort(port)
                .withPassword(password)
                .withDatabase(database)
                .withTimeout(Duration.of(timeout, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisURI);// <2> 创建客户端
        StatefulRedisConnection<String,String> connection = redisClient.connect();// <3> 创建线程安全的连接
        //RedisCommands<String, String> redisCommands = connection.sync();  // <4> 创建同步命令
        return connection;
    }

    /*@Bean(name = "jedisPool")
    public JedisPool jedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return new JedisPool(config,host,port,timeout,password);
    }
    @Bean
    @ConditionalOnMissingBean(RedisClient.class)
    public RedisClient redisClient(@Qualifier("jedisPool") JedisPool pool) {
        LOGGER.info("初始化……Redis Client==Host={},Port={}", host, port);
        RedisClient redisClient = new RedisClient();
        redisClient.setJedisPool(pool);
        return redisClient;
    }*/
}
