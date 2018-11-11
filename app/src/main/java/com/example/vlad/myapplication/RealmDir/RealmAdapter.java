package com.example.vlad.myapplication.RealmDir;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vlad.myapplication.R;

import java.util.List;


public class RealmAdapter extends RecyclerView.Adapter<RealmAdapter.ViewHolder>{
    private List<Realm> mRealmlist;
    private Context mContext;
    private RecyclerView mRecyclerV;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView type;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            name = (TextView) v.findViewById(R.id.name);
            type = (TextView) v.findViewById(R.id.type);

        }
    }

    public RealmAdapter(List<Realm> myDataset, Context context, RecyclerView recyclerView){
        mRealmlist = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    @Override
    public RealmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.example_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Realm realm = mRealmlist.get(position);
        holder.name.setText("Name: " + realm.getName());
        holder.type.setText("Type: " + realm.getType());

    }
    @Override
    public int getItemCount() {
        return mRealmlist.size();
    }

    }

