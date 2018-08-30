package com.katomaran.robotics.rengas;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavigationCustomer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnItemClick {
    ArrayList<Model> arrayList = new ArrayList<Model>();
    String Shopname, ShopAddress;

    // Recycler view
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private static ArrayList<ListItem> listItems;
    private String countBadge = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //recycler View
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        //listItemss = new ArrayList<>();
        listItems = new ArrayList<>();
        for (int i = 0; i < 15; i++) {

            ListItem listItem = new ListItem("Lavender Incense Stick", "stick", "12 PCS ", "25 EA", "100", R.drawable.test);
            listItems.add(listItem);
            Log.i("count", String.valueOf(i));
        }
        adapter = new ListItemAdapter(listItems, this, this);
        recyclerView.setAdapter(adapter);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Pink));
        }


        TextView shop = findViewById(R.id.store);
        //get shop name & shop address from ListViewAdapter
        Intent intent = getIntent();
        Shopname = intent.getStringExtra("contentTv");
        shop.setText(Shopname);
        ShopAddress = intent.getStringExtra("actionBarTitle");
        shop.setText(ShopAddress);

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, Shopname + '\n' + ShopAddress, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    // Move to cart list on clicking cart...
    public void cart(View v) {
        Intent i = new Intent(NavigationCustomer.this, FinalProduct.class);
        startActivity(i);
    }


    private void prepareListData(List<String> listDataHeader, Map<String,
            List<String>> listDataChild) {


        // Adding child data
        listDataHeader.add("List of Product");


        // Adding child data
        List<String> top = new ArrayList<String>();
        top.add("Dhoop Cups");
        top.add("Food Products");
        top.add("Hygiene Products");
        top.add("Incense Sticks");
        top.add("Oil Prayer Products");
        top.add("Pooja Powders");


        listDataChild.put(listDataHeader.get(0), top); // Header, Child data

    }

    private void enableExpandableList() {
        final ArrayList listDataHeader = new ArrayList<String>();
        final HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.left_drawer);

        prepareListData(listDataHeader, listDataChild);
        ExpandListAdapter listAdapter = new ExpandListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                // Temporary code:
                // till here
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_customer, menu);

        MenuItem menuVal = menu.findItem(R.id.badge);
        MenuItemCompat.setActionView(menuVal, R.layout.badge_layout);
        View menu_hotlist = (View) MenuItemCompat.getActionView(menuVal);
        TextView countBadgeTxt = (TextView) menu_hotlist.findViewById(R.id.counter);
        countBadgeTxt.setText(countBadge);
        enableExpandableList();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(int position, boolean isVisible) {


        listItems.get(position).setVisible(isVisible);
        adapter.notifyItemChanged(position);

        if (isVisible) {
            countBadge = String.valueOf(Integer.parseInt(countBadge) + 1);
        } else {
            countBadge = String.valueOf(Integer.parseInt(countBadge) - 1);
        }
        invalidateOptionsMenu();
    }

    public static List<ListItem> getSelectedItems() {

        List<ListItem> listItemsTemp = new ArrayList<>();
        for (int i = 0; i < listItems.size(); i++) {
            if (listItems.get(i).isVisible()) {
                listItemsTemp.add(listItems.get(i));
            }
        }
        return listItemsTemp;
    }
}
