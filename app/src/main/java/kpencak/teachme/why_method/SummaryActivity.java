package kpencak.teachme.why_method;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;

import kpencak.teachme.R;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        String summary = extras.getString("step1") + "\n" + extras.getString("step2") +
                "\n" + extras.getString("step3") + "\n" + extras.getString("step4");

        TextView textView = findViewById(R.id.summary_text);
        textView.setText(summary);

        Button goBack = findViewById(R.id.btn_goBack);
        goBack.setOnClickListener(view -> {
            setResult(RESULT_OK);
            finish();
        });
    }
}