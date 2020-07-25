<?php 

require_once 'connect.php';

$key = $_POST['key'];

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

if ( $key == "insert" ){
    
    $birth_newformat = date('Y-m-d', strtotime($birth));

    $query = "INSERT INTO entreprise_sos (nom_entreprise, responsable,phone,email,type,domicile,latitude,logitude,quartier,commune,avenue,numero_home,date_save)
     VALUES ('$nom_entreprise','$responsable','$phone','$email','$type','$domicile','$latitude','$logitude','$quartier','$commune','$avenue','$numero_home','$birth_newformat')";    

        if ( mysqli_query($conn, $query) ){

            if ($picture == null) {

                $finalPath = "/demo_sos/sos_logo.png"; 
                $result["value"] = "1";
                $result["message"] = "Success";
    
                echo json_encode($result);
                mysqli_close($conn);

            } else {

                $id = mysqli_insert_id($conn);
                $path = "sos_picture/$id.jpeg";
                $finalPath = "/demo_sos/".$path;

                $insert_picture = "UPDATE entreprise_sos SET picture='$finalPath' WHERE id='$id' ";
            
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