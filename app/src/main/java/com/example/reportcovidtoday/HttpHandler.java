package com.example.reportcovidtoday;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public String makerServiceCall(String regUrl){
        String response=null;
        try{
            URL url = new URL(regUrl);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response=convertStreamToString(in);
        }catch (Exception e){
            Log.e(TAG,"Exexption" + e.getMessage());
        }
        return response;
    } // emd method makerServiceCall

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String line;
        try{
            while ((line = reader.readLine())!=null){
                sb.append(line).append('\n');
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }// end method convertStreamToString

} // end class
