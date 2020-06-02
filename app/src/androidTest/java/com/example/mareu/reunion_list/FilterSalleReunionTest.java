package com.example.mareu.reunion_list;


import android.support.test.espresso.DataInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.mareu.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class FilterSalleReunionTest {

    @Rule
    public ActivityTestRule<ListReunionActivity> mActivityTestRule = new ActivityTestRule<>(ListReunionActivity.class);

    @Test
    public void listReunionActivityTest3() {
        onView(allOf(withId(R.id.item_filter_reunion),
                childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        withId(R.id.appbar),
                                        0)),
                        3),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.salleSpinner),
                childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        withId(R.id.appbar),
                                        0)),
                        2),
                isDisplayed())).perform(click());

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatTextView.perform(click());

        onView(allOf(withId(R.id.item_list_item), withText("Salle A - 6/5/2020 - 16:00 - Peach"),
                childAtPosition(
                        allOf(withId(R.id.item_list_reunion),
                                childAtPosition(
                                        withId(R.id.list_neighbours),
                                        0)),
                        1),
                isDisplayed())).check(matches(withText(containsString("Salle A"))));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
