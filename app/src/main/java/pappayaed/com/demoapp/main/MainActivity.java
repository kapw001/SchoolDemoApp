package pappayaed.com.demoapp.main;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import pappayaed.com.demoapp.R;
import pappayaed.com.demoapp.preference.SessionManagenent;
import pappayaed.com.demoapp.showlist.ShowListActivity;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.DeleteItems {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    private TextView name, total, totalfirst;

    private SessionManagenent sessionManagenent;

    private boolean isMenuSelected = false;

    private boolean isDotSelected = false;

    private double totalAmount = 0.00;

    private ArrayList<Items> itemList;

    private String selectedItemName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManagenent = SessionManagenent.getSessionManagenent();
        itemList = new ArrayList<>();
//
//        Items items = new Items();
//        items.setItemName("Puri");
//        items.setPrice("10.0");
//        itemList.add(items);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerAdapter = new RecyclerAdapter(this, itemList);


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setAdapter(recyclerAdapter);

        name = (TextView) findViewById(R.id.name);
        total = (TextView) findViewById(R.id.total);
        totalfirst = (TextView) findViewById(R.id.totalfirst);

        Map map = sessionManagenent.getSession();
        String n = map.get(SessionManagenent.KEY_EMAIL).toString();
        name.setText(n);
    }


    public void addNumber(View view) {

        String btText = "0";

        double point = .00;

        if (view.getId() == R.id.bdot) {
            isDotSelected = true;
        } else {
            btText = ((Button) view).getText().toString();
        }

        if (isDotSelected) {
            point += Double.parseDouble("." + btText);
            totalAmount += point;
        } else {
//            point += Double.parseDouble("." + btText);
            totalAmount += Double.parseDouble(btText) + point;
        }

        double t = Double.parseDouble(String.format("%.2f", totalAmount));

        totalfirst.setText(t + "0");

//        Toast.makeText(this, "Ok  " + btText, Toast.LENGTH_SHORT).show();
    }


    public void menu(View view) {

        switch (view.getId()) {
            case R.id.idly:
                selectedItemName = "Idly";
                isMenuSelected = true;
                changeViewSates(view, R.id.idly);

                break;
            case R.id.puri:
                selectedItemName = "Puri";
                isMenuSelected = true;
                changeViewSates(view, R.id.puri);
                break;
            case R.id.delete:

                if (itemList.size() > 0) {
                    itemList.remove(itemList.size() - 1);
                    TotalAmountUpdate(itemList);
                    recyclerAdapter.updateItems(itemList);

                    totalAmount = 0.00;
                    isDotSelected = false;
                    totalfirst.setText(totalAmount + "");
                }

                break;
            case R.id.add:

                if (isMenuSelected && totalAmount > 0) {

                    Items items = new Items();
                    items.setItemName(selectedItemName);
                    items.setPrice(totalAmount + "");
                    itemList.add(items);

                    TotalAmountUpdate(itemList);
                    recyclerAdapter.updateItems(itemList);

                    totalAmount = 0.00;
                    isDotSelected = false;
                    totalfirst.setText(totalAmount + "");
                }
                break;
        }

    }

    public void TotalAmountUpdate(ArrayList<Items> list) {

        double t = 0.00;
        ArrayList<Items> l = list;
        for (int i = 0; i < l.size(); i++) {
            Items items = l.get(i);
            t += Double.parseDouble(items.getPrice());
        }

        total.setText(t + "0");

    }


    private void changeViewSates(View view, int id) {

        LinearLayout puril = (LinearLayout) findViewById(R.id.puri);
        puril.setBackgroundColor(getResources().getColor(R.color.menuitemcolor));
        LinearLayout idlyl = (LinearLayout) findViewById(R.id.idly);
        idlyl.setBackgroundColor(getResources().getColor(R.color.menuitemcolor));

        if (id == R.id.idly) {
            idlyl.setBackgroundColor(Color.RED);
        } else if (id == R.id.puri) {
            puril.setBackgroundColor(Color.RED);
        }

    }

    @Override
    public void position(int pos) {

        if (itemList.size() > 0) {
            itemList.remove(pos);
            TotalAmountUpdate(itemList);
            recyclerAdapter.updateItems(itemList);
        }

    }

    public void onPay(View view) {

        Intent intent = new Intent(this, ShowListActivity.class);
        intent.putParcelableArrayListExtra("list", itemList);
        startActivity(intent);

//        Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show();
    }
}
