package com.delet_dis.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

  private TextInputEditText emailInputEdit;
  private TextInputEditText passwordInputEdit;
  private Button loginButton;
  private TextInputLayout emailWrapper;
  private TextInputLayout passwordWrapper;

  private final int MAX_PASSWORD_LENGTH = 8;
  private final int MIN_PASSWORD_LENGTH = 3;
  private final int MIN_EMAIL_LENGTH = 4;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	emailInputEdit = findViewById(R.id.emailInputEdit);
	passwordInputEdit = findViewById(R.id.passwordInputEdit);
	loginButton = findViewById(R.id.loginButton);
	emailWrapper = findViewById(R.id.emailInput);
	passwordWrapper = findViewById(R.id.passwordInput);

	loginButton.setEnabled(false);

	loginButton.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		loginButtonOnclick(emailInputEdit, passwordInputEdit, emailWrapper, passwordWrapper, loginButton);
	  }
	});


	emailInputEdit.addTextChangedListener(new TextWatcher() {
	  @Override
	  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	  }

	  @Override
	  public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (emailInputEdit.hasFocus() && emailInputEdit.length() != 0) {
		  checkEmailLength(emailInputEdit, loginButton, emailWrapper);
		  checkInputsLengths(emailInputEdit, passwordInputEdit, loginButton);
		  checkPasswordCorrectness(passwordInputEdit, loginButton);
		}
		if (passwordInputEdit.hasFocus() && passwordInputEdit.length() != 0) {
		  checkPasswordLength(passwordInputEdit, loginButton, passwordWrapper);
		  checkInputsLengths(emailInputEdit, passwordInputEdit, loginButton);
		  checkPasswordCorrectness(passwordInputEdit, loginButton);
		}
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
		if (emailInputEdit.hasFocus() && emailInputEdit.length() != 0) {
		  checkEmailLength(emailInputEdit, loginButton, emailWrapper);
		  checkInputsLengths(emailInputEdit, passwordInputEdit, loginButton);
		  checkPasswordCorrectness(passwordInputEdit, loginButton);
		}
		if (passwordInputEdit.hasFocus() && passwordInputEdit.length() != 0) {
		  checkPasswordLength(passwordInputEdit, loginButton, passwordWrapper);
		  checkInputsLengths(emailInputEdit, passwordInputEdit, loginButton);
		  checkPasswordCorrectness(passwordInputEdit, loginButton);
		}
	  }

	  @Override
	  public void afterTextChanged(Editable s) {
	  }
	});
  }

  private void checkInputsLengths(TextInputEditText email, TextInputEditText password, Button login) {
	if (email.length() >= MIN_EMAIL_LENGTH && password.length() <= MAX_PASSWORD_LENGTH && password.length() >= MIN_PASSWORD_LENGTH) {
	  login.setEnabled(true);
	} else {
	  login.setEnabled(false);
	}
  }

  private void checkEmailLength(TextInputEditText email, Button login, TextInputLayout wrapper) {
	if (email.length() < MIN_EMAIL_LENGTH) {
	  wrapper.setError(getString(R.string.emailError));
	  login.setEnabled(false);
	} else {
	  wrapper.setError(null);
	  login.setEnabled(true);
	}
  }

  private void checkPasswordLength(TextInputEditText password, Button login, TextInputLayout wrapper) {
	if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
	  wrapper.setError(getString(R.string.passwordError));
	  login.setEnabled(false);
	} else {
	  wrapper.setError(null);
	  login.setEnabled(true);
	}
  }

  private void checkPasswordCorrectness(TextInputEditText password, Button login) {
	if (!password.getText().toString().equals(getString(R.string.matchingPassword))) {
	  login.setText(getString(R.string.registerText));
	} else {
	  login.setText(getString(R.string.loginText));
	}
  }

  private void loginButtonOnclick(TextInputEditText email, TextInputEditText password, TextInputLayout emailW, TextInputLayout passwordW, Button button) {
	email.setText("");
	password.setText("");
	email.clearFocus();
	password.clearFocus();

	emailW.setError(null);
	passwordW.setError(null);

	checkPasswordCorrectness(password,button);

	afterLoginOrRegisterActivityGo();
  }

  private void afterLoginOrRegisterActivityGo(){
	Intent afterLoginOrRegisterActivityGo = new Intent(MainActivity.this, UserRegistrationActivity.class);
	startActivity(afterLoginOrRegisterActivityGo);
  }

}