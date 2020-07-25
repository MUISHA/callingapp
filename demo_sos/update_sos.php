<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$key = $_POST['key'];

if ( $key == "update" ){

        $id                = $_POST['id'];
        $nom_entreprise    =  $_POST['nom_entreprise'];
        $responsable       =  $_POST['responsable'];
        $phone             =  $_POST['phone'];
        $email             =  $_POST['email'];
        $type              =  $_POST['type'];
        $domicile          =  $_POST['domicile'];
        $latitude          =  $_POST['latitude'];
        $logitude          =  $_POST['logitude'];
        $quartier          =  $_POST['quartier'];
        $commune           =  $_POST['commune'];
        $avenue            =  $_POST['avenue'];
        $numero_home       =  $_POST['numero_home'];
        $date_save         =  $_POST['date_save'];
        $picture           =  $_POST['picture'];

        $date_save =  date('Y-m-d', strtotime($date_save));

    $query = "UPDATE entreprise_sos SET 
    
        nom_entreprise='$nom_entreprise',
        responsable='$responsable',
        phone='$phone',
        email='$email',
        type='$type',
        domicile='$domicile',
        latitude='$latitude',
        logitude='$logitude',
        quartier='$quartier',
        commune='$commune',
        avenue='$avenue',
        numero_home='$numero_home',
        date_save='$date_save'    
    
    WHERE id='$id' ";

        if ( mysqli_query($conn, $query) ){

            if ($picture == null) {

                $result["value"] = "1";
                $result["message"] = "Success";
    
                echo json_encode($result);
                mysqli_close($conn);

            } else {

                $path = "sos_picture/$id.jpeg";
                $finalPath = "/demo_sos/".$path;

                $insert_picture = "UPDATE entreprise SET picture='$finalPath' WHERE id='$id' ";
            
                if (mysqli_query($conn, $insert_picture)) {
            
                    if ( file_put_contents( $path, base64_decode($picture) ) ) {
                        
                        $result["value"] = "1";
                        $result["message"] = "Success!";
            
                        echo json_encode($result);
                        mysqli_close($conn);
            
                    } else {
                        
                        $response["value"] = "0";
                        $response["message"] = "Error! ".mysqli_error($conn);
                        echo json_encode($response);

                        mysqli_close($conn);
                    }

                }
            }

        } 
        else {
            $response["value"] = "0";
            $response["message"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
        }
}

?>