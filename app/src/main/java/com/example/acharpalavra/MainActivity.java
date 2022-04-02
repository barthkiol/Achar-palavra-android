package com.example.acharpalavra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    TextView word;
    TextView findWord;
    int aux = 1;
    int wordOrder = 1;
    Button btnC, btnV, btnK, btnA, btnP, btnB;
    Button btnNext;
    Button btnListen;
    private  TextToSpeech txtVoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        word = findViewById(R.id.word);
        findWord = findViewById(R.id.findWord);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnP = findViewById(R.id.btnP);
        btnC = findViewById(R.id.btnC);
        btnV = findViewById(R.id.btnV);
        btnK = findViewById(R.id.btnK);
        btnNext = findViewById(R.id.btnNext);
        btnListen = findViewById(R.id.btnListen);
        word.setText("_AS_");





        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewC) {
                if(word.getText().toString().equals("_AS_")){
                    word.setText("CAS_");
                    aux = 3;
                }
                else if(word.getText().toString().equals("_ASA")){
                    word.setText("CASA");
                    aux = 4;
                    btnNext.setVisibility(View.VISIBLE);
                    btnListen.setVisibility(View.VISIBLE);
                    wordOrder = 2;
                }
                else if(word.getText().toString().equals("__RR_")){
                    word.setText("C_RR_");
                    aux = 8;

                }
                else if(word.getText().toString().equals("__RRO")){
                    word.setText("C_RRO");
                    aux = 11;

                }
                else if(word.getText().toString().equals("_ARR_")){
                    word.setText("CARR_");
                    aux = 12;

                }
                else if(word.getText().toString().equals("_ARRO")){
                    word.setText("CARRO");
                    aux = 4;
                    btnNext.setVisibility(View.VISIBLE);
                    wordOrder = 1;
                    btnListen.setVisibility(View.VISIBLE);
                }
            }
        });

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewA) {
                if(word.getText().toString().equals("_AS_")){
                    word.setText("_ASA");
                    aux = 2;
                }
                else if (word.getText().toString().equals("CAS_")){
                    word.setText("CASA");
                    aux = 4;
                    btnNext.setVisibility(View.VISIBLE);
                    wordOrder = 2;
                    btnListen.setVisibility(View.VISIBLE);
                }
                else if(word.getText().toString().equals("__RR_")){
                    word.setText("_ARR_");
                    aux = 9;

                }
                else if(word.getText().toString().equals("C_RR_")){
                    word.setText("CARR_");
                    aux = 12;

                }
                else if(word.getText().toString().equals("__RRO")){
                    word.setText("_ARRO");
                    aux = 13;

                }
                else if(word.getText().toString().equals("C_RRO")){
                    word.setText("CARRO");
                    aux = 4;
                    btnNext.setVisibility(View.VISIBLE);
                    wordOrder = 1;
                    btnListen.setVisibility(View.VISIBLE);
                }
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewB) {

            }
        });

        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewV) {

            }
        });

        btnK.setOnClickListener(new View.OnClickListener() { //// O
            @Override
            public void onClick(View viewK) {
                if(word.getText().toString().equals("__RR_")){
                    word.setText("__RRO");
                    aux = 10;
                }
                else if (word.getText().toString().equals("C_RR_")){
                    word.setText("C_RRO");
                    aux = 11;

                }
                else if (word.getText().toString().equals("_ARR_")){
                    word.setText("_ARRO");
                    aux = 13;


                }
                else if (word.getText().toString().equals("CARR_")){
                    word.setText("CARRO");
                    aux = 4;
                    btnNext.setVisibility(View.VISIBLE);
                    wordOrder = 1;
                    btnListen.setVisibility(View.VISIBLE);
                }
            }
        });

        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewP) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewNext) {
                if(wordOrder == 2){
                    word.setText("__RR_");
                    btnNext.setVisibility(View.INVISIBLE);
                    btnK.setText("O");
                    aux = 7;
                    btnListen.setVisibility(View.INVISIBLE);
                }
                else{
                    word.setText("_AS_");
                    btnNext.setVisibility(View.INVISIBLE);
                    btnListen.setVisibility(View.INVISIBLE);
                    btnK.setText("K");
                    aux = 1;
                }
            }
        });

        txtVoice = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS){
                    Locale loc = new Locale("pt_BR");
                    int result = txtVoice.setLanguage(loc);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "Language Not Supported");
                    } else{
                        btnListen.setEnabled(true);
                    }
                }else{
                    Log.e("TTS", "Initialization Failed");
                }
            }
        });

        btnListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewVoice) {
                speak();
            }
        });


    }

    private void speak(){
        String text = word.getText().toString();

        txtVoice.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if(txtVoice != null){
            txtVoice.stop();
            txtVoice.shutdown();
        }
        super.onDestroy();
    }
}