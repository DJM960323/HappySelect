package com.example.happyselect;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happyselect.adapter.Item;
import com.example.happyselect.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //用户输入的数据的集合
    private List<Item> itemList = new ArrayList<>();
    //保存用户输入的数据
     private String itemContent = "";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.item_text)
    TextView itemText;
    @BindView(R.id.select_button)
    Button selectButton;
    @BindView(R.id.item_edit_text)
    EditText itemEditText;
    @BindView(R.id.add_item_btn)
    Button addItemBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList.add(new Item("手撕包菜"));
        itemList.add(new Item("青椒肉丝"));
        itemList.add(new Item("包菜回锅肉"));
        RecyclerView itemRecyclerView = (RecyclerView)findViewById(R.id.item_recycler_view);
        //RecyclerView的布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        //设置RecyclerView的列表方向
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        //设置RecyclerView的布局管理器
        itemRecyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapter = new ItemAdapter(itemList);
        itemRecyclerView.setAdapter(adapter);
        ButterKnife.bind(this);

        //actionBar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "新功能，敬请期待☺", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "新功能，敬请期待☺", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "新功能，敬请期待☺", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }


    @OnClick({R.id.select_button, R.id.add_item_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_button:
                break;
            case R.id.add_item_btn:
                itemContent = itemEditText.getText().toString();
                if ("".equals(itemContent)) {
                    Toast.makeText(this, "请先输入数据", Toast.LENGTH_SHORT).show();
                } else {
                    itemList.add(new Item(itemContent));
                    itemEditText.setText("");
                    Toast.makeText(this, itemContent + "添加成功", Toast.LENGTH_SHORT).show();
                    Log.e("list", "" + itemList.size());
                }

                break;
        }
    }


}
