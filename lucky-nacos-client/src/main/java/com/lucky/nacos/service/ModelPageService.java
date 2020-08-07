package com.lucky.nacos.service;

import com.alibaba.fastjson.*;
import com.lucky.nacos.entity.ModelPage;
import com.lucky.nacos.mapper.ModelPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ModelPageService {
    @Autowired
    ModelPageMapper modelPageMapper;

    public List<ModelPage> findAll(){

        return modelPageMapper.selectAll();
    };


    public Map<String,Object> byExample(){
        Map map = new HashMap();
        Example ex = new Example(ModelPage.class);
        ex.createCriteria().andEqualTo("id","15530");
        List<ModelPage> pages = modelPageMapper.selectByExample(ex);
        
        map.put("page",pages);
        return map;
    }
}
