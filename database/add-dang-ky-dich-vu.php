<?php
/*
Hướng dẫn dùng add-dang-ky-dich-vu.php
gửi một cái json lên add-dang-ky-dich-vu.php
json đó có dạng:
{
	"params":{
		"MADV":(mã dịch vụ),
		"MATK":(mã tài khoản),
		"SODUTRONGVI":(số dư mới)
	}
}

////////////////////////////////////////////////////////////
nhận về một json tùy trường hợp:
a/khi  chưa gửi lên cái json đó lên: -> thất bại 
	{
		“status”: false,
		“message”: “contents is empty”
	} 
b/Đăng ký dịch vụ thành công: -> thành công
	{
		“status”: true,
		“message”: “Đăng ký dịch vụ thành công”
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
		
		$MADV = $params["MADV"];	
		$MATK = $params["MATK"];
		$SODUTRONGVI = $params["SODUTRONGVI"];
		
		// upate số du trong vi của tài khoản
		$sql = '
		UPDATE
			taikhoan
		SET
			SODUTRONGVI = ?			
		WHERE MATK = "'.$MATK.'"';
		
		$stmt = $dbCon->prepare($sql);
		$stmt->execute(array($SODUTRONGVI));
		
		// insert new vào đăng kí dịch vụ
		$sql = 'INSERT INTO DANGKYDICHVU(MATK,MADV) VALUES (?,?)'	;			
		$stmt = $dbCon->prepare($sql);
		$stmt->execute(array($MATK,$MADV));
		echo json_encode(array('status' => true, 'message' => 'Đăng ký dịch vụ thành công'));
		
    }
    catch(PDOException $ex){
        die(json_encode(array('status' => false, 'message' => $ex->getMessage())));
    }
?>