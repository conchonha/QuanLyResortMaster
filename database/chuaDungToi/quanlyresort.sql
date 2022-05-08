-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 22, 2020 lúc 02:21 PM
-- Phiên bản máy phục vụ: 10.1.38-MariaDB
-- Phiên bản PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlyresort`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dangkydichvu`
--

CREATE TABLE `dangkydichvu` (
  `MATK` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `MADV` char(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `dangkydichvu`
--

INSERT INTO `dangkydichvu` (`MATK`, `MADV`) VALUES
('TK0', 'DV1'),
('TK0', 'DV2'),
('TK1', 'DV2'),
('TK2', 'DV0'),
('TK2', 'DV3');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dichvu`
--

CREATE TABLE `dichvu` (
  `MADV` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `TENDV` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MOTA` text COLLATE utf8_unicode_ci,
  `GIA` float(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `dichvu`
--

INSERT INTO `dichvu` (`MADV`, `TENDV`, `MOTA`, `GIA`) VALUES
('DV0', 'massage cùng mozart', 'Nơi bạn được thư giãn cả gân cốt lẫn tinh thần với dòng âm nhạc cổ điển do nhà thiên tài âm nhạc - Mozart sáng tác.\r\nTọa lạc ở khu M, với gần 12 phòng, với không gian thoáng đãng, gần gũi thiên nhiên, hoặc không gian ấm cúng như được ở nhà.\r\nLàm việc từ 8:00 giờ đến 20:00, cả tuần.\r\n', 25000.00),
('DV1', 'karaoke hết chỗ chê', 'Nơi bạn được thõa sức theo đuổi niềm đam mê âm nhạc của mình.\r\nCó đủ loại nhạc, từ trữ tình đến rock’n’roll.\r\nCó thể mang đồ ăn thức uống vào.\r\nTọa lạc ở tầng hầm khu B và C, với 21 phòng, trang bị dàn karaoke đạt chuẩn quốc tế.\r\nLàm việc từ 14:00 đến 23:59, cả tuần.\r\n', 15000.00),
('DV2', 'phòng gym đạt chuẩn chuyên nghiệp', 'Môi trường sạch sẽ, chuyên nghiệp và có huấn luyện viên riêng.\r\nTọa lạc ở công viên Khu G và phòng A200, trang bị đầy đủ các thiết bị, máy tập thể lực cùng dụng cụ y tế,… \r\nLàm việc từ 6:00 giờ đến 18:00, cả tuần.\r\n', 26000.00),
('DV3', 'xông hơi với thảo dược', 'Thảo dược nhập khẩu từ châu âu, giúp gìn giữ vẻ đẹp tươi trẻ, phới phới như tuổi 20.\r\nTọa lạc ở lầu 2 Tòa B, với gần 30 phòng.\r\nLàm việc từ 8:00 tới 20:00 , cả tuần.\r\n', 28000.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dichvu_co_keyword`
--

CREATE TABLE `dichvu_co_keyword` (
  `KEYWORD` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MADV` char(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `dichvu_co_keyword`
--

INSERT INTO `dichvu_co_keyword` (`KEYWORD`, `MADV`) VALUES
('ăn uống', 'DV1'),
('gym', 'DV2'),
('hát hò', 'DV1'),
('karaoke', 'DV1'),
('massage', 'DV0'),
('nóng', 'DV3'),
('spa', 'DV0'),
('spa', 'DV3'),
('sức khỏe', 'DV0'),
('sức khỏe', 'DV2'),
('sức khỏe', 'DV3'),
('tắm', 'DV3'),
('thảo dược', 'DV0'),
('thảo dược', 'DV3'),
('thể thao', 'DV2'),
('thư giãn', 'DV3'),
('thuể', 'DV1'),
('thuể', 'DV2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dukhach`
--

CREATE TABLE `dukhach` (
  `MADK` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `MATK` char(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MADKDAIDIEN` char(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HOTENDK` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SDT` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NGAYSINH` date DEFAULT NULL,
  `CMND` char(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `dukhach`
--

INSERT INTO `dukhach` (`MADK`, `MATK`, `MADKDAIDIEN`, `HOTENDK`, `SDT`, `NGAYSINH`, `CMND`) VALUES
('DK0', 'TK0', NULL, 'Trần Văn Luân', '0964985615', '2000-05-29', '164985364'),
('DK1', NULL, 'DK0', 'Nguyễn Văn A', '9468531594', '2000-02-22', '649853159'),
('DK2', 'TK2', 'DK0', 'Nguyễn Thị B', '0985643582', '2001-12-10', '164852349'),
('DK3', NULL, 'DK0', 'Nguễn Văn C', '0946853168', '2010-02-22', NULL),
('DK4', 'TK1', NULL, 'Trần Bình Tiến', '0946853589', '2002-02-24', '159468534');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `keyword`
--

CREATE TABLE `keyword` (
  `KEYWORD` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `keyword`
--

INSERT INTO `keyword` (`KEYWORD`) VALUES
('ăn uống'),
('áo'),
('bơi'),
('dù'),
('gym'),
('hát hò'),
('karaoke'),
('lửa trại'),
('massage'),
('nóng'),
('quần'),
('spa'),
('sức khỏe'),
('tắm'),
('thảo dược'),
('thể thao'),
('thư giãn'),
('thuể'),
('thuyền');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaiphong`
--

CREATE TABLE `loaiphong` (
  `MALP` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `TENLP` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaiphong`
--

INSERT INTO `loaiphong` (`MALP`, `TENLP`) VALUES
('LP0', 'Standard'),
('LP1', 'Deluxe'),
('LP2', 'Superio');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phong`
--

CREATE TABLE `phong` (
  `MAP` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `MALP` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `MADK` char(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SOP` int(11) DEFAULT NULL,
  `LAU` int(11) DEFAULT NULL,
  `NGAYDEN` date DEFAULT NULL,
  `NGAYDI` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phong`
--

INSERT INTO `phong` (`MAP`, `MALP`, `MADK`, `SOP`, `LAU`, `NGAYDEN`, `NGAYDI`) VALUES
('A200', 'LP0', NULL, 0, 2, NULL, NULL),
('A201', 'LP0', NULL, 1, 2, NULL, NULL),
('A202', 'LP0', NULL, 2, 2, NULL, NULL),
('A300', 'LP0', NULL, 0, 3, NULL, NULL),
('A301', 'LP0', NULL, 1, 3, NULL, NULL),
('A302', 'LP0', NULL, 2, 3, NULL, NULL),
('B200', 'LP1', 'DK0', 0, 2, '2020-04-19', '2020-04-30'),
('B201', 'LP1', NULL, 1, 2, NULL, NULL),
('B202', 'LP1', 'DK0', 2, 2, '2020-04-22', '2020-04-30'),
('C200', 'LP2', NULL, 0, 2, NULL, NULL),
('C300', 'LP2', NULL, 0, 3, NULL, NULL),
('C400', 'LP2', 'DK4', 0, 4, '2020-05-07', '2020-05-22');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MATK` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `MADK` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `EMAILTK` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MATKHAUTK` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MATHENGANHANG` char(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`MATK`, `MADK`, `EMAILTK`, `MATKHAUTK`, `MATHENGANHANG`) VALUES
('TK0', 'DK0', 'TranVanLuan@gmail.com', '123', '123456789'),
('TK1', 'DK4', 'TranBinhTien@gmail.com', '321', '987654321'),
('TK2', 'DK2', 'NguyenThiB@gmail.com', '000', '741258963');

-- --------------------------------------------------------

--
-- Cấu trúc đóng vai cho view `userview`
-- (See below for the actual view)
--
CREATE TABLE `userview` (
`MATK` char(5)
,`EMAILTK` varchar(80)
,`MATKHAUTK` varchar(80)
,`MATHENGANHANG` char(20)
,`MADK` char(5)
,`MADKDAIDIEN` char(5)
,`HOTENDK` varchar(80)
,`SDT` char(20)
,`NGAYSINH` date
,`CMND` char(20)
);

-- --------------------------------------------------------

--
-- Cấu trúc cho view `userview`
--
DROP TABLE IF EXISTS `userview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`` SQL SECURITY DEFINER VIEW `userview`  AS  select `tk`.`MATK` AS `MATK`,`tk`.`EMAILTK` AS `EMAILTK`,`tk`.`MATKHAUTK` AS `MATKHAUTK`,`tk`.`MATHENGANHANG` AS `MATHENGANHANG`,`dk`.`MADK` AS `MADK`,`dk`.`MADKDAIDIEN` AS `MADKDAIDIEN`,`dk`.`HOTENDK` AS `HOTENDK`,`dk`.`SDT` AS `SDT`,`dk`.`NGAYSINH` AS `NGAYSINH`,`dk`.`CMND` AS `CMND` from (`taikhoan` `tk` join `dukhach` `dk` on((`dk`.`MATK` = `tk`.`MATK`))) ;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `dangkydichvu`
--
ALTER TABLE `dangkydichvu`
  ADD PRIMARY KEY (`MATK`,`MADV`),
  ADD KEY `FK_DANGKYDICHVU2` (`MADV`);

--
-- Chỉ mục cho bảng `dichvu`
--
ALTER TABLE `dichvu`
  ADD PRIMARY KEY (`MADV`);

--
-- Chỉ mục cho bảng `dichvu_co_keyword`
--
ALTER TABLE `dichvu_co_keyword`
  ADD PRIMARY KEY (`KEYWORD`,`MADV`),
  ADD KEY `FK_DICHVU_CO_KEYWORD2` (`MADV`);

--
-- Chỉ mục cho bảng `dukhach`
--
ALTER TABLE `dukhach`
  ADD PRIMARY KEY (`MADK`),
  ADD UNIQUE KEY `CMND` (`CMND`),
  ADD KEY `FK_CO_3` (`MATK`),
  ADD KEY `FK_DAIDIEN` (`MADKDAIDIEN`);

--
-- Chỉ mục cho bảng `keyword`
--
ALTER TABLE `keyword`
  ADD PRIMARY KEY (`KEYWORD`);

--
-- Chỉ mục cho bảng `loaiphong`
--
ALTER TABLE `loaiphong`
  ADD PRIMARY KEY (`MALP`);

--
-- Chỉ mục cho bảng `phong`
--
ALTER TABLE `phong`
  ADD PRIMARY KEY (`MAP`),
  ADD KEY `FK_DATPHONG` (`MADK`),
  ADD KEY `FK_RELATIONSHIP_5` (`MALP`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MATK`),
  ADD UNIQUE KEY `MATHENGANHANG` (`MATHENGANHANG`),
  ADD KEY `FK_CO_2` (`MADK`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `dangkydichvu`
--
ALTER TABLE `dangkydichvu`
  ADD CONSTRAINT `FK_DANGKYDICHVU` FOREIGN KEY (`MATK`) REFERENCES `taikhoan` (`MATK`),
  ADD CONSTRAINT `FK_DANGKYDICHVU2` FOREIGN KEY (`MADV`) REFERENCES `dichvu` (`MADV`);

--
-- Các ràng buộc cho bảng `dichvu_co_keyword`
--
ALTER TABLE `dichvu_co_keyword`
  ADD CONSTRAINT `FK_DICHVU_CO_KEYWORD` FOREIGN KEY (`KEYWORD`) REFERENCES `keyword` (`KEYWORD`),
  ADD CONSTRAINT `FK_DICHVU_CO_KEYWORD2` FOREIGN KEY (`MADV`) REFERENCES `dichvu` (`MADV`);

--
-- Các ràng buộc cho bảng `dukhach`
--
ALTER TABLE `dukhach`
  ADD CONSTRAINT `FK_CO_3` FOREIGN KEY (`MATK`) REFERENCES `taikhoan` (`MATK`),
  ADD CONSTRAINT `FK_DAIDIEN` FOREIGN KEY (`MADKDAIDIEN`) REFERENCES `dukhach` (`MADK`);

--
-- Các ràng buộc cho bảng `phong`
--
ALTER TABLE `phong`
  ADD CONSTRAINT `FK_DATPHONG` FOREIGN KEY (`MADK`) REFERENCES `dukhach` (`MADK`),
  ADD CONSTRAINT `FK_RELATIONSHIP_5` FOREIGN KEY (`MALP`) REFERENCES `loaiphong` (`MALP`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_CO_2` FOREIGN KEY (`MADK`) REFERENCES `dukhach` (`MADK`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
