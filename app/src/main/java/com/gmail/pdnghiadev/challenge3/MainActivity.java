package com.gmail.pdnghiadev.challenge3;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.gmail.pdnghiadev.challenge3.Adapter.MyAdapter;
import com.gmail.pdnghiadev.challenge3.Helper.OnStartDragListener;
import com.gmail.pdnghiadev.challenge3.Helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity implements OnStartDragListener{

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private List<MyItem> dataItems;
    private MyAdapter myAdapter;
    private int count = 0;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // Get data
        List<MyItem> dataItems = getData();

        // Creating new adapter object
        myAdapter = new MyAdapter(dataItems, this, this);
        mRecyclerView.setAdapter(myAdapter);

        // Setting the layoutManager
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new MyItemDecoration(10, myAdapter));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(myAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @NonNull
    private List<MyItem> getData() {
        // Populating our data set
        dataItems = new ArrayList<MyItem>();
        dataItems.add(new MyItem(R.mipmap.ic_access_alarm_black_48dp, "Alarm 48dp"));
        dataItems.add(new MyItem(R.mipmap.ic_airplanemode_active_black_36dp, "Airplanemode 36dp"));
        dataItems.add(new MyItem(R.mipmap.ic_battery_charging_90_black_24dp, "Battery charging 90 24dp"));
        dataItems.add(new MyItem(R.mipmap.ic_brightness_high_black_48dp, "Brightness high 48dp"));
        return dataItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.show_view) {
            switch (count){
                case 0:
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                    count++;
                    break;
                case 1:
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                    count++;
                    break;
                case 2:
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    count++;
                    break;
                case 3:
                    mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    count = 0;
                    break;
            }
            return true;
        }else if(id == R.id.add_item){
            int[] img = {R.mipmap.ic_access_alarm_black_48dp, R.mipmap.ic_airplanemode_active_black_36dp, R.mipmap.ic_battery_charging_90_black_24dp, R.mipmap.ic_brightness_high_black_48dp};
            String[] name = {"Alarm 48dp", "Airplanemode 36dp", "Battery charging 90 24dp", "Brightness high 48dp"};
            Random rd = new Random();
            int rad = rd.nextInt(4);
            switch (count){
                case 0:
                    mRecyclerView.setItemAnimator(new SlideInUpAnimator());
                    break;
                case 1:
                    mRecyclerView.setItemAnimator(new SlideInRightAnimator());
                    break;
                case 2:
                    mRecyclerView.setItemAnimator(new SlideInUpAnimator());
                    break;
                case 3:
                    mRecyclerView.setItemAnimator(new SlideInUpAnimator());
                    break;
            }
            myAdapter.addItem(new MyItem(img[rad], name[rad]));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
