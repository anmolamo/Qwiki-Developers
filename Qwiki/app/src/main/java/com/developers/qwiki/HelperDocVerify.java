package com.developers.qwiki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HelperDocVerify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_doc_verify);

        Spinner docSpinner = (Spinner) findViewById(R.id.doc);

        String[] items = new String[] { "Voter ID", "Ration Card", "PAN Card", "Driving License", "Passport", "Passbook" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        docSpinner.setAdapter(adapter);

        docSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        Spinner serviceSpinner = (Spinner) findViewById(R.id.services);

        String[] itemsServices = new String[] { "Grocery", "Restaurant", "Hospital", "Ambulance", "Medical Shop" };

        ArrayAdapter<String> adapterServices = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, itemsServices);

        serviceSpinner.setAdapter(adapterServices);

        serviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });






    }
}
