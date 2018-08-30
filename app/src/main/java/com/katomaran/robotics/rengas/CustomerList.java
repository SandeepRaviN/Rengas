package com.katomaran.robotics.rengas;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class CustomerList extends AppCompatActivity {

    ListView listView;
    ListViewAdapter adapter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        if(Build.VERSION.SDK_INT >=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Pink));
        }

       ActionBar actionBar = getSupportActionBar();
       actionBar.setTitle("Search a Customer");

        title = new String[]{"Arun","Ash","Akash","Bala","Babu","Ram","Ravi","Rajesh","Sandeep","Gobi","Kiruba"};
        description = new String[]{"Arun Store...","Ash Store...","Akash Store...","Bala Store...","Babu Store...","Ram Store...","Ravi Store...","Rajesh Store...","Sandeep Store...","Gobi Store...","Kiruba Store..."};
        icon = new int[]{R.drawable.ic_user_24dp,R.drawable.ic_user_24dp,R.drawable.ic_user_24dp,R.drawable.ic_user_24dp,R.drawable.ic_user_24dp,R.drawable.ic_user_24dp,R.drawable.ic_user_24dp,R.drawable.ic_user_24dp,R.drawable.ic_user_24dp,R.drawable.ic_user_24dp,R.drawable.ic_user_24dp};
        listView = findViewById(R.id.listView);

        for (int i =0; i<title.length; i++){
            Model model = new Model(title[i], description[i], icon[i]);
            //bind all strings in an array
            arrayList.add(model);
        }

        // pass result to listViewAdapter class
      adapter = new ListViewAdapter(this, arrayList);
        // bind the adapter to the Listview
      listView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

       MenuItem myActionMenuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) myActionMenuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.action_settings){
            //do your functionality here

                Intent i = new Intent(CustomerList.this,AddCustomer.class);
                startActivity(i);
                finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
} */
/* design row of listview
* adding menu to add searchview in actionbar
* add model class
* add adapter class
* add name images in drawable folder */
