<?php
/*
Hướng dẫn dùng get-all-dich-vu.php
không cần gửi gì lên get-all-dich-vu.php
////////////////////////////////////////////////////////////
nhận về một json tùy trường hợp:
a/thực hiện lấy hết dịch vụ thành công: -> thành công
	{
		“status”: true,
		“data”: [
			{
				"MADV":"DV0", "TENDV": (tên của dịch vụ), "MOTA": (mô tả của dịch vụ),
				"GIA": (giá của dịch vụ), "TENHINH":(), "KEYWORD":(key word của dịch vụ),
				PHAIDANGKI: (có phải đăng kí ?)
			},
			{
				"MADV":"DV1", "TENDV": (tên của dịch vụ), "MOTA": (mô tả của dịch vụ),
				"GIA": (giá của dịch vụ), "TENHINH":(), "KEYWORD":(key word của dịch vụ),
				PHAIDANGKI: (có phải đăng kí ?)
			},
			{
				"MADV":"DV2", "TENDV": (tên của dịch vụ), "MOTA": (mô tả của dịch vụ),
				"GIA": (giá của dịch vụ), "TENHINH":(), "KEYWORD":(key word của dịch vụ),
				PHAIDANGKI: (có phải đăng kí ?)
			}
		],
		“message”: 'Load success'
	}
b/lỗi khác: -> thất bại 
	{
		“status”: false,
		“message”: (tên lỗi, thường là tiếng anh)
	} 
*/
	require_once ('connection.php');

    $sql = '
	SELECT
		dichvu.MADV,
		dichvu.TENDV,
		dichvu.MOTA,
		dichvu.GIA,
        dichvu.TENHINH,
		dichvu_co_keyword.KEYWORD,
		dichvu.PHAIDANGKI
	FROM
		dichvu
	INNER JOIN dichvu_co_keyword ON dichvu_co_keyword.MADV = dichvu.MADV
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
