package br.com.ps.adctest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.com.ps.adctest.R;
import br.com.ps.adctest.model.User;

import static android.text.TextUtils.isEmpty;

public class RegisterStep1Fragment extends Fragment {

    public RegisterStep1Fragment() {
        // Required empty public constructor
    }

    public static RegisterStep1Fragment newInstance() {
        return new RegisterStep1Fragment();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_register_step1, container, false);

        final EditText txtName = inflate.findViewById(R.id.txt_name);
        final EditText txtSurname = inflate.findViewById(R.id.txt_surname);
        Button btnContinue = inflate.findViewById(R.id.btn_continue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String name = txtName.getText().toString();
                String surname = txtSurname.getText().toString();
                if (!isEmpty(name) && !isEmpty(surname)) {
                    User u = new User();
                    u.setName(name);
                    u.setSurname(surname);
                    getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, RegisterStep2Fragment.newInstance(u))
                        .commit();
                }
            }
        });

        return inflate;
    }
}
