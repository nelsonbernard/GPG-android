<?php
$db_name = 'u297075769_gmapp';
$mysql_user = 'u297075769_tito';
$mysql_pass = 'w0OLEqUV8TyM';
$server_name = 'localhost';

$con = mysqli_connect($server_name, $mysql_user, $mysql_pass, $db_name);
if(!$con)
{
	echo "Connection failed...".mysqli_connect_error();
}
else
{

}



?>