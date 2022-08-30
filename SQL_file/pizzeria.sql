-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2022. Jún 02. 09:20
-- Kiszolgáló verziója: 10.4.21-MariaDB
-- PHP verzió: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `pizzeria`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `feltet`
--

CREATE TABLE `feltet` (
  `feltet_id` smallint(6) NOT NULL,
  `feltet_nev` varchar(30) COLLATE latin2_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COLLATE=latin2_hungarian_ci;

--
-- A tábla adatainak kiíratása `feltet`
--

INSERT INTO `feltet` (`feltet_id`, `feltet_nev`) VALUES
(1, 'paradicsomos'),
(2, 'fokhagymas'),
(3, 'eros-alap'),
(5, 'sonka'),
(6, 'sajt'),
(7, 'szalámi'),
(11, 'kukorica'),
(12, 'paradicsom'),
(13, 'csirkehús'),
(14, 'kolbász'),
(15, 'ananász'),
(16, 'hagyma');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `feltet_pizza`
--

CREATE TABLE `feltet_pizza` (
  `fp_id` smallint(6) NOT NULL,
  `feltet_id` smallint(6) NOT NULL,
  `pizza_id` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COLLATE=latin2_hungarian_ci;

--
-- A tábla adatainak kiíratása `feltet_pizza`
--

INSERT INTO `feltet_pizza` (`fp_id`, `feltet_id`, `pizza_id`) VALUES
(1, 1, 1),
(5, 2, 2),
(7, 3, 3),
(8, 6, 1),
(9, 6, 2),
(10, 6, 3),
(11, 14, 1),
(12, 13, 2),
(13, 15, 3),
(14, 7, 3);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `feltet_pizza_extra`
--

CREATE TABLE `feltet_pizza_extra` (
  `fp_id` smallint(6) NOT NULL,
  `rendeles_id` smallint(6) NOT NULL,
  `feltet_id` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COLLATE=latin2_hungarian_ci;

--
-- A tábla adatainak kiíratása `feltet_pizza_extra`
--

INSERT INTO `feltet_pizza_extra` (`fp_id`, `rendeles_id`, `feltet_id`) VALUES
(1, 1, 7),
(2, 1, 11),
(3, 1, 12),
(4, 2, 12),
(5, 2, 13),
(6, 2, 14),
(7, 3, 15),
(8, 3, 16),
(9, 4, 6),
(10, 5, 6),
(11, 5, 7),
(12, 5, 11);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `pizza`
--

CREATE TABLE `pizza` (
  `pizza_id` smallint(6) NOT NULL,
  `pizza_nev` varchar(30) COLLATE latin2_hungarian_ci NOT NULL,
  `pizza_ar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COLLATE=latin2_hungarian_ci;

--
-- A tábla adatainak kiíratása `pizza`
--

INSERT INTO `pizza` (`pizza_id`, `pizza_nev`, `pizza_ar`) VALUES
(1, 'sajtos', 500),
(2, 'bolognai', 600),
(3, 'húsos', 800);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `rendeles`
--

CREATE TABLE `rendeles` (
  `rendeles_id` smallint(6) NOT NULL,
  `pizza_id` smallint(6) NOT NULL,
  `rendelesi_ido` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `pizza_meret` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COLLATE=latin2_hungarian_ci;

--
-- A tábla adatainak kiíratása `rendeles`
--

INSERT INTO `rendeles` (`rendeles_id`, `pizza_id`, `rendelesi_ido`, `pizza_meret`) VALUES
(1, 3, '2022-05-28 16:07:18', 32),
(2, 2, '2022-05-29 08:28:49', 52),
(3, 3, '2022-05-29 08:30:43', 32),
(4, 1, '2022-05-29 08:32:48', 32),
(5, 1, '2022-05-29 08:33:56', 47);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `feltet`
--
ALTER TABLE `feltet`
  ADD PRIMARY KEY (`feltet_id`);

--
-- A tábla indexei `feltet_pizza`
--
ALTER TABLE `feltet_pizza`
  ADD PRIMARY KEY (`fp_id`);

--
-- A tábla indexei `feltet_pizza_extra`
--
ALTER TABLE `feltet_pizza_extra`
  ADD PRIMARY KEY (`fp_id`);

--
-- A tábla indexei `pizza`
--
ALTER TABLE `pizza`
  ADD PRIMARY KEY (`pizza_id`);

--
-- A tábla indexei `rendeles`
--
ALTER TABLE `rendeles`
  ADD PRIMARY KEY (`rendeles_id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `feltet`
--
ALTER TABLE `feltet`
  MODIFY `feltet_id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT a táblához `feltet_pizza`
--
ALTER TABLE `feltet_pizza`
  MODIFY `fp_id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT a táblához `feltet_pizza_extra`
--
ALTER TABLE `feltet_pizza_extra`
  MODIFY `fp_id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT a táblához `pizza`
--
ALTER TABLE `pizza`
  MODIFY `pizza_id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `rendeles`
--
ALTER TABLE `rendeles`
  MODIFY `rendeles_id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
