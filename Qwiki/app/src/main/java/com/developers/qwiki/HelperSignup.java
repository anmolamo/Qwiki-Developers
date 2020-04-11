package com.developers.qwiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelperSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_signup);

        Button signinHelper=findViewById(R.id.signInnewHelper);
        signinHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HelperSignup.this,HelperDocVerify.class);
                startActivity(intent);
            }
        });
    }
}
