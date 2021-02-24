package com.exception.category;

public class MustHandle {
    public static void main(String[] args) throws ClassNotFoundException {
        // 非继承自RuntimeException的异常在编程时强制要求处理
        // 处理时既可以Catch 也可以直接在方法声明上标注要抛出这个异常
        try {
            Class clazz = Class.forName("com.exception.category.MustHandle");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
