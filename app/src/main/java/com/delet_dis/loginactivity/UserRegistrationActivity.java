package com.delet_dis.loginactivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserRegistrationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_user_registration);

	addBackButton();

	TextView forwardedEmail = findViewById(R.id.forwardedEmail);
	TextView forwardedPassword = findViewById(R.id.forwardedPassword);

	forwardedEmail.setText(getForwardedEmail());
	forwardedPassword.setText(getForwardedPassword());
  }

  private String getForwardedEmail() {
	return getIntent().getStringExtra("email");
  }

  private String getForwardedPassword() {
	return getIntent().getStringExtra("password");
  }

  private void addBackButton() {
	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}