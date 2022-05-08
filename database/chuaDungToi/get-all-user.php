<?php
    
	require_once ('connection.php');

    $sql = "
	SELECT 
		tk.MATK AS MATK, 
		tk.EMAILTK AS EMAILTK, 
		tk.MATKHAUTK AS MATKHAUTK, 
		tk.MATHENGANHANG AS MATHENGANHANG, 
		dk.MADK AS MADK, 
		dk.MADKDAIDIEN AS MADKDAIDIEN, 
		dk.HOTENDK AS HOTENDK, 
		dk.SDT AS SDT, 
		dk.NGAYSINH AS NGAYSINH, 
		dk.CMND AS CMND 
	FROM    
        taikhoan AS tk
    INNER JOIN dukhach AS dk ON dk.MATK = tk.MATK    
	";	

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
