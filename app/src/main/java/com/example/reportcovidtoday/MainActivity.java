package com.example.reportcovidtoday;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String TAG=MainActivity.class.getSimpleName();
//    private static  String url = "https://covid19.th-stat.com/api/open/today";
    private Button Confirmed;
    private Button NewConfirmed;
    private Button Hospitalized;
    private Button Deaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Confirmed = (Button)findViewById(R.id.Confirmed);
        NewConfirmed = (Button)findViewById(R.id.NewConfirmed);
        Hospitalized = (Button)findViewById(R.id.Hospitalized);
        Deaths = (Button)findViewById(R.id.Deaths);
        new GetContact().execute();

    }

    private class GetContact extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            String url = "https://covid19.th-stat.com/api/open/today";
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makerServiceCall(url);
            try{
                JSONObject mj = new JSONObject(jsonStr);
                String Confirm = mj.getString("Confirmed");
                String NewConfirm = mj.getString("NewConfirmed");
                String Hospitalize = mj.getString("Hospitalized");
                String Death = mj.getString("Deaths");
                Confirmed.setText("จำนวนผู้ติดเชื้อ : "+Confirm+" คน");
                NewConfirmed.setText("จำนวนผู้ติดเชื้อใหม่ : "+NewConfirm+" คน");
                Hospitalized.setText("จำนวนผู้รักษาหายแล้ว : "+Hospitalize+" คน");
                Deaths.setText("จำนวนผู้เสียชีวิต : "+Death+" คน");


            }catch (Exception e) {
                e.printStackTrace();
            }
            return  null;
        } // end method doInBackground
    }
}