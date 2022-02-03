package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    //create an empty list to hold the numbers.
    private final List<String> myList = new ArrayList<>();
     //create a reference to a textView
     private TextView showNumber;
     //create a reference to a textView
     private Button advanced;
    //create a variable for symbols
    private final ArrayList<String> mySymbols = new ArrayList<String>(Arrays.asList("*","-","+","/"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reference the textView id
        showNumber = (TextView) findViewById(R.id.textView);

        //reference a transition button id
        advanced = (Button) findViewById(R.id.button19);
    }

    /**
     * set up onClick buttons
     */

    public void onSix(View view) {
        myList.add("6");
        showNumber.append("6");
    }

    public void onFive(View view) {
        myList.add("5");
        showNumber.append("5");
    }

    public void onDash(View view) {
        myList.add("-");
        showNumber.append("-");
    }

    public void onFour(View view) {
        myList.add("4");
        showNumber.append("4");
    }

    public void onThree(View view) {
        myList.add("3");
        //showNumber.setText("3");
        showNumber.append("3");
    }

    public void onSum(View view) {
        myList.add("+");
        showNumber.append("+");
    }

    public void onOne(View view) {
        myList.add("1");
        showNumber.append("1");
        //showOnList();
    }

    public void onTwo(View view) {
        myList.add("2");
        //showOnList();
        showNumber.append("2");
    }

    public void onSeven(View view) {
        myList.add("7");
        showNumber.append("7");
    }

    public void onNine(View view) {
        myList.add("9");
        showNumber.append("9");
    }

    public void onEight(View view) {
        myList.add("8");
        showNumber.append("8");
    }

    public void onMultiply(View view) {
        myList.add("*");
        showNumber.append("*");
    }

    public void onC(View view) {
        myList.clear();
        showNumber.setText(" ");
    }

    public void onZero(View view) {
        myList.add("0");
        showNumber.append("0");
    }

    public void onDivide(View view) {
        myList.add("/");
        showNumber.append("/");
    }

    public void onEqual(View view) {
        //myList.add("=");
        showNumber.append("=");
        verifyOrder();
    }

    public void onAdvance(View view) {
        Intent intent = new Intent(this, AdvancedActivity2.class);
        startActivity(intent);
    }

    /**
     * function checks the order of myList
     * each time calculating the result.
     */

    public void verifyOrder(){
        //int result = 0;
        int firstItem = 0;

        //boolean isCorrect = true;
        //check if the first item is an int
        try{
            firstItem = Integer.parseInt(myList.get(0));
        }catch (NumberFormatException ex){
            showNumber.setText(R.string.not_an_op);
        }

        //loop through the reminder of the list
        //check if a sign is always followed by a number
        String currentSymbol = "";
        int currentInt = 0;
        int counter = 1;


        if (myList.size() >= 3){
            boolean isStr = true;
            while(isStr && counter < myList.size()){
                //call a function to check if sign is in symbol list
                if (isSymbol(myList.get(counter))){
                    currentSymbol = myList.get(counter);
                    //counter++;
                }
                else{
                    isStr = false;
                    showNumber.setText(R.string.not_an_op);

                }
                //check if the currentInt is an int
                try{
                    currentInt = Integer.parseInt(myList.get(counter + 1));
                    //counter++;
                }catch (NumberFormatException ex){
                    isStr = false;
                    showNumber.setText(R.string.not_an_op);
                }
                //perform calculation add on
                firstItem = calculate(firstItem, currentSymbol,currentInt);
                counter+=2;

            }
        }else{
            showNumber.setText(R.string.not_an_op);
        }
        showNumber.append(Integer.toString(firstItem));
    }


    /**
     * returns true if the input is in mySymbols
     */

    private boolean isSymbol(String s) {
        boolean isTrue = false;
        for (int i = 0; i<mySymbols.size(); i++){
            if (s.equals(mySymbols.get(i))){
                isTrue = true;
            }
        }
        return isTrue;
    }

    /**
     * create a switch that calculates result depending on the sign
     */

    private int calculate(int firstItem, String secondSymbol, int currentItem) {
        int result = 0;
        switch (secondSymbol) {
            case "*":
                result = firstItem * currentItem;
                break;
            case "-":
                result = firstItem - currentItem;
                break;
            case "/":
                result = firstItem / currentItem;
                break;
            default:
                result = firstItem + currentItem;
                break;
        }
        return result;
    }

}