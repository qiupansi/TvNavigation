package com.psqiu.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//更多TV项目资源(如桌面，直播点播，教育，应用市场，文件管理器，设置，酒店应用等)，添加微信：qiupansi
//If you want more TV project resources,such as TvLauncher,TvLive,TvAppStore,TvSettings,TvFileManager,TvEducation,TvHotel,TvMusic,TvRemote and so on，Add me wechat：qiupansi
public class MainActivity extends AppCompatActivity {

    private NavigationLinearLayout mNavigationLinearLayout;
    private NavigationCursorView mNavigationCursorView;
    private TextView mContent;
    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationLinearLayout = (NavigationLinearLayout) findViewById(R.id.mNavigationLinearLayout_id);
        mNavigationCursorView = (NavigationCursorView) findViewById(R.id.mNavigationCursorView_id);
        mContent = (TextView) findViewById(R.id.mContent);
        data.add("我的电视");
        data.add("影视");
        data.add("教育");
        data.add("游戏");
        data.add("应用");
        data.add("动漫");
        data.add("少儿");
        data.add("VIP专区");
        mNavigationLinearLayout.setDataList(data);
        mNavigationLinearLayout.setNavigationListener(mNavigationListener);
        mNavigationLinearLayout.setNavigationCursorView(mNavigationCursorView);
        mNavigationLinearLayout.requestFocus();
        mContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_LEFT:
                        case KeyEvent.KEYCODE_DPAD_RIGHT:
                            mNavigationLinearLayout.jumpTo(keyCode);
                    }
                return false;
            }
        });
    }

    private NavigationLinearLayout.NavigationListener mNavigationListener = new NavigationLinearLayout.NavigationListener() {
        @Override
        public void onNavigationChange(int pos, int keyCode) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                case KeyEvent.KEYCODE_DPAD_RIGHT: //模拟刷新内容区域
                    if (pos >= 0) {
                        mContent.setText("我是内容显示区域，当前页面为：" + data.get(pos) + "，左右切换内容，上键回到导航栏");
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    break;
                case KeyEvent.KEYCODE_MENU://模拟重新编辑刷新了导航栏栏目数据
                    List<String> data = new ArrayList<>();
                    data.add("分栏1");
                    data.add("分栏2");
                    data.add("分栏3");
                    data.add("分栏4");
                    data.add("分栏5");
                    data.add("分栏6");
                    data.add("分栏7");
                    data.add("分栏8");
                    mNavigationLinearLayout.setDataList(data);
                    break;
            }
        }
    };
}
