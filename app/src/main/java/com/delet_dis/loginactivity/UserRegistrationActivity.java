package com.delet_dis.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;

public class UserRegistrationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_user_registration);

	addBackButton();
  }

  private void addBackButton(){
	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}