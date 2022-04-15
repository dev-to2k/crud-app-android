<?php
require_once 'configuration.php';

$response = array();

if ($_POST['manv'] && $_POST['luong']) {
  $id = $_POST['manv'];
  $luong = $_POST['luong'];
  $stmt = $conn->prepare("UPDATE nhanvien SET luong = ? WHERE manv = ?");
  $stmt->bind_param("ss", $luong, $id);

  if ($stmt->execute() == true) {
    $response['error'] = false;
    $response['message'] = "Update successfully!";
  } else {
    $response['error'] = true;
    $response['message'] = "Incorrect id!";
  }
} else {
  $response['error'] = true;
  $response['message'] = "Insufficient Parameters!";
}

echo json_encode($response);
