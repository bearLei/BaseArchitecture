package com.ubtech.example_lib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.ubtech.base_lib.utils.ToastUtils;
import com.ubtech.example_lib.adapter.RoomAdapter;
import com.ubtech.example_lib.database.UserInfo;
import com.ubtech.example_lib.viewmodel.UserViewModel;
import com.ubtrobot.log.ALog;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private Button insert;
    private EditText editName;
    private RecyclerView recyclerView;
    private RoomAdapter mAdapter;
    private List<UserInfo> mData;
    private UserViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        subscribeUI();
        insert = findViewById(R.id.room_insert);
        editName = findViewById(R.id.edit_name);
        recyclerView = findViewById(R.id.recycleView);
        initView();
        setListener();
        register();
    }

    private void initView() {
        mData = new ArrayList<>();
        mAdapter = new RoomAdapter(this, mData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    private void setListener() {
        insert.setOnClickListener(v -> {
            String name = editName.getText().toString();
            if (TextUtils.isEmpty(name)) {
                ToastUtils.showShortToast("请输入昵称");
                return;
            }
            UserInfo info = new UserInfo();
            info.setName(name);
            mViewModel.insert(info);
        });


    }

    /**
     * 注册ViewModel
     */
    private void subscribeUI() {
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    /**
     * 列表的数据来源-->数据库
     * 监听数据库的变换:当数据库数据发生变换就会回调以下方法
     * 插入数据--->数据变换--->回调--->刷新页面:完成数据驱动视图变换
     */
    private void register() {
        mViewModel.getAllUserInfo().observe(this, it -> {
            ALog.tag("lei").d("当前线程："+Thread.currentThread().getName());
            mData.clear();
            mData.addAll(it);
            mAdapter.notifyDataSetChanged();
        });
    }
}
