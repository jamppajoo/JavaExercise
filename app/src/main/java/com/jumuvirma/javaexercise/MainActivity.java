package com.jumuvirma.javaexercise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   public final static int value = 1;
    String[] messages = {"ASD1","ASD2"} ;
    String[] dates ={"QWE1","QWE2"} ;

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> subAdapter;
    ListView listView;
    int i= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);


        listView.setAdapter(new ImageAdapter(this,messages,dates));


        /*

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,messages);
        listView.setAdapter(adapter);
        */


    }
    protected void addNotificationClick(View view)
    {
        Intent intent = new Intent(this,DateActivity.class);
        startActivityForResult(intent,value);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (value) : {
                if (resultCode == MainActivity.RESULT_OK) {
                    String message = data.getStringExtra("message");
                    int day = data.getIntExtra("day",1);
                    int month = data.getIntExtra("month",1)+1;
                    int year = data.getIntExtra("year",2000);
                    i++;

                }
                break;
            }
        }
    }
}
class ImageAdapter extends BaseAdapter{

    private Context context;
    private final String[] messages;
    private final String[] dates;

    public ImageAdapter(Context context, String[] messages, String[] dates)
    {
        this.context = context;
        this.messages = messages;
        this.dates = dates;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linearLayout;

        Log.d("A","A");
        if(convertView ==null)
        {
            Log.d("B","B");
            linearLayout = new View(context);
            linearLayout = inflater.inflate(R.layout.itemlist, null);

            TextView textView = (TextView) linearLayout.findViewById(R.id.itemTextView);
            textView.setText("A");
        }
        else
            linearLayout = (View) convertView;
        return linearLayout;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
