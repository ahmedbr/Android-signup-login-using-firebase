package com.ahmedbr.firebaseregisteration.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
    Button loginButton;
    Button signupButton;
    private OnFragmentInteractionListener listener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        emailEditText = view.findViewById(R.id.emailEditText);
        passEditText = view.findViewById(R.id.passwordEditText);
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
                User user = new User();
                user.setEmail(emailEditText.getText().toString());
                user.setPassword(passEditText.getText().toString());
                listener.onSignupClicked(user);
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
