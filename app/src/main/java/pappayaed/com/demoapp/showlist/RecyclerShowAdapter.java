package pappayaed.com.demoapp.showlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pappayaed.com.demoapp.R;
import pappayaed.com.demoapp.main.Items;
import pappayaed.com.demoapp.main.RecyclerAdapter;

/**
 * Created by yasar on 3/5/17.
 */

class RecyclerShowAdapter extends RecyclerView.Adapter<RecyclerShowAdapter.Row> {

    private Context context;
    private ArrayList<Items> list;

    public RecyclerShowAdapter(Context context, ArrayList<Items> list) {

        this.list = list;
        this.context = context;
    }

    @Override
    public Row onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_itemlist, parent, false);
        return new Row(view);
    }

    @Override
    public void onBindViewHolder(Row holder, int position) {
        Items items = list.get(position);

        holder.name.setText(items.getItemName());
        holder.price.setText(items.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Row extends RecyclerView.ViewHolder {
        private TextView price;
        private TextView name;

        public Row(View v) {
            super(v);

            price = (TextView) v.findViewById(R.id.price);
            name = (TextView) v.findViewById(R.id.name);
        }
    }
}
