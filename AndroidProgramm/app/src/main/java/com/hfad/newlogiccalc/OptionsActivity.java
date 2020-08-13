package com.hfad.newlogiccalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {


    private SharedPreferences sharedPrefs;
    SharedPreferences.Editor ed;
    public static final String APP_PREFERENCES = "settings";

    public static final String APP_PREFERENCES_INVERSION_PRIORITY = "inversionPriority";
    public static final String APP_PREFERENCES_CONJUNCTION_PRIORITY = "conjunctionPriority";
    public static final String APP_PREFERENCES_SHEFFERSTROKE_PRIORITY = "shefferStrokePriority";
    public static final String APP_PREFERENCES_DISJUNCTION_PRIORITY = "disjunctionPriority";
    public static final String APP_PREFERENCES_PIERCEARROW_PRIORITY = "pierceArrowPriority";
    public static final String APP_PREFERENCES_ADDMOD2_PRIORITY = "addMod2Priority";
    public static final String APP_PREFERENCES_EQUIVALENTION_PRIORITY = "equivalentionPriority";
    public static final String APP_PREFERENCES_IMPLICATION_PRIORITY = "implicationPriority";
    public static final String APP_PREFERENCES_REVERSEDIMPLICATION_PRIORITY = "reversedImplicationPriority";

    Spinner inversionSpinner;
    Spinner conjunctionSpinner;
    Spinner shefferStrokeSpinner;
    Spinner disjunctionSpinner;
    Spinner pierceArrowSpinner;
    Spinner addMod2Spinner;
    Spinner equivalentionSpinner;
    Spinner implicationSpinner;
    Spinner reversedImplicationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        sharedPrefs = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        ed = sharedPrefs.edit();
        inversionSpinner = (Spinner) findViewById(R.id.spinnerInversion);
        conjunctionSpinner = (Spinner) findViewById(R.id.spinnerConjunction);
        shefferStrokeSpinner = (Spinner) findViewById(R.id.spinnerShefferStroke);
        disjunctionSpinner = (Spinner) findViewById(R.id.spinnerDisjunction);
        pierceArrowSpinner = (Spinner) findViewById(R.id.spinnerPierceArrow);
        addMod2Spinner = (Spinner) findViewById(R.id.spinnerAddMod2);
        equivalentionSpinner = (Spinner) findViewById(R.id.spinnerEquivalention);
        implicationSpinner = (Spinner) findViewById(R.id.spinnerImplication);
        reversedImplicationSpinner = (Spinner) findViewById(R.id.spinnerReversedImplication);


        inversionSpinner.setSelection(sharedPrefs.getInt(APP_PREFERENCES_INVERSION_PRIORITY, 3));
        conjunctionSpinner.setSelection(sharedPrefs.getInt(APP_PREFERENCES_CONJUNCTION_PRIORITY, 2));
        shefferStrokeSpinner.setSelection(sharedPrefs.getInt(APP_PREFERENCES_SHEFFERSTROKE_PRIORITY, 2));
        disjunctionSpinner.setSelection(sharedPrefs.getInt(APP_PREFERENCES_DISJUNCTION_PRIORITY, 1));
        pierceArrowSpinner.setSelection(sharedPrefs.getInt(APP_PREFERENCES_PIERCEARROW_PRIORITY, 1));
        addMod2Spinner.setSelection(sharedPrefs.getInt(APP_PREFERENCES_ADDMOD2_PRIORITY, 0));
        equivalentionSpinner.setSelection(sharedPrefs.getInt(APP_PREFERENCES_EQUIVALENTION_PRIORITY, 0));
        implicationSpinner.setSelection(sharedPrefs.getInt(APP_PREFERENCES_IMPLICATION_PRIORITY, 0));
        reversedImplicationSpinner.setSelection(sharedPrefs.getInt(APP_PREFERENCES_REVERSEDIMPLICATION_PRIORITY, 0));

        Toolbar toolbar = (Toolbar) findViewById(R.id.options);
        setSupportActionBar(toolbar);
    }

    public void onSave(View view) {
        Integer inversionValue = Integer.valueOf(inversionSpinner.getSelectedItem().toString());
        Integer conjunctionValue = Integer.valueOf(conjunctionSpinner.getSelectedItem().toString());
        Integer shefferStrokeValue = Integer.valueOf(shefferStrokeSpinner.getSelectedItem().toString());
        Integer disjunctionValue = Integer.valueOf(disjunctionSpinner.getSelectedItem().toString());
        Integer pierceArrowValue = Integer.valueOf(pierceArrowSpinner.getSelectedItem().toString());
        Integer addMod2Value = Integer.valueOf(addMod2Spinner.getSelectedItem().toString());
        Integer equivalentionValue = Integer.valueOf(equivalentionSpinner.getSelectedItem().toString());
        Integer implicationValue = Integer.valueOf(implicationSpinner.getSelectedItem().toString());
        Integer reversedImplicationValue = Integer.valueOf(reversedImplicationSpinner.getSelectedItem().toString());


        ed.putInt(APP_PREFERENCES_INVERSION_PRIORITY, inversionValue-1);
        ed.putInt(APP_PREFERENCES_CONJUNCTION_PRIORITY, conjunctionValue-1);
        ed.putInt(APP_PREFERENCES_SHEFFERSTROKE_PRIORITY, shefferStrokeValue-1);
        ed.putInt(APP_PREFERENCES_DISJUNCTION_PRIORITY, disjunctionValue-1);
        ed.putInt(APP_PREFERENCES_PIERCEARROW_PRIORITY, pierceArrowValue-1);
        ed.putInt(APP_PREFERENCES_ADDMOD2_PRIORITY, addMod2Value-1);
        ed.putInt(APP_PREFERENCES_EQUIVALENTION_PRIORITY, equivalentionValue-1);
        ed.putInt(APP_PREFERENCES_IMPLICATION_PRIORITY, implicationValue-1);
        ed.putInt(APP_PREFERENCES_REVERSEDIMPLICATION_PRIORITY, reversedImplicationValue-1);
        ed.apply();

        Toast toast = Toast.makeText(getApplicationContext(),
                "Parameters was saved", Toast.LENGTH_SHORT);
        toast.show();

    }

    public void onDefault(View view) {
        inversionSpinner.setSelection(3);
        conjunctionSpinner.setSelection(2);
        shefferStrokeSpinner.setSelection(2);
        disjunctionSpinner.setSelection(1);
        pierceArrowSpinner.setSelection(1);
        addMod2Spinner.setSelection(0);
        equivalentionSpinner.setSelection(0);
        implicationSpinner.setSelection(0);
        reversedImplicationSpinner.setSelection(0);
    }
}