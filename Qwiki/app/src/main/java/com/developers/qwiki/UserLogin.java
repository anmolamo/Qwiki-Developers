package com.kwiki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developers.qwiki.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserLogin extends AppCompatActivity {

    private static final String TAG = "UserLogin";
    SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;
    EditText e1,e2;
    TextView tv1;
    Button b1;
    FirebaseAuth auth;

    AlertDialog.Builder al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        e1 = (EditText) findViewById(R.id.edittext1);
        e2= (EditText) findViewById(R.id.edittext2);

        tv1= (TextView) findViewById(R.id.textView2);

        auth=FirebaseAuth.getInstance();

        b1= (Button) findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=e1.getText().toString();
                String pass=e2.getText().toString();

                if(name.isEmpty())
                {
                    e1.setError("Please Enter the UserName");
                }

                else if (pass.isEmpty())
                {
                    e2.setError("Please Enter the Password");
                }

                else {
                    if (!name.toLowerCase().matches("admin") && !pass.toLowerCase().matches("admin")){
                        auth.signInWithEmailAndPassword(name, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(UserLogin.this, "Log In Sucessful", Toast.LENGTH_SHORT).show();
//                                Intent i=new Intent(UserLogin.this,MapsActivity.class);
//                                startActivity(i);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserLogin.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
//                        Intent i=new Intent(UserLogin.this,MapsActivity.class);
//                        startActivity(i);
                        finish();
                    }
                }
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserLogin.this,Signup.class);
                startActivity(i);
            }
        });

        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton=(SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            Intent intent=new Intent(UserLogin.this, MapsActivity.class);
//            startActivity(intent);
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        al=new AlertDialog.Builder(UserLogin.this);
        al.setTitle("  Do you want to exit?");
        al.setCancelable(false);
        al.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        al.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        al.show();
    }
}
