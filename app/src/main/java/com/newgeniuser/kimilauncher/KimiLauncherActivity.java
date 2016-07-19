package com.newgeniuser.kimilauncher;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class KimiLauncherActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return KimiLauncherFragment.newInstance();
    }
/*    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_kimi_launcher);
    }*/
}
