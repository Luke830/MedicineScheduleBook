package luke.com.project.medicineschedulebook;

import android.util.Log;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

/**
 * Created by philipp on 02/06/16.
 */
public class DayAxisValueFormatter implements IAxisValueFormatter {

    private BarLineChartBase<?> chart;
    private ArrayList<String> arrayList;

    public DayAxisValueFormatter(BarLineChartBase<?> chart, ArrayList<String> arrayList) {
        this.chart = chart;
        this.arrayList = arrayList;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

//        Log.e("DEBUG", "value = " + value);
        int days = (int) value;
        return arrayList.get(days).substring(6, 8) + "일";
    }
}
