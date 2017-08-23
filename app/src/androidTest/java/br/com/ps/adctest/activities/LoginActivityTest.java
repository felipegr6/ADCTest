package br.com.ps.adctest.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static br.com.ps.adctest.repository.Repository.clearAllData;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    Robot ROBOT = new Robot();

    @Test
    public void registerAndLogin()  {
        clearAllData(getInstrumentation().getTargetContext());
        ROBOT.entrarNaTelaDeCadastro()
                .informarNomeDoUsuario("Joao")
                .informarSobrenomeDoUsuario("Silva")
                .prosseguirComCadastro()
                .informarEmailDoUsuario("joao@silva.com")
                .informarSenha("senha")
                .informarConfirmacaoDeSenha("senha")
                .concluirCadastro()
                .informarEmailParaLogin("joao@silva.com")
                .informarSenhaParaLogin("senha")
                .efetuarLogin();
        ROBOT.verificarLoginComSucesso();
    }

    @Test
    public void login() {
        ROBOT.informarEmailParaLogin("joao@silva.com")
                .informarSenhaParaLogin("senha")
                .efetuarLogin();
    }

    @Test
    public void pressLoginButtonAndShowErrorInAllFields() {
        ROBOT.efetuarLogin()
                .verificarMensagemDeFalhaParaLoginNaoInformado();
    }

}
