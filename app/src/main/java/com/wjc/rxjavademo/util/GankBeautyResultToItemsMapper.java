package com.wjc.rxjavademo.util;

import android.util.Log;

import com.wjc.rxjavademo.model.GankBeauty;
import com.wjc.rxjavademo.model.GankBeautyResult;
import com.wjc.rxjavademo.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by WJC on 2017/10/2.
 */

public class GankBeautyResultToItemsMapper implements Func1<GankBeautyResult, List<Item>> {

    private static GankBeautyResultToItemsMapper INSTANCE = new GankBeautyResultToItemsMapper();

    private GankBeautyResultToItemsMapper() {

    }

    public static GankBeautyResultToItemsMapper getInstance() {

        return INSTANCE;
    }

    @Override
    public List<Item> call(GankBeautyResult gankBeautyResult) {
        List<GankBeauty> gankBeauties = gankBeautyResult.beauties;
        List<Item> items = new ArrayList<>(gankBeauties.size());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for (GankBeauty gankBeauty : gankBeauties){
            Item item = new Item();
            try {
                Date date = inputFormat.parse(gankBeauty.createdAt);
                item.description = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                item.description = "unknow date";
            }
            item.imgUrl = gankBeauty.url;

            items.add(item);
        }
        return items;
    }
}
