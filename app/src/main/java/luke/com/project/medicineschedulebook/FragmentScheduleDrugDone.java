package luke.com.project.medicineschedulebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentScheduleDrugDone extends Fragment implements View.OnClickListener {


    public FragmentScheduleDrugDone() {
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
        View view = inflater.inflate(R.layout.fragment_schedule_drug_done, container, false);


        view.findViewById(R.id.done_button_back).setOnClickListener(this);

        String drugDay = ((ScheduleDrugDoneActivity) getActivity()).drugDay;
        String drugWeek = ((ScheduleDrugDoneActivity) getActivity()).drugWeek;

        if (!TextUtils.isEmpty(drugDay) && "null".equals(drugDay) == false) {
            view.findViewById(R.id.done_button_day).setVisibility(View.VISIBLE);
            view.findViewById(R.id.done_button_day).setOnClickListener(this);
        } else {
            view.findViewById(R.id.done_button_day).setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(drugWeek) && "null".equals(drugWeek) == false) {
            view.findViewById(R.id.done_button_week).setVisibility(View.VISIBLE);
            view.findViewById(R.id.done_button_week).setOnClickListener(this);
        } else {
            view.findViewById(R.id.done_button_week).setVisibility(View.GONE);
        }

        ((TextView) view.findViewById(R.id.done_text_view_title)).setText(((ScheduleDrugDoneActivity) getActivity()).title);
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
            case R.id.done_button_back:
                backButtonEvent();
                break;
            case R.id.done_button_day:
                Utils.replaceFragement(getActivity(), R.id.frame_layout_done, new FragmentAddDayDrugDone(), this);
                break;
            case R.id.done_button_week:
                Utils.replaceFragement(getActivity(), R.id.frame_layout_done, new FragmentAddWeekDrugDone(), this);
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
