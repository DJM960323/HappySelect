package com.example.happyselect.activity;

import android.content.Intent;
import android.os.Bundle;
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

        Connector.getDatabase();
        List<Item> lists = LitePal.findAll(Item.class);

        for (int i = 0; i < lists.size(); i++) {
            itemList.add(lists.get(i).getItemText());
        }



    }

//    public ArrayList<String> getItemList() {
//        ArrayList<String> items = new ArrayList<>();
//        SharedPreferences preferences = getSharedPreferences("ItemList", MODE_PRIVATE);
//
//        for (int i = 0; i < preferences.getInt("itemLength", 0); i++) {
//            String item = preferences.getString("" + i, "");
//            Log.e("list",preferences.getString("" + i,"") + "已经获取到");
//            items.add(item);
//        }
//        return items;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

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


    @OnClick(R.id.select_button)
    public void onViewClicked() {
        if (!isStart){
            isStart = true;
            selectButton.setText("结   束");
            select(isStart);
        }else{
            isStart = false;
            selectButton.setText("开   始");
            select(isStart);
        }
    }

    public void select(boolean isStart)  {
        if (isStart){
            while (true){
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int index = (int)(Math.random() * itemList.size());
                                    itemText.setText(itemList.get(index));
                                }
                            });
                        }
                    }).sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
