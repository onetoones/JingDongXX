package com.example.jingdongxx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DHActivity extends AppCompatActivity {
int i=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    i--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i<=1){
                        startActivity(new Intent(DHActivity.this,MainActivity.class));
                        finish();
                        break;

                    }
                }
            }
        }).start();

    }
}
