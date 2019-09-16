package com.example.latihanlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class LibraryZxing extends AppCompatActivity {

    ImageView iv;
    Button btn_gnrt;
    EditText et_generate;
    String et_value;
    Thread thread;
    Bitmap bitmap;
    public final static int QEcodeWidth = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_library_zxing);

        iv = findViewById (R.id.iv_generate);
        et_generate = findViewById (R.id.editText);
        btn_gnrt = findViewById (R.id.btn_generate);

        btn_gnrt.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                et_value = et_generate.getText ().toString ();

                try {
                    bitmap = TextToImageEncode (et_value);

                    iv.setImageBitmap (bitmap);

                } catch (WriterException we){
                    we.printStackTrace ();
                }
            }
        });

    }

    Bitmap TextToImageEncode(String value) throws WriterException{
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter ().encode (
                    value, BarcodeFormat.DATA_MATRIX.QR_CODE,QEcodeWidth,QEcodeWidth,null
            );
        } catch (IllegalArgumentException IAE){
            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth ();
        int bitMatrixHeight = bitMatrix.getHeight ();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.CodeBlackColor):getResources().getColor(R.color.CodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight,
                Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;

    }
}
