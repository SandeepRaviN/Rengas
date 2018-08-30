package com.katomaran.robotics.rengas;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Login_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(Build.VERSION.SDK_INT >=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Pink));
        }
    }
    public void reg(View v){
        Intent i = new Intent(Login_Activity.this,Register.class);
        startActivity(i);
        finish();
    }
    public void forget(View v){
        Intent i = new Intent(Login_Activity.this,ForgotPassword.class);
        startActivity(i);
        finish();
    }
    public void login(View v){
        Intent i = new Intent(Login_Activity.this,CustomerList.class);
        startActivity(i);
        finish();
    }
}
