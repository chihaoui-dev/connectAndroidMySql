<?php
require("connexion.php");
$response=array();
if ((isset($_GET['nom'])) && (isset($_GET['nature'])))
{
$nom=$_GET['nom'];
$nature=$_GET['nature'];


$larequete="insert into plante(nom,nature) values('$nom','$nature')";
$req=$conn->prepare($larequete);
$req->execute();
//$result=mysql_query($larequete);
if ($req){
$response["success"]=1;
$response["message"]="ajout avec succèes";

echo json_encode($response);
}
else
{
$response["success"]=0;
$response["message"]="error";

echo json_encode($response);
}
}
else
{
$response["success"]=0;
$response["message"]="champs manquants";

echo json_encode($response);
}
?>