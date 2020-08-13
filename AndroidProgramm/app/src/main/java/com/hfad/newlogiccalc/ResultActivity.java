package com.hfad.newlogiccalc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ResultActivity extends AppCompatActivity {

    private String inputLogicExpression;
    public static final String EXTRA_INPUT_STRING ="inputString" ;

    View clickSource;
    View touchSource;
    ListView listViewSets;
    ListView listViewResults;
    ListView listViewLetters;
    ListView listViewNumOfSet;
    HorizontalScrollView horizontalScrollView;
    int offset=0;
    LogicExpression logicExpression= new LogicExpression();


    private SharedPreferences sharedPrefs;
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
    private byte inversionPriority;
    private byte conjunctionPriority;
    private byte shefferStrokePriority;
    private byte disjunctionPriority;
    private byte pierceArrowPriority;
    private byte addMod2Priority;
    private byte equivalentionPriority;
    private byte implicationPriority;
    private byte reversedImplicationPriority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.options);
        setSupportActionBar(toolbar);


        inputLogicExpression=(String) getIntent().getExtras().get(EXTRA_INPUT_STRING);
        listViewSets = (ListView) findViewById(R.id.list_view_sets);
        listViewResults = (ListView) findViewById(R.id.list_view_result);
        listViewLetters = (ListView) findViewById(R.id.list_view_letters);
        listViewNumOfSet = (ListView) findViewById(R.id.list_view_num_of_set);
        horizontalScrollView= (HorizontalScrollView) findViewById(R.id.horizontalScrollView);



        listViewSets.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (touchSource == null)
                    touchSource = v;

                if(v == touchSource) {
                    listViewResults.dispatchTouchEvent(event);
                    listViewNumOfSet.dispatchTouchEvent(event);
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        clickSource = v;
                        touchSource = null;
                    }
                }

                return false;
            }
        });

        horizontalScrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {



                touchSource = null;



                return false;
            }
        });



        listViewResults.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (touchSource == null)
                    touchSource = v;
                if(v == touchSource) {
                    listViewSets.dispatchTouchEvent(event);
                    listViewNumOfSet.dispatchTouchEvent(event);
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        clickSource = v;
                        touchSource = null;
                    }
                }
                return false;
            }
        });
        listViewNumOfSet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (touchSource == null)
                    touchSource = v;
                if(v == touchSource) {
                    listViewSets.dispatchTouchEvent(event);
                    listViewResults.dispatchTouchEvent(event);
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        clickSource = v;
                        touchSource = null;
                    }
                }
                return false;
            }
        });


        listViewSets.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(view == clickSource)
                {
                    listViewResults.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
                    listViewNumOfSet.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}
        });

        listViewResults.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(view == clickSource) {
                    listViewSets.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
                    listViewNumOfSet.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}
        });

        listViewNumOfSet.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(view == clickSource) {
                    listViewSets.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
                    listViewResults.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}
        });



        //ArrayAdapter<String> numOfSetAdapter = new ArrayAdapter<>(this,)

        sharedPrefs = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        inversionPriority =(byte) sharedPrefs.getInt(APP_PREFERENCES_INVERSION_PRIORITY, 4 );
        conjunctionPriority =(byte) sharedPrefs.getInt(APP_PREFERENCES_CONJUNCTION_PRIORITY, 3 );
        shefferStrokePriority =(byte) sharedPrefs.getInt(APP_PREFERENCES_SHEFFERSTROKE_PRIORITY, 3 );
        disjunctionPriority =(byte) sharedPrefs.getInt(APP_PREFERENCES_DISJUNCTION_PRIORITY, 2 );
        pierceArrowPriority =(byte) sharedPrefs.getInt(APP_PREFERENCES_PIERCEARROW_PRIORITY, 2 );
        addMod2Priority =(byte) sharedPrefs.getInt(APP_PREFERENCES_ADDMOD2_PRIORITY, 1 );
        equivalentionPriority =(byte) sharedPrefs.getInt(APP_PREFERENCES_EQUIVALENTION_PRIORITY, 1 );
        implicationPriority =(byte) sharedPrefs.getInt(APP_PREFERENCES_IMPLICATION_PRIORITY, 1 );
        reversedImplicationPriority =(byte) sharedPrefs.getInt(APP_PREFERENCES_REVERSEDIMPLICATION_PRIORITY, 1 );

        setTruthTable();
        setVariablesAndSetsAndNums();










    }

    private void setTruthTable(){
        ArrayList<Byte> resultBytes=logicExpression.takeTruthTable(inputLogicExpression, reversedImplicationPriority, implicationPriority,equivalentionPriority,addMod2Priority,pierceArrowPriority,
                disjunctionPriority,shefferStrokePriority,conjunctionPriority, inversionPriority);;

        ArrayList<String> result = new ArrayList<>();
        for (Byte i: resultBytes)
            result.add(String.valueOf(i));
        ArrayAdapter<String> adapterResult = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, result);
        listViewResults.setAdapter(adapterResult);

    }

    private void setVariablesAndSetsAndNums() {

        HashSet<Character> variables = logicExpression.takeVariables(inputLogicExpression);
        Character[] variablesArray = variables.toArray(new Character[variables.size()]);
        Arrays.sort(variablesArray);
        ArrayList<String> variablesList =new ArrayList<>();
        String variablesString = "";
        for (Character i: variablesArray){
            variablesString+=String.valueOf(i);
            variablesString+="   ";
        }
        variablesList.add(variablesString);

        CustomListAdapter adapterVariables = new CustomListAdapter(this, android.R.layout.simple_list_item_1, variablesList);
        listViewLetters.setAdapter(adapterVariables);


        ArrayList<String> setsList = new ArrayList<>();
        ArrayList<String> numOfSetList = new ArrayList<>();
        if (!variables.isEmpty()) {

            int i = 0;

            while (i != Math.pow(2, variablesArray.length)) {
                //String zeroOneSelection = "0".repeat(variables_array.length - Integer.toBinaryString(i).length()) + Integer.toBinaryString(i);// набор
                String zeroOneSelection ="";  // набор

                for (int v=0; v < variablesArray.length - Integer.toBinaryString(i).length() ; v++)
                    zeroOneSelection+="0";
                zeroOneSelection+=Integer.toBinaryString(i);
                zeroOneSelection = zeroOneSelection.replace(" ", "");

                //String[] selectionArray = zeroOneSelection.split("");

                char[] strToArray = zeroOneSelection.toCharArray();
                String set="";
                for (int j=0;j<strToArray.length;j++){
                    set+=String.valueOf(strToArray[j]);
                    set+="   ";
                }
                setsList.add(set);
                numOfSetList.add(String.valueOf(i));
                i++;

            }
            CustomListAdapter adapterSets = new CustomListAdapter(this, android.R.layout.simple_list_item_1, setsList);
            listViewSets.setAdapter(adapterSets);

            ArrayAdapter<String> adapterNums = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numOfSetList);
            listViewNumOfSet.setAdapter(adapterNums);


    }




}



}