package com.example.multithread;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Activity2";
    Boolean boolbtn = false;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv1 = findViewById(R.id.tv1);
        final TextView tv2 = findViewById(R.id.tv2);
        final TextView tv3 = findViewById(R.id.tv3);
        final Button btn = findViewById(R.id.btnStart);
        final Button nextBtn = findViewById(R.id.next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolbtn = !boolbtn;
                Log.d(TAG, "onClick: "+boolbtn);
                if (!boolbtn) {
                    tv1.setText("Stopped1");
                    tv2.setText("Stopped2");
                    tv3.setText("Stopped3");
                    btn.setText("Start");
                } else {
                    btn.setText("Stop");

                    Thread t1 =   new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (boolbtn){
                                sleep();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv1.setText("Started1");
                                        Log.d(TAG, "run1: "+tv1.getText());
                                    }
                                });
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv1.setText("Activity1");
                                        Log.d(TAG, "run1: "+tv1.getText());
                                        Log.d(TAG, "run1: "+Thread.currentThread());
                                    }
                                });
                            }
                        }
                    });
                    t1.setName("thread tv1");
                    Thread t2 =  new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (boolbtn){
                                sleep();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv2.setText("Started2");
                                        Log.d(TAG, "run2: "+tv2.getText());
                                    }
                                });
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv2.setText("Activity2");
                                        Log.d(TAG, "run2: "+tv2.getText());
                                        Log.d(TAG, "run2: "+Thread.currentThread());
                                    }
                                });
                            }
                        }
                    });
                    t2.setName("thread tv2");
                    t1.start();
                    t2.start();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
    }


    public void sleep(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}