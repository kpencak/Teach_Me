package kpencak.teachme.mnemotechnics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kpencak.teachme.R;

public class MnemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnemo);
    }

    public void goToPalace(View view) {
        Intent intent = new Intent(this, PalaceActivity.class);
        startActivity(intent);
    }

    public void goToRhymes(View view) {
        Intent intent = new Intent(this, RhymesActivity.class);
        startActivity(intent);
    }

    public void goToAcronyms(View view) {
        Intent intent = new Intent(this, AcronymsActivity.class);
        startActivity(intent);
    }

    public void goToChains(View view) {
        Intent intent = new Intent(this, ChainsActivity.class);
        startActivity(intent);
    }
}