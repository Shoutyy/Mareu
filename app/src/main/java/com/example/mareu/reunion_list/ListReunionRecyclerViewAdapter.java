package com.example.mareu.reunion_list;


import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mareu.R;
import com.example.mareu.events.DeleteReunionEvent;
import com.example.mareu.model.Reunion;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListReunionRecyclerViewAdapter extends RecyclerView.Adapter<ListReunionRecyclerViewAdapter.ViewHolder> {


    private final List<Reunion> mReunions;
    private TextView tv;

    public ListReunionRecyclerViewAdapter(List<Reunion> items) {
        mReunions = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reunion, parent, false);
        tv = (TextView) view.findViewById(R.id.item_list_item);
        tv.setSelected(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Reunion reunion = mReunions.get(position);
        holder.mReunionItem.setText(reunion.getLieu()+" - "+reunion.getDate()+" - "+reunion.getHeure()+" - "+reunion.getSujet());
        holder.mReunionParticipants.setText(Arrays.toString(reunion.getParticipants())
                .replace("[", "")
                .replace("]", ""));

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteReunionEvent(reunion));
            }
        });
    }


    @Override
    public int getItemCount() {
        return mReunions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_item)
        public TextView mReunionItem;
        @BindView(R.id.item_list_participants)
        public TextView mReunionParticipants;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;
        @BindView(R.id.item_list_reunion)
        public ConstraintLayout mReunionLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

