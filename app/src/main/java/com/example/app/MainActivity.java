package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;
public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString()))
                    display.setText("");
            }
        });
    }


    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText((strToAdd));
            display.setSelection(cursorPos+1);
        }
        else{
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos+1);
        }

    }
    public void zerobtn(View View){
        updateText( "0");
    }
    public void clearbtn(View View){
        display.setText("");
    }
    public void paranthesisbtn(View View){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")){
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")){
                closedPar += 1;
            }
        }
        if (openPar == closedPar || display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if (closedPar < openPar && !display.getText().toString().substring(textLen-1,textLen).equals(")")){
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }
    public void exponentbtn(View View){
        updateText( "^");
    }
    public void divisionbtn(View View){
        updateText( "÷");
    }
    public void sevenbtn(View View){
        updateText( "7");
    }
    public void eightbtn(View View){
        updateText( "8");
    }
    public void ninebtn(View View){
        updateText( "9");
    }
    public void multiplybtn(View View){
        updateText( "x");
    }
    public void fourbtn(View View){
        updateText( "7");
    }
    public void fivebtn(View View){
        updateText( "5");
    }
    public void sixbtn(View View){
        updateText( "6");
    }
    public void subtractbtn(View View){
        updateText( "-");
    }
    public void onebtn(View View){
        updateText( "1");
    }
    public void twobtn(View View){
        updateText( "2");
    }
    public void threebtn(View View){
        updateText( "3");
    }
    public void addbtn(View View){
        updateText( "+");
    }
    public void plusminusbtn(View View){
        updateText( "+/-");
    }
    public void pointbtn(View View){
        updateText( ".");
    }
    public void equalbtn(View View){
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("x", "*");

        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }
    public void backbtn(View View){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos !=0 && textLen !=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}