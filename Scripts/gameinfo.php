<?php
require 'connect.php';

if($_SERVER['REQUEST_METHOD'] === 'POST'){
	if($data = file_get_contents('php://input')){

		$json_result = json_decode($data);
		$id = $json_result->{'id'};
	}
	else
	{
		$id = $_POST['id'];
	}

	if($sql = $con->query("SELECT * FROM GAMES WHERE ID = '$id'")){

	$result = array();

		while($row = mysqli_fetch_array($sql)){
			array_push($result, array("id"=>$row['ID'],
									"name"=>$row['NAME'],
									"image"=>$row['IMAGE_NAME'],
									"loose"=>$row['LOOSE'],
									"cib"=>$row['CIB'],
									"new"=>$row['NEW'],
									"consoleid"=>$row['CONSOLEID']));
		}
	}

	header("Content-type: application/json");
	echo json_encode(array('result'=>$result));
}
else
{
	$result = "Must use POST method";
	echo json_encode(array('results error'=>$result));
}
mysqli_close($con);

?>