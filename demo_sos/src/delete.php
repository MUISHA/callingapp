<?php
/**
 * Created by PhpStorm.
 * User: Styve
 * Date: 25/07/2020
 * Time: 16:49
 */

require 'model/Entreprise.php';

if (isset($_REQUEST['id'])) {

    $id = $_REQUEST['id'];

    $entreprise = new Entreprise();

    $entreprise->setId($id);

    print_r($entreprise->delete() ? json_encode(200) : json_encode(400));


}