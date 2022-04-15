<?php
require_once 'configuration.php';

$response = array();

if (
  isset($_POST['hotennv']) && isset($_POST['ngaysinh']) && isset($_POST['gioitinh']) && isset($_POST['noisinh'])
  && isset($_POST['luong'])
) {
  $hotennv = $_POST['hotennv'];
  $ngaysinh = $_POST['ngaysinh'];
  $gioitinh = $_POST['gioitinh'];
  $noisinh = $_POST['noisinh'];
  $luong = $_POST['luong'];

  $stmt = $conn->prepare("INSERT INTO `nhanvien` (`hotennv`, `ngaysinh`, `gioitinh`, `noisinh`, `luong`) VALUES (?,?,?,?,?)");
  $stmt->bind_param("sssss", $hotennv, $ngaysinh, $gioitinh, $noisinh, $luong);

  if ($stmt->execute() == true) {
    $response['error'] = false;
    $response['message'] = "product created successfully!";
  } else {
    $response['error'] = true;
    $response['message'] = "failed\n" . $conn->error;
  }
} else {
  $response['error'] = true;
  $response['message'] = "Insufficient parameters";
}

echo json_encode($response);
