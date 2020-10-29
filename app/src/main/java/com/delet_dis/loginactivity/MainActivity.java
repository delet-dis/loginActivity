package com.delet_dis.loginactivity;

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
		loginButtonOnclick(emailInputEdit, passwordInputEdit);
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
		}
		if (passwordInputEdit.hasFocus() && passwordInputEdit.length() != 0) {
		  checkPasswordLength(passwordInputEdit, loginButton, passwordWrapper);
		  checkInputsLengths(emailInputEdit, passwordInputEdit, loginButton);
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
		}
		if (passwordInputEdit.hasFocus() && passwordInputEdit.length() != 0) {
		  checkPasswordLength(passwordInputEdit, loginButton, passwordWrapper);
		  checkInputsLengths(emailInputEdit, passwordInputEdit, loginButton);
		}
	  }

	  @Override
	  public void afterTextChanged(Editable s) {
	  }
	});
  }

  public void checkInputsLengths(TextInputEditText email, TextInputEditText password, Button login) {
	if (email.length() >= 4 && password.length() <= 8 && password.length() >= 3) {
	  login.setEnabled(true);
	} else {
	  login.setEnabled(false);
	}
  }

  public void checkEmailLength(TextInputEditText email, Button login, TextInputLayout wrapper) {
	if (email.length() < 4) {
	  wrapper.setError(getString(R.string.emailError));
	  login.setEnabled(false);
	} else {
	  wrapper.setError(null);
	  login.setEnabled(true);
	}
  }

  public void checkPasswordLength(TextInputEditText password, Button login, TextInputLayout wrapper) {
	if (password.length() < 3 || password.length() > 8) {
	  wrapper.setError(getString(R.string.passwordError));
	  login.setEnabled(false);
	} else {
	  wrapper.setError(null);
	  login.setEnabled(true);
	}
  }

  public void loginButtonOnclick(TextInputEditText email, TextInputEditText password) {
	email.setText("");
	password.setText("");
	email.clearFocus();
	password.clearFocus();

	emailWrapper.setError(null);
	passwordWrapper.setError(null);
  }

}