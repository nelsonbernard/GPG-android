<?php
require 'connect.php';

$sql = $con->query("SELECT * FROM CONSOLES");

$result = array();

while($row = mysqli_fetch_array($sql)){
	array_push($result, array("id"=>$row['ID'],
								"consoleid"=>$row['CONSOLEID'],
								"name"=>$row['NAME']));
}

echo json_encode(array('result'=>$result));
mysqli_close($con);

?>