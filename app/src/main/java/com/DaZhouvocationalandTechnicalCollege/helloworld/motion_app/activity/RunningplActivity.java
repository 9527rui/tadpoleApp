package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;

public class RunningplActivity extends AppCompatActivity implements View.OnClickListener{
    private Button ycts;
    private LinearLayout jhdzxz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runningpl);
        initview();
    }

    private void initview() {
        ycts=findViewById(R.id.ycts);
        ycts.setOnClickListener(this);
        jhdzxz=findViewById(R.id.jhdzxz);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ycts:
                jhdzxz.setVisibility(View.GONE);
                break;
        }
    }
}
