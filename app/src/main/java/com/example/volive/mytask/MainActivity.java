package com.example.volive.mytask;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
   ArrayList<String> serviceNamesArr=new ArrayList<>();
   ArrayList<String> serviceImagesArr=new ArrayList<>();
   RecyclerView recycle_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recycle_list=(RecyclerView)findViewById(R.id.recycler_view);
        //recycle_list.setHasFixedSize(true);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recycle_list.setLayoutManager(verticalLayoutmanager);

       posetService();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void  posetService(){

        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("API-KEY","98745786");
        params.put("vendor_id","1");
        client.post("http://voliveafrica.com/perfume/api/Customer/vendorservices", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response=new String(responseBody);
                        try {
                            JSONObject json=new JSONObject(response);
                            String imagepath=json.optString("perfume_images_path");
                            JSONArray array = json.getJSONArray("vendor_perfumes");
                          for(int i=0;i<array.length();i++){
                              JSONObject jsonObject= (JSONObject) array.get(i);
                              String vendor_services_name= (String) jsonObject.get("vendor_services_name");
                              String image=jsonObject.getString("vendor_services_image");
                              serviceNamesArr.add(vendor_services_name);
                              serviceImagesArr.add(image);
                          }
                          ServiceAdapter adapter=new ServiceAdapter(MainActivity.this,serviceNamesArr,serviceImagesArr,imagepath);
                            recycle_list.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        System.out.print("Response==="+"Ntg");
                    }
                }
        );
    }
}
