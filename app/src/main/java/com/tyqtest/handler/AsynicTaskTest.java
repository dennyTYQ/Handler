package com.tyqtest.handler;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 谭雅清 on 2017/10/25.
 */

public class AsynicTaskTest extends Activity  implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        isRunning = true;
        TimeTickLoad timeTickLoad = new TimeTickLoad();
        timeTickLoad.execute();


    }

    private TextView textView_count;
    private Button button_count;
    private  int count = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView_count = (TextView) findViewById(R.id.text_count);
        button_count = (Button) findViewById(R.id.click_count);
       // textView_count.setOnClickListener(this);
        button_count.setOnClickListener(this);

    }
    private  class  TimeTickLoad extends AsyncTask<Void, Integer,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            textView_count.setText("时间已经过去了"+String.valueOf(count)+ "S");
                    super.onProgressUpdate(values);
        }



        @Override
        protected Void doInBackground(Void... voids) {
            while (isRunning){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                publishProgress(0);
            }
            return null;
        }
    }
}
