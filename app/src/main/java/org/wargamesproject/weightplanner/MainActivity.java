package org.wargamesproject.weightplanner;

import android.content.Context;
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
import android.content.SharedPreferences;

import org.wargamesproject.weightplanner.model.WeightType;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener
{
    // Member variables
    private WeightType currentWeightUnits;
    private SharedPreferences preferences;
    private TextView currentWeightView,targetWeightView;
    // Constants
    private static final String TAG = "WEIGHTPLAN_MAIN";
    private static final String PREF_SELECTED_UNITS = "unitType";
    private static final String PREF_CURRENT_WEIGHT = "currentWeight";
    private static final String PREF_TARGET_WEIGHT = "targetWeight";

    //
    // Activity methods
    //

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.v(TAG,"In onCreate()...");

        setContentView(R.layout.activity_main);

        // Get the saved settings for the app
        this.preferences = this.getPreferences(Context.MODE_PRIVATE);
        float currentWeight = this.preferences.getFloat(PREF_CURRENT_WEIGHT,195.0f);
        float targetWeight = this.preferences.getFloat(PREF_TARGET_WEIGHT,175.0f);
        String weightUnitsStr = this.preferences.getString(PREF_SELECTED_UNITS,WeightType.POUNDS.name());

        this.currentWeightUnits = WeightType.valueOf(weightUnitsStr);

        Spinner unitsSpinner = (Spinner)findViewById(R.id.unitsSpinner);

        ArrayAdapter<WeightType> unitsAdapter = new ArrayAdapter<WeightType>(this,R.layout.support_simple_spinner_dropdown_item,WeightType.values());
        unitsSpinner.setAdapter(unitsAdapter);
        unitsSpinner.setOnItemSelectedListener(this);
        // Set the selection of the spinner to the current weight units.  Using ordinal (zero-based)
        // index of the WeightType since the order of the enum equals their order in the Spinner
        unitsSpinner.setSelection(this.currentWeightUnits.ordinal());

        this.currentWeightView = (TextView)findViewById(R.id.weightValueText);
        this.targetWeightView = (TextView)findViewById(R.id.targetValueText);
        this.updateTextFields(currentWeight,targetWeight);
    }

    protected void onPause()
    {
        super.onPause();

        Log.v(TAG,"In onPause()...");

        // Save the existing weight information
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putFloat(PREF_CURRENT_WEIGHT,Float.parseFloat(currentWeightView.getText().toString()));
        editor.putFloat(PREF_TARGET_WEIGHT,Float.parseFloat(targetWeightView.getText().toString()));
        editor.putString(PREF_SELECTED_UNITS,this.currentWeightUnits.name());
        editor.commit();

        Log.v(TAG,"Application preferences saved.");
    }

    //
    // Implementation of methods declared in OnItemSelectedListener
    //

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        Log.v(TAG,"In onItemSelected()...");

        WeightType newUnits = (WeightType)parent.getItemAtPosition(position);

        Log.v(TAG,newUnits.toString());
        Log.v(TAG,newUnits.getClass().getName());

        float currentWeight = Float.parseFloat(currentWeightView.getText().toString());
        float targetWeight = Float.parseFloat(targetWeightView.getText().toString());

        // Get the conversion factor
        float conversionFactor = this.currentWeightUnits.convertTo(newUnits);

        Log.v(TAG,"conversionFactor = " + conversionFactor);

        currentWeight = currentWeight * conversionFactor;
        targetWeight = targetWeight * conversionFactor;

        Log.v(TAG,"new currentWeight = " + currentWeight);
        Log.v(TAG,"new targetWeight = " + targetWeight);

        this.currentWeightUnits = newUnits;
        this.updateTextFields(currentWeight,targetWeight);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        // Does nothing
    }

    //
    // Private methods
    //

    private void updateTextFields(float currentWeight,float targetWeight)
    {
        // Update the text views with the provided weight values
        currentWeightView.setText(String.format("%.2f",currentWeight));
        targetWeightView.setText(String.format("%.2f",targetWeight));
    }
}
