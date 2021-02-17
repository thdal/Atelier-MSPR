package fr.epsi.ateliermspr;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MsprActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Activity", "#####:"+getClass().getSimpleName());
    }

    @Override
    public void finish() {
        super.finish();
        Log.d("Activity", "#####:"+getClass().getSimpleName());
    }
}
