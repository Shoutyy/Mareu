package com.example.mareu;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.mareu.reunion_list.ListReunionActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class ReunionListTest {

    private ListReunionActivity mActivity;

    @Rule
    public ActivityTestRule<ListReunionActivity> mActivityRule =
            new ActivityTestRule<>(ListReunionActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

}
