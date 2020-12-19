package kpencak.teachme.repeating_method;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import kpencak.teachme.R;

public class SummaryTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_test);

        Bundle extras = getIntent().getExtras();

        ArrayList<Integer> userAnswers = extras.getIntegerArrayList("userAnswers");

        setScore(userAnswers);

        Button goBack = findViewById(R.id.btn_back);
        goBack.setOnClickListener(view -> {
            setResult(RESULT_OK);
            finish();
        });
    }

    private void setScore(ArrayList<Integer> userAnswers) {
        int score = 0;

        for (Integer i : userAnswers) {
            if (i == 1)
               score++;
        }

        TextView scoreText = findViewById(R.id.score);
        String scoreStr = score + "/10";
        scoreText.setText(scoreStr);

        TextView message = findViewById(R.id.messageSummary);
        String messageStr;
        if (score < 4)
            messageStr = "Musisz jeszcze trochę popracować";
        else if (score < 7)
            messageStr = "Jesteś na dobrej drodze!";
        else
            messageStr = "Świetna robota!";
        message.setText(messageStr);
    }
}