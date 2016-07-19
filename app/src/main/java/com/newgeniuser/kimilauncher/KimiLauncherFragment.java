package com.newgeniuser.kimilauncher;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by KimiChang on 2016/7/19.
 */
public class KimiLauncherFragment extends android.support.v4.app.Fragment {
    private RecyclerView mRecyclerView;
    public static KimiLauncherFragment NewInstance(){
        return new KimiLauncherFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_kimi_launcher,container,false);
        mRecyclerView=(RecyclerView)v.findViewById(R.id.fragment_kimi_launcher_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }
}
