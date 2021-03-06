package com.wjc.rxjavademo.module.map_2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wjc.rxjavademo.BaseFragment;
import com.wjc.rxjavademo.R;
import com.wjc.rxjavademo.adapter.ItemListAdapter;
import com.wjc.rxjavademo.model.Item;
import com.wjc.rxjavademo.network.Network;
import com.wjc.rxjavademo.util.GankBeautyResultToItemsMapper;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WJC on 2017/10/5.
 */

public class MapFragment extends BaseFragment {

    private int page = 0;

    @BindView(R.id.pageTV)
    TextView pageTv;

    @BindView(R.id.previousPageBt)
    Button previousPageBt;

    @BindView(R.id.gridRv)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    ItemListAdapter itemListAdapter = new ItemListAdapter();
    Observer<List<Item>> observer = new Observer<List<Item>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            swipeRefreshLayout.setRefreshing(false);
            Log.e("MSG",e.getMessage());
            Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(List<Item> images) {
            swipeRefreshLayout.setRefreshing(false);
            pageTv.setText(getString(R.string.page_with_number,page));
            Log.e("length",images.size()+"!");
            itemListAdapter.setImages(images);
        }
    };


    @OnClick(R.id.previousPageBt)
    void previousPage(){
        loadPage(--page);
        if(page == 1){
            previousPageBt.setEnabled(false);
        }
    }

    @OnClick(R.id.nextPageBt)
    void  nextPage(){
        loadPage(++page);
        if (page == 2){
            previousPageBt.setEnabled(true);
        }
    }

//    private void loadPage(int page){
//        Log.e("PAGE",page+"!");
//        swipeRefreshLayout.setRefreshing(true);
//        unsubscribe();
//        subscription = Network.getGankApi()
//                .getBeauties(10,page)
//                .map(GankBeautyResultToItemsMapper.getInstance())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//
//    }

    private void loadPage(int page) {
        swipeRefreshLayout.setRefreshing(true);
        unsubscribe();
        subscription = Network.getGankApi()
                .getBeauties(10, page)
                .map(GankBeautyResultToItemsMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);
        ButterKnife.bind(this,view);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(itemListAdapter);

        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.RED,Color.BLACK);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_map;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_map;
    }
}
