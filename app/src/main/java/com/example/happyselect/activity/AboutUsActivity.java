package com.example.happyselect.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.happyselect.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_about_us)
    Toolbar toolbarAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarAboutUs);

    }
}
