package com.wjc.rxjavademo.model;

/**
 * Created by WJC on 2017/10/2.
 */

public class FakeToken {
    public String token;

    public boolean expired;

    public FakeToken(){

    }

    public FakeToken(boolean expired){
        this.expired=expired;
    }
}
