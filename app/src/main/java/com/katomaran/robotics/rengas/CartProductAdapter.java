package com.katomaran.robotics.rengas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Sandeep on 25-08-2018.
 */
public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ViewHolder> {

    private List<CartProduct> cartProducts;
    private Context context;
    private OnCartClick onCartClick;

    public CartProductAdapter(List<CartProduct> cartProducts, Context context, OnCartClick onCartClick) {
        this.cartProducts = cartProducts;
        this.context = context;
        this.onCartClick = onCartClick;

    }

    public void removeItem(int position) {
        cartProducts.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cartProducts.size());
    }

    public void restoreItem(CartProduct model, int position) {
        cartProducts.add(position, model);
        // notify item added by position
        notifyItemInserted(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_in_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final CartProduct cartProduct = cartProducts.get(position);
        holder.textViewName.setText(cartProduct.getName());
            holder.textViewType.setText(cartProduct.getType());
            holder.textViewSiz.setText(cartProduct.getSiz());
            holder.textViewQut.setText(cartProduct.getQut());
            holder.textViewCost.setText(cartProduct.getCost());
            holder.textViewcount.setText(cartProduct.getCount());
            holder.imageViewProduct.setImageResource(cartProduct.getPro_Img());
            holder.increse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int cost = Integer.parseInt(cartProduct.getCost());
                    int quantity = Integer.parseInt(cartProduct.getCount());
                    int individualPrice = cost / quantity;
                    quantity = quantity + 1;
                    onCartClick.onItemClick(position, String.valueOf(individualPrice * quantity), String.valueOf(quantity));
                }
            });
            holder.decrese.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int cost = Integer.parseInt(cartProduct.getCost());
                    int quantity = Integer.parseInt(cartProduct.getCount());
                    if (quantity - 1 > 0) {
                        int individualPrice = cost / quantity;
                        quantity = quantity - 1;
                        onCartClick.onItemClick(position, String.valueOf(individualPrice * quantity), String.valueOf(quantity));
                    } else {
                        Toast.makeText(context, "Message", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewType;
        public TextView textViewSiz;
        public TextView textViewQut;
        public TextView textViewCost;
        public TextView textViewcount;
        public ImageView imageViewProduct;
        public Button increse;
        public Button decrese;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.TextView1_cart_PN);
            textViewType = itemView.findViewById(R.id.TextView2_cart_PT);
            textViewSiz = itemView.findViewById(R.id.TextView3_cart_S);
            textViewQut = itemView.findViewById(R.id.TextView3_cart_Q);
            textViewCost = itemView.findViewById(R.id.TextView4_cart_C);
            imageViewProduct = itemView.findViewById(R.id.ImageView1_cart_P);
            textViewcount = itemView.findViewById(R.id.quantity_text_view);
            increse = itemView.findViewById(R.id.button2);
            decrese = itemView.findViewById(R.id.button1);
        }

    }
}

