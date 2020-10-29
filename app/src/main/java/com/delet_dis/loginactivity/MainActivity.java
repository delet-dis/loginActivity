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

	checkInputsLengths(emailInputEdit, passwordInputEdit);

	emailInputEdit.addTextChangedListener(new TextWatcher() {
	  @Override
	  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	  }

	  @Override
	  public void onTextChanged(CharSequence s, int start, int before, int count) {
		checkInputsLengths(emailInputEdit, passwordInputEdit);
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
		checkInputsLengths(emailInputEdit, passwordInputEdit);
	  }

	  @Override
	  public void afterTextChanged(Editable s) {
	  }
	});
  }

  public void checkInputsLengths(TextInputEditText email, TextInputEditText password) {
	if (email.length() > 4 && password.length() <= 8 && password.length() >= 3) {
	  loginButton.setEnabled(true);
	} else {
	  loginButton.setEnabled(false);
	}
  }

}