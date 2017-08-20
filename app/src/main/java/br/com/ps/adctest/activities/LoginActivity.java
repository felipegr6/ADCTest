package br.com.ps.adctest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.ps.adctest.R;
import br.com.ps.adctest.repository.Repository;
import br.com.ps.adctest.util.SimpleTextWatcher;

import static android.text.TextUtils.isEmpty;

public class LoginActivity extends AppCompatActivity {

    public static final int REGISTER = 1384;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText txtUsername = findViewById(R.id.txt_username);
        final EditText txtPassword = findViewById(R.id.txt_password);

        final TextInputLayout tilUsername = findViewById(R.id.til_username);
        final TextInputLayout tilPassword = findViewById(R.id.til_password);

        Button register = findViewById(R.id.btn_register);
        Button login = findViewById(R.id.btn_login);

        SimpleTextWatcher simpleTextWatcher = new SimpleTextWatcher() {
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilUsername.setError(null);
                tilPassword.setError(null);
            }
        };

        txtUsername.addTextChangedListener(simpleTextWatcher);
        txtPassword.addTextChangedListener(simpleTextWatcher);

        login.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                if (isEmpty(username) || isEmpty(password)) {
                    if (isEmpty(username)) {
                        tilUsername.setError("Em branco");
                    }
                    if (isEmpty(password)) {
                        tilPassword.setError("Em branco");
                    }
                    return;
                }

                if (Repository.login(LoginActivity.this, username, password)) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Usuário e/ou senha inválida.",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class),
                    REGISTER);
            }
        });
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTER && resultCode == RESULT_OK) {
            Toast.makeText(this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }
}
