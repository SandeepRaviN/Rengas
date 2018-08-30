package com.katomaran.robotics.rengas;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sandeep on 14-08-2018.
 */

public class ListViewAdapter extends BaseAdapter{

    //variables
    Context mContext;
    LayoutInflater inflater;
    List<Model> modellist;
    ArrayList<Model> arrayList;

    //constructor
    public ListViewAdapter(Context context, List<Model> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Model>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder {
        TextView mTitleTv, mDescTv;
        Button mButtonBv;
        ImageView mIconIv;
    }
        @Override
        public int getCount() {
            return modellist.size();
        }

        @Override
        public Object getItem(int i) {
            return modellist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

    @Override
    public View getView(final int postition, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row, null);

            //locate the views in row.xml
            holder.mTitleTv = view.findViewById(R.id.mainTitle);
            holder.mDescTv = view.findViewById(R.id.mainDesc);
            holder.mIconIv = view.findViewById(R.id.mainIcon);
            holder.mButtonBv = view.findViewById(R.id.order);


            view.setTag(holder);

        }
        else {
            holder = (ViewHolder)view.getTag();
        }
        //set the results into textviews
        holder.mTitleTv.setText(modellist.get(postition).getTitle());
        holder.mDescTv.setText(modellist.get(postition).getDesc());
        //set the result in imageview
        holder.mIconIv.setImageResource(modellist.get(postition).getIcon());
        holder.mButtonBv.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String infoa = modellist.get(postition).getDesc();
                String infob = modellist.get(postition).getTitle();
               // code later

                    //start NewActivity with title for actionbar and text for textview

                   Intent intent = new Intent(mContext, NavigationCustomer.class);

                    intent.putExtra("actionBarTitle", infob);
                    intent.putExtra("contentTv",infoa);
                    mContext.startActivity(intent);
            }
        });

        //listview item clicks
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String info = modellist.get(postition).getDesc();
//
//               // code later
//
//                    //start NewActivity with title for actionbar and text for textview
//
//                   Intent intent = new Intent(mContext, CustomerPage.class);
//
//                    intent.putExtra("actionBarTitle", info);
//                    intent.putExtra("contentTv",info);
//                    mContext.startActivity(intent);
//
//            }
//        });


        return view;
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()==0){
            modellist.addAll(arrayList);
        }
        else {
            for (Model model : arrayList){
                if (model.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}