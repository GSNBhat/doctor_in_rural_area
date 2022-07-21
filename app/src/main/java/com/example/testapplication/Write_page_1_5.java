package com.example.testapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Write_page_1_5 extends AppCompatActivity {

    TextView id, name, surname, marks, tagid;
    ImageView imageset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_page_1_5);


        tagid = findViewById(R.id.tagidvalue);
        Intent i0 = getIntent();
        String str0 = i0.getStringExtra("message0");
        tagid.setText(str0);

        id = findViewById(R.id.useridvalue);
        Intent i1 = getIntent();
        String str1 = i1.getStringExtra("message1");
        id.setText(str1);

        name = findViewById(R.id.usernamevalue);
        Intent i2 = getIntent();
        String str2 = i2.getStringExtra("message2");
        name.setText(str2);

        surname = findViewById(R.id.usersurnamevalue);
        Intent i3 = getIntent();
        String str3 = i3.getStringExtra("message3");
        surname.setText(str3);

        marks = findViewById(R.id.usermarksvalue);
        Intent i4 = getIntent();
        String str4 = i4.getStringExtra("message4");
        marks.setText(str4);

        imageset = findViewById(R.id.imageset);
        Bundle extras = getIntent().getExtras();
        final byte[] b = extras.getByteArray("picture");
        try {
            Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
            imageset.setImageBitmap(bmp);
        } catch (Exception e) {
            Toast.makeText(this, "Error please try again  " + e, Toast.LENGTH_LONG).show();
        }

        imageset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Write_page_1_5.this, Show_image.class);
                i1.putExtra("picture", b);
                startActivity(i1);
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(Write_page_1_5.this, Main2Activity.class);
        startActivity(intent);
    }

}
