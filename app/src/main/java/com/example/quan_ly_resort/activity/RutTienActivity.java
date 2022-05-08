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

public class RutTienActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 1;

    TextView tvSoDu;
    TextView tvMaTheNganHang;
    EditText etSoTien;
    Button btRutTien;
    TextView tvError;

    Bundle receivedBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rut_tien);

        Intent receivedIntent = getIntent();
        receivedBundle = receivedIntent.getExtras();

        tvSoDu = findViewById(R.id.tvSoDu_actRutTien);
        tvMaTheNganHang = findViewById(R.id.tvMaTheNganHang_actRutTien);
        etSoTien = findViewById(R.id.etSoTien_actRutTien);
        btRutTien = findViewById(R.id.btRutTien_actRutTien);
        tvError = findViewById(R.id.tvError_actRutTien);

        tvSoDu.setText(receivedBundle.getInt(TaiKhoan.SODUTRONGVI_COLUMN) + " đ");
        tvMaTheNganHang.setText(receivedBundle.getString(TaiKhoan.MATHENGANHANG_COLUMN));

        btRutTien.setOnClickListener(this);
    }

    private boolean checkNapTien(Bundle receivedBundle) {
        String tienNhap = etSoTien.getText().toString();
        int soDu = receivedBundle.getInt(TaiKhoan.SODUTRONGVI_COLUMN);
        if(tienNhap.isEmpty()){
            tvError.setText("chưa nhập số tiền");
            return false;
        }
        if(Integer.parseInt(tienNhap) > soDu){
            tvError.setText("số tiền phải nhỏ hơn hoặc bằng số dư trong ví");
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent resultIntent = new Intent(RutTienActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED,resultIntent);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if(checkNapTien(receivedBundle)) {
            int soDuMoi = receivedBundle.getInt(TaiKhoan.SODUTRONGVI_COLUMN) - Integer.parseInt(etSoTien.getText().toString());
            Bundle resultBundle = new Bundle();
            resultBundle.putInt(TaiKhoan.SODUTRONGVI_COLUMN,soDuMoi);

            Intent resultIntent = new Intent(RutTienActivity.this, MainActivity.class);
            resultIntent.putExtras(resultBundle);
            setResult(RESULT_OK,resultIntent);
            finish();
        }
    }
}
