-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 14, 2020 lúc 07:09 PM
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
('TK1', 'DV5'),
('TK1', 'DV7'),
('TK2', 'DV0'),
('TK2', 'DV3'),
('TK2', 'DV7'),
('TK2', 'DV8'),
('TK5', 'DV1'),
('TK5', 'DV10'),
('TK5', 'DV4'),
('TK5', 'DV8');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `datphong`
--

CREATE TABLE `datphong` (
  `MAP` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `MADK` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `NGAYDEN` date DEFAULT NULL,
  `NGAYDI` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `datphong`
--

INSERT INTO `datphong` (`MAP`, `MADK`, `NGAYDEN`, `NGAYDI`) VALUES
('A200', 'DK6', '2021-03-20', '2021-03-29'),
('A201', 'DK29', '2021-01-05', '2021-01-14'),
('A201', 'DK8', '2021-02-15', '2021-02-28'),
('A202', 'DK7', '2021-01-10', '2021-01-14'),
('A300', 'DK10', '2021-01-20', '2021-02-19'),
('A300', 'DK25', '2021-01-01', '2021-01-09'),
('A300', 'DK31', '2021-01-10', '2021-01-14'),
('A300', 'DK9', '2021-03-05', '2021-03-19'),
('A301', 'DK29', '2021-01-05', '2021-01-14'),
('A302', 'DK11', '2021-01-20', '2021-01-29'),
('A302', 'DK21', '2021-01-01', '2021-01-19'),
('B200', 'DK0', '2020-04-19', '2020-04-30'),
('B200', 'DK12', '2021-02-20', '2021-03-14'),
('B200', 'DK24', '2021-01-01', '2021-01-14'),
('B201', 'DK13', '2021-01-01', '2021-01-09'),
('B201', 'DK31', '2021-01-10', '2021-01-14'),
('B201', 'DK4', '2020-05-07', '2020-05-22'),
('B202', 'DK0', '2020-04-19', '2020-04-30'),
('B202', 'DK14', '2021-02-05', '2021-02-19'),
('B202', 'DK21', '2021-01-01', '2021-01-19'),
('B202', 'DK5', '2020-12-12', '2021-12-28'),
('C200', 'DK15', '2021-01-05', '2021-01-19'),
('C300', 'DK25', '2021-01-01', '2021-01-09'),
('C300', 'DK28', '2021-01-10', '2021-01-19'),
('C400', 'DK20', '2021-01-01', '2021-01-19'),
('C400', 'DK4', '2020-05-07', '2020-05-22');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dichvu`
--

CREATE TABLE `dichvu` (
  `MADV` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `TENDV` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MOTA` text COLLATE utf8_unicode_ci,
  `GIA` float(8,2) DEFAULT NULL,
  `TENHINH` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `PHAIDANGKI` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `dichvu`
--

INSERT INTO `dichvu` (`MADV`, `TENDV`, `MOTA`, `GIA`, `TENHINH`, `PHAIDANGKI`) VALUES
('DV0', 'massage cùng mozart', 'Nơi bạn được thư giãn cả gân cốt lẫn tinh thần với dòng âm nhạc cổ điển do nhà thiên tài âm nhạc - Mozart sáng tác.\r\nTọa lạc ở khu M, với gần 12 phòng, với không gian thoáng đãng, gần gũi thiên nhiên, hoặc không gian ấm cúng như được ở nhà.\r\nLàm việc từ 8:00 giờ đến 20:00, cả tuần.\r\n', 25000.00, 'massage', 1),
('DV1', 'karaoke hết chỗ chê', 'Nơi bạn được thõa sức theo đuổi niềm đam mê âm nhạc của mình.\r\nCó đủ loại nhạc, từ trữ tình đến rock’n’roll.\r\nCó thể mang đồ ăn thức uống vào.\r\nTọa lạc ở tầng hầm khu B và C, với 21 phòng, trang bị dàn karaoke đạt chuẩn quốc tế.\r\nLàm việc từ 14:00 đến 23:59, cả tuần.\r\n', 15000.00, 'karaoke', 1),
('DV10', 'Hội họp, văn phòng', 'Đi kèm với nhà hàng là dịch vụ phòng họp, hội trường để tổ chức các sự kiện như sinh nhật, đám cưới, event công ty, hội thảo…\r\nCác phòng họp, sự kiện đi kèm trong khách sạn có quy mô từ nhỏ từ vài chục người đến vài trăm người. Ngoài ra ở đây còn được trang bị bàn ghế, âm thanh, ánh sáng để đảm bảo sự kiện diễn ra tốt đẹp.', 15000.00, 'van_phong', 1),
('DV2', 'phòng gym đạt chuẩn chuyên nghiệp', 'Môi trường sạch sẽ, chuyên nghiệp và có huấn luyện viên riêng.\r\nTọa lạc ở công viên Khu G và phòng A200, trang bị đầy đủ các thiết bị, máy tập thể lực cùng dụng cụ y tế,… \r\nLàm việc từ 6:00 giờ đến 18:00, cả tuần.\r\n', 26000.00, 'gym', 1),
('DV3', 'xông hơi với thảo dược', 'Thảo dược nhập khẩu từ châu âu, giúp gìn giữ vẻ đẹp tươi trẻ, phới phới như tuổi 20.\r\nTọa lạc ở lầu 2 Tòa B, với gần 30 phòng.\r\nLàm việc từ 8:00 tới 20:00 , cả tuần.\r\n', 28000.00, 'xong_hoi', 1),
('DV4', 'Trông Trẻ', 'Bạn có thắc mắc khách sạn vì sao lại mở dịch vụ giữ trẻ không? Đó là bởi vì rất nhiều gia đình đi du lịch hoặc công tác dẫn theo con trẻ. Tuy nhiên vì bận họp hoặc đến những nơi không tiện cho trẻ em, hoặc spa, làm đẹp… thì sẽ cần đến dịch vụ này.\r\nTại đây, trẻ sẽ được trông coi, vui chơi rất hiện đại. Hoặc những thời gian rảnh, bạn có thể cho trẻ xuống khu vực này để tham gia các trò chơi cũng rất hợp lý.', 10000.00, 'trong_tre', 1),
('DV5', 'Thể Thao', 'đây là các môn thể thao dành cho giới thượng lưu và đây là cơ hội để bạn có thể trải nghiệp nó. Bạn sẽ bắt gặp các sân golf với diện tích lớn tích hợp trong khách sạn này\r\nCả tennis và golf, đều sẽ có những huấn luyện viên cực kỳ nhiệt tình,thiết bị hiện đại đảm bảo cuộc chơi của bạn sẽ thêm phần thú vị', 20000.00, 'golf', 1),
('DV6', 'Thu đổi ngoại tệ', 'Thu đổi ngoại tệ\', \'Trao đổi các loại tiền tệ nước ngoài\r\nGiao dịch thu mua nhiều loại ngoại tệ mạnh: USD, EUR, GBP, CAD, HKD, SGD, JPY.', 1000.00, 'thu_doi_ngoai_te', 0),
('DV7', 'Buffet', 'Cung cấp các bữa tiệc buffet theo chủ đề mỗi buổi tối trong tuần\r\nTại quầy buffet, có rất nhiều món cho thực khách chọn lựa như: các loại panchan độc đáo phong cách Hàn Quốc; các loại salad rất tốt cho sức khỏe; khu đồ nóng với cơm, súp truyền thống; quầy đồ Nhật với “thuyền” sushi và sashimi và các món tráng miệng như kem tươi, chè và hoa quả theo mùa.\r\nNếu thực khách muốn được phục vụ tại bàn, nhà hàng luôn sẵn sàng giúp đỡ, tạo điều kiện để mỗi khách đều cảm thấy thỏa mãn, vừa dùng những món ăn yêu thích ngon lành vừa thoải mái “tám” chuyện với bạn bè, người thân hay đối tác.', 25000.00, 'buffet', 1),
('DV8', 'Casino', 'Ở một số khách sạn cao cấp sẽ có khu phức hợp vui chơi giải trí, trong đó bao gồm dịch vụ casino. Tại đây du khách sẽ được tham gia nhiều trò chơi mang tính giải trí cao, đủ các thể loại trò chơi từ trong nước đến quốc tế.', 10000.00, 'casino', 1),
('DV9', 'Room', 'Tại khách sạn, phòng của bạn sẽ có người dọn dẹp, thay chăn ga, khăn tắm và quét lau sạch sẽ mỗi khi bạn ra ngoài, tạo cảm giác tiện nghi và thoải mái nhất cho khách. Đây là dịch vụ miễn phí, bạn không cần phải trả tiền.\r\nĐối với những khách ở lâu ngày hoặc “trưng diện” nhiều thì chắc chắn bạn sẽ cần đến dịch vụ giặt ủi, bởi lẽ việc tự giặt ủi ở khách sạn là rất khó khăn. Dịch vụ này được phục vụ rất nhanh chóng, sạch sẽ, thơm tho lại ủi phẳng đồ, giao tận phòng.', 0.00, 'don_dep_phong', 0);

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
('ăn uống', 'DV7'),
('buffet', 'DV7'),
('casino', 'DV8'),
('dọn dẹp', 'DV9'),
('giặt ủi', 'DV9'),
('goft', 'DV5'),
('gym', 'DV2'),
('hát hò', 'DV1'),
('karaoke', 'DV1'),
('massage', 'DV0'),
('Máy in', 'DV10'),
('Máy tính kết nối Internet', 'DV10'),
('nóng', 'DV3'),
('spa', 'DV0'),
('spa', 'DV3'),
('sức khỏe', 'DV0'),
('sức khỏe', 'DV2'),
('sức khỏe', 'DV3'),
('tắm', 'DV3'),
('tennis', 'DV5'),
('thảo dược', 'DV0'),
('thảo dược', 'DV3'),
('thể thao', 'DV2'),
('thu đổi ngoại tệ', 'DV6'),
('thư giãn', 'DV3'),
('thuê', 'DV1'),
('thuê', 'DV2'),
('trông trẻ', 'DV4');

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
('DK10', NULL, NULL, 'Bùi Hoàng V', '0986587856', '1999-07-17', '015874252'),
('DK11', NULL, NULL, 'Phạm Vũ Hà', '0985632584', '1999-05-09', '012546874'),
('DK12', NULL, NULL, 'Phạm Phong Quá', '0986584579', '1996-07-20', '569874123'),
('DK13', NULL, NULL, 'Nguyễn Vũ Linh', '0985632131', '1998-07-09', '0451236541'),
('DK14', NULL, NULL, 'Hoàng Văn Pha', '0986584569', '1997-07-15', '188795684'),
('DK15', NULL, NULL, 'Hạ Vũ T', '0985647856', '1998-07-19', '0451230145'),
('DK16', NULL, 'DK4', 'Mai Văn Đạt', '0956784545', '1995-07-13', '012345876'),
('DK17', NULL, 'DK4', 'Mai Thị Thủy', '0987456211', '2011-09-07', NULL),
('DK18', NULL, 'DK4', 'Mai Hà Giang', '0941576232', '1999-07-15', '017896320'),
('DK19', NULL, 'DK4', 'Mai Hà Nam', '0985014523', '2010-10-11', NULL),
('DK2', 'TK2', 'DK0', 'Nguyễn Thị B', '0985643582', '2001-12-10', '164852349'),
('DK20', NULL, NULL, 'Đào Thanh M', '0986502047', '1996-05-03', '0123456708'),
('DK21', NULL, NULL, 'Trần Thị Nga', '0939856445', '1998-02-28', '999856854'),
('DK22', NULL, 'DK21', 'Nuyễn Văn Bé', NULL, '2008-01-01', NULL),
('DK23', NULL, 'DK21', 'Trần Thị Tí', '0953285665', '2010-01-04', NULL),
('DK24', NULL, NULL, 'Tran Nhan Van', '0398564521', '2000-05-20', '0314684595'),
('DK25', 'TK5', NULL, 'Tran Thi Thu', '0315685348', '1918-05-30', '16497522462'),
('DK26', NULL, 'DK25', 'Nguyen Van Mot', NULL, '2013-05-10', NULL),
('DK27', NULL, 'DK25', 'TranThi Hai', '154654515', '2000-05-07', '415425454635'),
('DK28', NULL, NULL, 'Tran Thi Mau', '21564641546', '1999-07-17', '4524584187'),
('DK29', 'TK6', NULL, 'Nguyen Thi Anh', '1458146487', '1995-02-24', '484841861'),
('DK3', NULL, 'DK0', 'Nguễn Văn C', '0946853168', '2010-02-22', NULL),
('DK30', NULL, 'DK29', 'Tran Van Tu', '47545841587', '2014-03-13', NULL),
('DK31', NULL, NULL, 'Nguyen Thi Thu', '1754548455', '1999-08-21', '4458448744'),
('DK32', NULL, 'DK31', 'Ngan Thi Thao', NULL, '2012-05-14', NULL),
('DK4', 'TK1', NULL, 'Trần Bình Tiến', '0946853589', '2002-02-24', '159468534'),
('DK5', NULL, NULL, 'Đào Thị Hoa', '0946853589', '1988-11-05', '031598756'),
('DK6', 'TK3', NULL, 'Nguyễn Văn Hạ', '0986584578', '1999-07-15', '056987525'),
('DK7', NULL, NULL, 'Đào Hạ Phong', '0985632145', '1997-06-09', '025463145'),
('DK8', 'TK4', NULL, 'Trần Văn Tý', '0986584545', '1999-07-15', '015487251'),
('DK9', NULL, NULL, 'Phạm Thị T', '0985632189', '1997-02-02', '518445463');

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
('buffet'),
('casino'),
('dọn dẹp'),
('dù'),
('giặt ủi'),
('goft'),
('gym'),
('hát hò'),
('karaoke'),
('lửa trại'),
('massage'),
('Máy in'),
('Máy tính kết nối Internet'),
('nóng'),
('nước uống'),
('quần'),
('spa'),
('sức khỏe'),
('tắm'),
('tennis'),
('thảo dược'),
('thể thao'),
('thu đổi ngoại tệ'),
('thư giãn'),
('thuê'),
('thuyền'),
('trông trẻ');

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
('LP0', 'standard'),
('LP1', 'deluxe'),
('LP2', 'superio');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phong`
--

CREATE TABLE `phong` (
  `MAP` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `MALP` char(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phong`
--

INSERT INTO `phong` (`MAP`, `MALP`) VALUES
('A200', 'LP0'),
('A201', 'LP0'),
('A202', 'LP0'),
('A300', 'LP0'),
('A301', 'LP0'),
('A302', 'LP0'),
('B200', 'LP1'),
('B201', 'LP1'),
('B202', 'LP1'),
('C200', 'LP2'),
('C300', 'LP2'),
('C400', 'LP2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MATK` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `MADK` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `EMAILTK` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MATKHAUTK` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MATHENGANHANG` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SODUTRONGVI` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`MATK`, `MADK`, `EMAILTK`, `MATKHAUTK`, `MATHENGANHANG`, `SODUTRONGVI`) VALUES
('TK0', 'DK0', 'TranVanLuan@gmail.com', '123', '123456789', 1000000),
('TK1', 'DK4', 'TranBinhTien@gmail.com', '321', '987654321', 75000),
('TK2', 'DK2', 'NguyenThiB@gmail.com', '000', '741258963', 10000),
('TK3', 'DK6', 'NguyenVanHa@gmail.com', '111', '13465829456', 0),
('TK4', 'DK8', 'TranVanTy@gmail.com', '111', '1544468529', 0),
('TK5', 'DK25', 'TrnThiThu@gmail.com', '999', '4154568414518', 0),
('TK6', 'DK29', 'NguyenThiAnh@gmail.com', '111', '54151547454', 35000);

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
-- Chỉ mục cho bảng `datphong`
--
ALTER TABLE `datphong`
  ADD PRIMARY KEY (`MAP`,`MADK`),
  ADD KEY `FK_DATPHONG2` (`MADK`);

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
  ADD KEY `FK_RELATIONSHIP_5` (`MALP`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MATK`),
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
-- Các ràng buộc cho bảng `datphong`
--
ALTER TABLE `datphong`
  ADD CONSTRAINT `FK_DATPHONG` FOREIGN KEY (`MAP`) REFERENCES `phong` (`MAP`),
  ADD CONSTRAINT `FK_DATPHONG2` FOREIGN KEY (`MADK`) REFERENCES `dukhach` (`MADK`);

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
