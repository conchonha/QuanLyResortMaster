<?php

    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');
    
    $host = '127.0.0.1';
    $dbName = 'quanlyresort';
    $username = 'root';
    $password = '';

    try{
        $dbCon = new PDO("mysql:host=".$host.";dbname=".$dbName.";charset=utf8", $username, $password, array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));
    }
    catch(PDOException $ex){
        die(json_encode(array('status' => false, 'message' => 'Unable to connect: ' . $ex->getMessage())));
    }

?>
