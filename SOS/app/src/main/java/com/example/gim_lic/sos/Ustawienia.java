package com.example.gim_lic.sos;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ustawienia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustawienia);

        final EditText mail = (EditText)findViewById(R.id.Mail);
        final EditText phone = (EditText)findViewById(R.id.phone);
        Button save = (Button)findViewById(R.id.save);

        SharedPreferences dane = PreferenceManager.getDefaultSharedPreferences(this);
        String emailSaved = dane.getString("email", "");
        mail.setText(emailSaved);
        String phoneSaved = dane.getString("phone", "");
        phone.setText(phoneSaved);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences dane = PreferenceManager.getDefaultSharedPreferences(Ustawienia.this);
                SharedPreferences.Editor edytor = dane.edit();
                edytor.putString("email", mail.getText().toString());
                edytor.putString("phone", phone.getText().toString());
                edytor.apply();
                Toast.makeText(Ustawienia.this, "Ustawienia zapisane!", Toast.LENGTH_LONG).show();
                finish();
            }

        });
    }
}
