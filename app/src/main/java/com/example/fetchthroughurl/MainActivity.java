package com.example.fetchthroughurl;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    String url="";
    ProgressDialog dialog;
    ArrayList al=new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /*    username=(EditText)findViewById(R.id.username);
        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.passwoed);*/

        //list = (ListView) findViewById(R.id.list);
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading...");
        //  dialog.show();
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJsonData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "response error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        RequestQueue rQueue= Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);
    }

    void parseJsonData(String response) {
        try{
            JSONObject object=new JSONObject(response);
            JSONArray arr=object.getJSONArray("user");

            for (int i=0;i<arr.length();i++)
            {
                al.add(arr.getJSONObject(i).getString("user"));
            }
            ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
