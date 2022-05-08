package com.example.quan_ly_resort.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.R;

public class DeluxeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deluxe);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent resultIntent = new Intent(DeluxeActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED,resultIntent);
                finish();
                break;
        }
        return true;
    }
}