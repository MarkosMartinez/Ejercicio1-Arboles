-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-01-2023 a las 08:45:34
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `eh_garden`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arboles`
--

CREATE TABLE `arboles` (
  `id` int(11) NOT NULL,
  `nombre_comun` varchar(255) NOT NULL,
  `nombre_cientifico` varchar(255) NOT NULL,
  `habitad` varchar(255) NOT NULL,
  `altura` int(11) NOT NULL,
  `origen` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `arboles`
--

INSERT INTO `arboles` (`id`, `nombre_comun`, `nombre_cientifico`, `habitad`, `altura`, `origen`) VALUES
(1, 'Prueba común', 'Prueba científico', 'Humedal', 1, 'Asia');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arboles`
--
ALTER TABLE `arboles`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arboles`
--
ALTER TABLE `arboles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
