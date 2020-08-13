package com.hfad.newlogiccalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class InputActivity extends AppCompatActivity {





    private String input = "";
    private TextView textViewInput;
    private Button deleteButton;
    LogicExpression logicExpression = new LogicExpression();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);


        textViewInput = (TextView) findViewById(R.id.textViewInput);

        deleteButton = (Button) findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onDeleteShort();
            }
        });

        deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                onDeleteLong();
                return true;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.options);
        setSupportActionBar(toolbar);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Заполнение меню; элементы действий добавляются на панель приложения
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_options:
                Intent intent = new Intent(this, OptionsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onChangeCase(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        Button btnA = (Button) findViewById(R.id.buttonLetterA);
        Button btnB = (Button) findViewById(R.id.buttonLetterB);
        Button btnC = (Button) findViewById(R.id.buttonLetterC);
        Button btnD = (Button) findViewById(R.id.buttonLetterD);
        Button btnE = (Button) findViewById(R.id.buttonLetterE);
        Button btnF = (Button) findViewById(R.id.buttonLetterF);
        Button btnG = (Button) findViewById(R.id.buttonLetterG);
        Button btnH = (Button) findViewById(R.id.buttonLetterH);
        Button btnI = (Button) findViewById(R.id.buttonLetterI);
        Button btnJ = (Button) findViewById(R.id.buttonLetterJ);
        Button btnK = (Button) findViewById(R.id.buttonLetterK);
        Button btnL = (Button) findViewById(R.id.buttonLetterL);
        Button btnM = (Button) findViewById(R.id.buttonLetterM);
        Button btnN = (Button) findViewById(R.id.buttonLetterN);
        Button btnO = (Button) findViewById(R.id.buttonLetterO);
        Button btnP = (Button) findViewById(R.id.buttonLetterP);
        Button btnQ = (Button) findViewById(R.id.buttonLetterQ);
        Button btnR = (Button) findViewById(R.id.buttonLetterR);
        Button btnS = (Button) findViewById(R.id.buttonLetterS);
        Button btnT = (Button) findViewById(R.id.buttonLetterT);
        Button btnU = (Button) findViewById(R.id.buttonLetterU);
        Button btnV = (Button) findViewById(R.id.buttonLetterV);
        Button btnW = (Button) findViewById(R.id.buttonLetterW);
        Button btnX = (Button) findViewById(R.id.buttonLetterX);
        Button btnY = (Button) findViewById(R.id.buttonLetterY);
        Button btnZ = (Button) findViewById(R.id.buttonLetterZ);


        if (!btnCase.isChecked()) {
            btnA.setText("a");
            btnB.setText("b");
            btnC.setText("c");
            btnD.setText("d");
            btnE.setText("e");
            btnF.setText("f");
            btnG.setText("g");
            btnH.setText("h");
            btnI.setText("i");
            btnJ.setText("j");
            btnK.setText("k");
            btnL.setText("l");
            btnM.setText("m");
            btnN.setText("n");
            btnO.setText("o");
            btnP.setText("p");
            btnQ.setText("q");
            btnR.setText("r");
            btnS.setText("s");
            btnT.setText("t");
            btnU.setText("u");
            btnV.setText("v");
            btnW.setText("w");
            btnX.setText("x");
            btnY.setText("y");
            btnZ.setText("z");
        } else {
            btnA.setText("A");
            btnB.setText("B");
            btnC.setText("C");
            btnD.setText("D");
            btnE.setText("E");
            btnF.setText("F");
            btnG.setText("G");
            btnH.setText("H");
            btnI.setText("I");
            btnJ.setText("J");
            btnK.setText("K");
            btnL.setText("L");
            btnM.setText("M");
            btnN.setText("N");
            btnO.setText("O");
            btnP.setText("P");
            btnQ.setText("Q");
            btnR.setText("R");
            btnS.setText("S");
            btnT.setText("T");
            btnU.setText("U");
            btnV.setText("V");
            btnW.setText("W");
            btnX.setText("X");
            btnY.setText("Y");
            btnZ.setText("Z");
        }


    }

    public void onA(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "a";
        else
            input += "A";
        textViewInput.setText(input);

    }

    public void onB(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "b";
        else
            input += "B";
        textViewInput.setText(input);

    }

    public void onC(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "c";
        else
            input += "C";
        textViewInput.setText(input);

    }

    public void onD(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "d";
        else
            input += "D";
        textViewInput.setText(input);

    }

    public void onE(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "e";
        else
            input += "E";
        textViewInput.setText(input);

    }

    public void onF(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "f";
        else
            input += "F";
        textViewInput.setText(input);

    }

    public void onG(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "g";
        else
            input += "G";
        textViewInput.setText(input);

    }

    public void onH(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "h";
        else
            input += "H";
        textViewInput.setText(input);

    }

    public void onI(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "i";
        else
            input += "I";
        textViewInput.setText(input);

    }

    public void onJ(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "j";
        else
            input += "J";
        textViewInput.setText(input);

    }

    public void onK(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "k";
        else
            input += "K";
        textViewInput.setText(input);

    }

    public void onL(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "l";
        else
            input += "L";
        textViewInput.setText(input);

    }

    public void onM(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "m";
        else
            input += "M";
        textViewInput.setText(input);

    }

    public void onN(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "n";
        else
            input += "N";
        textViewInput.setText(input);

    }

    public void onO(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "o";
        else
            input += "O";
        textViewInput.setText(input);

    }

    public void onP(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "p";
        else
            input += "P";
        textViewInput.setText(input);

    }

    public void onQ(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "q";
        else
            input += "Q";
        textViewInput.setText(input);

    }

    public void onR(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "r";
        else
            input += "R";
        textViewInput.setText(input);

    }

    public void onS(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "s";
        else
            input += "S";
        textViewInput.setText(input);

    }

    public void onT(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "t";
        else
            input += "T";
        textViewInput.setText(input);

    }

    public void onU(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "u";
        else
            input += "U";
        textViewInput.setText(input);

    }

    public void onV(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "v";
        else
            input += "V";
        textViewInput.setText(input);

    }

    public void onW(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "w";
        else
            input += "W";
        textViewInput.setText(input);

    }

    public void onX(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "x";
        else
            input += "X";
        textViewInput.setText(input);

    }

    public void onY(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "y";
        else
            input += "Y";
        textViewInput.setText(input);

    }

    public void onZ(View view) {
        ToggleButton btnCase = (ToggleButton) findViewById(R.id.buttonCase);
        if (!btnCase.isChecked())
            input += "z";
        else
            input += "Z";
        textViewInput.setText(input);

    }

    public void onAddMod2(View view) {
        input += "⊕";
        textViewInput.setText(input);
    }

    public void onPierceArrow(View view) {
        input += "↓";
        textViewInput.setText(input);
    }

    public void onInversion(View view) {
        input += "¬";
        textViewInput.setText(input);
    }

    public void onConjunction(View view) {
        input += "∧";
        textViewInput.setText(input);
    }

    public void onShefferStroke(View view) {
        input += "|";
        textViewInput.setText(input);
    }

    public void onDisjunction(View view) {
        input += "∨";
        textViewInput.setText(input);
    }

    public void onEquivalention(View view) {
        input += "↔";
        textViewInput.setText(input);
    }

    public void onImplication(View view) {
        input += "→";
        textViewInput.setText(input);
    }

    public void onReversedImplication(View view) {
        input += "←";
        textViewInput.setText(input);
    }

    public void onOpenBracket(View view) {
        input += "(";
        textViewInput.setText(input);
    }

    public void onClosedBracket(View view) {
        input += ")";
        textViewInput.setText(input);
    }

    public void onZero(View view) {
        input += "0";
        textViewInput.setText(input);
    }

    public void onOne(View view) {
        input += "1";
        textViewInput.setText(input);
    }

    public void onOpenSqBracket(View view) {
        input += "[";
        textViewInput.setText(input);
    }

    public void onCloseSqBracket(View view) {
        input += "]";
        textViewInput.setText(input);
    }

    public void onOpenFgBracket(View view) {
        input += "{";
        textViewInput.setText(input);
    }

    public void onCloseFgBracket(View view) {
        input += "}";
        textViewInput.setText(input);
    }

    public void onCalculate(View view) {

        if (logicExpression.isCorrectInput(input)) {
            Intent intent = new Intent(this, ResultActivity.class);
            input=input.replace('}',')').replace('{','(').replace(']',')').replace('[','(');
            intent.putExtra(ResultActivity.EXTRA_INPUT_STRING, input);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Wrong expression!!!", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    /*
    public void onDelete(View view){
        //input=input.substring(0,input.length()-1);
        input=input.replaceAll(".$","");
        textViewInput.setText(input);
    }
    */

    public void onDeleteShort() {
        //input=input.substring(0,input.length()-1);
        input = input.replaceAll(".$", "");
        textViewInput.setText(input);
    }

    public void onDeleteLong() {
        //input=input.substring(0,input.length()-1);
        input = "";
        textViewInput.setText(input);
    }


}