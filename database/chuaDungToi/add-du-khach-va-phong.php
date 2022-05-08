<?php
/*
Hướng dẫn dùng add-du-khach.php
gửi thông tin HOTENDK, SDT, NGAYSINH, CMND lên add-du-khach-va-phong.php
////////////////////////////////////////////////////////////
nhận về một json tùy trường hợp:
a/khi bà chưa gửi lên cái json đó lên: -> thất bại 
	{
		“status”: false,
		“message”: “contents is empty”
	} 
b/thực hiện thêm nhóm du khách thành công: -> thành công
	{
		“status”: true,
		“data”: (id insert cuối cùng),
		“message”: 'Thêm du khách thành công'
	}
c/lỗi khác: -> thất bại 
	{
		“status”: false,
		“message”: (tên lỗi, thường là tiếng anh)
	} 
*/
    require_once ('connection.php');

    if (!isset($_POST['HOTENDK']) || !isset($_POST['SDT']) || isset($_POST['NGAYSINH']) || isset($_POST['CMND']) ) {
        die(json_encode(array('status' => false, 'message' => 'Parameters not valid')));
    }
	
	$sql = "SELECT COUNT(*) FROM DUKHACH";	
	$res = $dbCon->query($sql);
	$order_DUKHACH = $res->fetchColumn();

    $MADK = "DK".$order_DUKHACH;
    $MATK = null;
    $MADKDAIDIEN = null;
    $HOTENDK = $_POST['HOTENDK'];
    $SDT = $_POST['SDT'];
    $NGAYSINH = $_POST['NGAYSINH'];
    $CMND = $_POST['CMND'];
	

    $sql = 'INSERT INTO DUKHACH(MADK,MATK,MADKDAIDIEN,HOTENDK,SDT,NGAYSINH,CMND) VALUES(?,?,?,?,?,?,?)';

    try{
        $stmt = $dbCon->prepare($sql);
        $stmt->execute(array($MADK,$MATK,$MADKDAIDIEN,$HOTENDK,$SDT,$NGAYSINH,$CMND));
        
        echo json_encode(array('status' => true, 'data' => $dbCon->lastInsertId(), 'message' => 'Thêm du khách thành công'));
    }
    catch(PDOException $ex){
        die(json_encode(array('status' => false, 'message' => $ex->getMessage())));
    }

?>