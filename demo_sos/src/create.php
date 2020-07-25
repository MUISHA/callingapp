<?php
/**
 * Created by PhpStorm.
 * User: Styve
 * Date: 25/07/2020
 * Time: 16:48
 */

require 'model/Entreprise.php';

$nom_entreprise=isset($_REQUEST['nom'])?$_REQUEST['nom']:"";
$responsable=isset($_REQUEST['responsable'])?$_REQUEST['responsable']:"";
$phone=isset($_REQUEST['phone'])?$_REQUEST['phone']:"";
$email=isset($_REQUEST['email'])?$_REQUEST['email']:"";
$type=isset($_REQUEST['tpe'])?$_REQUEST['tpe']:"";
$domicile=isset($_REQUEST['domicile'])?$_REQUEST['domicile']:"";
$latitude=isset($_REQUEST['lat'])?$_REQUEST['lat']:"";
$logitude=isset($_REQUEST['log'])?$_REQUEST['log']:"";
$quartier=isset($_REQUEST['quart'])?$_REQUEST['quart']:"";
$commune=isset($_REQUEST['commune'])?$_REQUEST['commune']:"";
$avenue=isset($_REQUEST['av'])?$_REQUEST['av']:"";
$numero_home=isset($_REQUEST['num_home'])?$_REQUEST['num_home']:"";
$picture=isset($_REQUEST['pic'])?$_REQUEST['pic']:"";



$entreprise=new Entreprise();

$entreprise->setNomEntreprise($nom_entreprise);
$entreprise->setResponsable($responsable);
$entreprise->setPhone($phone);
$entreprise->setEmail($email);
$entreprise->setType($type);
$entreprise->setDomicile($domicile);
$entreprise->setLatitude($latitude);
$entreprise->setLogitude($logitude);
$entreprise->setQuartier($quartier);
$entreprise->setCommune($commune);
$entreprise->setAvenue($avenue);
$entreprise->setNumeroHome($numero_home);
$entreprise->setPicture($picture);

print_r($entreprise->create()? json_encode(201) : json_encode(400) );

