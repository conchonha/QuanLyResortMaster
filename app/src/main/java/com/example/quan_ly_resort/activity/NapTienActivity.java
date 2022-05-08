package com.example.quan_ly_resort.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.R;
import com.example.quan_ly_resort.model.TaiKhoan;
import com.example.quan_ly_resort.model.User;

public class NapTienActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 0;

    TextView tvMaTheNganHang;
    EditText etSoTien;
    Button btNapTien;
    TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_tien);

        Intent receivedIntent = getIntent();
        final Bundle receivedBundle = receivedIntent.getExtras();

        tvMaTheNganHang = findViewById(R.id.tvMaTheNganHang_actNapTien);
        etSoTien = findViewById(R.id.etSoTien_actNapTien);
        btNapTien = findViewById(R.id.btNapTien_actNapTien);
        tvError = findViewById(R.id.tvError);

        tvMaTheNganHang.setText(receivedBundle.getString(TaiKhoan.MATHENGANHANG_COLUMN));

        btNapTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkNapTien(receivedBundle)) {
                    int soDuMoi = receivedBundle.getInt(TaiKhoan.SODUTRONGVI_COLUMN) + Integer.parseInt(etSoTien.getText().toString());
                    Bundle resultBundle = new Bundle();
                    resultBundle.putInt(TaiKhoan.SODUTRONGVI_COLUMN,soDuMoi);

                    Intent resultIntent = new Intent(NapTienActivity.this, MainActivity.class);
                    resultIntent.putExtras(resultBundle);
                    setResult(RESULT_OK,resultIntent);
                    finish();
                }
            }
        });
    }

    private boolean checkNapTien(Bundle receivedBundle) {// *** giả sử Thẻ ngân hàng luôn có đủ tiền để nạp ***
        String tienNhap = etSoTien.getText().toString();
        if(tienNhap.isEmpty()){
            tvError.setText("chưa nhập số tiền");
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent resultIntent = new Intent(NapTienActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED,resultIntent);
                finish();
                break;
        }
        return true;
    }
}
