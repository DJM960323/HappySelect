package com.example.happyselect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.happyselect.R;
import com.example.happyselect.adapter.Item;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //用户输入的数据的集合
    private List<String> itemList = new ArrayList<>();

    private boolean isStart = false;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.item_text)
    TextView itemText;
    @BindView(R.id.select_button)
    Button selectButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        //actionBar
        setSupportActionBar(toolbar);

        //创建数据库
        Connector.getDatabase();
        //启动的时候获取itemList数据
        getList();
        Log.e("Tag","MainActivity.onCreate");

    }

    //加载toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    //toolbar上面的按钮的点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "新功能，敬请期待☺", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "新功能，敬请期待☺", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }


    //处理开始按钮的点击事件
    @OnClick(R.id.select_button)
    public void onViewClicked() {
        if (isStart) {
            isStart = false;
            select();
            selectButton.setText("开   始");
        } else {
            isStart = true;
            select();
            selectButton.setText("结   束");
        }
    }

    //这个方法可以实现文本每隔两秒就改变一次,
    public void select() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                itemText.setText(itemList.get(msg.arg1));
                super.handleMessage(msg);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isStart) {
                    Message msg = new Message();
                    //随机生成下标
                    int index = (int) (Math.random() * itemList.size());
                    msg.arg1 = index;
                    //发送给主线程
                    handler.sendMessage(msg);
                    try {
                        //线程睡眠45ms，每隔45ms文本修改一次
                        Thread.sleep(45);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //获取list数据
    public void getList(){
        List<Item> lists = LitePal.findAll(Item.class);
        for (int i = 0; i < lists.size(); i++) {
            itemList.add(lists.get(i).getItemText());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //跳转页面的时候就清空itemList,以免数据错乱
        itemList.clear();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //跳回当前页面的时候就加载ItemList,因为可能有数据更新
        getList();
        Log.e("Tag", "MainActivity.onRestart: ");
    }
}