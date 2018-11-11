package com.example.vlad.myapplication.MountDir;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vlad.myapplication.R;

import java.util.List;

public class MountAdapter extends RecyclerView.Adapter<MountAdapter.ViewHolder>{
    private List<Mount> mMountlist;
    private Context mContext;
    private RecyclerView mRecyclerV;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            name = (TextView) v.findViewById(R.id.name);


        }
    }

    public MountAdapter(List<Mount> myDataset, Context context, RecyclerView recyclerView){
        mMountlist = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.mount_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Mount mount = mMountlist.get(position);
        holder.name.setText("Name: " + mount.getName());

    }
    @Override
    public int getItemCount() {
        return mMountlist.size();
    }

}