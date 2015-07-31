package com.example.root.sportdb;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.*;
import com.parse.ParseObject;
import java.util.ArrayList;
import java.util.List;
import android.util.*;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.view.View.OnClickListener;


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

        addListenerOnButton(); // Push
        addListenerOnButton1(); // Pop
        addListenerOnButton2(); // Unpin-Wipe

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

    public void addListenerOnButton1() {
        //Reading Parse for All Row's -- Listing Records
        button = (Button) findViewById(R.id.btnRead);
        button.setOnClickListener(new OnClickListener() {
            String names, ids, emails, phones;

            @Override
            public void onClick(View arg0) {
                final ParseObject testObject = new ParseObject("TestObject");
                final ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
                query.whereEqualTo("Name", "Latesh"); //query.orderByAscending("Name");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> testObjectList, ParseException e) {
                        if (e == null) {
                            ArrayList<ParseObject> Arlist = new ArrayList<ParseObject>();
                            TextView tvname = (TextView) findViewById(R.id.txtViewName);
                            TextView tvemail = (TextView) findViewById(R.id.txtViewEmail);
                            TextView tvphone = (TextView) findViewById(R.id.txtViewPhone);
                            TextView tvid = (TextView) findViewById(R.id.txtViewID);

                            Arlist.add(testObjectList.get(0));
                            Arlist.add(testObjectList.get(0));
                            Arlist.add(testObjectList.get(0));
                            Arlist.add(testObjectList.get(0));

                            names = Arlist.get(0).get("Name").toString();
                            emails = Arlist.get(0).get("Email").toString();
                            phones = Arlist.get(0).get("Phone").toString();
                            ids = Arlist.get(0).get("ID").toString();
                            tvname.setText(names);
                            tvemail.setText(emails);
                            tvphone.setText(phones);
                            tvid.setText(ids);
                            Log.d("test", "Retrieved " + testObjectList.size() + " testObjects");


                        } else {
                            Toast bread;
                            bread = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
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
        //Wiping - Unpinning Records from Parse Data
        button = (Button) findViewById(R.id.btnDelete);
        button.setOnClickListener(new OnClickListener()
        {
            String names,ids,emails,phones;
            @Override
            public void onClick(View arg0)
            {
                final ParseObject testObject = new ParseObject("TestObject");
                final ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
                query.whereEqualTo("Name", "Latesh"); //query.orderByAscending("Name");
                query.findInBackground(new FindCallback<ParseObject>()
                {
                    public void done(List<ParseObject> testObjectList, ParseException e)
                    {
                        if (e == null)
                        {
                            for (ParseObject delete: testObjectList)
                            {
                                delete.deleteInBackground();
                                Toast bread;
                                bread = Toast.makeText(getApplicationContext(), "Record Unpinned" , Toast.LENGTH_LONG);
                                bread.show();


                            }
                            Log.d("test", "Retrieved " + testObjectList.size() + " testObjects");


                        } else {
                            Toast bread;
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

