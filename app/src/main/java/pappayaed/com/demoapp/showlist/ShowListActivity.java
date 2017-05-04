package pappayaed.com.demoapp.showlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import pappayaed.com.demoapp.R;
import pappayaed.com.demoapp.main.Items;
import pappayaed.com.demoapp.main.RecyclerAdapter;

public class ShowListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecyclerShowAdapter recyclerAdapter;
    private ArrayList<Items> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        itemList = new ArrayList<>();

        ArrayList<Items> list = getIntent().getParcelableArrayListExtra("list");

        itemList.addAll(list);
        recyclerAdapter = new RecyclerShowAdapter(this, itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
    }
}
