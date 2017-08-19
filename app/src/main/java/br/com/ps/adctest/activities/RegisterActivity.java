package br.com.ps.adctest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import br.com.ps.adctest.R;
import br.com.ps.adctest.fragments.RegisterStep1Fragment;

public class RegisterActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportFragmentManager().beginTransaction()
            .add(R.id.frame, RegisterStep1Fragment.newInstance())
            .commit();
    }
}
