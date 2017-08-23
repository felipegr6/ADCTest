package br.com.ps.adctest.activities;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import br.com.ps.adctest.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static br.com.ps.adctest.repository.Repository.clearAllData;
import static org.hamcrest.Matchers.allOf;

@LargeTest @RunWith(AndroidJUnit4.class) public class LoginActivityTest {

    @Rule public ActivityTestRule<LoginActivity> mActivityTestRule =
        new ActivityTestRule<>(LoginActivity.class);

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher,
        final int position) {

        return new TypeSafeMatcher<View>() {
            @Override public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(
                    ((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test public void registerAndLogin() {
        clearAllData(getInstrumentation().getTargetContext());

        ViewInteraction appCompatButton =
            onView(allOf(withId(R.id.btn_register), withText("Cadastrar"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.txt_name), isDisplayed()));
        appCompatEditText.perform(replaceText("Joao"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.txt_surname), isDisplayed()));
        appCompatEditText2.perform(replaceText("Silva"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 =
            onView(allOf(withId(R.id.btn_continue), withText("Continuar"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText3 = onView(allOf(withId(R.id.txt_login), isDisplayed()));
        appCompatEditText3.perform(replaceText("joao@silva.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 =
            onView(allOf(withId(R.id.txt_password), isDisplayed()));
        appCompatEditText4.perform(replaceText("senha"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 =
            onView(allOf(withId(R.id.txt_confirm_password), isDisplayed()));
        appCompatEditText5.perform(replaceText("senha"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 =
            onView(allOf(withId(R.id.btn_register), withText("Cadastrar"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText6 =
            onView(allOf(withId(R.id.txt_username), isDisplayed()));
        appCompatEditText6.perform(replaceText("joao@silva.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 =
            onView(allOf(withId(R.id.txt_password), isDisplayed()));
        appCompatEditText7.perform(replaceText("senha"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 =
            onView(allOf(withId(R.id.btn_login), withText("Login"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textView = onView(allOf(withId(R.id.lbl_success),
            childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0), isDisplayed()));
        textView.check(matches(withText("Sucesso!")));
    }

    @Test public void login() {
        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.txt_username), isDisplayed()));
        appCompatEditText.perform(replaceText("joao@silva.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 =
            onView(allOf(withId(R.id.txt_password), isDisplayed()));
        appCompatEditText2.perform(replaceText("senha"), closeSoftKeyboard());

        ViewInteraction appCompatButton =
            onView(allOf(withId(R.id.btn_login), withText("Login"), isDisplayed()));
        appCompatButton.perform(click());
    }

    @Test public void pressLoginButtonAndShowErrorInAllFields() {
        ViewInteraction appCompatButton =
            onView(allOf(withId(R.id.btn_login), withText("Login"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView1 = onView(
            allOf(withId(R.id.textinput_error), withText("Em branco"),
                childAtPosition(childAtPosition(withId(R.id.til_username), 1), 0), isDisplayed()));
        textView1.check(matches(withText("Em branco")));

        ViewInteraction textView2 = onView(
            allOf(withId(R.id.textinput_error), withText("Em branco"),
                childAtPosition(childAtPosition(withId(R.id.til_password), 1), 0), isDisplayed()));
        textView2.check(matches(withText("Em branco")));
    }
}
