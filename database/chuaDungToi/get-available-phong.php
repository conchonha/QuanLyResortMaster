<?php
    require_once ('connection.php');	
	
	if($json = file_get_contents('php://input',true) == false){
		die(json_encode(array('status' => false, 'message' => "contents is empty")));
	}
		
	$obj = json_decode($json,true);
	
	$nhomDuKhach = $obj["nhomDuKhach"];	

    try{
		$sql = 'INSERT INTO DUKHACH(MADK,MATK,MADKDAIDIEN,HOTENDK,SDT,NGAYSINH,CMND) VALUES(?,?,?,?,?,?,?)';
		$stmt = $dbCon->prepare($sql);	    
		
		$sql = "SELECT COUNT(*) FROM DUKHACH";	
		$res = $dbCon->query($sql);
		$order_DUKHACH = $res->fetchColumn(); 
		
        $first = true;
		$daiDien_MADK = "DK".$order_DUKHACH;
		if (is_array($nhomDuKhach) || is_object($nhomDuKhach)){
			foreach($nhomDuKhach as $duKhach){
				$MADK = "DK".$order_DUKHACH;
				$MATK = null;
				if ($first == true){
					$MADKDAIDIEN = null;
					$first = false;
				}else{
					$MADKDAIDIEN = $daiDien_MADK;
				}
				$HOTENDK = $duKhach['HOTENDK'];
				$SDT = $duKhach['SDT'];
				$NGAYSINH = $duKhach['NGAYSINH'];
				$CMND = $duKhach['CMND'];
				
				$stmt->execute(array($MADK,$MATK,$MADKDAIDIEN,$HOTENDK,$SDT,$NGAYSINH,$CMND));
				
				$order_DUKHACH = $order_DUKHACH + 1;
			}
		}
		
        
        echo json_encode(array('status' => true, 'data' => $dbCon->lastInsertId(), 'message' => 'Thêm nhóm du khách thành công'));
    }
    catch(PDOException $ex){
        die(json_encode(array('status' => false, 'message' => $ex->getMessage())));
    }

?>