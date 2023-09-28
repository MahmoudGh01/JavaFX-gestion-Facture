-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 20, 2023 at 03:34 PM
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
-- Database: `aqua`
--

-- --------------------------------------------------------

--
-- Table structure for table `bl`
--

CREATE TABLE `bl` (
  `id` int(11) NOT NULL,
  `Id_Client` int(11) NOT NULL,
  `Totale` double NOT NULL,
  `Date_BL` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bl`
--

INSERT INTO `bl` (`id`, `Id_Client`, `Totale`, `Date_BL`) VALUES
(1, 1, 7.699999999999999, '2023-08-25'),
(2, 1, 12177, '2023-08-19'),
(3, 1, 34.65, '2023-08-12'),
(4, 1, 3.15, '2023-08-19'),
(5, 1, 2.8, '2023-08-10'),
(6, 1, 3.15, '2023-08-19'),
(7, 1, 30.799999999999997, '2023-09-28'),
(8, 1, 5436.75, '2023-09-20');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `Name` varchar(250) NOT NULL,
  `MF` varchar(250) NOT NULL,
  `Adresse` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `Name`, `MF`, `Adresse`) VALUES
(1, 'Dar Zaghouan', '1234567', 'Zaghouan');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `Name` varchar(250) NOT NULL,
  `P_Unitaire` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id`, `Name`, `P_Unitaire`) VALUES
(1, 'Drap', 123),
(2, 'Drap', 123),
(3, 'Drap de bain', 0.35),
(4, 'Tapis de Bain', 0.45);

-- --------------------------------------------------------

--
-- Table structure for table `produitbl`
--

CREATE TABLE `produitbl` (
  `id` int(11) NOT NULL,
  `Qte` int(11) NOT NULL,
  `Id_BL` int(11) NOT NULL,
  `Nom` varchar(250) NOT NULL,
  `PU` double NOT NULL,
  `id_produit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produitbl`
--

INSERT INTO `produitbl` (`id`, `Qte`, `Id_BL`, `Nom`, `PU`, `id_produit`) VALUES
(4, 9, 6, 'Drap de bain', 0.35, 3),
(5, 88, 7, 'Drap de bain', 0.35, 3),
(6, 55, 8, 'Tapis de Bain', 0.45, 4),
(7, 44, 8, 'Drap', 123, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bl`
--
ALTER TABLE `bl`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produitbl`
--
ALTER TABLE `produitbl`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bl`
--
ALTER TABLE `bl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `produitbl`
--
ALTER TABLE `produitbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
