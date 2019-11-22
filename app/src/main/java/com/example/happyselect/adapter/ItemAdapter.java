package com.example.happyselect.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happyselect.R;

import java.util.List;

/**
 * Created on 2019-11-22.
 *
 * @author DengJianMing
 * @describe
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private List<Item> itemList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageButton deleteImageBtn;
        TextView itemText;
        public ViewHolder(View view){
            super(view);
            deleteImageBtn = (ImageButton)view.findViewById(R.id.delete_item_btn);
            itemText = (TextView)view.findViewById(R.id.item_text_view);
        }
    }

    public ItemAdapter(List<Item> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String itemText = itemList.get(position).getItemText();
        holder.deleteImageBtn.setBackgroundResource(R.drawable.delete);
        holder.itemText.setText(itemText);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
