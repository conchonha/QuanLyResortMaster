package com.example.quan_ly_resort.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.model.TaiKhoan;
import com.google.zxing.WriterException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class ThanhToanActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 3;

    ImageView ivQRCode;

    Map<String,Integer> mapMatHangDummy;

    Bundle receivedBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        getMockData();
        Intent receivedIntent = getIntent();
        receivedBundle = receivedIntent.getExtras();

        String maTaiKhoan = receivedBundle.getString(TaiKhoan.MATK_COLUMN);

        ivQRCode = findViewById(R.id.ivQRCode);

        String inputValueDummy = maTaiKhoan;

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        QRGEncoder qrgEncoder = new QRGEncoder(inputValueDummy, null, QRGContents.Type.TEXT,smallerDimension);

        try {
            // Getting QR-Code as Bitmap
            Bitmap bitmap = qrgEncoder.encodeAsBitmap();
            // Setting Bitmap to ImageView
            ivQRCode.setImageBitmap(bitmap);

            transactionDummy();
        } catch (WriterException e) {
            Log.v("<< ThanhToanActivity >>", e.toString());
        }
    }

    private void transactionDummy() {
        Object[] arrKey = mapMatHangDummy.keySet().toArray();
        Object key = arrKey[new Random().nextInt(arrKey.length)];

        String matHangDummy = (String) key;
        int giaDummy = mapMatHangDummy.get(key);
        int soDuTrongVi = receivedBundle.getInt(TaiKhoan.SODUTRONGVI_COLUMN);
        if(soDuTrongVi < giaDummy){
            Intent resultIntent = new Intent(ThanhToanActivity.this, MainActivity.class);
            setResult(RESULT_CANCELED,resultIntent);
            showFailDialog("Không đủ tiền trong ví cho mặt hàng "+matHangDummy+" giá "+giaDummy+" đ");
        }else{
            int soDuMoi = soDuTrongVi - giaDummy;
            Bundle resultBundle = new Bundle();
            resultBundle.putInt(TaiKhoan.SODUTRONGVI_COLUMN,soDuMoi);

            Intent resultIntent = new Intent(ThanhToanActivity.this, MainActivity.class);
            resultIntent.putExtras(resultBundle);
            setResult(RESULT_OK,resultIntent);
            showSuccesDialog("Bạn đã thanh toán "+ giaDummy +" đ cho mặt hàng "+ matHangDummy);
        }
    }

    private void showFailDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ThanhToanActivity.this);
        builder.setTitle(R.string.thanh_toan_that_bai);

        builder.setMessage(msg);

        builder.setNeutralButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void showSuccesDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ThanhToanActivity.this);
        builder.setTitle(R.string.thanh_toan_thanh_cong);

        builder.setMessage(msg);

        builder.setNeutralButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void getMockData() {
        mapMatHangDummy = new HashMap<>();

        mapMatHangDummy.put("Nước suối",5000);
        mapMatHangDummy.put("Khăn ướt",5000);
        mapMatHangDummy.put("Nón",10000);
        mapMatHangDummy.put("Áo thun resort",20000);
        mapMatHangDummy.put("Sac dự phòng",100000);
        mapMatHangDummy.put("Kem chống nắng",15000);
        mapMatHangDummy.put("Kính râm",7000);
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent resultIntent = new Intent(ThanhToanActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED,resultIntent);
                finish();
                break;
        }
        return true;
    }
}
