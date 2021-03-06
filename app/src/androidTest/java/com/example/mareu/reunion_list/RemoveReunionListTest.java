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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RemoveReunionListTest {

    private static int ITEMS_COUNT = 6;
    @Rule
    public ActivityTestRule<ListReunionActivity> mActivityTestRule = new ActivityTestRule<>(ListReunionActivity.class);

    @Test
    public void removeReunionListTest() {
        onView(allOf(withId(R.id.item_list_delete_button),
                childAtPosition(
                        allOf(withId(R.id.item_list_reunion),
                                childAtPosition(
                                        withId(R.id.list_reunions),
                                        0)),
                        3),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.list_reunions),
                withParent(allOf(withId(R.id.container),
                        childAtPosition(
                                withId(R.id.main_content),
                                1))),
                isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
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
