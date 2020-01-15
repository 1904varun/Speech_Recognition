package me.varun1904.speechrecognition;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_SPEECH = 1000 ;
    TextView outputArea;
    ImageButton inputButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputArea = (TextView) findViewById(R.id.output);
        inputButton = (ImageButton) findViewById(R.id.input);

        inputButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                speak();
            }
        });


    }

    private void speak(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello! Please speak something");
        try{
            startActivityForResult(intent, CODE_SPEECH);
        }
        catch (Exception e){
            Toast.makeText(this,""+e.getMessage(), Toast.LENGTH_SHORT ).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CODE_SPEECH:{
                if(resultCode==RESULT_OK && null!=data){
                    ArrayList<String>outputString = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    outputArea.setText(outputString.get(0));
                }
                break;
            }
        }
    }
}
