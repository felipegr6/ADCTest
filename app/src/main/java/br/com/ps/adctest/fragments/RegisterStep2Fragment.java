package br.com.ps.adctest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.ps.adctest.R;
import br.com.ps.adctest.model.User;
import br.com.ps.adctest.repository.Repository;

import static android.app.Activity.RESULT_OK;
import static android.text.TextUtils.isEmpty;

public class RegisterStep2Fragment extends Fragment {

    public static final String ARG_USER = "argUser";

    private User user;

    public RegisterStep2Fragment() {
        // Required empty public constructor
    }

    public static RegisterStep2Fragment newInstance(User user) {
        RegisterStep2Fragment fragment = new RegisterStep2Fragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_USER, user);

        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(ARG_USER);
        }
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_register_step2, container, false);

        final EditText txtLogin = inflate.findViewById(R.id.txt_login);
        final EditText txtPassword = inflate.findViewById(R.id.txt_password);
        final EditText txtConfirmPassword = inflate.findViewById(R.id.txt_confirm_password);
        Button btnRegister = inflate.findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String login = txtLogin.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();
                if (!isEmpty(login) && !isEmpty(password) && (password.equals(confirmPassword))) {
                    user.setLogin(login);
                    user.setPassword(password);
                }
                if (Repository.registerUser(getActivity(), user)) {
                    getActivity().setResult(RESULT_OK);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "Erro ao cadastrar usuário", Toast.LENGTH_SHORT)
                        .show();
                }
            }
        });

        return inflate;
    }
}
