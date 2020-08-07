package com.lucky.nacos;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LyckyList {
    public Character name ;

    public Character getName() {
        return name;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("name","哈哈哈");
        object.put("age",123);
        object.put("sex","男");
        jsonArray.add(object);
        jsonArray = jsonArray.stream().map( s -> {
            JSONObject obj = (JSONObject) s;
            String age = obj.getString("age");
            return age;
        }).collect(Collectors.toCollection(JSONArray::new));
        System.out.println(jsonArray);
    }
    @Test
    public  void LocalTest(){
        LyckyList list = new LyckyList();
        String st = "";
        System.out.println(list.toString());

    }

    @Test
    public void dataTest(){
        JSONObject jsonObject = JSONObject.parseObject("{\"1\": {\"name\":\"maple\",\"sex\":\"man\",\"childrens\":[{\"name\":\"草根\",\"sex\":\"man\",\"date\":\"2018-01-01\"},{\"name\":\"merry\",\"sex\":\"woman\",\"date\":\"2017-01-01\"},{\"name\":\"liming\",\"sex\":\"woman\",\"date\":\"2016-01-01\"}]}}");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Comparator<Object> dateComparator = (a, b) -> {
            int result = 0;
            try {
                Date dt1 = df.parse(((JSONObject)a).getString("date"));
                Date dt2 = df.parse(((JSONObject)b).getString("date"));
                result = dt1.compareTo(dt2);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                return result;
            }
        };
        jsonObject.forEach((key, val) -> {
            JSONObject obj = (JSONObject) val;
            if (obj.getJSONArray("childrens") != null) {
                JSONArray array = obj.getJSONArray("childrens");
                array = array.stream().filter(arrObj -> !"merry".equals(((JSONObject) arrObj).getString("name")))
                        .sorted(dateComparator)
                        .collect(Collectors.toCollection(JSONArray::new));
                obj.put("childrens", array);
            } else {
                obj.put("childrens", new JSONArray());
            }
        });
        System.out.println(jsonObject);
    }
    @Test
    public void json(){
    }
}
