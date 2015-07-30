package com.example.root.sportdb;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.*;
import com.parse.ParseObject;

import java.util.List;

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
        addListenerOnButton2();

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
                bread = Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG);
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
                final ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
                query.whereEqualTo("Name", "Latesh");
                query.findInBackground(new FindCallback<ParseObject>() {
                   public void done(List<ParseObject> testObjectList, ParseException e) {
                        Toast bread;
                       String names;
                      if (e == null) {
                            bread = Toast.makeText(getApplicationContext(), "Success" , Toast.LENGTH_LONG);
                            bread.show();
                          TextView tv = (TextView) findViewById(R.id.txtViewName);
                           tv.setText(testObjectList.toString());
                            Log.d("test", "Retrieved " + testObjectList.size() + " testObjects");

                          for(ParseObject TestObject : testObjectList)
                          {
                              TestObject.get("Name");
                              names = (String) TestObject.get("Name").toString();
                              tv.setText(names);

                          }

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


    public void addListenerOnButton2()
    {

        //Reading Parse for All Row's -- Listing Records
        button = (Button) findViewById(R.id.btnRead);
        button.setOnClickListener(new OnClickListener()

        {
           String ID, Name, Email, Phone;
            @Override
            public void onClick(View arg0)
            {

                final ParseObject testObject = new ParseObject("TestObject");
                final ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
                query.whereEqualTo("Name", "Latesh");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> testObjectList, ParseException e) {
                        Toast bread;
                        String names;
                        if (e == null) {
                            bread = Toast.makeText(getApplicationContext(), "Success" , Toast.LENGTH_LONG);
                            bread.show();
                            TextView tv = (TextView) findViewById(R.id.txtViewName);
                            tv.setText(testObjectList.toString());
                            Log.d("test", "Retrieved " + testObjectList.size() + " testObjects");

                            for(ParseObject TestObject : testObjectList)
                            {
                                TestObject.get("Name");
                                names = (String) TestObject.get("Name").toString();
                                tv.setText(names);

                            }

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
