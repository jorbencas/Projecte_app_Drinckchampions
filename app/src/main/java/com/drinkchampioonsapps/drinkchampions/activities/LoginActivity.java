package com.drinkchampioonsapps.drinkchampions.activities;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.drinkchampioonsapps.drinkchampions.R;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;


public class LoginActivity extends AppCompatActivity {
  private FirebaseAuth auth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    auth = FirebaseAuth.getInstance();
    doLogin();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RC_SIGN_IN) {
      if (resultCode == RESULT_OK) {
        doLogin();
        finish();
      }
    }
  }

private void doLogin() {
  FirebaseUser currentUser = auth.getCurrentUser();
  if (currentUser != null) {
    //String username = currentUser.getDisplayName();
    //String username_uid = currentUser.getUid();
    String username_email = currentUser.getEmail();

    SharedPreferences prefs = getApplication().getSharedPreferences("ToDoPrefs", 0);
    prefs.edit().putString("username", username_email).commit();
    Intent i = new Intent(this, MainActivity.class);
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
        | Intent.FLAG_ACTIVITY_NEW_TASK
        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(i);
  } else {
    startActivityForResult(
            AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setProviders(
                AuthUI.EMAIL_PROVIDER,
                AuthUI.GOOGLE_PROVIDER)
            .build(), RC_SIGN_IN);
  }
}
}
