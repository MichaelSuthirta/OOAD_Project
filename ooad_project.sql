-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2025 at 11:22 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ooad_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart_items`
--

CREATE TABLE `cart_items` (
  `idCustomer` int(11) DEFAULT NULL,
  `idProduct` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `deliveries`
--

CREATE TABLE `deliveries` (
  `idProduct` int(11) DEFAULT NULL,
  `idCourier` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `idOrder` int(11) DEFAULT NULL,
  `idProduct` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_headers`
--

CREATE TABLE `order_headers` (
  `idOrder` int(11) NOT NULL,
  `idCustomer` int(11) DEFAULT NULL,
  `idPromo` int(11) DEFAULT NULL,
  `orderStatus` varchar(20) DEFAULT NULL,
  `orderedAt` date DEFAULT NULL,
  `totalAmount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `idProduct` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promo`
--

CREATE TABLE `promo` (
  `idPromo` int(11) NOT NULL,
  `code` varchar(30) DEFAULT NULL,
  `headline` varchar(75) DEFAULT NULL,
  `discountPercentage` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `idUser` int(11) NOT NULL,
  `fullName` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `gender` varchar(12) DEFAULT NULL,
  `role` varchar(10) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `emergencyContact` varchar(20) DEFAULT NULL,
  `vehicleType` varchar(30) DEFAULT NULL,
  `vehiclePlate` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD KEY `idProduct` (`idProduct`),
  ADD KEY `idCustomer` (`idCustomer`);

--
-- Indexes for table `deliveries`
--
ALTER TABLE `deliveries`
  ADD KEY `idProduct` (`idProduct`),
  ADD KEY `idCourier` (`idCourier`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD KEY `idOrder` (`idOrder`),
  ADD KEY `idProduct` (`idProduct`);

--
-- Indexes for table `order_headers`
--
ALTER TABLE `order_headers`
  ADD PRIMARY KEY (`idOrder`),
  ADD KEY `idCustomer` (`idCustomer`),
  ADD KEY `idPromo` (`idPromo`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`idProduct`);

--
-- Indexes for table `promo`
--
ALTER TABLE `promo`
  ADD PRIMARY KEY (`idPromo`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order_headers`
--
ALTER TABLE `order_headers`
  MODIFY `idOrder` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `idProduct` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `promo`
--
ALTER TABLE `promo`
  MODIFY `idPromo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`),
  ADD CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`idCustomer`) REFERENCES `users` (`idUser`);

--
-- Constraints for table `deliveries`
--
ALTER TABLE `deliveries`
  ADD CONSTRAINT `deliveries_ibfk_1` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`),
  ADD CONSTRAINT `deliveries_ibfk_2` FOREIGN KEY (`idCourier`) REFERENCES `users` (`idUser`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`idOrder`) REFERENCES `order_headers` (`idOrder`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`);

--
-- Constraints for table `order_headers`
--
ALTER TABLE `order_headers`
  ADD CONSTRAINT `order_headers_ibfk_1` FOREIGN KEY (`idCustomer`) REFERENCES `users` (`idUser`),
  ADD CONSTRAINT `order_headers_ibfk_2` FOREIGN KEY (`idPromo`) REFERENCES `promo` (`idPromo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
