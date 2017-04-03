package luke.com.project.medicineschedulebook;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by firefox on 2017. 4. 3..
 */

public class FragmentPrescription extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_prescription, container, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_date);
        tvTitle.setText("처방전");

        view.findViewById(R.id.image_button_menu).setOnClickListener(this);

        view.findViewById(R.id.btn_take_picture).setOnClickListener(this);
        view.findViewById(R.id.btn_view_picture).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btn_take_picture:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;

            case R.id.btn_view_picture:
                intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setType("image/*");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.image_button_menu:
                if (((CalendarActivity) getActivity()).drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    ((CalendarActivity) getActivity()).drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    ((CalendarActivity) getActivity()).drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;

        }
    }
}
