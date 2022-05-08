<?php
/*
Hướng dẫn dùng get-user.php
gửi một cái json lên get-user.php
json đó có dạng:
{
	"params":{"EMAILTK":(email của user cần kiếm)}
}

////////////////////////////////////////////////////////////
nhận về một json tùy trường hợp:
a/khi chưa gửi lên cái json đó lên: -> thất bại 
	{
		“status”: false,
		“message”: “contents is empty”
	} 
b/lấy được thông tin user: -> thành công
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
		“message”: 'Load success'
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
		$url_components = parse_url($content);// "params2=heyyy&params1=suppp&"
		
		parse_str($url_components['path'],$url_params);		
		
		$params = json_decode($url_params['params'],true);		
		
		$EMAILTK = $params["EMAILTK"];	
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
		WHERE EMAILTK = "'.$EMAILTK.'"';
		
		$stmt = $dbCon->prepare($sql);
        $stmt->execute();
		
		$data = array();
		while ($row = $stmt->fetch(PDO::FETCH_ASSOC))
		{
			$data[] = $row;
		}
		echo json_encode(array('status' => true, 'data' => $data, 'message' => 'Load success'));
		
    }catch(PDOException $ex){
        die(json_encode(array('status' => false, 'message' => $ex->getMessage())));
    }   

?>
