package com.ubtech.base_lib.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ubtrobot.log.ALog;

/**
 * Created by lei on 2020/5/22
 * desc:
 */
public class BaseFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    protected Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        ALog.tag(TAG).d("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ALog.tag(TAG).d("onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        ALog.tag(TAG).d("onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        ALog.tag(TAG).d("onPause");
        super.onPause();
    }

    @Override
    public void onStart() {
        ALog.tag(TAG).d("onStart");
        super.onStart();
    }

    @Override
    public void onDestroy() {
        ALog.tag(TAG).d("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        ALog.tag(TAG).d("onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        ALog.tag(TAG).d("onDetach");
        super.onDetach();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ALog.tag(TAG).d("onAttach");
        super.onAttach(context);
    }
}
