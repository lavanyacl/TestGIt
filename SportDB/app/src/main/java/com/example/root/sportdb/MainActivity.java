package com.example.root.sportdb;

import android.app.Activity.*;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.*;
import com.parse.ParseObject;

import java.util.List;
import java.util.ArrayList;
import android.util.*;
import android.util.*;




public class MainActivity extends ActionBarActivity
{
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "lG75j5BVA3Y7K7HubpV2wMC3D5sJ9cP2mQnryYUy", "Ht2LOvXISgvZkaoMl1WQRzFup4NkF2GznKSO7j3D");

        addListenerOnButton();
        addListenerOnButton1();

    }

    public void addListenerOnButton()
    {


        button = (Button) findViewById(R.id.btnPush);
        button.setOnClickListener(new OnClickListener() {
            EditText id, name, email, phone;
            String ID, Name, Email, Phone;

            @Override
            public void onClick(View arg0) {


                ParseObject testObject = new ParseObject("TestObject");

                id = (EditText) findViewById(R.id.txtID);
                name = (EditText) findViewById(R.id.txtName);
                email = (EditText) findViewById(R.id.txtEmail);
                phone = (EditText) findViewById(R.id.txtPhone);

                ID = id.getText().toString();
                Name = name.getText().toString();
                Email = email.getText().toString();
                Phone = phone.getText().toString();


                testObject.put("ID", ID);
                testObject.put("Name", Name);
                testObject.put("Email", Email);
                testObject.put("Phone", Phone);


                testObject.saveInBackground();
                Toast bread;
                bread = Toast.makeText(getApplicationContext(), "Success" , Toast.LENGTH_LONG);
                bread.show();


            }

        });


    }


    public void addListenerOnButton1()
    {

        //Reading Parse Data to Variables

        button = (Button) findViewById(R.id.btnRead);
        button.setOnClickListener(new OnClickListener()

        {
            //EditText id, name, email, phone;
            String ID, Name, Email, Phone;
          @Override
            public void onClick(View arg0)
            {

                final ParseObject testObject = new ParseObject("TestObject");
               // ParseQuery query = ParseQuery.getQuery("TestObject");
                //query.getInBackground("1wIp4sluuh", new GetCallback<ParseObject>() {
                //    public void done(ParseObject object, ParseException e) {
                 //       if (e == null) {
                            // object will be your testobject data
                  //          String Name = object.getObjectId();
                   //         String Email  = object.getObjectId();
                    //        String Phone  = object.getObjectId();
                            //Toast.makeText(this, "Error " + e, Toast.LENGTH_LONG).show();
                            //Toast.makeText(ParseObject.this,"Error "+ e, Toast.LENGTH_SHORT ).show();
                    //        Toast bread;
                     //       bread = Toast.makeText(getApplicationContext(), "Error" , Toast.LENGTH_LONG);
                      //      bread.show();

                        //} else {
                            // something went wrong
                       // }
                   // }
                //});
                ListView lv;
                lv  = (ListView) findViewById(R.id.listView);
                final Adapter  adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.abc_list_menu_item_layout);

                ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
                query.whereEqualTo("Name", "Latesh");

                query.findInBackground(new FindCallback<ParseObject>() {
                   public void done(List<ParseObject> testObjectList, ParseException e) {
                        Toast bread;
                       String[] Names = new String[10];
                      if (e == null) {
                            bread = Toast.makeText(getApplicationContext(), "Success" , Toast.LENGTH_LONG);
                            bread.show();
                            Log.d("test", "Retrieved " + testObjectList.size() + " testObjects");
                            //access via new for-loop
//                            for (ParseObject obj : testObjectList)
  //                          {
    //                            Names[0]=testObjectList.get(0).toString();
  //                          }
int k=0;
                          for (ParseObject Name : testObjectList)
                          {
                              adapter.getItem(0);
                         }
                          //lv.setAdapter(adapter);
                          bread = Toast.makeText(getApplicationContext(), Names[0] , Toast.LENGTH_LONG);
                          bread.show();
                        } else {
                            bread = Toast.makeText(getApplicationContext(), "Error" , Toast.LENGTH_LONG);
                            bread.show();
                            Log.d("test", "Error: " + e.getMessage());
                        }

                    }
                });



            }

        });


    }

}
