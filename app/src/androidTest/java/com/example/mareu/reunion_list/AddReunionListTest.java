package com.example.mareu.reunion_list;


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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddReunionListTest {

    @Rule
    public ActivityTestRule<ListReunionActivity> mActivityTestRule = new ActivityTestRule<>(ListReunionActivity.class);

    @Test
    public void listReunionActivityTest() {
        onView(allOf(withId(R.id.item_list_ajout_button),
                childAtPosition(
                        allOf(withId(R.id.main_content),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        2),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.nomDeReunion),
                childAtPosition(
                        allOf(withId(R.id.ajoutReunion),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        1),
                isDisplayed())).perform(replaceText("Mario"), closeSoftKeyboard());

        onView(allOf(withId(R.id.salleSpinner),
                childAtPosition(
                        allOf(withId(R.id.ajoutReunion),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        11),
                isDisplayed())).perform(click());

        onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1).perform(click());

        onView(allOf(withId(R.id.date),
                childAtPosition(
                        allOf(withId(R.id.ajoutReunion),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        3),
                isDisplayed())).perform(click());

        onView(allOf(withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.ScrollView")),
                                0),
                        3))).perform(scrollTo(), click());

        onView(allOf(withId(R.id.time),
                childAtPosition(
                        allOf(withId(R.id.ajoutReunion),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        5),
                isDisplayed())).perform(click());

        onView(allOf(withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.ScrollView")),
                                0),
                        3))).perform(scrollTo(), click());

       onView(allOf(withId(R.id.participants),
                childAtPosition(
                        allOf(withId(R.id.ajoutReunion),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        7),
                isDisplayed())).perform(replaceText("Mario@lamn.com"), closeSoftKeyboard());

        onView(allOf(withId(R.id.button_participants),
                childAtPosition(
                        allOf(withId(R.id.ajoutReunion),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        8),
                isDisplayed())).perform(click());


        onView(allOf(withId(R.id.submitButton), withText("SUBMIT"),
                childAtPosition(
                        allOf(withId(R.id.ajoutReunion),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        12),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.item_list_reunion),
                childAtPosition(
                        allOf(withId(R.id.list_reunions),
                                withParent(withId(R.id.container))),
                        6),
                isDisplayed())).check(matches(isDisplayed()));
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
