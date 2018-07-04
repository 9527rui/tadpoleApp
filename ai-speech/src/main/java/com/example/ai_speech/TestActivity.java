package com.example.ai_speech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TestActivity extends AppCompatActivity {
    private   Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        intent=new Intent(this,Voice_service.class);
        Intent intent1=new Intent(this,Voice_service.class);
        intent1.putExtra("run",1);
        startService(intent1);

    }

    public void test1(View view){

        int i = view.getId ();
        if (i == R.id.bt_1) {
            intent.putExtra ("run", 1000);
            startService (intent);

        } else if (i == R.id.bt_2) {
            intent.putExtra ("run", 2000);
            startService (intent);

        } else if (i == R.id.bt_3) {
            intent.putExtra ("run", 3000);
            startService (intent);

        } else if (i == R.id.bt_4) {
            Test ();

        }
    }

    private void Test() {

        new Thread(){
            @Override
            public void run() {
                try {
                    for (int a=0;a<10000;a++){
                        Thread.sleep(10);

                        Log.i("=====", "run:"+a);
                        if (a%1000==0){
                            Intent intent = new Intent(TestActivity.this, Voice_service.class);
                            intent.putExtra("run",a);
                            startService(intent);
                          //  String s=null;
//                            switch (a){
//                                case 1000:
//                                    intent.putExtra("run",a);
//                                    break;
//                                case 2000:
//                                    break;
//                                case 3000:
//                                    break;
//                                case 4000:
//                                    break;
//                                case 5000:
//                                    break;
//                                case 6000:
//                                    break;
//                                case 7000:
//                                    break;
//                                case 8000:
//                                    break;
//                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
