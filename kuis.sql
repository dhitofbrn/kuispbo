-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 14 Apr 2020 pada 22.10
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kuis`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `anggota`
--

CREATE TABLE `anggota` (
  `Nim` varchar(9) NOT NULL,
  `Nama` varchar(20) NOT NULL,
  `Ttl` date NOT NULL,
  `Jenkel` varchar(10) NOT NULL,
  `Agama` varchar(20) NOT NULL,
  `Daftar` date NOT NULL,
  `Berlaku` date NOT NULL,
  `Kelas` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `anggota`
--

INSERT INTO `anggota` (`Nim`, `Nama`, `Ttl`, `Jenkel`, `Agama`, `Daftar`, `Berlaku`, `Kelas`) VALUES
('121', 'ande', '2002-01-01', 'Perempuan', 'Katolik', '2019-01-10', '2024-01-10', 'a'),
('122', 'awe', '2002-09-09', 'Laki laki', 'Hindu', '2020-02-10', '2025-02-10', 'a'),
('123', 'Dhit', '2000-02-02', 'Laki laki', 'Islam', '2020-10-03', '2025-10-03', 'b');

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE `buku` (
  `Kode` varchar(3) NOT NULL,
  `Judul` varchar(30) NOT NULL,
  `Pengarang` varchar(20) NOT NULL,
  `Penerbit` varchar(20) NOT NULL,
  `Tahun` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`Kode`, `Judul`, `Pengarang`, `Penerbit`, `Tahun`) VALUES
('1', 'daun', 'tere', 'gram', '2012'),
('2', 'yang', 'tere', 'gram', '2013'),
('3', 'jatuh', 'liye', 'kompas', '2014'),
('4', 'dari', 'liye', 'kompas', '2020');

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE `karyawan` (
  `Nik` varchar(9) NOT NULL,
  `Namakry` varchar(20) NOT NULL,
  `Jenkel` varchar(20) NOT NULL,
  `Golongan` int(11) NOT NULL,
  `Jabatan` varchar(20) NOT NULL,
  `Tunjangan` int(11) NOT NULL,
  `Gaji` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`Nik`, `Namakry`, `Jenkel`, `Golongan`, `Jabatan`, `Tunjangan`, `Gaji`) VALUES
('111', 'ade', 'Laki laki', 3, 'Magang', 0, 500000),
('222', 'eda', 'Perempuan', 3, 'Magang', 0, 500000),
('333', 'we', 'Perempuan', 1, 'Magang', 0, 500000),
('444', 'tuu', 'Perempuan', 3, 'Magang', 0, 500000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `peminjaman`
--

CREATE TABLE `peminjaman` (
  `Id` varchar(3) NOT NULL,
  `Nimp` varchar(9) NOT NULL,
  `Kodep` varchar(3) NOT NULL,
  `Tglp` date NOT NULL,
  `Tglk` date NOT NULL,
  `Lama` bigint(5) NOT NULL,
  `Denda` bigint(11) NOT NULL,
  `Nikp` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `peminjaman`
--

INSERT INTO `peminjaman` (`Id`, `Nimp`, `Kodep`, `Tglp`, `Tglk`, `Lama`, `Denda`, `Nikp`) VALUES
('1', '123', '1', '2020-01-10', '2020-10-10', 23673600000, 23673599993000, '222'),
('9', '122', '2', '2020-02-09', '2020-03-09', 2505600000, 2505599993000, '111');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`Nim`);

--
-- Indeks untuk tabel `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`Kode`);

--
-- Indeks untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`Nik`);

--
-- Indeks untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD KEY `fkbuku` (`Kodep`),
  ADD KEY `fkanggota` (`Nimp`),
  ADD KEY `fkkaryawan` (`Nikp`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `fkanggota` FOREIGN KEY (`Nimp`) REFERENCES `anggota` (`Nim`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkbuku` FOREIGN KEY (`Kodep`) REFERENCES `buku` (`Kode`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkkaryawan` FOREIGN KEY (`Nikp`) REFERENCES `karyawan` (`Nik`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
