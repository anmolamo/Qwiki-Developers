package com.e.helperregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HelperDocVerify extends AppCompatActivity {

    ImageView i1,i2;
    EditText et;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_doc_verify);

        i1=findViewById(R.id.image1);
        i2=findViewById(R.id.image2);

        et=findViewById(R.id.adhaarNum);

        b=findViewById(R.id.select);

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

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog=new Dialog(getApplicationContext());
                dialog.setContentView(R.layout.csdialog);
                dialog.setCanceledOnTouchOutside(false);

                ImageButton ib1= (ImageButton) dialog.findViewById(R.id.ib1);
                ImageButton ib2= (ImageButton) dialog.findViewById(R.id.ib2);

                TextView tev1= (TextView) dialog.findViewById(R.id.tev1);
                TextView tev2= (TextView) dialog.findViewById(R.id.tev2);

                ib1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i,101);
                        dialog.dismiss();

                    }
                });

                tev1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i,101);
                        dialog.dismiss();


                    }
                });

                ib2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent();
                        i.setType("image/*");

                        i.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(i,0);
                        dialog.dismiss();

                    }
                });

                tev2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent();
                        i.setType("image/*");

                        i.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(i,0);
                        dialog.dismiss();

                    }
                });

                dialog.show();

            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog=new Dialog(getApplicationContext());
                dialog.setContentView(R.layout.csdialog);
                dialog.setCanceledOnTouchOutside(false);

                ImageButton ib1= (ImageButton) dialog.findViewById(R.id.ib1);
                ImageButton ib2= (ImageButton) dialog.findViewById(R.id.ib2);

                TextView tev1= (TextView) dialog.findViewById(R.id.tev1);
                TextView tev2= (TextView) dialog.findViewById(R.id.tev2);

                ib1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i,101);
                        dialog.dismiss();

                    }
                });

                tev1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i,101);
                        dialog.dismiss();


                    }
                });

                ib2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent();
                        i.setType("image/*");

                        i.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(i,0);
                        dialog.dismiss();

                    }
                });

                tev2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent();
                        i.setType("image/*");

                        i.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(i,0);
                        dialog.dismiss();

                    }
                });

                dialog.show();

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num=et.getText().toString();

                boolean result=Verhoeff.validateVerhoeff(num);

                String msg=String.valueOf(result);

                if (msg=="true")
                {
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(), "Enter Valid Aadhar number", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
