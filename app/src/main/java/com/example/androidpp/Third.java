package com.example.androidpp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Third extends AppCompatActivity {
    EditText e1,e2;
    ProgressBar p1;
    Button login,register;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        e1=(EditText)findViewById(R.id.editTextText);
        e2=(EditText)findViewById(R.id.editTextText2);
        e2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        login=(Button)findViewById(R.id.button5);

        p1=(ProgressBar)findViewById(R.id.progressBar);
        firebaseAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(view -> {
            Intent i= new Intent(Third.this,Fivth.class);
            startActivity(i);
            finish();
        });
        login.setOnClickListener(view -> {
            String s1=e1.getText().toString().trim();
            String s2=e2.getText().toString();
            if(s1.isEmpty())
            {
                e1.setError("Please fill the password");
                return;
            }
            else
            {
                p1.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Third.this, "Congratulations", Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.INVISIBLE);
                            Intent j=new Intent(Third.this,Forth.class);
                            startActivity(j);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Third.this, "Mismatched", Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }
}