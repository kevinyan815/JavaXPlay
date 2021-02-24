package com.learngenerics;

import com.learngenerics.define.MyGenericClass;
import com.learngenerics.ext.GrandParent;
import com.learngenerics.ext.Parent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UseGenericClassAppMain {
    public static void main(String[] args) {
        userStringList();
        System.out.println("======================");
        useStringListGenerics();
    }

    private static List createStringList() {
        List ret = new ArrayList();
        for (int i = 0; i < 10; i++) {
            ret.add("str" + (i % 5));
        }
        return ret;
    }

    private static void userStringList() {
        List strList = createStringList();
        for (int i = 0; i < strList.size(); i++) {
            // TODO ArrayList默认的元素类型是Object, 使用时必须强制转换，虽然在创建的时候添加的都是String 类型的元素
            String str = (String) strList.get(i);
            str = str.toUpperCase();
            System.out.println(str);
        }
    }

    // TODO 知道没用，得告诉Java说这个List里只有String，告诉Java的方法就是使用泛型
    private static List<String> createStringListGenerics() {
        // TODO >> 泛型的语法就是在支持泛型的类型上，给出类型的定义
        // TODO >> List接口是支持泛型的，类型就是List里允许的元素的类型
        // TODO >> 创建List实例和引用的时候，都可以（非必要）指定泛型对应的类型
        // TODO >> 不指定，那就是Object，所以我们之前用的List的元素就是Object类型
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // TODO >> 如果尝试增加非String类型的元素进去，则会报错
            ret.add("str" + (i % 5));
//            ret.add(new Object());
        }

        return ret;
    }

    private static void useStringListGenerics() {
        List<String> strList = createStringListGenerics();
        for (int i = 0; i < strList.size(); i++) {
            // TODO 不用再强制转换，用着方便
            String str = strList.get(i).toUpperCase();
            System.out.println(str);
        }
    }
}
