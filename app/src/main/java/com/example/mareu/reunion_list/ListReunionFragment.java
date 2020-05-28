package com.example.mareu.reunion_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.events.DeleteReunionEvent;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ReunionApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class ListReunionFragment extends Fragment {

    private ReunionApiService mApiService;
    private List<Reunion> mReunions;
    private RecyclerView mRecyclerView;
    private Context mContext;



    public static ListReunionFragment newInstance() {
        ListReunionFragment fragment = new ListReunionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getReunionApiService();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_reunion_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        return view;
    }


    private void initList() {
        ListReunionActivity activity = (ListReunionActivity) getActivity();
        String fLieu = activity.fLieu;
        String fDate = activity.fDate;
        mReunions = mApiService.getReunions(fDate, fLieu);
        mRecyclerView.setAdapter(new ListReunionRecyclerViewAdapter(mReunions));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        initList();
    }




    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {
        mApiService.deleteReunion(event.reunion);
        initList();
    }
}