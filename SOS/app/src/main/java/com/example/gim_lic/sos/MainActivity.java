package com.example.gim_lic.sos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sos = (Button)findViewById(R.id.sos);
        Button ustawienia = (Button)findViewById(R.id.settings);

        ustawienia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MainActivity.this, Ustawienia.class);
                startActivity(settingsIntent);
            }
        });

        final SharedPreferences dane = PreferenceManager.getDefaultSharedPreferences(this);
        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNmr = dane.getString("phone", "");
                Intent sms = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNmr));
                sms.putExtra("sms_body", "Test apki");
                startActivity(sms);

                String email = dane.getString("email", "");
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "POMOCY");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Jestem w niebezpieczeństwie");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });

    }
}
