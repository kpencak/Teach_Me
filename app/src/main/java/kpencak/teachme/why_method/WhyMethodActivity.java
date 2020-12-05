package kpencak.teachme.why_method;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import kpencak.teachme.R;
import kpencak.teachme.dictionary.DictionaryItem;

public class WhyMethodActivity extends AppCompatActivity {
    private static final int SUMMARY_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_why_method);

        Button summary = findViewById(R.id.btn_summary);
        summary.setOnClickListener(view -> {
            Intent intent = new Intent(this, SummaryActivity.class);
            EditText step1 = findViewById(R.id.why_method_step_1_answer);
            EditText step2 = findViewById(R.id.why_method_step_2_answer);
            EditText step3 = findViewById(R.id.why_method_step_3_answer);
            EditText step4 = findViewById(R.id.why_method_step_4_answer);

            String answer1 = step1.getText().toString();
            String answer2 = step2.getText().toString();
            String answer3 = step3.getText().toString();
            String answer4 = step4.getText().toString();

            intent.putExtra("step1", answer1);
            intent.putExtra("step2", answer2);
            intent.putExtra("step3", answer3);
            intent.putExtra("step4", answer4);
            startActivityForResult(intent, SUMMARY_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        finish();
    }
}