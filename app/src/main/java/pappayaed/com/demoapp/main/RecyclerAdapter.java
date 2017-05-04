package pappayaed.com.demoapp.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pappayaed.com.demoapp.R;

/**
 * Created by yasar on 3/5/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Row> {

    private Context context;
    private ArrayList<Items> list;
    private DeleteItems deleteItems;

    public RecyclerAdapter(Context context, ArrayList<Items> list) {
        this.list = list;
        this.context = context;
        deleteItems = (DeleteItems) context;
    }

    public void updateItems(ArrayList<Items> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public Row onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        return new Row(view);
    }

    @Override
    public void onBindViewHolder(Row holder, final int position) {

        Items items = list.get(position);

        holder.name.setText(items.getItemName());
        holder.total.setText("RM " + items.getPrice());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                deleteItems.position(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Row extends RecyclerView.ViewHolder {
        private ImageView delete;

        private TextView total;
        private TextView name;

        public Row(View v) {
            super(v);
            delete = (ImageView) v.findViewById(R.id.menudelete);
            total = (TextView) v.findViewById(R.id.totaltotal);
            name = (TextView) v.findViewById(R.id.nametext);
        }
    }

    interface DeleteItems {
        void position(int pos);
    }
}
