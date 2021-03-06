package com.example.mat.hamurtamu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mat.hamurtamu.model.Kost;

import java.util.List;

///**
// * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
// * specified {@link OnListFragmentInteractionListener}.
// *
// */
public class MyKostRecyclerViewAdapter extends RecyclerView.Adapter<MyKostRecyclerViewAdapter.ViewHolder> {


    private final List<Kost> mValues;
//    private final OnListFragmentInteractionListener mListener;

    public MyKostRecyclerViewAdapter(List<Kost> items) {
        mValues = items;
//        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_kost, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
        holder.textAlamat.setText(mValues.get(position).getAlamat());
        holder.textHarga.setText(mValues.get(position).getHarga());

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textAlamat;
        public final TextView textHarga;
//        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textAlamat = view.findViewById(R.id.alamat);
            textHarga = view.findViewById(R.id.status);
        }
    }
}
