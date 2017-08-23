package br.com.ps.adctest.activities;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.squareup.spoon.Spoon;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import br.com.ps.adctest.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.runner.lifecycle.Stage.RESUMED;
import static org.hamcrest.Matchers.allOf;

class Robot {

    Activity getCurrentActivity() {
        final AtomicReference<Activity> mReference = new AtomicReference<>();
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance()
                        .getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    mReference.set((Activity) resumedActivities.iterator().next());
                }
            }
        });
        return mReference.get();
    }

    Robot entrarNaTelaDeCadastro()  {
        onView(allOf(withId(R.id.btn_register), withText("Cadastrar"), isDisplayed()))
                .perform(click());
        Spoon.screenshot(getCurrentActivity(), "Entrou-na-primeira-tela-de-cadastro");
        return this;
    }

    Robot informarNomeDoUsuario(String nomeDoUsuario)  {
        onView(allOf(withId(R.id.txt_name), isDisplayed()))
                .perform(replaceText(nomeDoUsuario), closeSoftKeyboard());
        Spoon.screenshot(getCurrentActivity(), "Nome-do-usuario-informado");
        return this;
    }

    Robot informarSobrenomeDoUsuario(String sobrenome)  {
        onView(allOf(withId(R.id.txt_surname), isDisplayed()))
                .perform(replaceText(sobrenome), closeSoftKeyboard());
        Spoon.screenshot(getCurrentActivity(), "Sobrenome-do-usuario-informado");
        return this;
    }

    Robot prosseguirComCadastro()  {
        onView(allOf(withId(R.id.btn_continue), withText("Continuar"), isDisplayed()))
                .perform(click());
        Spoon.screenshot(getCurrentActivity(), "Entrou-na-segunda-tela-de-cadastro");
        return this;
    }

    Robot informarEmailDoUsuario(String email)  {
        ViewInteraction appCompatEditText3 = onView(allOf(withId(R.id.txt_login), isDisplayed()));
        appCompatEditText3.perform(replaceText(email), closeSoftKeyboard());
        Spoon.screenshot(getCurrentActivity(), "Informou-email-do-usuario");
        return this;
    }

    Robot informarSenha(String senha)  {
        ViewInteraction appCompatEditText4 =
                onView(allOf(withId(R.id.txt_password), isDisplayed()));
        appCompatEditText4.perform(replaceText(senha), closeSoftKeyboard());
        Spoon.screenshot(getCurrentActivity(), "Informou-senha-do-usuario");
        return this;
    }

    Robot informarConfirmacaoDeSenha(String senha)  {
        ViewInteraction appCompatEditText5 =
                onView(allOf(withId(R.id.txt_confirm_password), isDisplayed()));
        appCompatEditText5.perform(replaceText(senha), closeSoftKeyboard());
        Spoon.screenshot(getCurrentActivity(), "Confirmou-senha-do-usuario");
        return this;
    }

    Robot concluirCadastro()  {
        ViewInteraction appCompatButton3 =
                onView(allOf(withId(R.id.btn_register), withText("Cadastrar"), isDisplayed()));
        appCompatButton3.perform(click());
        Spoon.screenshot(getCurrentActivity(), "Concluiu-cadastro");
        return this;
    }

    Robot informarEmailParaLogin(String email)  {
        ViewInteraction appCompatEditText6 =
                onView(allOf(withId(R.id.txt_username), isDisplayed()));
        appCompatEditText6.perform(replaceText(email), closeSoftKeyboard());
        Spoon.screenshot(getCurrentActivity(), "Preencheu-email-para-login");
        return this;
    }

    Robot informarSenhaParaLogin(String senha)  {
        ViewInteraction appCompatEditText7 =
                onView(allOf(withId(R.id.txt_password), isDisplayed()));
        appCompatEditText7.perform(replaceText(senha), closeSoftKeyboard());
        Spoon.screenshot(getCurrentActivity(), "Preencheu-senha-para-login");
        return this;
    }

    Robot efetuarLogin() {
        ViewInteraction appCompatButton4 =
                onView(allOf(withId(R.id.btn_login), withText("Login"), isDisplayed()));
        appCompatButton4.perform(click());
        return this;
    }

    Robot verificarLoginComSucesso()  {
        ViewInteraction textView = onView(allOf(withId(R.id.lbl_success),
                Utilities.childAtPosition(Utilities.childAtPosition(withId(android.R.id.content), 0), 0), isDisplayed()));
        textView.check(matches(withText("Sucesso!")));
        Spoon.screenshot(getCurrentActivity(), "Login-com-sucesso");
        return this;
    }

    void verificarMensagemDeFalhaParaLoginNaoInformado()  {
        ViewInteraction textView1 = onView(
                allOf(withId(R.id.textinput_error), withText("Em branco"),
                        Utilities.childAtPosition(Utilities.childAtPosition(withId(R.id.til_username), 1), 0), isDisplayed()));
        textView1.check(matches(withText("Em branco")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textinput_error), withText("Em branco"),
                        Utilities.childAtPosition(Utilities.childAtPosition(withId(R.id.til_password), 1), 0), isDisplayed()));
        textView2.check(matches(withText("Em branco")));
        Spoon.screenshot(getCurrentActivity(), "Login-com-falha");
    }
}
