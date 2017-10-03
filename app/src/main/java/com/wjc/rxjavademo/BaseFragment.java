package com.wjc.rxjavademo;


import android.app.AlertDialog;
import android.app.Fragment;
import android.support.annotation.Nullable;

import butterknife.OnClick;
import butterknife.Optional;
import rx.Subscription;

/**
 * Created by WJC on 2017/10/2.
 */

public abstract class BaseFragment extends Fragment {

    protected Subscription subscription;

    @Optional
    @OnClick(R.id.tip_bt1)
    void tip() {
        new AlertDialog.Builder(getActivity())
                .setTitle(getTitleRes())
                .setView(getActivity().getLayoutInflater().inflate(getDialogRes(), null))
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
