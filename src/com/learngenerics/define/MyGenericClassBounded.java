package com.learngenerics.define;

import com.learngenerics.ext.GrandParent;

public class MyGenericClassBounded <MyType extends GrandParent>{
    private MyType myType;

    public MyGenericClassBounded(MyType myType) {
        myType.getNum();

        this.myType = myType;
    }

    public void setMyType(MyType myType) {
        this.myType = myType;
    }
}
