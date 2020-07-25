 <?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$query = "SELECT * FROM entreprise_sos ORDER BY id DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'id'                = $row['di'],
        'nom_entreprise'    =>$row['nom_entreprise'],
        'responsable'       =>$row['responsable'],
        'phone'             =>$row['phone'],
        'email'             =>$row['email'],
        'type'              =>$row['type'],
        'domicile'          =>$row['domicile'],
        'latitude'          =>$row['latitude'],
        'logitude'          =>$row['logitude'],
        'quartier'          =>$row['quartier'],
        'commune'           =>$row['commune'],
        'avenue'            =>$row['avenue'],
        'numero_home'       =>$row['numero_home'],
        'date_save'         =>date('d M Y', strtotime($row['date_save'])),
        'picture'           =>"http://$server_name" . $row['picture'],
        'love'              =>$row['love'])
    );
}

echo json_encode($response);

mysqli_close($conn);

?>