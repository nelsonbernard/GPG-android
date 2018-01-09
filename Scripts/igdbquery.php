<?php

/*$baseurl = 'https://api-2445582011268.apicast.io';
$platformurl = '/platforms';
$gamesurl = '/games';
$fields = 'fields=';
$searchurl = '/?search=';*/

if($_SERVER['REQUEST METHOD'] == 'GET'){
	echo 'GET method detected';
}
/*	if(isset($_GET['consolename'])){

		$consolename = $_GET['consolename'];
		echo $consolename;
		$url = $baseurl . $platformurl . $searchurl . urlencode($consolename);
		$context = stream_context_create(['https' => ['method' => 'GET', 'user-key' => '008337b3a45b175cade813c414568c70']]);
		$response = file_get_contents($url, false, $context);
	}	*/

if($_SERVER['REQUEST METHOD'] == 'POST'){
	echo 'POST method detected';
}
/*	if($data = file_get_contents('php://input')){
		$json_result = json_decode($data);
		$consolename = json_result($_POST['consolename']);
	}*/



/*echo $consolename*/
?>