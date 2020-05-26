package com.ubtech.example_lib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.ubtech.example_lib.adapter.RoomAdapter;
import com.ubtech.example_lib.database.UserInfo;
import com.ubtech.example_lib.viewmodel.TestViewmodel;
import com.ubtech.example_lib.viewmodel.UserViewModel;

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
//        subscribeUI();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        mViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
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
            UserInfo info = new UserInfo();
            info.setName(name);
            mViewModel.insert(info);
        });


    }

    private void subscribeUI() {
//        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void register() {
        mViewModel.getAllUserInfo().observe(this,it -> {
            mData.clear();
            mData.addAll(it);
            mAdapter.notifyDataSetChanged();
        });
    }
}
