-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 16, 2024 at 07:07 PM
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
-- Database: `restaurants`
--

-- --------------------------------------------------------

--
-- Table structure for table `allergens`
--

CREATE TABLE `allergens` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `allergens`
--

INSERT INTO `allergens` (`id`, `name`) VALUES
(5, 'chocolate'),
(4, 'eggs'),
(3, 'gluten'),
(6, 'peanuts'),
(8, 'soy');

-- --------------------------------------------------------

--
-- Table structure for table `allergens_for_recipe`
--

CREATE TABLE `allergens_for_recipe` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `allergen_id` bigint(20) UNSIGNED NOT NULL,
  `recipe_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `allergens_for_recipe`
--

INSERT INTO `allergens_for_recipe` (`id`, `allergen_id`, `recipe_id`) VALUES
(1, 5, 2),
(2, 3, 2),
(3, 4, 2),
(4, 3, 1),
(5, 4, 1),
(6, 6, 1),
(8, 8, 3),
(9, 6, 4),
(11, 4, 5),
(12, 3, 6),
(13, 3, 7);

-- --------------------------------------------------------

--
-- Table structure for table `drinks`
--

CREATE TABLE `drinks` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `serve_style` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `drinks`
--

INSERT INTO `drinks` (`id`, `name`, `type`, `serve_style`) VALUES
(2, 'wine', 'alcohol', 'cold'),
(3, 'water', 'non-alcoholic', 'cold'),
(4, 'cappuccino', 'coffee', 'hot'),
(5, 'orange juice', 'non-alcoholic', 'cold'),
(6, 'iced tea', 'non-alcoholic', 'cold');

-- --------------------------------------------------------

--
-- Table structure for table `drinks_for_recipe`
--

CREATE TABLE `drinks_for_recipe` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `drink_id` bigint(20) UNSIGNED NOT NULL,
  `recipe_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `drinks_for_recipe`
--

INSERT INTO `drinks_for_recipe` (`id`, `drink_id`, `recipe_id`) VALUES
(1, 4, 1),
(2, 3, 4),
(3, 5, 1),
(4, 6, 2),
(7, 3, 5),
(8, 4, 3),
(9, 3, 6),
(10, 5, 7),
(11, 3, 8);

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE `ingredients` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ingredients`
--

INSERT INTO `ingredients` (`id`, `name`) VALUES
(8, 'baking powder'),
(6, 'chocolate chips'),
(4, 'cream'),
(2, 'eggs'),
(5, 'flour'),
(3, 'sugar'),
(7, 'vanilla extract');

-- --------------------------------------------------------

--
-- Table structure for table `ingredients_for_recipe`
--

CREATE TABLE `ingredients_for_recipe` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `ingredient_id` bigint(20) UNSIGNED NOT NULL,
  `recipe_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ingredients_for_recipe`
--

INSERT INTO `ingredients_for_recipe` (`id`, `ingredient_id`, `recipe_id`) VALUES
(1, 4, 1),
(2, 2, 1),
(3, 5, 1),
(4, 2, 4),
(5, 4, 2),
(6, 6, 1),
(7, 7, 2),
(8, 8, 3),
(9, 6, 4),
(10, 7, 4),
(11, 8, 4),
(12, 2, 5),
(13, 2, 6),
(14, 5, 6),
(15, 5, 7),
(16, 4, 7),
(18, 5, 8);

-- --------------------------------------------------------

--
-- Table structure for table `recipes`
--

CREATE TABLE `recipes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `serve_style` varchar(50) NOT NULL,
  `preparation_time` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `recipes`
--

INSERT INTO `recipes` (`id`, `name`, `type`, `serve_style`, `preparation_time`) VALUES
(1, 'cake', 'dessert', 'cold', 15),
(2, 'cookies', 'breakfast', 'hot', 5),
(3, 'pancakes', 'breakfast', 'hot', 10),
(4, 'sandwich', 'breakfast', 'cold', 10),
(5, 'salad', 'appetizer', 'cold', 8),
(6, 'Spaghetti Bolognese', 'main course', 'hot', 30),
(7, 'Margarita Pizza', 'main course', 'hot', 20),
(8, 'Fruit Salad', 'dessert', 'cold', 15);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `allergens`
--
ALTER TABLE `allergens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `allergens_for_recipe`
--
ALTER TABLE `allergens_for_recipe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `allergen_id` (`allergen_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Indexes for table `drinks`
--
ALTER TABLE `drinks`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `drinks_for_recipe`
--
ALTER TABLE `drinks_for_recipe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `drink_id` (`drink_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Indexes for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `ingredients_for_recipe`
--
ALTER TABLE `ingredients_for_recipe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ingredient_id` (`ingredient_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Indexes for table `recipes`
--
ALTER TABLE `recipes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `allergens`
--
ALTER TABLE `allergens`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `allergens_for_recipe`
--
ALTER TABLE `allergens_for_recipe`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `drinks`
--
ALTER TABLE `drinks`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `drinks_for_recipe`
--
ALTER TABLE `drinks_for_recipe`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `ingredients_for_recipe`
--
ALTER TABLE `ingredients_for_recipe`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `recipes`
--
ALTER TABLE `recipes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `allergens_for_recipe`
--
ALTER TABLE `allergens_for_recipe`
  ADD CONSTRAINT `allergens_for_recipe_ibfk_1` FOREIGN KEY (`allergen_id`) REFERENCES `allergens` (`id`),
  ADD CONSTRAINT `allergens_for_recipe_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);

--
-- Constraints for table `drinks_for_recipe`
--
ALTER TABLE `drinks_for_recipe`
  ADD CONSTRAINT `drinks_for_recipe_ibfk_1` FOREIGN KEY (`drink_id`) REFERENCES `drinks` (`id`),
  ADD CONSTRAINT `drinks_for_recipe_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);

--
-- Constraints for table `ingredients_for_recipe`
--
ALTER TABLE `ingredients_for_recipe`
  ADD CONSTRAINT `ingredients_for_recipe_ibfk_1` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`),
  ADD CONSTRAINT `ingredients_for_recipe_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
