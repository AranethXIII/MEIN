package com.example.myapplication.View;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.Scenario;

import com.example.myapplication.R;

import java.util.Locale;


public class Game extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton aw1;
    RadioButton aw2;
    RadioButton aw3;
    RadioButton aw4;
    TextView tex;
    Button button;
    Scenario scenario;
    private boolean done;
    TextToSpeech mTTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        radioGroup = findViewById(R.id.radioGrp);
        aw1 = findViewById(R.id.aw);
        aw2 = findViewById(R.id.aw2);
        aw3 = findViewById(R.id.aw3);
        aw4 = findViewById(R.id.aw4);
        tex = findViewById(R.id.textView2);
        button = findViewById(R.id.next);
        done = false;

        //TODO use Model.Scenario class for backend interaction

        //=======example
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.GERMAN);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        Log.e("TTS", "Initialization failed");
                    }
                }
            }
        });

        scenario = Scenario.randomScenario();
//        mTTS.speak(scenario.getcurrentDescription(),TextToSpeech.QUEUE_ADD,null);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (done) {
                    scenario = Scenario.randomScenario();
                    System.out.println("---------new Scenario------------");
                    fillField();
                    done = false;
                }
                if (radioGroup.getCheckedRadioButtonId() != -1) {
                    int ans = -1;
                    if (aw1.isChecked()) ans = 0;
                    if (aw2.isChecked()) ans = 1;
                    if (aw3.isChecked()) ans = 2;
                    if (aw4.isChecked()) ans = 3;
                    scenario.selectAction(ans);

                    if (scenario.getAnsweroptions() == null) {
                        tex.setText(scenario.getcurrentState());
                        aw1.setVisibility(View.INVISIBLE);
                        aw2.setVisibility(View.INVISIBLE);
                        aw3.setVisibility(View.INVISIBLE);
                        aw4.setVisibility(View.INVISIBLE);
                        done = true;
                        mTTS.speak(scenario.getcurrentState(),TextToSpeech.QUEUE_FLUSH,null);
                    } else {
                        fillField();
                    }
                }
            }
        });
        mTTS.speak("Schinken",TextToSpeech.QUEUE_ADD,null);



        fillField();
    }
        @Override
        protected void onDestroy() {
        super.onDestroy();
            if (mTTS != null) {
                mTTS.stop();
                mTTS.shutdown();
            }
        }


    void fillField() {
        tex.setText(scenario.getVcase().getDescription());

        mTTS.speak(scenario.getVcase().getDescription(),TextToSpeech.QUEUE_FLUSH,null);
        mTTS.speak(scenario.getAnswerOptionDescriptions().get(0),TextToSpeech.QUEUE_ADD,null);
        mTTS.speak(scenario.getAnswerOptionDescriptions().get(1),TextToSpeech.QUEUE_ADD,null);
        mTTS.speak(scenario.getAnswerOptionDescriptions().get(2),TextToSpeech.QUEUE_ADD,null);
        mTTS.speak(scenario.getAnswerOptionDescriptions().get(3),TextToSpeech.QUEUE_ADD,null);

        aw1.setText(scenario.getAnswerOptionDescriptions().get(0));
        aw2.setText(scenario.getAnswerOptionDescriptions().get(1));
        aw3.setText(scenario.getAnswerOptionDescriptions().get(2));
        aw4.setText(scenario.getAnswerOptionDescriptions().get(3));



        aw1.setVisibility(View.VISIBLE);
        aw2.setVisibility(View.VISIBLE);
        aw3.setVisibility(View.VISIBLE);
        aw4.setVisibility(View.VISIBLE);

        aw1.setChecked(false);
        aw2.setChecked(false);
        aw3.setChecked(false);
        aw4.setChecked(false);
    }

    private void speak(String s) {
        String text = s;
        float pitch=0.1f;
        float speed=0.1f;
        mTTS.setPitch(0.1f);
        mTTS.setSpeechRate(0.1f);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
