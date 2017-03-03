package org.wargamesproject.weightplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import org.wargamesproject.weightplanner.model.WeightConverter;
import org.wargamesproject.weightplanner.model.WeightType;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener
{
    private WeightType currentWeightUnits;
    private static final String TAG = "WEIGHTPLAN_MAIN";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Spinner unitsSpinner = (Spinner)findViewById(R.id.unitsSpinner);

        ArrayAdapter<WeightType> unitsAdapter = new ArrayAdapter<WeightType>(this,R.layout.support_simple_spinner_dropdown_item,WeightType.values());
        unitsSpinner.setAdapter(unitsAdapter);
        unitsSpinner.setOnItemSelectedListener(this);
        unitsSpinner.setSelection(1);  // Default to pounds

        this.currentWeightUnits = WeightType.POUNDS;
    }

    //
    // Implementation of methods declared in OnItemSelectedListener
    //

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        Log.v(TAG,"In onItemSelected()...");

        WeightType item = (WeightType)parent.getItemAtPosition(position);

        Log.v(TAG,item.toString());
        Log.v(TAG,item.getClass().getName());

        TextView currentWeightView = (TextView)findViewById(R.id.weightValueText);
        TextView targetWeightView = (TextView)findViewById(R.id.targetValueText);
        double currentWeight = Double.parseDouble(currentWeightView.getText().toString());
        double targetWeight = Double.parseDouble(targetWeightView.getText().toString());

        // Get the conversion factor
        double conversionFactor = this.currentWeightUnits.convertTo(item);

        Log.v(TAG,"conversionFactor = " + conversionFactor);

        currentWeight = currentWeight * conversionFactor;
        targetWeight = targetWeight * conversionFactor;

        Log.v(TAG,"new currentWeight = " + currentWeight);
        Log.v(TAG,"new targetWeight = " + targetWeight);

        this.currentWeightUnits = item;

        // Update the text views with the converted weight values
        currentWeightView.setText(Double.toString(currentWeight));
        targetWeightView.setText(Double.toString(targetWeight));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        // Does nothing
    }
}
