package com.delet_dis.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

  private final int MAX_PASSWORD_LENGTH = 8;
  private final int MIN_PASSWORD_LENGTH = 3;
  private final int MIN_EMAIL_LENGTH = 4;
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
	loginButton = findViewById(R.id.registerButton);
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

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (requestCode == 1) {
	  if (resultCode == RESULT_OK) {
		emailInputEdit.setText(data.getStringExtra("email"));
		showRegistrationToast(data.getStringExtra("email"));

		passwordInputEdit.setText("");
		passwordInputEdit.clearFocus();
	  }
	}
  }

  private void checkInputsLengths(TextInputEditText email, TextInputEditText password, Button login) {
	login.setEnabled(email.length() >= MIN_EMAIL_LENGTH && password.length() <= MAX_PASSWORD_LENGTH && password.length() >= MIN_PASSWORD_LENGTH);
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
	if (!Objects.requireNonNull(password.getText()).toString().equals(getString(R.string.matchingPassword))) {
	  login.setText(getString(R.string.registerText));
	} else {
	  login.setText(getString(R.string.loginText));
	}
  }

  private void loginButtonOnclick(TextInputEditText email, TextInputEditText password, TextInputLayout emailW, TextInputLayout passwordW, Button button) {

	if (checkIfRegistrationIsNeeded(password)) {
	  forwardForRegistration(email, password);
	} else {

	  showLoginToast(Objects.requireNonNull(email.getText()).toString());

	  email.setText("");
	  password.setText("");
	  email.clearFocus();
	  password.clearFocus();

	  emailW.setError(null);
	  passwordW.setError(null);
	}

	checkPasswordCorrectness(password, button);
  }

  private void forwardForRegistration(TextInputEditText email, TextInputEditText password) {
	Intent afterLoginOrRegisterActivityGo = new Intent(MainActivity.this, UserRegistrationActivity.class);

	String emailForForwarding = Objects.requireNonNull(email.getText()).toString();
	String passwordForForwarding = Objects.requireNonNull(password.getText()).toString();

	afterLoginOrRegisterActivityGo.putExtra("email", emailForForwarding);
	afterLoginOrRegisterActivityGo.putExtra("password", passwordForForwarding);

	startActivityForResult(afterLoginOrRegisterActivityGo, 1);
  }

  private boolean checkIfRegistrationIsNeeded(TextInputEditText password) {
	return !Objects.requireNonNull(password.getText()).toString().equals(getString(R.string.matchingPassword));
  }

  private void showLoginToast(String email) {
	Toast.makeText(getApplicationContext(), getString(R.string.successfulLogin) + email, Toast.LENGTH_LONG).show();
  }

  private void showRegistrationToast(String email) {
	Toast.makeText(getApplicationContext(), getString(R.string.successfulRegistration) + email, Toast.LENGTH_LONG).show();
  }


}