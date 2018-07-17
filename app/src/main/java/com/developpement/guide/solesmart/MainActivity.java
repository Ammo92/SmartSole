package com.developpement.guide.solesmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.login) EditText login;
    @BindView(R.id.pass) EditText  password;
    @BindView(R.id.connect) Button connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.connect) void ClickConnectButton() {
        String loginText = login.toString();
        String passwordText = password.toString();
        Intent intent = new Intent(MainActivity.this, Tabs.class);
        startActivity(intent);
    }
}
