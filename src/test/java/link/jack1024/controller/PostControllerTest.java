package link.jack1024.controller;

import link.jack1024.pojo.BusMap;
import org.junit.Test;

import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class PostControllerTest {
    @Test
    public void testAdd(){
        //获取当前系统时间并转化为字符串
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String enterTime = sdf.format(date);
        System.out.println(enterTime);
    }

    @Test
    public void testHa(){
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(5);
        integers.add(4);
        integers.add(3);
        integers.add(1);
        integers.add(2);
        integers.add(1,100);
        integers.add(4,200);
        for (Integer i : integers){
            System.out.println(i);
        }
        ArrayList<Integer> cop = null;
        boolean b = cop.addAll(integers);
        for (Integer i : cop){
            System.out.println(i);
        }
    }

    @Test
    public void testKa(){
        int[][] map = {
            { 0, 8, 1, 2, -1 },
            { 8, 0, -1, 10000, -1 },
            { 1, -1, 0, 2, 3 },
            { 2, 10000, 2, 0, -1 },
            { -1, -1, 3, -1, 0 }
        };
        BusMap busMap = new BusMap();
        busMap.setMapArray(map);
        busMap.recommend(0);
    }
}
