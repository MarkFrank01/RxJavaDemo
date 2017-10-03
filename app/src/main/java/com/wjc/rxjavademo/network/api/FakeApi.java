package com.wjc.rxjavademo.network.api;

import android.support.annotation.NonNull;

import com.wjc.rxjavademo.model.FakeThing;
import com.wjc.rxjavademo.model.FakeToken;


import java.util.Random;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by WJC on 2017/10/3.
 */

public class FakeApi {
    Random random = new Random();

    public Observable<FakeToken> getFakeToken(@NonNull String fakeAuth){
        return  Observable.just(fakeAuth)
                         .map(new Func1<String, FakeToken>() {
                             @Override
                             public FakeToken call(String faketoken) {
                                 //we should add some delay random to mock the network delay
                                 int fateNetWorkTimeCost = random.nextInt(500) + 500;

                                 try {
                                     Thread.sleep(fateNetWorkTimeCost);
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }
                                 FakeToken fakeToken = new FakeToken();
                                 fakeToken.token = createToken();

                                 return fakeToken;
                             }
                         });
    }

    private Observable<FakeThing> getFakeDate(FakeToken fakeToken){
        return Observable.just(fakeToken)
                .map(new Func1<FakeToken, FakeThing>() {
                    @Override
                    public FakeThing call(FakeToken fakeToken) {
                        //we should add some delay random to mock the network delay
                        int fakeNetWorkTimeCost = random.nextInt(500)  + 500;
                        try {
                            Thread.sleep(fakeNetWorkTimeCost);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(fakeToken.expired){
                            throw new IllegalArgumentException();
                        }

                        FakeThing fakeData = new FakeThing();
                        fakeData.id = (int) (System.currentTimeMillis()%1000);
                        fakeData.name = "FAKE_USER_" + fakeData.id;
                        return fakeData;
                    }
                });
    }

    private static String createToken(){
        return  "fake_token_" + System.currentTimeMillis() % 10000;
    }
}
