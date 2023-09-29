-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 29, 2023 at 06:50 PM
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
-- Database: `Aqua`
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
-- Table structure for table `facture`
--

CREATE TABLE `facture` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `net` double NOT NULL,
  `id_client` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(8, 12, 9, 'Tapis de Bain', 0.45, 4),
(9, 2, 9, 'Drap', 123, 1),
(10, 23, 10, 'Tapis de Bain', 0.45, 4),
(11, 9, 11, 'Tapis de Bain', 0.45, 4),
(12, 11, 12, 'Tapis de Bain', 0.45, 4),
(13, 11, 13, 'Drap de bain', 0.35, 3),
(14, 1, 14, 'Drap de bain', 0.35, 3),
(15, 12, 14, 'Tapis de Bain', 0.45, 4),
(16, 11, 15, 'Tapis de Bain', 0.45, 4),
(17, 11, 16, 'Drap de bain', 0.35, 3),
(18, 11, 16, 'Drap de bain', 0.35, 3),
(19, 11, 17, 'Drap de bain', 0.35, 3),
(20, 11, 17, 'Drap de bain', 0.35, 3),
(21, 11, 18, 'Drap de bain', 0.35, 3),
(22, 11, 18, 'Drap de bain', 0.35, 3),
(23, 11, 19, 'Drap de bain', 0.35, 3),
(24, 11, 19, 'Drap de bain', 0.35, 3);

-- --------------------------------------------------------

--
-- Table structure for table `ProduitFacture`
--

CREATE TABLE `ProduitFacture` (
  `id` int(11) NOT NULL,
  `qte` int(11) NOT NULL,
  `Id_BL` int(11) NOT NULL,
  `Id_Produit` int(11) NOT NULL,
  `PU` double NOT NULL,
  `Nom` text NOT NULL,
  `Id_Facture` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ProduitFacture`
--

INSERT INTO `ProduitFacture` (`id`, `qte`, `Id_BL`, `Id_Produit`, `PU`, `Nom`, `Id_Facture`) VALUES
(1, 2, 9, 1, 123, 'Drap', 14),
(2, 35, 9, 4, 0.45, 'Tapis de Bain', 14),
(3, 20, 11, 4, 0.45, 'Tapis de Bain', 15),
(4, 11, 13, 3, 0.35, 'Drap de bain', 16),
(5, 20, 11, 4, 0.45, 'Tapis de Bain', 16);

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
-- Indexes for table `facture`
--
ALTER TABLE `facture`
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
-- Indexes for table `ProduitFacture`
--
ALTER TABLE `ProduitFacture`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bl`
--
ALTER TABLE `bl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `produitbl`
--
ALTER TABLE `produitbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `ProduitFacture`
--
ALTER TABLE `ProduitFacture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
