package com.ahmedbr.firebaseregisteration.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ahmedbr.firebaseregisteration.R;
import com.ahmedbr.firebaseregisteration.fragment.LoginFragment;
import com.ahmedbr.firebaseregisteration.fragment.SignUpFragment;
import com.ahmedbr.firebaseregisteration.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener,
        SignUpFragment.OnFragmentInteractionListener {

    LoginFragment loginFragment;
    SignUpFragment signUpFragment;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        loginFragment = LoginFragment.newInstance();
        signUpFragment = SignUpFragment.newInstance();
        onNavigationToSignupClicked();

        mAuth = FirebaseAuth.getInstance();
//        updateUI(currentUser);
    }

    @Override
    public void onLoginClicked(User user) {
        signIn(user.getEmail(), user.getPassword());
    }

    private void loginAndNavigate(User user) {
        signIn(user.getEmail(), user.getPassword());
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(AuthActivity.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            navigateToMainActivity();
                        } else {
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
        createAccount(user.getEmail(), user.getPassword());
    }

    private void createAccount(String email, String password) {
        final User user1 = new User();
        user1.setEmail(email);
        user1.setPassword(password);
        mAuth.createUserWithEmailAndPassword(user1.getEmail(), user1.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "Create user with password success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginAndNavigate(user1);
//                            updateUI(user);
                        } else {
                            Log.w("TAG", "Create user with password failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }

    @Override
    public void onNavigateToLoginClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, loginFragment)
                .commitNow();
    }
}
