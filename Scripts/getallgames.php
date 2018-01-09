<?php
require 'connect.php';

$sql = $con->query("SELECT * FROM GAMES");

$result = array();

while($row = mysqli_fetch_array($sql)){
	array_push($result, array("id"=>$row['ID'],
								"consoleid"=>$row['CONSOLEID'],
								"name"=>$row['NAME'],
								"image"=>$row['IMAGE_NAME'],
								"loose"=>$row['LOOSE'],
								"cib"=>$row['CIB'],
								"new"=>$row['NEW'],));
}

 echo json_encode(array('result'=>$result));
 mysqli_close($con);
?>