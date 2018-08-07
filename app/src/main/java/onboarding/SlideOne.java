package onboarding;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.android.ignitioncoin.org.ignition.R;

/**
 * Slide one for App Onboarding!
 */

public class SlideOne extends Fragment {

    private TextView mTitle, mDescription;

    public SlideOne() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.slide_one, container, false);

        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/sofia-pro.ttf");
        mTitle = (TextView) v.findViewById(R.id.title);
        mDescription = (TextView) v.findViewById(R.id.description);

        mTitle.setTypeface(customFont);
        mDescription.setTypeface(customFont);

        return v;
    }


}