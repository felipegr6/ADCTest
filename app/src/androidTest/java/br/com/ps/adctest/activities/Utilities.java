package br.com.ps.adctest.activities;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by ramaro on 23/08/17.
 */

class Utilities {
    static Matcher<View> childAtPosition(final Matcher<View> parentMatcher,
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
}
