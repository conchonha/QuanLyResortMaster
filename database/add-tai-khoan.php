<?php
/*
Hướng dẫn dùng add-tai-khoan.php
gửi  một cái json lên add-tai-khoan.php
json đó có dạng:
{
	"params":{
		"EMAILTK":(email cần tạo), "MATKHAUTK":( mật khẩu tài khoản ), 
		"CMND": ( chứng minh nhân dân của du khách muốn tạo tài khoản), 
		"MATHENGANHANG": (mã thẻ ngân hàng của tài khoản)
	}
}

////////////////////////////////////////////////////////////
nhận về một json tùy trường hợp:
a/khi  chưa gửi lên cái json đó lên: -> thất bại 
	{
		“status”: false,
		“message”: “contents is empty”
	} 
b/thực hiện thêm nhóm tài khoản thành công: -> thành công
	{
		“status”: true,
		“message”: 'thêm tài khoản thành công'
	}
c/lỗi khác: -> thất bại 
	{
		“status”: false,
		“message”: (tên lỗi, thường là tiếng anh)
	} 
d/ không dùng phuong thức post -> thất bại 
{
	status: false,
	message: "phải dùng phương thức post"
}
e/ không kiếm thấy CMND (do nhập CMND sai hoặc không phải là du khách) -> thất bại 
{
	status: false,
	message: "nhập CMND sai hoặc bạn không phải là du khách"
}
f/ MADK đã đăng kí tài khoản -> thất bại 
{
	status: false,
	message: "du khách đã có tài khoản"
}
g/ email đã có người đăng kí -> thất bại 
{
	status: false,
	message: "email đã có người đăng kí"
}
h/ CMND đã có người đăng kí -> thất bại 
{
	status: false,
	message: "CMND đã có người đăng kí"
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
		$url_components = parse_url($content);
		
		parse_str($url_components['path'],$url_params);		
		
		$params = json_decode($url_params['params'],true);
		
		$EMAILTK = $params["EMAILTK"];
		$MATKHAUTK = $params["MATKHAUTK"];
		$CMND = $params["CMND"];
		$MATHENGANHANG = $params["MATHENGANHANG"];
		
		$sql = 'SELECT COUNT(*) FROM DUKHACH WHERE CMND = "'.$CMND.'" ';		
		$res = $dbCon->query($sql);
		$soDuKhachCoCMNDCanTim = $res->fetchColumn(); 
		
		if($soDuKhachCoCMNDCanTim == 1){			
			$sql = 'SELECT MADK FROM DUKHACH WHERE CMND = "'.$CMND.'" ';		
			$stmt = $dbCon->prepare($sql);
			$stmt->execute();
			$row = $stmt->fetch();			
			$MADK = $row['MADK'];
			// kiểm tra MADK đã đăng kí tài khoản chưa
			$sql = 'SELECT COUNT(*) FROM TAIKHOAN WHERE MADK = "'.$MADK.'" ';			
			$res = $dbCon->query($sql);
			$soDuKhachCoTimDuoc = $res->fetchColumn(); 
			if($soDuKhachCoTimDuoc == 1){
				die(json_encode(array('status' => false, 'message' => 'du khách đã có tài khoản')));
			}
			// kiểm tra EMAILTK đã đăng kí tài khoản chưa
			$sql = 'SELECT COUNT(*) FROM TAIKHOAN WHERE EMAILTK = "'.$EMAILTK.'" ';			
			$res = $dbCon->query($sql);
			$soDuKhachCoTimDuoc = $res->fetchColumn(); 
			if($soDuKhachCoTimDuoc == 1){
				die(json_encode(array('status' => false, 'message' => 'email đã có người đăng kí')));
			}
			
			// kiểm tra CMND đã đăng kí tài khoản chưa
			$sql = 'SELECT COUNT(*) FROM DUKHACH, TAIKHOAN WHERE DUKHACH.CMND = "'.$CMND.'" AND TAIKHOAN.EMAILTK = "'.$EMAILTK.'"';			
			$res = $dbCon->query($sql);
			$soDuKhachCoTimDuoc = $res->fetchColumn(); 
			if($soDuKhachCoTimDuoc == 1){
				die(json_encode(array('status' => false, 'message' => 'CMND đã có người đăng kí')));
			}
			
			// tạo MATK tiếp theo
			$sql = 'SELECT COUNT(*) FROM TAIKHOAN';		
			$res = $dbCon->query($sql);
			$order_TAIKHOAN = $res->fetchColumn(); 
			$MATK =  'TK'.$order_TAIKHOAN;			
			// insert new vào tài khoản
			$sql = 'INSERT INTO TAIKHOAN(MATK,MADK,EMAILTK,MATKHAUTK,MATHENGANHANG) VALUES (?,?,?,?,?)'	;			
			$stmt = $dbCon->prepare($sql);
			$stmt->execute(array($MATK,$MADK,$EMAILTK,$MATKHAUTK,$MATHENGANHANG));
			// update du khách 
			$sql = 'UPDATE DUKHACH SET MATK = ? WHERE MADK = ?';			
			$stmt = $dbCon->prepare($sql);
			$stmt->execute(array($MATK,$MADK));
			
			echo json_encode(array('status' => true, 'message' => 'Thêm tài khoản thành công'));			
		}else{
			die(json_encode(array('status' => false, 'message' => 'nhập CMND sai hoặc bạn không phải là du khách')));
		}	
		
    }catch(PDOException $ex){
        die(json_encode(array('status' => false, 'message' => $ex->getMessage())));
    }
	

?>