<?php
require 'connect.php';

$id = $_POST['id'];

if($sql = $con->query("SELECT * FROM GAMES WHERE ID = '$id'")){

	$result = array();

	while($row = mysqli_fetch_row($sql)){
		array_push($result, array("id"=>$row['ID'],
								"name"=>$row['NAME'],
								"image"=>$row['IMAGE_NAME'],
								"loose"=>$row['LOOSE'],
								"cib"=>$row['CIB'],
								"new"=>$row['NEW']));
	}
}


echo json_encode(array('result'=>$result));
mysqli_close($con);

?>