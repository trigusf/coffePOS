-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2026 at 03:37 PM
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
-- Database: `db_coffepos`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaction`
--

CREATE TABLE `detail_transaction` (
  `id_detail_transaction` int(11) NOT NULL,
  `id_transaction` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `harga` int(255) NOT NULL,
  `qty` int(255) NOT NULL,
  `subtotal` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_transaction`
--

INSERT INTO `detail_transaction` (`id_detail_transaction`, `id_transaction`, `id_product`, `harga`, `qty`, `subtotal`) VALUES
(42, 19, 1, 20000, 1, 20000),
(43, 19, 4, 28000, 1, 28000),
(44, 19, 5, 23000, 1, 23000);

-- --------------------------------------------------------

--
-- Table structure for table `level`
--

CREATE TABLE `level` (
  `id_level` int(11) NOT NULL,
  `level` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `level`
--

INSERT INTO `level` (`id_level`, `level`) VALUES
(1, 'admin'),
(2, 'kasir');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id_product` int(11) NOT NULL,
  `nama_product` varchar(255) NOT NULL,
  `harga_product` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `category` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id_product`, `nama_product`, `harga_product`, `stock`, `category`) VALUES
(1, 'americano', 20000, 15, 'Coffe'),
(4, 'moca latte', 28000, 15, 'Coffe'),
(5, 'peach americano', 23000, 15, 'Coffe');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id_transaction` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `total` int(255) NOT NULL,
  `bayar` int(255) NOT NULL,
  `kembali` int(255) NOT NULL,
  `tgl_transaction` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id_transaction`, `id_user`, `total`, `bayar`, `kembali`, `tgl_transaction`) VALUES
(19, 1, 71000, 80000, 9000, '2026-06-08');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `id_level` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `id_level`, `username`, `password`) VALUES
(1, 1, 'boni', '123'),
(2, 2, 'roni', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_transaction`
--
ALTER TABLE `detail_transaction`
  ADD PRIMARY KEY (`id_detail_transaction`),
  ADD UNIQUE KEY `id_transaction` (`id_transaction`,`id_product`),
  ADD KEY `id_product` (`id_product`);

--
-- Indexes for table `level`
--
ALTER TABLE `level`
  ADD PRIMARY KEY (`id_level`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id_product`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id_transaction`),
  ADD UNIQUE KEY `id_user` (`id_user`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `id_level` (`id_level`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_transaction`
--
ALTER TABLE `detail_transaction`
  MODIFY `id_detail_transaction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `level`
--
ALTER TABLE `level`
  MODIFY `id_level` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id_product` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id_transaction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_transaction`
--
ALTER TABLE `detail_transaction`
  ADD CONSTRAINT `detail_transaction_ibfk_1` FOREIGN KEY (`id_transaction`) REFERENCES `transaction` (`id_transaction`),
  ADD CONSTRAINT `detail_transaction_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`id_level`) REFERENCES `level` (`id_level`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
