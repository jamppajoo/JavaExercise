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
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   public final static int value = 1;

    ArrayList<String> messages = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> subAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        readFromStorage();

        listView.setAdapter(new ImageAdapter(this,messages,dates));

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

                    messages.add(message);
                    dates.add("" +day + "." + month);
                    writeToStorage(messages,dates);
                    listView.setAdapter(new ImageAdapter(this,messages,dates));

                }
                break;
            }
        }
    }

    public void writeToStorage(ArrayList messages, ArrayList dates){
        this.messages = messages;
        this.dates = dates;
        FileOutputStream os = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try{
            os = this.openFileOutput("SavedNotifications.txt",MODE_PRIVATE);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        osw = new OutputStreamWriter(os);
        bw = new BufferedWriter(osw);

        try{
            bw.write(messages.get(0).toString());
            bw.newLine();
            bw.write(dates.get(0).toString());
            bw.newLine();
            bw.flush();
            bw.close();
            osw.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void readFromStorage(){

        ArrayList<String> readedMessages = new ArrayList<>();
        ArrayList<String> readedDates = new ArrayList<>();
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;


        try{
            is = openFileInput("SavedNotifications.txt");
        }catch (FileNotFoundException e ){
            e.printStackTrace();
        }

        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);

        String message;
        String date;
        try{
            while ((message = br.readLine())!=null){
                readedMessages.add(message);
                date = br.readLine();
                readedDates.add(date);
            }
            br.close();
            isr.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        messages = readedMessages;
        dates = readedDates;
    }
}

class ImageAdapter extends BaseAdapter{

    private Context context;
    private final ArrayList messages;
    private final ArrayList dates;

    public ImageAdapter(Context context, ArrayList messages, ArrayList dates)
    {
        this.context = context;
        this.messages = messages;
        this.dates = dates;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linearLayout;

        if(convertView ==null)
        {
            linearLayout = new View(context);
            linearLayout = inflater.inflate(R.layout.itemlist, null);

            TextView message = (TextView) linearLayout.findViewById(R.id.itemTextView);
            TextView date = (TextView) linearLayout.findViewById(R.id.subItemTextView);

            message.setText(messages.get(position).toString());
            date.setText(dates.get(position).toString());
        }
        else
            linearLayout = (View) convertView;
        return linearLayout;
    }

    @Override
    public int getCount() {
        return messages.size();
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