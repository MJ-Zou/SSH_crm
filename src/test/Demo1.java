package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.Customer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.Math.pow;

public class Demo1 {

    //演示fastjson
    @Test
    public void run1(){
        Customer c=new Customer();
        c.setCust_id(20L);
        c.setCust_name("测试");
        c.setCust_phone("120");

        //装换成json字符串
        String s = JSON.toJSONString(c);
        System.out.println(s);
    }

    @Test
    public void run2(){
        List<Customer>list=new ArrayList<Customer>();
        Customer c=new Customer();
        c.setCust_id(20L);
        c.setCust_name("测试");
        c.setCust_phone("120");
        list.add(c);

        Customer c2=new Customer();
        c2.setCust_id(20L);
        c2.setCust_name("测试");
        c2.setCust_phone("120");
        list.add(c2);

        //装换成json字符串
        String s = JSON.toJSONString(list);
        System.out.println(s);
    }

    //默认情况下禁止循环的引用
    @Test
    public void run3(){
        List<Customer>list=new ArrayList<Customer>();
        Customer c=new Customer();
        c.setCust_id(20L);
        c.setCust_name("测试");
        c.setCust_phone("120");

        list.add(c);
        list.add(c);

        //装换成json字符串
        //String s = JSON.toJSONString(list);

        //禁止循环的引用
        String s=JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(s);
    }

    //死循环
    @Test
    public void run4(){
        Person p=new Person();
        p.setPname("美美");

        Role r=new Role();
        r.setPname("管理员");

        p.setRole(r);
        r.setPerson(p);

        //禁止循环的引用
        String s=JSON.toJSONString(r);
        System.out.println(s);
    }

}
