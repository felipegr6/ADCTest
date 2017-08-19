package br.com.ps.adctest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.ps.adctest.R;

public class RegisterStep2Fragment extends Fragment {

    public RegisterStep2Fragment() {
        // Required empty public constructor
    }

    public static RegisterStep2Fragment newInstance() {
        RegisterStep2Fragment fragment = new RegisterStep2Fragment();
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
        View inflate = inflater.inflate(R.layout.fragment_register_step2, container, false);
        return inflate;
    }
}
