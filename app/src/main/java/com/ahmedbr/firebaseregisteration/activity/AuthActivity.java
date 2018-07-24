package com.ahmedbr.firebaseregisteration.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahmedbr.firebaseregisteration.R;
import com.ahmedbr.firebaseregisteration.fragment.LoginFragment;
import com.ahmedbr.firebaseregisteration.fragment.SignUpFragment;
import com.ahmedbr.firebaseregisteration.model.User;

public class AuthActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener,
        SignUpFragment.OnFragmentInteractionListener {

    LoginFragment loginFragment;
    SignUpFragment signUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        loginFragment = LoginFragment.newInstance();
        signUpFragment = SignUpFragment.newInstance();

        onNavigationToSignupClicked();
    }

    @Override
    public void onLoginClicked(User user) {
        loginAndNavigate(user);
    }

    private void loginAndNavigate(User user) {
        navigateToMainActivity();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onNavigationToSignupClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, signUpFragment)
                .commitNow();
    }

    @Override
    public void onSignupClicked(User user) {
        loginAndNavigate(user);

    }

    @Override
    public void onNavigateToLoginClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, loginFragment)
                .commitNow();

    }
}
