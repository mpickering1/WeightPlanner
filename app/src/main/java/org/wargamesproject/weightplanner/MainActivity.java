package org.wargamesproject.weightplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import org.wargamesproject.weightplanner.model.WeightConverter;

public class MainActivity extends AppCompatActivity implements TextWatcher
{
    private WeightConverter weightModel;
    private static final String TAG = "WEIGHTPLAN_MAIN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.weightModel = new WeightConverter();

        TextView weightEntry = (TextView)findViewById(R.id.weightValueText);
        weightEntry.addTextChangedListener(this);
        weightEntry.setText(Double.toString(weightModel.getWeight()));

        TextView stoneView = (TextView)findViewById(R.id.stoneValue);
        TextView poundsView = (TextView)findViewById(R.id.lbsValue);

        stoneView.setText(Double.toString(weightModel.getWeightInStone()));
        poundsView.setText(Double.toString(weightModel.getWeightInPounds()));
    }

    //
    // Implementation of methods declared in TextWater
    //

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Log.v(TAG, "In beforeTextChanged()...");
        //Log.v(TAG, s.toString());
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        //Log.v(TAG, "In onTextChanged()...");
        //Log.v(TAG, s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.v(TAG, "In afterTextChanged()...");
        Log.v(TAG, s.toString());

        try
        {
            this.weightModel.setWeight(Double.valueOf(s.toString()));

            TextView stoneView = (TextView)findViewById(R.id.stoneValue);
            TextView poundsView = (TextView)findViewById(R.id.lbsValue);

            stoneView.setText(Double.toString(weightModel.getWeightInStone()));
            poundsView.setText(Double.toString(weightModel.getWeightInPounds()));
        }
        catch (NumberFormatException e)
        {
            Log.e(TAG,"NumberFormatException on input for weight.  Expected number, got: " + s.toString());
        }
    }

    //
    // Private
    //
}
