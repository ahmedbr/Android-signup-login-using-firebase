package com.ahmedbr.firebaseregisteration.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmedbr.firebaseregisteration.R;
import com.ahmedbr.firebaseregisteration.model.User;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    EditText emailEditText;
    EditText passEditText;
    EditText fullNameEditText;
    Button loginButton;
    Button signupButton;
    private OnFragmentInteractionListener listener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        emailEditText = view.findViewById(R.id.emailEditText);
        passEditText = view.findViewById(R.id.passwordEditText);
        fullNameEditText = view.findViewById(R.id.full_name_edit_text);
        loginButton = view.findViewById(R.id.login_button);
        signupButton = view.findViewById(R.id.signup_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNavigateToLoginClicked();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String pass = passEditText.getText().toString().trim();
                String fullName = fullNameEditText.getText().toString();
                User user;
                if (!email.equals("") && !pass.equals("") && fullName.length() != 0) {
                    user = new User(email, pass, fullName);
//                    user.setEmail(email);
//                    user.setPassword(pass);
//                    user.setName(fullName);
                    listener.onSignupClicked(user);
                } else if (fullName.length() == 0) {
                    Toast.makeText(getContext(), "Full Name is required! ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "invalid email or password", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onSignupClicked(User user);

        void onNavigateToLoginClicked();
    }
}
