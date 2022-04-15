<?php
require_once 'configuration.php';

$response = array();

if ($_POST['manv']) {
  $id = $_POST['manv'];
  $stmt = $conn->prepare("SELECT hotennv, ngaysinh, gioitinh, noisinh, luong FROM nhanvien WHERE manv = ?");
  $stmt->bind_param("s", $id);
  $result = $stmt->execute();

  if ($result == true) {
    $response['error'] = false;
    $response['message'] = "Retrieval Successful";
    $stmt->store_result();
    $stmt->bind_result($hotennv, $ngaysinh, $gioitinh, $noisinh, $luong);
    $stmt->fetch();
    $response['hotennv'] = $hotennv;
    $response['ngaysinh'] = $ngaysinh;
    $response['noisinh'] = $noisinh;
    $response['luong'] = $luong;
    $response['gioitinh'] = $gioitinh;
  }
} else {
  $response['error'] = true;
  $response['message'] = "Insufficient Parameters";
}

echo json_encode($response);
