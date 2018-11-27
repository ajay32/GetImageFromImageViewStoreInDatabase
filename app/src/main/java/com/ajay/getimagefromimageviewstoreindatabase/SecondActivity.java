package com.ajay.getimagefromimageviewstoreindatabase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView1, imageView2;
    String str;
    byte[] byteArray;

    // getting Image from Imageview -> Drawable File ->Byte Array -> Bitmap


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageView1 = (ImageView) findViewById(R.id.imageview1);
        imageView2 = (ImageView) findViewById(R.id.imageview2);

        imageView1.setImageResource(R.drawable.facee);

        // getting drawable from imageview
        Drawable drawable = imageView1.getDrawable();

        BitmapDrawable bitmapDrawable = ((BitmapDrawable) drawable);
        Bitmap bitmap = bitmapDrawable .getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();  //converting into byte array


        //make sure u are using Base64 encoding to Store IMAGE BYTE ARRAY
        // UTF-8 or any other encoding will not work with Image byte array nor simple putting in string.
        str = Base64.encodeToString(byteArray,
                Base64.NO_WRAP);
    }

    public void buttonclick(View view) {

        // decoding image byte array
        byte[] array = Base64.decode(str, Base64.DEFAULT);

        Bitmap bitmap = Bytes2Bimap(array);

        imageView2.setImageBitmap(bitmap);

    }

    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
}
