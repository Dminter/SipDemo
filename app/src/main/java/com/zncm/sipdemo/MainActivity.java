package com.zncm.sipdemo;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.sipdroid.sipua.ui.Receiver;
import org.sipdroid.sipua.ui.Settings;
import org.sipdroid.sipua.ui.SettingsNew;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private Button button1;
    private static AlertDialog m_AlertDlg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_menu(editText);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });


    }


    void call_menu(EditText view) {
        String target = view.getText().toString();
        if (m_AlertDlg != null) {
            m_AlertDlg.cancel();
        }
        if (target.length() == 0)
            m_AlertDlg = new AlertDialog.Builder(this)
                    .setMessage(org.sipdroid.sipua.R.string.empty)
                    .setTitle(org.sipdroid.sipua.R.string.app_name)
                    .setIcon(org.sipdroid.sipua.R.drawable.icon22)
                    .setCancelable(true)
                    .show();
        else if (!Receiver.engine(this).call(target,true))
            m_AlertDlg = new AlertDialog.Builder(this)
                    .setMessage(org.sipdroid.sipua.R.string.notfast)
                    .setTitle(org.sipdroid.sipua.R.string.app_name)
                    .setIcon(org.sipdroid.sipua.R.drawable.icon22)
                    .setCancelable(true)
                    .show();
    }
}
