package com.e.userregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class UserRegistratio extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6;
    Button b1,b2;

    CheckBox cb;

    FirebaseAuth auth;

    String verificationId;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        e1=findViewById(R.id.username);
        e2=findViewById(R.id.useremail);
        e3=findViewById(R.id.userphone);
        e4=findViewById(R.id.userotp);
        e5=findViewById(R.id.mainPassUser);
        e6=findViewById(R.id.verifyPassUser);

        b1=findViewById(R.id.verifyPhone);
        b2=findViewById(R.id.signInnewUser);

        cb=findViewById(R.id.checkbx);

        auth= FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificationId!=null)
                    verifyPhoneNumberWithCode();

                else
                    startPhoneNumberVerification();
            }
        });

        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                verificationId=s;
                b1.setText("Verify Code");
            }
        };


        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String name=e1.getText().toString();
                            String email=e2.getText().toString();
                            String phone=e3.getText().toString();
                            String pass=e5.getText().toString();
                            String repass=e6.getText().toString();

                            if(name.isEmpty())
                            {
                                e1.setError("Please Enter your name");
                            }

                            else if (email.isEmpty())
                            {
                                e2.setError("Please Enter your Email Address");
                            }

                            else if (phone.isEmpty())
                            {
                                e3.setError("Please Enter your phone number");
                            }

                            else if (pass.isEmpty())
                            {
                                e5.setError("Please Enter the password");
                            }

                            else if (repass.isEmpty())
                            {
                                e6.setError("Please Re-enter the password");
                            }

                            else if (!repass.equals(pass))
                            {
                                e6.setError("Please Enter the same password");
                            }

                            else
                            {
                                auth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(getApplicationContext(), "Registration Sucessful", Toast.LENGTH_SHORT).show();

                                        Intent i=new Intent(getApplicationContext(),UserLogin.class);
                                        startActivity(i);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

    }

    private void verifyPhoneNumberWithCode()
    {
        PhoneAuthCredential credential=PhoneAuthProvider.getInstance().getCredential(verificationId,e4.getText().toString());
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();



                }
                userIsLoggedIn();
            }
        });
    }

    private void userIsLoggedIn() {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null)
        {
            b1.setText("Verified");
            return;
        }
    }

    private void startPhoneNumberVerification() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(e3.getText().toString(),60, TimeUnit.SECONDS,this,mCallbacks);
    }
}
