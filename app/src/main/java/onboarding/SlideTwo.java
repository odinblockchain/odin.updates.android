package onboarding;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.android.blockchain.org.odin.R;

/**
 * Slide one for App Onboarding!
 */

public class SlideTwo extends Fragment {

    private TextView mTitle, mTitleTwo, mDescription;

    public SlideTwo() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.slide_two, container, false);

        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/rubiclight.ttf");
        mTitle = (TextView) v.findViewById(R.id.title);
        mTitleTwo = (TextView) v.findViewById(R.id.title_two);
        mDescription = (TextView) v.findViewById(R.id.description);

        mTitle.setTypeface(customFont);
        mTitleTwo.setTypeface(customFont);
        mDescription.setTypeface(customFont);

        return v;
    }


}