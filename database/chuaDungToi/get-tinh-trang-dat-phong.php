<?php
/*
Hướng dẫn dùng get-tinh-trang-dat-phong.php
không cần gửi gì lên get-tinh-trang-dat-phong.php
////////////////////////////////////////////////////////////
nhận về một json tùy trường hợp:
a/thực hiện lấy hết đặt phòng thành công: -> thành công
	{
		“status”: true,
		“data”: [
			{"MALP":(mã loại phòng), "TENLP":(tên loại phong), "MAP":(mã phòng), "MADK":(ma du khách), "MADK":(ngày đến), "NGAYDI":(ngày đi)},
			{"MALP":(mã loại phòng), "TENLP":(tên loại phong), "MAP":(mã phòng), "MADK":(ma du khách), "MADK":(ngày đến), "NGAYDI":(ngày đi)},
			{"MALP":(mã loại phòng), "TENLP":(tên loại phong), "MAP":(mã phòng), "MADK":(ma du khách), "MADK":(ngày đến), "NGAYDI":(ngày đi)}
		],
		“message”: 'Load success'
	}
datphong có
(1)MALP tăng dần
(2)NGAYDEN tăng dần
có thể null : MADK, NGAYDEN, NGAYDI

b/lỗi khác: -> thất bại 
	{
		“status”: false,
		“message”: (tên lỗi, thường là tiếng anh)
	} 

bà coi cách xử lý json nhận về tương tự lab8 á
*/
    require_once ('connection.php');
	$sql ='
	SELECT
		loaiphong.MALP,
		loaiphong.TENLP,
		phong.MAP,
		datphong.MADK,
		datphong.NGAYDEN,
		datphong.NGAYDI
	FROM
		loaiphong
	LEFT JOIN phong ON phong.MALP = loaiphong.MALP
	LEFT JOIN datphong ON datphong.MAP = phong.MAP
	ORDER BY MALP ASC, NGAYDEN ASC 
	';

    try{
        $stmt = $dbCon->prepare($sql);
        $stmt->execute();
    }
    catch(PDOException $ex){
        die(json_encode(array('status' => false, 'message' => $ex->getMessage())));
    }

    $data = array();
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC))

    {
        $data[] = $row;
    }
    echo json_encode(array('status' => true, 'data' => $data, 'message' => 'Load success'));
?>