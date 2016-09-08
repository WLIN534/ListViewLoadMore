package com.zl.listviewloadmore;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView listView;
    ListViewAdapter adapter;
    List<String> datas;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x01:
                    adapter.setDatas(datas);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        initData();
        initView();

        loadMore();
    }

    private void loadMore() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            boolean isSlidingToLast = false;
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (listView.getLastVisiblePosition() == (listView.getCount() - 1)) {
                            Log.e("-------","滚动到底部");
                            for (int i = 0; i < 10; i++) {
                                datas.add("测试数据："+(datas.size()+1));
                            }
                            handler.sendEmptyMessage(0x01);
                        }
                        // 判断滚动到顶部

                        if(listView.getFirstVisiblePosition() == 0){
                        }

                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount && !isSlidingToLast) {
                    isSlidingToLast = true;
                } else
                    isSlidingToLast = false;
            }
        });
    }

    private void initView() {
        adapter = new ListViewAdapter(this);
        adapter.setDatas(datas);
        listView.setAdapter(adapter);
    }

    private void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("测试数据："+(datas.size()+1));
        }
    }
}
