package com.delet_dis.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class UserRegistrationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_user_registration);

	addBackButtonAndTitle();

	TextInputEditText email = findViewById(R.id.emailInputEdit);
	TextInputEditText password = findViewById(R.id.passwordInputEdit);
	Button register = findViewById(R.id.registerButton);

	email.setText(getForwardedEmail());
	password.setText(getForwardedPassword());

	final Intent forwardUserDataBack = new Intent();

	forwardUserDataBack.putExtra("email", Objects.requireNonNull(email.getText()).toString());
	forwardUserDataBack.putExtra("password", Objects.requireNonNull(password.getText()).toString());

	register.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		setResult(RESULT_OK, forwardUserDataBack);
		finish();
	  }
	});
  }

  private String getForwardedEmail() {
	return getIntent().getStringExtra("email");
  }

  private String getForwardedPassword() {
	return getIntent().getStringExtra("password");
  }

  private void addBackButtonAndTitle() {
	Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
	getSupportActionBar().setTitle("Registration");
  }
}