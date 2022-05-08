<?php
/*
Hướng dẫn dùng set-user.php
gửi một cái json lên set-user.php
json đó có dạng:
{
	"params":{
		"MATK":(mã tài khoản), 
		"EMAILTK":(email tài khoản), 
		"MATKHAUTK":(mật khẩu tài khoản), 
		"MATHENGANHANG":(mã thẻ ngân hàng), 
		"SODUTRONGVI": (Số dư trong ví)
	}
}

////////////////////////////////////////////////////////////
nhận về một json tùy trường hợp:
a/khi chưa gửi lên cái json đó lên: -> thất bại 
	{
		“status”: false,
		“message”: “contents is empty”
	} 
b/sửa được: -> thành công
	{
		“status”: true,
		“data”: [
			{
				"MATK":(mã tài khoản), "EMAILTK":(email tài khoản), 
				"MATKHAUTK":(mật khẩu tài khoản), "MATHENGANHANG":(mã thẻ ngân hàng), 
				"MADK":(mã du khách), "MADKDAIDIEN" :(mã du khách đại diện), 
				"HOTENDK":(họ tên du khách), "SDT": (số diện thoại), 
				"NGAYSINH": (ngày sinh), "CMND": (chứng minh nhân dân),
				"SODUTRONGVI": (Số dư trong ví)
			}
		],
		“message”: 'sửa tài khoản thành công'
	}
c/lỗi khác: -> thất bại 
	{
		“status”: false,
		“message”: (tên lỗi, thường là tiếng anh)
	} 
d/ không dùng phuong thức post - thất bại
{
	status: false,
	message: "phải dùng phương thức post"
}
e/ email đã có người đăng kí -> thất bại 
{
	status: false,
	message: "email đã có người đăng kí"
}
*/
    require_once ('connection.php');
	
	if($_SERVER['REQUEST_METHOD'] != 'POST'){
		die(json_encode(array('status' => false, 'message' => "phải dùng phương thức post")));
	}
	$content = file_get_contents('php://input');
	if($content == false){
		die(json_encode(array('status' => false, 'message' => "contents is empty")));
	}

	try{
		
		$urlParams_str = urlencode($content);
		$url_components = parse_url($content);// "params2=heyyy&params1=suppp&"
		
		parse_str($url_components['path'],$url_params);		
		
		$params = json_decode($url_params['params'],true);		
		
		
		$MATK = $params["MATK"];
		$EMAILTK = $params["EMAILTK"];
		$MATKHAUTK = $params["MATKHAUTK"];
		$MATHENGANHANG = $params["MATHENGANHANG"];
		$SODUTRONGVI = $params["SODUTRONGVI"];
		
		// kiểm tra EMAILTK đã đăng kí tài khoản chưa
		$sql = 'SELECT COUNT(*) FROM TAIKHOAN WHERE EMAILTK = "'.$EMAILTK.'" AND MATK <> "'.$MATK.'" ';	
		$res = $dbCon->query($sql);
		$soDuKhachCoTimDuoc = $res->fetchColumn(); 
		if($soDuKhachCoTimDuoc == 1){
			die(json_encode(array('status' => false, 'message' => 'email đã có người đăng kí')));
		}
		// update user
		$sql = '
		UPDATE
			taikhoan AS tk
		SET
			tk.EMAILTK = ?,
			tk.MATKHAUTK = ?,
			tk.MATHENGANHANG = ?,
			tk.SODUTRONGVI = ?			
		WHERE MATK = "'.$MATK.'"';
		
		$stmt = $dbCon->prepare($sql);
		$stmt->execute(array($EMAILTK,$MATKHAUTK,$MATHENGANHANG,$SODUTRONGVI));
		
		// get new user
		$sql = '
		SELECT 
			tk.MATK AS MATK, 
			tk.EMAILTK AS EMAILTK, 
			tk.MATKHAUTK AS MATKHAUTK, 
			tk.MATHENGANHANG AS MATHENGANHANG, 
			tk.SODUTRONGVI AS SODUTRONGVI,
			dk.MADK AS MADK, 
			dk.MADKDAIDIEN AS MADKDAIDIEN, 
			dk.HOTENDK AS HOTENDK, 
			dk.SDT AS SDT, 
			dk.NGAYSINH AS NGAYSINH, 
			dk.CMND AS CMND,
			dkdv.MADV AS MADV
			
		FROM    
			taikhoan AS tk
		INNER JOIN dukhach AS dk ON dk.MATK = tk.MATK    
		LEFT JOIN dangkydichvu AS dkdv ON dkdv.MATK = tk.MATK  
		WHERE tk.MATK = "'.$MATK.'"';
		
		$stmt = $dbCon->prepare($sql);
        $stmt->execute();
		
		$data = array();
		while ($row = $stmt->fetch(PDO::FETCH_ASSOC))
		{
			$data[] = $row;
		}
		echo json_encode(array('status' => true, 'data' => $data, 'message' => 'sửa tài khoản thành công'));
		
    }catch(PDOException $ex){
        die(json_encode(array('status' => false, 'message' => $ex->getMessage())));
    }   

?>
