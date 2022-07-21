package com.example.testapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Write_page_2 extends AppCompatActivity {

    TextView id, name, surname, marks, tagid;
    ImageView imageset;
    Button yes, no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_page_2);
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);


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


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Write_page_2.this, "Data Added successfully", Toast.LENGTH_SHORT).show();
                String str1 = id.getText().toString();
                String str0 = tagid.getText().toString();
                Intent i1 = new Intent(Write_page_2.this, Write_page_1_5.class);
                i1.putExtra("message1", str1);
                i1.putExtra("message0", str0);


                String str2 = name.getText().toString();
                i1.putExtra("message2", str2);

                String str3 = surname.getText().toString();
                i1.putExtra("message3", str3);


                String str4 = marks.getText().toString();
                i1.putExtra("message4", str4);


                i1.putExtra("picture", b);
                startActivity(i1);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Write_page_2.this, Write_page.class);
                startActivity(i1);

            }
        });

        imageset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Write_page_2.this, Show_image.class);
                i1.putExtra("picture", b);
                startActivity(i1);
            }
        });


    }

    public void onBackPressed() {
        Intent intent = new Intent(Write_page_2.this, Main2Activity.class);
        startActivity(intent);
    }
}
