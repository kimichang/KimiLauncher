package com.newgeniuser.kimilauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by KimiChang on 2016/7/19.
 */
public class KimiLauncherFragment extends android.support.v4.app.Fragment {
    private static final String TAG="KimiLauncherFragment";
    private RecyclerView mRecyclerView;
    public static KimiLauncherFragment newInstance(){
        return new KimiLauncherFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_kimi_launcher,container,false);
        mRecyclerView=(RecyclerView)v.findViewById(R.id.fragment_kimi_launcher_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();
        return v;
    }
    private void setupAdapter(){
        Intent startupIntent=new Intent(Intent.ACTION_MAIN);
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm=getActivity().getPackageManager();
        List<ResolveInfo> activities=pm.queryIntentActivities(startupIntent,0);
        Collections.sort(activities,new Comparator<ResolveInfo>(){
        public int compare(ResolveInfo a,ResolveInfo b){
            PackageManager pm=getActivity().getPackageManager();
            return String.CASE_INSENSITIVE_ORDER.compare(a.loadLabel(pm).toString(),b.loadLabel(pm).toString());
        }
        });
        Log.i(TAG,"found"+activities.size()+"activities.");
        mRecyclerView.setAdapter(new ActivityAdapter(activities));
    }

    private class ActivityHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ResolveInfo mResolveInfo;
        private TextView mNameTextView;
        public ActivityHolder(View itemView){
            super(itemView);
            mNameTextView=(TextView)itemView;
            mNameTextView.setOnClickListener(this);
        }
        public void bindActivity(ResolveInfo resolveInfo){
            mResolveInfo=resolveInfo;
            PackageManager pm=getActivity().getPackageManager();
            String appName=mResolveInfo.loadLabel(pm).toString();
            mNameTextView.setText(appName);
        }
        @Override
        public void onClick(View v){
            ActivityInfo activityInfo=mResolveInfo.activityInfo;
            Intent i=new Intent(Intent.ACTION_MAIN).setClassName(activityInfo.applicationInfo.packageName,activityInfo.name);
            startActivity(i);
        }
    }

    private class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder>{
        private final List<ResolveInfo> mActivities;
        public ActivityAdapter(List<ResolveInfo> activities){
            mActivities=activities;
        }
        @Override
        public ActivityHolder onCreateViewHolder(ViewGroup parent,int ViewType){
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false);
            return new ActivityHolder(view);
        }
        @Override
        public void onBindViewHolder (ActivityHolder activityHolder,int position){
            ResolveInfo resolveInfo=mActivities.get(position);
            activityHolder.bindActivity(resolveInfo);
        }
        @Override
        public int getItemCount(){
            return mActivities.size();
        }
    }
}
