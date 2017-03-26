package luke.com.project.medicineschedulebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentLeftMenu extends Fragment implements View.OnClickListener {


    public FragmentLeftMenu() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_left_menu, container, false);
        view.findViewById(R.id.diary_button).setOnClickListener(this);
        view.findViewById(R.id.alarm_button).setOnClickListener(this);
        view.findViewById(R.id.chart_button).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        ((CalendarActivity) getActivity()).drawerLayout.closeDrawer(GravityCompat.START);

        switch (view.getId()) {
            case R.id.diary_button:
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                Utils.replaceFragement(getActivity(), R.id.main_fragment_container, new FragmentMain());
                break;

            case R.id.alarm_button:

                break;

            case R.id.chart_button:
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                Utils.replaceFragement(getActivity(), R.id.main_fragment_container, new FragmentChar());
                break;
        }
    }

}
