package com.tyqtest.handler;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button start;
    ProgressDialog dialog = null;
    private Button button_click;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            dialog.setProgress(msg.arg1);
            handler.post(updateThread);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("下载文件");
        dialog.setMessage("正在下载中....");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setIcon(android.R.drawable.ic_input_add);
        dialog.setIndeterminate(false);
        dialog.setCancelable(true);

        start= (Button) findViewById(R.id.download);
        start.setOnClickListener(new  Button.OnClickListener(

        ){
            @Override
            public void onClick(View view) {
                dialog.show();
                handler.post(updateThread);
            }
        });
        button_click = (Button) findViewById(R.id.click);
        button_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AsynicTaskTest.class);
                startActivity(intent);
            }
        });
    }
    Runnable updateThread  = new
            Runnable() {
                int i = 0;
                @Override
                public void run() {
                    i +=1 ;
                    Message message = handler.obtainMessage();
                    message.arg1 = i;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(message);
                    if( i == 100){
                        handler.removeCallbacks(updateThread);
                        dialog.dismiss();
                        message.arg1 =0;
                        Toast.makeText(getApplicationContext(),"下载完成",Toast.LENGTH_LONG).show();
                       // handler.sendMessage(message);
                    }
                }
            };

}
