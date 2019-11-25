package com.example.happyselect.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.happyselect.R;
import com.example.happyselect.adapter.Item;
import com.example.happyselect.adapter.ItemAdapter;
import org.litepal.LitePal;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddItemActivity extends AppCompatActivity {


    public List<String> itemList = new ArrayList<>();

    private RecyclerView itemRecyclerView;
    private ItemAdapter adapter;

    @BindView(R.id.toolbar_add)
    Toolbar toolbarAdd;
    @BindView(R.id.add_item_edit_text)
    EditText addItemEditText;
    @BindView(R.id.add_item_btn)
    Button addItemBtn;
    @BindView(R.id.hint_text)
    TextView hintText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ButterKnife.bind(this);
        itemRecyclerView = (RecyclerView) findViewById(R.id.item_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        itemRecyclerView.setLayoutManager(linearLayoutManager);

        Log.e("Tag","AddItemActivity.onCreate");
        List<Item> items = LitePal.findAll(Item.class);
        for (int i = 0; i < items.size(); i++) {
            itemList.add(items.get(i).getItemText());
        }

        if (itemList.size() != 0) {
            hintText.setVisibility(View.GONE);
        }
        adapter = new ItemAdapter(itemList);
        adapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LitePal.deleteAll(Item.class,"itemText = ?",itemList.get(position));
                itemList.remove(position);
                adapter.notifyDataSetChanged();
                adapter.notifyItemRemoved(position);
            }
        });
        itemRecyclerView.setAdapter(adapter);
        itemRecyclerView.setItemAnimator(new DefaultItemAnimator());
        setSupportActionBar(toolbarAdd);


    }

    @OnClick(R.id.add_item_btn)
    public void onViewClicked() {
        String itemContent = addItemEditText.getText().toString();
        if ("".equals(itemContent)) {
            Toast.makeText(this, "请先输入数据", Toast.LENGTH_SHORT).show();
        } else {

            itemList.add(itemContent);
            Item item = new Item();
            item.setItemText(itemContent);
            item.save();
            adapter.notifyItemInserted(itemList.size() - 1);
            itemRecyclerView.scrollToPosition(itemList.size() - 1);
            addItemEditText.setText("");
            hintText.setVisibility(View.GONE);

        }
    }

}
