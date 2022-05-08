package com.example.quan_ly_resort.networkCallback;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.quan_ly_resort.MainActivity;
import com.example.quan_ly_resort.interfaces.VolleyCallback;
import com.example.quan_ly_resort.model.KhungGio;
import com.example.quan_ly_resort.model.Phong;
import com.example.quan_ly_resort.network.ServerReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DatPhongChecker implements VolleyCallback {
    public static final int CALLBACK_CODE = 1;
    private String url_string = MainActivity.WEBSERVER_IP +"/get-tinh-trang-dat-phong.php";
    private Map<String,ArrayList<Phong>> mapPhongTheoTENLP;

    private Date ngayDen_params;
    private Date ngayDi_params;
    private Map<String,Integer> mapThongTinPhong;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private VolleyCallback callbackResult;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public DatPhongChecker(Context context, VolleyCallback callbackResult, String ngayDen_params, String ngayDi_params, ArrayList<String> listLPCanDat) {
        try {
            this.mapPhongTheoTENLP = new HashMap<>();
            this.callbackResult = callbackResult;
            this.ngayDen_params = simpleDateFormat.parse(ngayDen_params);
            this.ngayDi_params = simpleDateFormat.parse(ngayDi_params);
            this.mapThongTinPhong = new HashMap<>();
            for (String loaiPhongCanDat : listLPCanDat){
                int soLuong = mapThongTinPhong.getOrDefault(loaiPhongCanDat,0);
                soLuong = soLuong + 1;
                mapThongTinPhong.put(loaiPhongCanDat,soLuong);
            }

            new ServerReceiver(url_string,context,this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<String> getPhongTrong(String tenLoaiPhong, int soPhongCanDat) throws ParseException {// đầu vào phải kho trùng ngày của nhau
        /*
        trả về arraylist chứa mã phòng trống
        ngày đến của khách này không được trùng với ngày đi của khách nọ và ngược lại
         */
        ArrayList<String> listPhongTrong = new ArrayList<>();
        ArrayList<Phong> listPhong = mapPhongTheoTENLP.get(tenLoaiPhong);
        for(Phong phong: listPhong){
            boolean hopLe = true;
            for(KhungGio kg: phong.getListKhungGio()){
                if(kg.getNgayDen() == null && kg.getNgayDi() == null)
                    break;
                Date ngayDen_daDat = simpleDateFormat.parse(kg.getNgayDen());
                Date ngayDi_daDat = simpleDateFormat.parse(kg.getNgayDi());
                if((ngayDen_params.compareTo(ngayDi_daDat)<=0 && ngayDi_params.compareTo(ngayDen_daDat)>=0)){
                    hopLe = false;
                    break;
                }
            }
            if(hopLe)
                listPhongTrong.add(phong.getMaPhong());

            if(listPhongTrong.size() == soPhongCanDat )
                return listPhongTrong;
        }
        return listPhongTrong;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private JSONObject successHandling() throws ParseException, JSONException {
        JSONObject jsonObj = new JSONObject();
        boolean status = true;
        JSONArray data = new JSONArray();
        String message = "";
        for( Map.Entry<String, Integer> entry : mapThongTinPhong.entrySet()){
            String loaiPhong = entry.getKey();
            int soPhong = entry.getValue();

            ArrayList<String> listPhongTrong = getPhongTrong(loaiPhong,soPhong);
            if (listPhongTrong.size() == soPhong){
                message = message.concat("\nĐủ phòng "+ loaiPhong);
            }else {
                status = false;
                message = message.concat("\nKhông đủ phòng "+ loaiPhong);
            }

            for(String maPhongTrong : listPhongTrong){
                JSONObject jso = new JSONObject();
                jso.put(Phong.TENLP_COLUMN,loaiPhong);
                jso.put(Phong.MAP_COLUMN,maPhongTrong);
                data.put(jso);
            }
        }

        jsonObj.put("callbackCode",CALLBACK_CODE);
        jsonObj.put("status",status);
        jsonObj.put("data",data);
        jsonObj.put("message",message);

        return jsonObj;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSuccess(JSONObject jsonObj) {
        try {
            JSONObject root = jsonObj;
            boolean status = root.getBoolean("status");
            String message = root.getString("message");
            if(status){
                JSONArray data = root.getJSONArray("data");
                Map<String,Phong> mapPhongTheoMAP = new HashMap<>();
                for(int i = 0;i<data.length();i++){
                    JSONObject dataItem = data.getJSONObject(i);
                    String MALP = dataItem.getString("MALP");
                    String TENLP = dataItem.getString("TENLP");
                    String MAP = dataItem.getString("MAP");
                    String MADK = dataItem.getString("MADK");
                    String NGAYDEN = dataItem.getString("NGAYDEN");
                    String NGAYDI = dataItem.getString("NGAYDI");

                    Phong phong;
                    if(! mapPhongTheoMAP.containsKey(MAP)){
                        phong = new Phong(MALP,TENLP,MAP,MADK,NGAYDEN,NGAYDI);
                    }else{
                        phong = mapPhongTheoMAP.get(MAP);
                        phong.addKhungGio(NGAYDEN,NGAYDI);
                    }
                    mapPhongTheoMAP.put(MAP,phong);
                }
                for(Phong phong: mapPhongTheoMAP.values()){
                    String TENLP = phong.getTenLoaiPhong();
                    ArrayList<Phong> listPhong = mapPhongTheoTENLP.getOrDefault(TENLP,new ArrayList<Phong>());
                    listPhong.add(phong);
                    mapPhongTheoTENLP.put(TENLP,listPhong);
                }

                callbackResult.onSuccess(successHandling());
            }else {
                Log.e("TinhTrangDatPhongGetter","onSuccess: kết nối được với server. nhưng "+ message);
            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onFail(String error) {//hàm sẽ được gọi khi SendJSONObject thất bại
        callbackResult.onFail(error);
    }
}
