<?php
const API_KEY = '87155ee49fb9cca0cedcdecd38260c83b2c2d0fc';

if($_SERVER['REQUEST_METHOD'] === 'POST'){
	if($data = file_get_contents('php://input')){

		$json_result = json_decode($data);
		$query_param = $json_result->{'query'};
	}
	else
	{
		$query_param = $_POST['query'];
	}

	$url = 'https://www.giantbomb.com/api/search/?api_key=' . API_KEY . '&query=' . urlencode($query_param) . '&resources=game&format=json';
	$context = stream_context_create(['http' => ['method' => 'GET', 'user_agent' => 'Gaming Price Guide']]);
	$response = file_get_contents($url, false, $context);

	$json = json_decode($response)->results;

	foreach ($json as $key => $value) {
		if(strtolower($value->name) === strtolower($query_param)){
			$gameurl = $value->api_detail_url;
		}
	}

	$url = $gameurl . '?api_key=' . API_KEY . '&format=json';

	$response = file_get_contents($url, false, $context);
	$json = json_decode($response)->results;

	$array = array();

	array_push($array, array("name"=>$json->name,
								 "description"=>$value->description,
								 "deck"=>$value->deck,
								 "screen_url"=>$value->image->screen_url,
								 "release_date"=>$value->original_release_date));

	header("Content-type: application/json");
	echo json_encode($array);
}
else
{
	$result = "Must use POST instead of GET";
	echo json_encode(array('result_get'=>$result));
}

?>