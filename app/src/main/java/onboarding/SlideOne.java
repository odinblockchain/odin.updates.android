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

public class SlideOne extends Fragment {

    private TextView mTitle, mTitleTwo, mDescription;

    public SlideOne() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.slide_one, container, false);

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