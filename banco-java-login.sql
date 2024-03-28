-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Tempo de geração: 28/03/2024 às 14:48
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bancojavalogin`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `tb_autenticacao`
--

CREATE TABLE `tb_autenticacao` (
  `id` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `senha` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tb_autenticacao`
--

INSERT INTO `tb_autenticacao` (`id`, `email`, `senha`) VALUES
(1, 'teste', 'teste'),
(2, 'aline@gmail.com', '123123');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tb_dados`
--

CREATE TABLE `tb_dados` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `DDD` varchar(5) NOT NULL,
  `celular` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `dataNascimento` varchar(14) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tb_dados`
--

INSERT INTO `tb_dados` (`id`, `nome`, `DDD`, `celular`, `email`, `dataNascimento`, `cpf`, `endereco`, `cidade`, `estado`) VALUES
(1, 'teste', '12', '11876543218', 'teste', '2024-03-13', 'teste', 'teste', 'teste', 'teste'),
(324, 'Agrícola da Terra Fonseca', '13', '87654239087', 'agrícola@gmail.com', '11-08-1990', '12138767027', 'rua amazonas rio do brasil pimpao', 'amaoznas', 'AM'),
(325, 'Antonio Morrendo das Dores', '11', '12345678909', 'AntonioMorrendoDasDores@gmail.com', '1980', '12138767027', 'Janeiro Fevereiro de Março Abril', 'Aguas de MArcos', 'Março'),
(326, 'Dezencio Feverencio de Oitenta e Cinco', '85', '10000000', '85@gmail.com', '87', '42358094072', 'Fevereiro de janeiro', 'SP', 'SP'),
(327, 'Inocencio Coitadinho', '', '', 'InocencioCoitadinho@gmail.com', '88', '23637848040', 'Homem BOm da Cunha Solto MAior', 'Maior', 'gasoso'),
(328, 'NAPOLEAO SEM MEDO E SEM MACULA', '45', '345678938476', '23637848040@gmail.com', '1600', '25278586001', 'Renato Pordeus Furtado', 'Rocambole Sinato', 'Vincente Mais ou Menos da Silva');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `tb_autenticacao`
--
ALTER TABLE `tb_autenticacao`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `tb_dados`
--
ALTER TABLE `tb_dados`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tb_autenticacao`
--
ALTER TABLE `tb_autenticacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tb_dados`
--
ALTER TABLE `tb_dados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=329;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
