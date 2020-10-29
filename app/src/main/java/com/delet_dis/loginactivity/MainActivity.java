package com.delet_dis.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

  private TextInputEditText emailInputEdit;
  private TextInputEditText passwordInputEdit;
  private Button loginButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	emailInputEdit = findViewById(R.id.emailInputEdit);
	passwordInputEdit = findViewById(R.id.passwordInputEdit);
	loginButton = findViewById(R.id.loginButton);

	loginButton.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		checkInputsLengths(emailInputEdit, passwordInputEdit, loginButton);
	  }
	});

	checkInputsLengths(emailInputEdit, passwordInputEdit, loginButton);

	emailInputEdit.addTextChangedListener(new TextWatcher() {
	  @Override
	  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	  }

	  @Override
	  public void onTextChanged(CharSequence s, int start, int before, int count) {
		checkInputsLengths(emailInputEdit, passwordInputEdit, loginButton);
	  }

	  @Override
	  public void afterTextChanged(Editable s) {
	  }
	});

	passwordInputEdit.addTextChangedListener(new TextWatcher() {
	  @Override
	  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	  }

	  @Override
	  public void onTextChanged(CharSequence s, int start, int before, int count) {
		checkInputsLengths(emailInputEdit, passwordInputEdit, loginButton);
	  }

	  @Override
	  public void afterTextChanged(Editable s) {
	  }
	});
  }

  public void checkInputsLengths(TextInputEditText email, TextInputEditText password, Button login) {
	if (email.length() > 4 && password.length() <= 8 && password.length() >= 3) {
	  login.setEnabled(true);
	} else {
	  login.setEnabled(false);
	}
	if (email.length() < 4) {
	  email.setError("Error");
	}
  }

}