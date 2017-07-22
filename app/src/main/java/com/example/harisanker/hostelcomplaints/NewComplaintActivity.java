package com.example.harisanker.hostelcomplaints;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dell on 21-06-2017.
 */
 public class NewComplaintActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaint);

        final String url = "https://students.iitm.ac.in/studentsapp/complaints_portal/hostel_complaints/addComplaint.php";
        final EditText prox = (EditText)findViewById(R.id.editText_room_number);
        final String roll_no = Utils.getprefString(UtilStrings.ROLLNO, this);
        final String name = Utils.getprefString(UtilStrings.NAME, this);

        final Spinner spinner_complaint_title = (Spinner) findViewById(R.id.spinner_complaint_title);
        //spinner_complaint_title.setOnItemSelectedListener(this);
        List<String> title = new ArrayList<String>();
        title.add("Washing Machine");
        title.add("Water Dispenser");
        title.add("Washroom");
        title.add("Problems in your room");
        title.add("Problems in your wing");


        ArrayAdapter<String> dataAdapter_title = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, title);
        dataAdapter_title.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_complaint_title.setAdapter(dataAdapter_title);

        final Spinner spinner_complaint_description = (Spinner) findViewById(R.id.spinner_complaint_description);

        List<String> description1 = new ArrayList<String>();
        List<String> description2 = new ArrayList<String>();
        List<String> description3 = new ArrayList<String>();
        List<String> description4 = new ArrayList<String>();
        List<String> description5 = new ArrayList<String>();


        description1.add("Power supply not proper");
        description1.add("Check water error");
        description1.add("Water outlet problem");
        description1.add("Dryer not functional");
        description1.add("Not even starting wash cycle");
        description1.add("Don't know what's the problem");

        final ArrayAdapter<String> dataAdapter_description1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, description1);
        dataAdapter_description1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        description2.add("Power supply not proper");
        description2.add("Heating or cooling not working");
        description2.add("Monkey drank from the dispenser");
        description2.add("Algae is growing");

        final ArrayAdapter<String> dataAdapter_description2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, description2);
        dataAdapter_description2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        description3.add("Power supply not proper");
        description3.add("Taps not properly working");
        description3.add("Showers not properly working");
        description3.add("Towel Hangers not present");
        description3.add("Washroom doors not closing properly");
        description3.add("Flush tanks not working");
        description3.add("Pipes leaking");

        final ArrayAdapter<String> dataAdapter_description3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, description3);
        dataAdapter_description3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        description4.add("Electrical work");
        description4.add("Civil work");
        description4.add("Furniture broken");
        description4.add("Internet problem (LAN port repair)");

        final ArrayAdapter<String> dataAdapter_description4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, description4);
        dataAdapter_description4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        description5.add("Electrical work");
        description5.add("Civil work");
        description5.add("Furniture broken");
        description5.add("Internet problem");
        description5.add("Wing not cleaned regularly");
        description5.add("Do not have a dustbin");
        description5.add("Cloth wires not proper");

        final ArrayAdapter<String> dataAdapter_description5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, description5);
        dataAdapter_description5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_complaint_title.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Spinner spinner = (Spinner) parent;

                    //do this
                    // On selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();
                    switch (position) {
                        case 0:
                            spinner_complaint_description.setAdapter(dataAdapter_description1);
                            break;
                        case 1:
                            spinner_complaint_description.setAdapter(dataAdapter_description2);
                            break;
                        case 2:
                            spinner_complaint_description.setAdapter(dataAdapter_description3);
                            break;
                        case 3:
                            spinner_complaint_description.setAdapter(dataAdapter_description4);
                            break;
                        case 4:
                            spinner_complaint_description.setAdapter(dataAdapter_description5);
                            break;

                    }


                    // Showing selected spinner item
                    //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }


            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Button writeCustomComplaint=(Button)findViewById(R.id.button_write);
        writeCustomComplaint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent=new Intent(NewComplaintActivity.this,CustomComplaintActivity.class);
                startActivity(intent);
            }
        });

        Button saveNewComplnt = (Button)findViewById(R.id.button_save);
        saveNewComplnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String title = spinner_complaint_title.getSelectedItem().toString();
                final String description = spinner_complaint_description.getSelectedItem().toString();
                final String proximity = prox.getText().toString();
                final String mUUID=UUID.randomUUID().toString();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(NewComplaintActivity.this, "sending complaint...", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        //params.put("hostel","narmada");
                        params.put("name",name);
                        params.put("rollno",roll_no);
                        //params.put("roomno",room_no);
                        params.put("title",title);
                        params.put("proximity",proximity);
                        params.put("description",description);
                        //params.put("tags","");
                        params.put("upvotes","0");
                        params.put("downvotes","0");
                        params.put ("resolved","0");
                        params.put("uuid",mUUID);
                        //params.put("datetime","");
                        return params;
                    }
                };
                MySingleton.getInstance(NewComplaintActivity.this).addToRequestQueue(stringRequest);
            }
        });

    }

}
