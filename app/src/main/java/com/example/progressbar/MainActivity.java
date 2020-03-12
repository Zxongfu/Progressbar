package com.example.progressbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// TODO: 2020/3/13 Practice progressBar
public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    int progress = 0;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressbar);
        textView = findViewById(R.id.textView);
        progressBar.setMax(100);
        textView.setText(progress + "%");

        //use Thread update progress
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(0);
                DoWork();
            }
        });
        thread.start();

    }

    private void DoWork() {
        //update progress pre sec with loop
        while (true) {
            for (progress = 0; progress <= 100; progress += 5) {
                try {
                    progressBar.setProgress(progress);
                    progressBar.setSecondaryProgress(progress + 5);
                    //update UIThread(textView)
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(progress + "%");
                            Log.i("a", String.valueOf(progress));
                        }
                    });
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
