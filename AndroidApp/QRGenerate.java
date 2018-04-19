package com.um56.jefhunt.professorapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRGenerate extends AppCompatActivity {

    Bitmap bitmap;
    String EditTextValue;
    EditText editText;
    ImageView imageView;
    public final static int QRcodeWidth = 500 ;
    String data;
    int randNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerate);
        editText=findViewById(R.id.edit);
        imageView = (ImageView)findViewById(R.id.imageView);
        Intent getIntent=getIntent();
        Bundle b=getIntent.getExtras();
        if(b!=null)
        {
            String j =(String) b.get("Roll");
            data=j;
            //data=j.split("@")[1];
            int i=0;

        }
        randNum= (int) (Math.random()*100 + 1);

       // editText.setText(data);
        try {
            bitmap = TextToImageEncode(data+"@"+randNum);
            imageView.setImageBitmap(bitmap);
            ForAuth forAuth=new ForAuth("50@"+data+"@"+randNum);
            forAuth.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Bitmap TextToImageEncode(String Value)  {
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
