package com.ajay.getimagefromimageviewstoreindatabase;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;


public class MainActivity extends AppCompatActivity {

    ImageView imageView1, imageView2;
    String str;
    byte[] byteArray;

    // putting Drawable folder image to ->Bitmap -> byte array ->Bitmap

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = (ImageView) findViewById(R.id.imageview1);
        imageView2 = (ImageView) findViewById(R.id.imageview2);

        imageView1.setImageResource(R.drawable.facee);

        Resources res = getResources();  // getting file from drawable folder
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.facee);
        byteArray = Bitmap2Bytes(bmp);


                // str = new String(byteArray);  //This code will not work for image byte array need Base64 encoding.

        // use Base64 encoding for byte array encode or decode.
         str = Base64.encodeToString(byteArray,
                Base64.NO_WRAP);


    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos); // we are able to convert in both format PNG and JPEG
        return baos.toByteArray();
    }


    public void buttonclick(View view) {

     //   byte[] byteArr = str.getBytes();  // will not work

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
