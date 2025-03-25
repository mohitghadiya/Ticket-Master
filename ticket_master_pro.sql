-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 30, 2024 at 06:30 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ticket_master_pro`
--

-- --------------------------------------------------------

--
-- Table structure for table `all_flight`
--

CREATE TABLE `all_flight` (
  `index_no` int(11) NOT NULL,
  `flight_name` varchar(50) NOT NULL,
  `start_place` varchar(50) NOT NULL,
  `end_place` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `all_flight`
--

INSERT INTO `all_flight` (`index_no`, `flight_name`, `start_place`, `end_place`) VALUES
(1, 'indigo', 'mumbai', 'kolkata'),
(2, 'indigo', 'delhi', 'Madras'),
(3, 'singapore_Go', 'delhi', 'singapore'),
(4, 'air_india', 'surat', 'dubai'),
(5, 'super_surat', 'mumbai', 'surat'),
(6, 'Madras_int', 'delhi', 'Madras');

-- --------------------------------------------------------

--
-- Table structure for table `all_movie`
--

CREATE TABLE `all_movie` (
  `index_no` int(11) NOT NULL,
  `movie_name` varchar(50) NOT NULL,
  `show_time` varchar(50) NOT NULL,
  `screen_no` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `all_movie`
--

INSERT INTO `all_movie` (`index_no`, `movie_name`, `show_time`, `screen_no`) VALUES
(1, 'Fakt Purusho Maate', '07:00 PM', 2),
(2, 'Fakt Purusho Maate', '09:00 PM', 1),
(3, 'Stree 2', '05:00 PM', 1),
(4, 'Stree 2', '11:00 PM', 3),
(5, 'Vedaa', '12:00 AM', 2),
(6, 'Vedaa', '3:15 PM', 2),
(7, 'Deadpool & Wolverine', '11:00 AM', 3),
(8, 'Deadpool & Wolverine', '04:00 PM', 3),
(9, 'Alien: Romulus', '02:00 PM', 4),
(10, 'Alien: Romulus', '05:00 PM', 4);

-- --------------------------------------------------------

--
-- Table structure for table `flight`
--

CREATE TABLE `flight` (
  `index_no` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `flight_name` varchar(50) NOT NULL,
  `start_place` varchar(50) NOT NULL,
  `end_place` varchar(50) NOT NULL,
  `seat` varchar(200) NOT NULL,
  `payment` varchar(50) NOT NULL,
  `date_and_time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flight`
--

INSERT INTO `flight` (`index_no`, `user_name`, `flight_name`, `start_place`, `end_place`, `seat`, `payment`, `date_and_time`) VALUES
(3, 'utsav', 'indigo', 'mumbai', 'kolkata', '21', '10000', '2024-08-29  00:46:54'),
(4, 'utsav', 'indigo', 'mumbai', 'kolkata', '15', '10000', '2024-08-30  00:18:21'),
(5, 'utsav', 'indigo', 'delhi', 'madras', '12', '12000', '2024-08-30  14:52:37'),
(6, 'utsav', 'indigo', 'mumbai', 'kolkata', '62', '10000', '2024-08-30  14:57:58'),
(7, 'utsav', 'indigo', 'mumbai', 'kolkata', '22', '10000', '2024-08-30  20:29:54'),
(8, 'utsav', 'indigo', 'mumbai', 'kolkata', '23', '70000', '2024-08-30  20:32:02'),
(9, 'utsav', 'indigo', 'mumbai', 'kolkata', '91', '70000', '2024-08-30  20:32:02'),
(10, 'utsav', 'indigo', 'mumbai', 'kolkata', '72', '70000', '2024-08-30  20:32:02'),
(11, 'utsav', 'indigo', 'mumbai', 'kolkata', '73', '70000', '2024-08-30  20:32:02'),
(12, 'utsav', 'indigo', 'mumbai', 'kolkata', '74', '70000', '2024-08-30  20:32:02'),
(13, 'utsav', 'indigo', 'mumbai', 'kolkata', '75', '70000', '2024-08-30  20:32:02'),
(14, 'utsav', 'indigo', 'mumbai', 'kolkata', '71', '70000', '2024-08-30  20:32:02'),
(15, 'utsav', 'singapore_Go', 'delhi', 'singapore', '11', '60000', '2024-08-30  20:34:50'),
(16, 'utsav', 'indigo', 'delhi', 'madras', '33', '12000', '2024-08-30  20:35:12'),
(17, 'utsav', 'indigo', 'mumbai', 'kolkata', '25', '10000', '2024-08-30  21:42:57'),
(18, 'utsav', 'air_india', 'surat', 'dubai', '11', '30000', '2024-08-30  21:44:47'),
(19, 'utsav', 'singapore_Go', 'delhi', 'singapore', '32', '60000', '2024-08-30  21:46:02'),
(20, 'utsav', 'singapore_Go', 'delhi', 'singapore', '22', '60000', '2024-08-30  21:49:09');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `index_no` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `movie_name` varchar(50) NOT NULL,
  `show_time` varchar(50) NOT NULL,
  `screen_no` int(11) NOT NULL,
  `seat` varchar(200) NOT NULL,
  `payment` varchar(50) NOT NULL,
  `date_and_time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`index_no`, `user_name`, `movie_name`, `show_time`, `screen_no`, `seat`, `payment`, `date_and_time`) VALUES
(1, 'utsav', 'Fakt Purusho Maate', '07:00 PM', 2, '11', '', '2024-08-28  18:10:58'),
(3, 'utsav', 'Vedaa', '3:15 PM', 2, '23', '', '2024-08-28  18:22:33'),
(4, 'utsav', 'Fakt Purusho Maate', '07:00 PM', 2, '23', '', '2024-08-28  18:47:07'),
(5, 'utsav', 'Fakt Purusho Maate', '09:00 PM', 1, '11', '300', '2024-08-28  20:57:31'),
(6, 'utsav', 'Fakt Purusho Maate', '09:00 PM', 1, '12', '600', '2024-08-28  20:57:43'),
(7, 'utsav', 'Fakt Purusho Maate', '09:00 PM', 1, '13', '600', '2024-08-28  20:57:43'),
(8, 'utsav', 'Vedaa', '3:15 PM', 2, '11', '350', '2024-08-29  00:45:55'),
(9, 'utsav', 'Fakt Purusho Maate', '09:00 PM', 1, '51', '300', '2024-08-30  18:24:41'),
(10, 'utsav', 'Stree 2', '05:00 PM', 1, '11', '300', '2024-08-30  18:25:04'),
(11, 'utsav', 'Stree 2', '05:00 PM', 1, '22', '300', '2024-08-30  18:33:43');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `index_no` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `device_id` varchar(100) NOT NULL,
  `date_and_time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`index_no`, `user_name`, `phone_number`, `device_id`, `date_and_time`) VALUES
(15, 'utsav', '9023570094', 'e74d4139e1e894d0b9aa4d1ed2762703a2690ed6e368292b91794d80e55b3596', '2024-08-30  18:33:19');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `all_flight`
--
ALTER TABLE `all_flight`
  ADD PRIMARY KEY (`index_no`);

--
-- Indexes for table `all_movie`
--
ALTER TABLE `all_movie`
  ADD PRIMARY KEY (`index_no`);

--
-- Indexes for table `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`index_no`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`index_no`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`index_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `all_flight`
--
ALTER TABLE `all_flight`
  MODIFY `index_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `all_movie`
--
ALTER TABLE `all_movie`
  MODIFY `index_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `flight`
--
ALTER TABLE `flight`
  MODIFY `index_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `index_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `index_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
