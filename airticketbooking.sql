-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3307
-- Thời gian đã tạo: Th9 21, 2023 lúc 07:14 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `airticketbooking`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `airplane`
--

CREATE TABLE `airplane` (
  `airplane_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `airplane`
--

INSERT INTO `airplane` (`airplane_id`, `name`, `country`) VALUES
('VA', 'VietNam Airline', 'Việt Nam'),
('VJ', 'VietJet Air', 'Việt Nam');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `airport`
--

CREATE TABLE `airport` (
  `airport_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `airport`
--

INSERT INTO `airport` (`airport_id`, `name`, `city`, `country`) VALUES
('BMV', 'Buôn Ma Thuột', 'Buôn Ma Thuột', 'Việt Nam'),
('CXR', 'Cam Ranh', 'Nha Trang', 'Việt Nam'),
('DAD', 'Đà Nẵng', 'Đà Nẵng', 'Việt Nam'),
('DLI', 'Liên Khương', 'Đà Lạt', 'Việt Nam'),
('HAN', 'Nội Bài', 'Hà Nội', 'Việt Nam'),
('HPH', 'Cát Bi', 'Hải Phòng', 'Việt Nam'),
('HUI', 'Phú Bài', 'Huế', 'Việt Nam'),
('PQC', 'Phú Quốc', 'Phú Quốc', 'Việt Nam'),
('PXU', 'Pleiku', 'Pleiku', 'Việt Nam'),
('SGN', 'Tân Sơn Nhất', 'TP Hồ Chí Minh', 'Việt Nam'),
('TBB', 'Tuy Hòa', 'Tuy Hòa', 'Việt Nam'),
('THD', 'Thọ Xuân', 'Thanh Hóa', 'Việt Nam'),
('UIH', 'Phù Cát', 'Quy Nhơn', 'Việt Nam'),
('VCA', 'Trà Nóc', 'Cần Thơ', 'Việt Nam'),
('VCL', 'Chu Lai', 'Chu Lai', 'Việt Nam'),
('VDO', 'Quốc tế Vân Đồn', 'Vân Đồn', 'Việt Nam'),
('VHD', 'Đồng Hới', 'Đồng Hới', 'Việt Nam'),
('VII', 'Quốc tế Vinh', 'Vinh', 'Việt Nam');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `bill_id` varchar(50) NOT NULL,
  `customer_id` varchar(50) DEFAULT NULL,
  `employee_id` varchar(50) DEFAULT NULL,
  `total_price` int(11) NOT NULL,
  `bill_date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `bill`
--

INSERT INTO `bill` (`bill_id`, `customer_id`, `employee_id`, `total_price`, `bill_date`) VALUES
('HD001', 'KH001', 'NV001', 2900000, '21-09-2023');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chair`
--

CREATE TABLE `chair` (
  `flight_id` varchar(50) NOT NULL,
  `type_chair` varchar(50) NOT NULL,
  `chair_number` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chair`
--

INSERT INTO `chair` (`flight_id`, `type_chair`, `chair_number`, `status`) VALUES
('VJ001', 'BUS', '1', '0'),
('VJ001', 'BUS', '10', '0'),
('VJ001', 'BUS', '11', '0'),
('VJ001', 'BUS', '12', '0'),
('VJ001', 'BUS', '13', '0'),
('VJ001', 'BUS', '14', '0'),
('VJ001', 'BUS', '15', '0'),
('VJ001', 'BUS', '16', '0'),
('VJ001', 'BUS', '17', '0'),
('VJ001', 'BUS', '18', '0'),
('VJ001', 'BUS', '19', '0'),
('VJ001', 'BUS', '2', '0'),
('VJ001', 'BUS', '20', '0'),
('VJ001', 'BUS', '21', '0'),
('VJ001', 'BUS', '22', '0'),
('VJ001', 'BUS', '23', '0'),
('VJ001', 'BUS', '24', '0'),
('VJ001', 'BUS', '25', '0'),
('VJ001', 'BUS', '26', '0'),
('VJ001', 'BUS', '27', '0'),
('VJ001', 'BUS', '28', '0'),
('VJ001', 'BUS', '29', '0'),
('VJ001', 'BUS', '3', '0'),
('VJ001', 'BUS', '30', '0'),
('VJ001', 'BUS', '31', '0'),
('VJ001', 'BUS', '32', '0'),
('VJ001', 'BUS', '33', '0'),
('VJ001', 'BUS', '34', '0'),
('VJ001', 'BUS', '35', '0'),
('VJ001', 'BUS', '36', '0'),
('VJ001', 'BUS', '37', '0'),
('VJ001', 'BUS', '38', '0'),
('VJ001', 'BUS', '39', '0'),
('VJ001', 'BUS', '4', '0'),
('VJ001', 'BUS', '40', '0'),
('VJ001', 'BUS', '41', '0'),
('VJ001', 'BUS', '42', '0'),
('VJ001', 'BUS', '43', '0'),
('VJ001', 'BUS', '44', '0'),
('VJ001', 'BUS', '45', '0'),
('VJ001', 'BUS', '46', '0'),
('VJ001', 'BUS', '47', '0'),
('VJ001', 'BUS', '48', '0'),
('VJ001', 'BUS', '49', '0'),
('VJ001', 'BUS', '5', '0'),
('VJ001', 'BUS', '50', '0'),
('VJ001', 'BUS', '51', '0'),
('VJ001', 'BUS', '52', '0'),
('VJ001', 'BUS', '53', '0'),
('VJ001', 'BUS', '54', '0'),
('VJ001', 'BUS', '55', '0'),
('VJ001', 'BUS', '56', '0'),
('VJ001', 'BUS', '57', '0'),
('VJ001', 'BUS', '58', '0'),
('VJ001', 'BUS', '59', '0'),
('VJ001', 'BUS', '6', '0'),
('VJ001', 'BUS', '60', '0'),
('VJ001', 'BUS', '7', '0'),
('VJ001', 'BUS', '8', '0'),
('VJ001', 'BUS', '9', '0'),
('VJ001', 'ECO', '1', '0'),
('VJ001', 'ECO', '10', '0'),
('VJ001', 'ECO', '100', '0'),
('VJ001', 'ECO', '101', '0'),
('VJ001', 'ECO', '102', '0'),
('VJ001', 'ECO', '103', '0'),
('VJ001', 'ECO', '104', '0'),
('VJ001', 'ECO', '105', '0'),
('VJ001', 'ECO', '106', '0'),
('VJ001', 'ECO', '107', '0'),
('VJ001', 'ECO', '108', '0'),
('VJ001', 'ECO', '109', '0'),
('VJ001', 'ECO', '11', '0'),
('VJ001', 'ECO', '110', '0'),
('VJ001', 'ECO', '111', '0'),
('VJ001', 'ECO', '112', '0'),
('VJ001', 'ECO', '113', '0'),
('VJ001', 'ECO', '114', '0'),
('VJ001', 'ECO', '115', '0'),
('VJ001', 'ECO', '116', '0'),
('VJ001', 'ECO', '117', '0'),
('VJ001', 'ECO', '118', '0'),
('VJ001', 'ECO', '119', '0'),
('VJ001', 'ECO', '12', '0'),
('VJ001', 'ECO', '120', '0'),
('VJ001', 'ECO', '121', '0'),
('VJ001', 'ECO', '122', '0'),
('VJ001', 'ECO', '123', '0'),
('VJ001', 'ECO', '124', '0'),
('VJ001', 'ECO', '125', '0'),
('VJ001', 'ECO', '126', '0'),
('VJ001', 'ECO', '127', '0'),
('VJ001', 'ECO', '128', '0'),
('VJ001', 'ECO', '129', '0'),
('VJ001', 'ECO', '13', '0'),
('VJ001', 'ECO', '130', '0'),
('VJ001', 'ECO', '131', '0'),
('VJ001', 'ECO', '132', '0'),
('VJ001', 'ECO', '133', '0'),
('VJ001', 'ECO', '134', '0'),
('VJ001', 'ECO', '135', '0'),
('VJ001', 'ECO', '136', '0'),
('VJ001', 'ECO', '137', '0'),
('VJ001', 'ECO', '138', '0'),
('VJ001', 'ECO', '139', '0'),
('VJ001', 'ECO', '14', '0'),
('VJ001', 'ECO', '140', '0'),
('VJ001', 'ECO', '141', '0'),
('VJ001', 'ECO', '142', '0'),
('VJ001', 'ECO', '143', '0'),
('VJ001', 'ECO', '144', '0'),
('VJ001', 'ECO', '145', '0'),
('VJ001', 'ECO', '146', '0'),
('VJ001', 'ECO', '147', '0'),
('VJ001', 'ECO', '148', '0'),
('VJ001', 'ECO', '149', '0'),
('VJ001', 'ECO', '15', '0'),
('VJ001', 'ECO', '150', '0'),
('VJ001', 'ECO', '151', '0'),
('VJ001', 'ECO', '152', '0'),
('VJ001', 'ECO', '153', '0'),
('VJ001', 'ECO', '154', '0'),
('VJ001', 'ECO', '155', '0'),
('VJ001', 'ECO', '156', '0'),
('VJ001', 'ECO', '157', '0'),
('VJ001', 'ECO', '158', '0'),
('VJ001', 'ECO', '159', '0'),
('VJ001', 'ECO', '16', '0'),
('VJ001', 'ECO', '160', '0'),
('VJ001', 'ECO', '161', '0'),
('VJ001', 'ECO', '162', '0'),
('VJ001', 'ECO', '163', '0'),
('VJ001', 'ECO', '164', '0'),
('VJ001', 'ECO', '165', '0'),
('VJ001', 'ECO', '166', '0'),
('VJ001', 'ECO', '167', '0'),
('VJ001', 'ECO', '168', '0'),
('VJ001', 'ECO', '169', '0'),
('VJ001', 'ECO', '17', '0'),
('VJ001', 'ECO', '170', '0'),
('VJ001', 'ECO', '171', '0'),
('VJ001', 'ECO', '172', '0'),
('VJ001', 'ECO', '173', '0'),
('VJ001', 'ECO', '174', '0'),
('VJ001', 'ECO', '175', '0'),
('VJ001', 'ECO', '176', '0'),
('VJ001', 'ECO', '177', '0'),
('VJ001', 'ECO', '178', '0'),
('VJ001', 'ECO', '179', '0'),
('VJ001', 'ECO', '18', '0'),
('VJ001', 'ECO', '180', '0'),
('VJ001', 'ECO', '19', '0'),
('VJ001', 'ECO', '2', '0'),
('VJ001', 'ECO', '20', '0'),
('VJ001', 'ECO', '21', '0'),
('VJ001', 'ECO', '22', '0'),
('VJ001', 'ECO', '23', '0'),
('VJ001', 'ECO', '24', '0'),
('VJ001', 'ECO', '25', '0'),
('VJ001', 'ECO', '26', '0'),
('VJ001', 'ECO', '27', '0'),
('VJ001', 'ECO', '28', '0'),
('VJ001', 'ECO', '29', '0'),
('VJ001', 'ECO', '3', '0'),
('VJ001', 'ECO', '30', '0'),
('VJ001', 'ECO', '31', '0'),
('VJ001', 'ECO', '32', '0'),
('VJ001', 'ECO', '33', '0'),
('VJ001', 'ECO', '34', '0'),
('VJ001', 'ECO', '35', '0'),
('VJ001', 'ECO', '36', '0'),
('VJ001', 'ECO', '37', '0'),
('VJ001', 'ECO', '38', '0'),
('VJ001', 'ECO', '39', '0'),
('VJ001', 'ECO', '4', '0'),
('VJ001', 'ECO', '40', '0'),
('VJ001', 'ECO', '41', '0'),
('VJ001', 'ECO', '42', '0'),
('VJ001', 'ECO', '43', '0'),
('VJ001', 'ECO', '44', '0'),
('VJ001', 'ECO', '45', '0'),
('VJ001', 'ECO', '46', '0'),
('VJ001', 'ECO', '47', '0'),
('VJ001', 'ECO', '48', '0'),
('VJ001', 'ECO', '49', '1'),
('VJ001', 'ECO', '5', '0'),
('VJ001', 'ECO', '50', '0'),
('VJ001', 'ECO', '51', '0'),
('VJ001', 'ECO', '52', '0'),
('VJ001', 'ECO', '53', '0'),
('VJ001', 'ECO', '54', '1'),
('VJ001', 'ECO', '55', '0'),
('VJ001', 'ECO', '56', '0'),
('VJ001', 'ECO', '57', '0'),
('VJ001', 'ECO', '58', '0'),
('VJ001', 'ECO', '59', '0'),
('VJ001', 'ECO', '6', '0'),
('VJ001', 'ECO', '60', '0'),
('VJ001', 'ECO', '61', '0'),
('VJ001', 'ECO', '62', '0'),
('VJ001', 'ECO', '63', '0'),
('VJ001', 'ECO', '64', '0'),
('VJ001', 'ECO', '65', '0'),
('VJ001', 'ECO', '66', '0'),
('VJ001', 'ECO', '67', '0'),
('VJ001', 'ECO', '68', '0'),
('VJ001', 'ECO', '69', '0'),
('VJ001', 'ECO', '7', '0'),
('VJ001', 'ECO', '70', '0'),
('VJ001', 'ECO', '71', '0'),
('VJ001', 'ECO', '72', '0'),
('VJ001', 'ECO', '73', '0'),
('VJ001', 'ECO', '74', '0'),
('VJ001', 'ECO', '75', '0'),
('VJ001', 'ECO', '76', '0'),
('VJ001', 'ECO', '77', '0'),
('VJ001', 'ECO', '78', '0'),
('VJ001', 'ECO', '79', '0'),
('VJ001', 'ECO', '8', '0'),
('VJ001', 'ECO', '80', '0'),
('VJ001', 'ECO', '81', '0'),
('VJ001', 'ECO', '82', '0'),
('VJ001', 'ECO', '83', '0'),
('VJ001', 'ECO', '84', '0'),
('VJ001', 'ECO', '85', '0'),
('VJ001', 'ECO', '86', '0'),
('VJ001', 'ECO', '87', '0'),
('VJ001', 'ECO', '88', '0'),
('VJ001', 'ECO', '89', '0'),
('VJ001', 'ECO', '9', '0'),
('VJ001', 'ECO', '90', '0'),
('VJ001', 'ECO', '91', '0'),
('VJ001', 'ECO', '92', '0'),
('VJ001', 'ECO', '93', '0'),
('VJ001', 'ECO', '94', '0'),
('VJ001', 'ECO', '95', '0'),
('VJ001', 'ECO', '96', '0'),
('VJ001', 'ECO', '97', '0'),
('VJ001', 'ECO', '98', '0'),
('VJ001', 'ECO', '99', '0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `customer_id` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`customer_id`, `surname`, `name`, `email`, `phone`, `address`) VALUES
('KH001', 'Nguyen', 'Huu Duc', 'duckg2083@gmail.com', '0946945409', 'An Duong Vuong, P 4, Quan 5, TPHCM'),
('KH002', 'Nguyen', 'Hieu', 'hiudu@gmail.com', '0939789223', 'Quận 5, TPHCM'),
('KH003', 'Nguyen', 'Dat', 'dat@gmail.com', '0912346712', 'Quan 5, TP HCM');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `detailbill`
--

CREATE TABLE `detailbill` (
  `bill_id` varchar(50) NOT NULL,
  `type_id` varchar(50) NOT NULL,
  `total_price` double NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `detailbill`
--

INSERT INTO `detailbill` (`bill_id`, `type_id`, `total_price`, `amount`) VALUES
('HD001', 'TKVJ001001', 1450000, 1),
('HD001', 'TKVJ001002', 1450000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `employee_id` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `birth` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`employee_id`, `user_name`, `pass`, `role_id`, `surname`, `name`, `gender`, `birth`, `email`, `phone`, `address`) VALUES
('NV001', 'duc', '1234', '1', 'Nguyen', 'Duc', 'Nam', '20-08-2003', 'duckg2083@gmail.com', '0946945409', 'An Duong Vuong, P4, Quan 5, TP HCM'),
('NV002', 'tram', '1234', '3', 'Si', 'Tram', 'Nam', '09-05-2003', 'tram@gmail.com', '0968942589', 'Quan 5, TP HCM');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `flight`
--

CREATE TABLE `flight` (
  `flight_id` varchar(50) NOT NULL,
  `route_id` varchar(50) NOT NULL,
  `plane_id` varchar(50) NOT NULL,
  `flight_schedule_id` varchar(50) NOT NULL,
  `totalSeats` varchar(50) NOT NULL,
  `bookedECOSeats` varchar(50) NOT NULL,
  `bookedBUSSeats` varchar(50) NOT NULL,
  `price_eco` int(11) NOT NULL,
  `price_bus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `flight`
--

INSERT INTO `flight` (`flight_id`, `route_id`, `plane_id`, `flight_schedule_id`, `totalSeats`, `bookedECOSeats`, `bookedBUSSeats`, `price_eco`, `price_bus`) VALUES
('VJ001', 'SGN-HAN', 'A110', 'LB001', '240', '2', '0', 1200000, 2300000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `flightschedule`
--

CREATE TABLE `flightschedule` (
  `flight_schedule_id` varchar(50) NOT NULL,
  `departureAirport` varchar(50) NOT NULL,
  `arrivalAirport` varchar(50) NOT NULL,
  `departureDate` varchar(50) NOT NULL,
  `arrivalDate` varchar(50) NOT NULL,
  `boartime` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `flightschedule`
--

INSERT INTO `flightschedule` (`flight_schedule_id`, `departureAirport`, `arrivalAirport`, `departureDate`, `arrivalDate`, `boartime`) VALUES
('LB001', 'SGN', 'HAN', '19-05-2023', '20-05-2023', '20:00'),
('LB002', 'SGN', 'HAN', '19-05-2023', '19-05-2023', '07:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `funtions`
--

CREATE TABLE `funtions` (
  `function_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `decripition` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `funtions`
--

INSERT INTO `funtions` (`function_id`, `name`, `decripition`) VALUES
('1', 'BÁN VÉ', NULL),
('10', 'KHÁCH HÀNG', NULL),
('11', 'HÓA ĐƠN', NULL),
('12', 'THỐNG KÊ', NULL),
('2', 'CHUYẾN BAY', NULL),
('3', 'LỘ TRÌNH', NULL),
('4', 'LỊCH TRÌNH', NULL),
('5', 'VÉ & LOẠI VÉ', NULL),
('6', 'MÁY BAY', NULL),
('7', 'SÂN BAY', NULL),
('8', 'HÃNG', NULL),
('9', 'NHÂN VIÊN', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `plane`
--

CREATE TABLE `plane` (
  `plane_id` varchar(50) NOT NULL,
  `plane_model` varchar(50) NOT NULL,
  `busi_seats` int(11) NOT NULL,
  `eco_seats` int(11) NOT NULL,
  `airplane_id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `plane`
--

INSERT INTO `plane` (`plane_id`, `plane_model`, `busi_seats`, `eco_seats`, `airplane_id`) VALUES
('A110', 'Airbus', 60, 180, 'VA'),
('A202', 'Boeing 787', 60, 180, 'VA ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rolefunc`
--

CREATE TABLE `rolefunc` (
  `role_id` varchar(50) NOT NULL,
  `function_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `rolefunc`
--

INSERT INTO `rolefunc` (`role_id`, `function_id`) VALUES
('1', '1'),
('1', '10'),
('1', '11'),
('1', '12'),
('1', '2'),
('1', '3'),
('1', '4'),
('1', '5'),
('1', '6'),
('1', '7'),
('1', '8'),
('1', '9'),
('2', '10'),
('2', '12'),
('2', '3'),
('2', '5'),
('3', '1'),
('3', '10'),
('3', '11');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `role_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`role_id`, `name`, `description`) VALUES
('1', 'admin', NULL),
('2', 'asdasd', ''),
('3', 'abc', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `route`
--

CREATE TABLE `route` (
  `route_id` varchar(50) NOT NULL,
  `origin` varchar(50) NOT NULL,
  `destination` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `route`
--

INSERT INTO `route` (`route_id`, `origin`, `destination`) VALUES
('SGN-DAD', 'TP Hồ Chí Minh', 'Đà Nẵng'),
('SGN-HAN', 'TP Hồ Chí Minh', 'Hà Nội');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `service`
--

CREATE TABLE `service` (
  `name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `service`
--

INSERT INTO `service` (`name`, `description`, `price`, `image`) VALUES
('Thêm hành lí 12kg', 'BUS001', 250000, ''),
('Water bottle', 'BUS002', 50000, ''),
('Combo fried rice + pepsi', 'BUS003', 170000, ''),
('Fried rice', 'BUS004', 120000, ''),
('Thêm hành lí 7kg', 'ECO001', 200000, ''),
('Fried rice', 'ECO002', 120000, ''),
('Water bottle', 'ECO003', 50000, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `servicefunc`
--

CREATE TABLE `servicefunc` (
  `type_id` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `servicefunc`
--

INSERT INTO `servicefunc` (`type_id`, `description`) VALUES
('DV001', 'ECO001'),
('DV001', 'ECO003'),
('DV002', 'BUS001'),
('DV002', 'BUS003');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ticket`
--

CREATE TABLE `ticket` (
  `ticket_id` varchar(50) NOT NULL,
  `flight_id` varchar(50) NOT NULL,
  `customer_id` varchar(50) NOT NULL,
  `type_id` varchar(50) DEFAULT NULL,
  `chair_number` varchar(50) NOT NULL,
  `bill_id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ticket`
--

INSERT INTO `ticket` (`ticket_id`, `flight_id`, `customer_id`, `type_id`, `chair_number`, `bill_id`) VALUES
('TKVJ001001', 'VJ001', 'KH001', 'DV001', '49', 'HD001'),
('TKVJ001002', 'VJ001', 'KH001', 'DV001', '54', 'HD001');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `type`
--

CREATE TABLE `type` (
  `type_id` varchar(50) NOT NULL,
  `type_name` varchar(50) NOT NULL,
  `type_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `type`
--

INSERT INTO `type` (`type_id`, `type_name`, `type_price`) VALUES
('DV001', 'Phổ thông', 250000),
('DV002', 'Thương gia', 420000);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `airplane`
--
ALTER TABLE `airplane`
  ADD PRIMARY KEY (`airplane_id`);

--
-- Chỉ mục cho bảng `airport`
--
ALTER TABLE `airport`
  ADD PRIMARY KEY (`airport_id`);

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `FK_BILL_CUSTOMER` (`customer_id`),
  ADD KEY `FK_BILL_EMPLOYEE` (`employee_id`);

--
-- Chỉ mục cho bảng `chair`
--
ALTER TABLE `chair`
  ADD PRIMARY KEY (`flight_id`,`type_chair`,`chair_number`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Chỉ mục cho bảng `detailbill`
--
ALTER TABLE `detailbill`
  ADD KEY `FK_DETAILBILL_TICKET` (`type_id`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Chỉ mục cho bảng `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`flight_id`),
  ADD KEY `FK_FLIGHT_PLANE` (`plane_id`),
  ADD KEY `FK_FLIGHT_ROUTE` (`route_id`),
  ADD KEY `FK_FLIGHT_FLIGHTSCHEDULE` (`flight_schedule_id`);

--
-- Chỉ mục cho bảng `flightschedule`
--
ALTER TABLE `flightschedule`
  ADD PRIMARY KEY (`flight_schedule_id`),
  ADD KEY `FK_FLIGHTSCHEDULE_AIRPORT` (`departureAirport`),
  ADD KEY `FK_FLIGHTSCHEDULE_AIRPORT1` (`arrivalAirport`);

--
-- Chỉ mục cho bảng `funtions`
--
ALTER TABLE `funtions`
  ADD PRIMARY KEY (`function_id`);

--
-- Chỉ mục cho bảng `plane`
--
ALTER TABLE `plane`
  ADD PRIMARY KEY (`plane_id`),
  ADD KEY `FK_PLANE_AIRPLANE` (`airplane_id`);

--
-- Chỉ mục cho bảng `rolefunc`
--
ALTER TABLE `rolefunc`
  ADD PRIMARY KEY (`role_id`,`function_id`),
  ADD KEY `FK_ROLEFUNC_FUNTIONS` (`function_id`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Chỉ mục cho bảng `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`route_id`);

--
-- Chỉ mục cho bảng `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`description`);

--
-- Chỉ mục cho bảng `servicefunc`
--
ALTER TABLE `servicefunc`
  ADD PRIMARY KEY (`type_id`,`description`),
  ADD KEY `FK_SERVICEFUNC_SERVICE` (`description`);

--
-- Chỉ mục cho bảng `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`ticket_id`),
  ADD KEY `FK_TICKET_TYPE1` (`type_id`),
  ADD KEY `FK_TICKET_BILL` (`bill_id`),
  ADD KEY `FK_TICKET_FLIGHT` (`flight_id`);

--
-- Chỉ mục cho bảng `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`type_id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `FK_BILL_CUSTOMER` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_BILL_EMPLOYEE` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE SET NULL ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `detailbill`
--
ALTER TABLE `detailbill`
  ADD CONSTRAINT `FK_DETAILBILL_TICKET` FOREIGN KEY (`type_id`) REFERENCES `ticket` (`ticket_id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `flight`
--
ALTER TABLE `flight`
  ADD CONSTRAINT `FK_FLIGHT_FLIGHTSCHEDULE` FOREIGN KEY (`flight_schedule_id`) REFERENCES `flightschedule` (`flight_schedule_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_FLIGHT_PLANE` FOREIGN KEY (`plane_id`) REFERENCES `plane` (`plane_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_FLIGHT_ROUTE` FOREIGN KEY (`route_id`) REFERENCES `route` (`route_id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `flightschedule`
--
ALTER TABLE `flightschedule`
  ADD CONSTRAINT `FK_FLIGHTSCHEDULE_AIRPORT` FOREIGN KEY (`departureAirport`) REFERENCES `airport` (`airport_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_FLIGHTSCHEDULE_AIRPORT1` FOREIGN KEY (`arrivalAirport`) REFERENCES `airport` (`airport_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `plane`
--
ALTER TABLE `plane`
  ADD CONSTRAINT `FK_PLANE_AIRPLANE` FOREIGN KEY (`airplane_id`) REFERENCES `airplane` (`airplane_id`) ON DELETE SET NULL ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `rolefunc`
--
ALTER TABLE `rolefunc`
  ADD CONSTRAINT `FK_ROLEFUNC_FUNTIONS` FOREIGN KEY (`function_id`) REFERENCES `funtions` (`function_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_ROLEFUNC_ROLES` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `servicefunc`
--
ALTER TABLE `servicefunc`
  ADD CONSTRAINT `FK_SERVICEFUNC_SERVICE` FOREIGN KEY (`description`) REFERENCES `service` (`description`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_SERVICEFUNC_TYPE` FOREIGN KEY (`type_id`) REFERENCES `type` (`type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK_TICKET_BILL` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`bill_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_TICKET_FLIGHT` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`flight_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_TICKET_TYPE1` FOREIGN KEY (`type_id`) REFERENCES `type` (`type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
