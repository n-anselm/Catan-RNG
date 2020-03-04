package com.anselmdevelopment.catanrng;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    String[] mEmailAddress = {"na.appdevelopment@gmail.com"};
    String mSubject = "Catan Spinner Feedback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        FrameLayout backArrow = findViewById(R.id.about_back_arrow);
        // Sets onClickListener on the back arrow
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView emailAddress = findViewById(R.id.textview_feedback);
        emailAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail(mEmailAddress, mSubject);
            }
        });
    }

    // Create a new method to open an email application when the email address is clicked.
    public void composeEmail(String[] mEmailAddress, String subject) {
        Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
        sendEmail.setData(Uri.parse("mailto:")); // Only email apps should handle this
        sendEmail.putExtra(Intent.EXTRA_EMAIL, mEmailAddress);
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (sendEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendEmail);
        }
    }
}
