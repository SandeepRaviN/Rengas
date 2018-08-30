package com.katomaran.robotics.rengas;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register extends Activity implements View.OnClickListener {
    private static View view;
    private static EditText fullName, emailId, mobileNumber, password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;
    private static CheckBox terms_conditions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        if(Build.VERSION.SDK_INT >=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Pink));
        }

        //object
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Designature, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        initViews();
        setListeners();

    }
    // Initialize all views
    private void initViews() {
        fullName = (EditText) findViewById(R.id.fullName);
        emailId = (EditText) findViewById(R.id.userEmailId);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        signUpButton = (Button) findViewById(R.id.signUpBtn);
        login = (TextView) findViewById(R.id.already_user);
        terms_conditions = (CheckBox) findViewById(R.id.terms_conditions);
    }
    // Set Listeners
    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    //class or method
    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpBtn:

                // Call checkValidation method
                checkValidation();
                break;

            case R.id.already_user:
                Intent i = new Intent(Register.this,Login_Activity.class);
                startActivity(i);
                finish();
                // Replace login fragment;
                break;
        }
    }
    public class Utils {

        //Email Validation pattern
        public static final String regEx = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        //Fragments Tags
     //   public static final String Login_Fragment = "Login_Fragment";
        public static final String SignUp_Fragment = "SignUp_Fragment";
     //  public static final String ForgotPassword_Fragment = "ForgotPassword_Fragment";

    }
    // Check Validation Method
    private void checkValidation() {

        // Get all edittext texts
        String getFullName = fullName.getText().toString();
        String getEmailId = emailId.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();

        // Pattern match for email id
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        // Check if all strings are null or not
        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0)

            Toast.makeText(getApplicationContext(),
                    "All fields are required.",Toast.LENGTH_SHORT).show();

            // Check if email id valid or not
        else if (!m.find())
            Toast.makeText(getApplicationContext(),
                    "Your Email Id is Invalid.",Toast.LENGTH_SHORT).show();

            // Check if both password should be equal
        else if (!getConfirmPassword.equals(getPassword))
        Toast.makeText(getApplicationContext(),
                "Both password doesn't match.",Toast.LENGTH_SHORT).show();

        // Make sure user should check Terms and Conditions checkbox
        else if (!terms_conditions.isChecked())
        Toast.makeText(getApplicationContext(),
                "Please select Terms and Conditions.",Toast.LENGTH_SHORT).show();

        // Else do signup or do your stuff
        else
        Toast.makeText(getApplicationContext(),
                "Do SignUp.",Toast.LENGTH_SHORT).show();

    }
}
