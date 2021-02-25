package com.learngenerics.define;

import com.learngenerics.ext.GrandParent;

public class MyGenericClassBounded <MyType extends GrandParent>{
    // 泛型类型不可以调用方法，比如声明时的 class A <T> {}
    // 在A类内，不能调用泛型T的方法，因为只有使用类A，初始化实例时才知道T它到底是什么类型
    // 不过可以向这里这样为泛型限定范围，这样就能在类内调用泛型类型的方法了，上面知道了MyType
    // 类型必须是GrandParent类或者其子类
    // 使用这个类MyGenericClassBounded也只能再这个范围内指定泛型类型。
    private MyType myType;

    public MyGenericClassBounded(MyType myType) {
        myType.getNum();

        this.myType = myType;
    }

    public void setMyType(MyType myType) {
        this.myType = myType;
    }
}
