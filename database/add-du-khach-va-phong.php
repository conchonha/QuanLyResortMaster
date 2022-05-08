<?php
/*
Hướng dẫn dùng add-du-khach-va-phong.php
gửi một cái json lên add-du-khach-va-phong.php
json đó có dạng:
{
	"params":{
		"duKhach":[
			{"HOTENDK":"Nguyễn Văn A","SDT":"0939886859","NGAYSINH":"2015-03-29","CMND":"123456789"},
			{"HOTENDK":"Nguyễn Văn B","SDT":"0939785468","NGAYSINH":"2010-03-30","CMND":"986535856"},
			{"HOTENDK":"Nguyễn Văn C","SDT":"0946485316","NGAYSINH":"2010-03-22","CMND":"365985346"}
		],
		"phong":[
			{"MAP":"C202"},
			{"MAP":"A201"}
		],
		"thoiGian":{
			"NGAYDEN":"2021-12-29", 
			"NGAYDI":"2022-1-13"			
		}
		
	}
}

////////////////////////////////////////////////////////////
nhận về một json tùy trường hợp:
a/khi  chưa gửi lên cái json đó lên: -> thất bại 
	{
		“status”: false,
		“message”: “contents is empty”
	} 
b/thực hiện thêm nhóm du khách thành công, nó sẽ trả về số mã phòng tương ứng với số phòng đăng kí  gửi lên: -> thành công
	{
		“status”: true,
		“message”: “Thêm du khách và đăng kí phòng thành công”
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
e/ không dùng phuong thức post - thất bại
{
	status: false,
	message: "số CMND"+ $CMND +"đã có người đăng kí"
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
		
		
		$duKhach = $params["duKhach"];	
		$phong = $params["phong"];	
		$thoiGian = $params["thoiGian"];
		$NGAYDEN = $thoiGian["NGAYDEN"];
		$NGAYDI = $thoiGian["NGAYDI"];
		
		
		// add nhóm du khách	
		$sql = "SELECT COUNT(*) FROM DUKHACH";	
		$res = $dbCon->query($sql);
		$order_DUKHACH = $res->fetchColumn(); 
		
		$daiDien_MADK = "DK".$order_DUKHACH;
		if (is_array($duKhach) || is_object($duKhach) || is_array($phong) || is_object($phong)){
			//kiem tra CMND			
			foreach($duKhach as $key => $value){
				$CMND = $value['CMND'];
				$sql = 'SELECT COUNT(*) FROM DUKHACH WHERE CMND = "'.$CMND.'" ';	
				$res = $dbCon->query($sql);
				$duKhachFound = $res->fetchColumn();
				
				if($duKhachFound != 0){
					die(json_encode(array('status' => false, 'message' => "số CMND ".$CMND." đã có người đăng kí")));
				}
			}			
			
			//insert into dukhach
			$sql = 'INSERT INTO DUKHACH(MADK,MATK,MADKDAIDIEN,HOTENDK,SDT,NGAYSINH,CMND) VALUES(?,?,?,?,?,?,?)';
			$stmt = $dbCon->prepare($sql);
			
			$first = true;
			foreach($duKhach as $key => $value){
				$MADK = "DK".$order_DUKHACH;
				$MATK = null;
				if ($first == true){
					$MADKDAIDIEN = null;
					$first = false;
				}else{
					$MADKDAIDIEN = $daiDien_MADK;
				}
				$HOTENDK = $value['HOTENDK'];
				$SDT = $value['SDT'];
				$NGAYSINH = $value['NGAYSINH'];
				$CMND = $value['CMND'];
				
				$stmt->execute(array($MADK,$MATK,$MADKDAIDIEN,$HOTENDK,$SDT,$NGAYSINH,$CMND));
				
				$order_DUKHACH = $order_DUKHACH + 1;
			}
			//insert into datPhong
			$sql = 'INSERT INTO DATPHONG (MAP,MADK,NGAYDEN,NGAYDI) VALUES (?,?,?,?)';
			$stmt = $dbCon->prepare($sql);
			foreach($phong as $key => $value){
				$MAP = $value['MAP'];
				$stmt->execute(array($MAP,$daiDien_MADK,$NGAYDEN,$NGAYDI));
			}
			
			// hiển thị kết quả			
			echo json_encode(array('status' => true, 'message' => 'Thêm du khách và đăng kí phòng thành công'));
			
		}else{
			die(json_encode(array('status' => false, 'message' => 'thiếu du khách hoặc thiếu mã phòng')));
		}
    }
    catch(PDOException $ex){
        die(json_encode(array('status' => false, 'message' => $ex->getMessage())));
    }
?>