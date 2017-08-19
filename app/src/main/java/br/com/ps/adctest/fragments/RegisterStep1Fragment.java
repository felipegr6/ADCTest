package br.com.ps.adctest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.ps.adctest.R;

public class RegisterStep1Fragment extends Fragment {

    public RegisterStep1Fragment() {
        // Required empty public constructor
    }

    public static RegisterStep1Fragment newInstance() {
        RegisterStep1Fragment fragment = new RegisterStep1Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_register_step1, container, false);
        return inflate;
    }
}
