package com.wjc.rxjavademo;


import android.app.AlertDialog;
import android.app.Fragment;

import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by WJC on 2017/10/2.
 */

public abstract class BaseFragment extends Fragment {

    protected Subscription subscription;


    @OnClick(R.id.tip_bt)
    void tip() {
        new AlertDialog.Builder(getActivity())
                .setTitle(getTitleRes())
                .setView(getActivity().getLayoutInflater().inflate(getDialogRes(),null))
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unSubsription();
    }

    protected void unSubsription() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    protected abstract int getTitleRes();

    protected abstract int getDialogRes();

}
