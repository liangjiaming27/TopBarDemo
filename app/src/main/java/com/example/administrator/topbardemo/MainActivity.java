package com.example.administrator.topbardemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topBar = (TopBar) findViewById(R.id.view_topbar);
        topBar.setOnTopbarClickListener(new topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this,"leftClick",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void RightClick() {
                Toast.makeText(MainActivity.this,"RightClick",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
