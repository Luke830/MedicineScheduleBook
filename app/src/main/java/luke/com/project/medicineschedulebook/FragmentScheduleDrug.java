package luke.com.project.medicineschedulebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class FragmentScheduleDrug extends Fragment implements View.OnClickListener {


    public FragmentScheduleDrug() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_schedule_drug, container, false);
        view.findViewById(R.id.button_back).setOnClickListener(this);
        view.findViewById(R.id.button_day).setOnClickListener(this);
        view.findViewById(R.id.button_week).setOnClickListener(this);

        ((TextView) view.findViewById(R.id.text_view_title)).setText(((ScheduleDrugActivity) getActivity()).title);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_back:
                backButtonEvent();
                break;
            case R.id.button_day:
                Utils.replaceFragement(getActivity(), R.id.frame_layout, new FragmentAddDayDrug(), this);
                break;
            case R.id.button_week:
                Utils.replaceFragement(getActivity(), R.id.frame_layout, new FragmentAddWeekDrug(), this);
                break;
        }

    }

    public void backButtonEvent() {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0) {
            getActivity().onBackPressed();
        } else {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}
