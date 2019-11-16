package com.example.hind.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int points;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitResult(View view) {
        EditText editText = findViewById(R.id.name_field);
        String name = editText.getText().toString();
        EditText favFruitEditText = findViewById(R.id.fav_fruit);
        String fav_fruit =  favFruitEditText.getText().toString();
        FruitCheckBox();
        DisplayMessage(name, fav_fruit);

    }

    public void AppleRadioButtonClicked(View v) {
        RadioGroup appleRadioGroup = findViewById(R.id.appleRadioGroup);
        int radioId = appleRadioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        if (radioButton.getText() == "Red"){
            points +=1;
        }

    }

    public void grapeRadioButtonClicked(View v) {
        RadioGroup grapeRadioGroup = findViewById(R.id.grapeRadioGroup);
        int radioId = grapeRadioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        if (radioButton.getText() == "purple"){
            points +=1;
        }
    }

    public void FruitCheckBox(){
        CheckBox Apple = findViewById(R.id.checkboxApple);
        boolean AppleChecked = Apple.isChecked();
        CheckBox orange = findViewById(R.id.checkboxOrange);
        boolean orangeChecked = orange.isChecked();
        CheckBox potatos = findViewById(R.id.checkboxPotatos);
        boolean potatosChecked = potatos.isChecked();
        CheckBox carrots = findViewById(R.id.checkboxCarrots);
        boolean carrotChecked = carrots.isChecked();
        CheckBox berry = findViewById(R.id.checkboxBerry);
        boolean berryChecked = berry.isChecked();

        int fruitPoints = 0;

        if(AppleChecked){
            fruitPoints += 1;
        }

        if(orangeChecked){
            fruitPoints += 1;
        }

        if(berryChecked){
            fruitPoints += 1;
        }

        if (!potatosChecked){
            fruitPoints += 1;
        }

        if (!carrotChecked){
            fruitPoints += 1;
        }

        if (fruitPoints == 5){
            points += 1;
        }else {
            Toast.makeText(this,"are you shoure about your answer?",Toast.LENGTH_SHORT).show();
        }
    }

    public void DisplayMessage(String name, String fav_fruit){
        TextView textView = findViewById(R.id.resutl_summary_text_view);
        textView.setText("Welcome " + name + "\n Your faviorte Fruit is :"+fav_fruit+"\nYour Score is " + points + "/3 Congrats!");
    }

}
