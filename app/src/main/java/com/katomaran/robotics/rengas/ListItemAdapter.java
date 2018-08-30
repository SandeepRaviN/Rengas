package com.katomaran.robotics.rengas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandeep on 20-08-2018.
 */

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {

    private List<ListItem> listItem;
    private Context context;
    private OnItemClick onItemClick;


    public ListItemAdapter(List<ListItem> listItems, Context context,OnItemClick onItemClick) {
        this.listItem = listItems;
        this.context = context;
        this.onItemClick=onItemClick;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ListItem listItems = listItem.get(position);

        holder.textViewName.setText(listItems.getName());
        holder.textViewType.setText(listItems.getType());
        holder.textViewSiz.setText(listItems.getSiz());
        holder.textViewQut.setText(listItems.getQut());
        holder.textViewCost.setText(listItems.getCost());
        holder.imageViewProduct.setImageResource(listItems.getPro_Img());

        if(!listItems.isVisible()){
            holder.buttonViewProduct.setImageResource(R.drawable.ic_remove_shopping_cart_black_24dp);
            holder.buttonViewProduct.setBackground(context.getResources().getDrawable(R.drawable.remove_buttom));
        }else {
            holder.buttonViewProduct.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
            holder.buttonViewProduct.setBackground(context.getResources().getDrawable(R.drawable.circle_button));
        }

        holder.buttonViewProduct.setOnClickListener(new View.OnClickListener() {
            // Get the data model based on position

            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position,!listItems.isVisible());
            }
        });


    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewType;
        public TextView textViewSiz;
        public TextView textViewQut;
        public TextView textViewCost;
        public ImageView imageViewProduct;
        public ImageView buttonViewProduct;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.TextView1_list_PN);
            textViewType = itemView.findViewById(R.id.TextView2_list_PT);
            textViewSiz = itemView.findViewById(R.id.TextView3_list_S);
            textViewQut = itemView.findViewById(R.id.TextView3_list_Q);
            textViewCost = itemView.findViewById(R.id.TextView4_list_C);
            imageViewProduct = itemView.findViewById(R.id.ImageView1_list_P);
            buttonViewProduct = itemView.findViewById(R.id.cart);

            // B1.setVisibility(View.GONE);
        }

    }

}
