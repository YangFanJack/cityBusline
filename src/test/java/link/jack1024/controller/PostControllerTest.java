package link.jack1024.controller;

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
}
