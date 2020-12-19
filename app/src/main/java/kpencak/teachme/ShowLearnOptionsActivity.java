package kpencak.teachme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kpencak.teachme.diverse_practice_method.DiversePracticeActivity;
import kpencak.teachme.flashcards_method.FlashcardsActivity;
import kpencak.teachme.repeating_method.RepeatingActivity;
import kpencak.teachme.why_method.WhyMethodActivity;

public class ShowLearnOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_learn_options);
    }

    public void goToWhyMethod(View view) {
        Intent intent = new Intent(this, WhyMethodActivity.class);
        startActivity(intent);
    }

    public void goToFlashcardsMethod(View view) {
        Intent intent = new Intent(this, FlashcardsActivity.class);
        startActivity(intent);
    }

    public void goToDiversePracticeMethod(View view) {
        Intent intent = new Intent(this, DiversePracticeActivity.class);
        startActivity(intent);
    }

    public void goToRepeatingMethod(View view) {
        Intent intent = new Intent(this, RepeatingActivity.class);
        startActivity(intent);
    }
//
//    public void goToMnemotechnicMethod(View view) {
//        Intent intent = new Intent(this, MnemotechnicActivity.class);
//        startActivity(intent);
//    }
//
//    public void goToTeacherMethod(View view) {
//        Intent intent = new Intent(this, TeacherActivity.class);
//        startActivity(intent);
//    }

}