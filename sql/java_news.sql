-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- ホスト: localhost:8889
-- 生成日時: 2022 年 1 月 18 日 05:16
-- サーバのバージョン： 5.7.34
-- PHP のバージョン: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `java_news`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `registration_date` date NOT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:未削除、1:削除済み',
  `create_date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'レコードの登録日時',
  `update_date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'レコードの更新日時'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- テーブルのデータのダンプ `admin`
--

INSERT INTO `admin` (`id`, `email`, `pass`, `name`, `registration_date`, `is_deleted`, `create_date_time`, `update_date_time`) VALUES
(1, 'hanako@yamada.co.jp', '1234', '山田花子', '2021-11-16', 0, '2021-11-16 15:39:02', '2021-11-16 15:55:45'),
(2, 'taro@suzuki.ne.jp', 'Taro1234', '鈴木太郎', '2021-12-15', 0, '2021-12-15 15:39:51', '2021-12-20 14:23:49');

-- --------------------------------------------------------

--
-- テーブルの構造 `genre`
--

CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `genre` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `registration_date` date NOT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:未削除、1:削除済み',
  `create_date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- テーブルのデータのダンプ `genre`
--

INSERT INTO `genre` (`id`, `genre`, `registration_date`, `is_deleted`, `create_date_time`, `update_date_time`) VALUES
(1, 'サイエンス', '2021-11-16', 0, '2021-11-16 15:20:50', '2021-11-17 11:49:21'),
(2, 'メモ', '2021-12-08', 0, '2021-12-08 15:33:50', '2021-12-10 14:29:08'),
(3, 'セキュリティ', '2022-01-13', 0, '2022-01-13 10:44:26', '2022-01-13 10:44:26');

-- --------------------------------------------------------

--
-- テーブルの構造 `news`
--

CREATE TABLE `news` (
  `id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `article` varchar(5000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `picture` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `genre_id` int(11) NOT NULL,
  `url` varchar(2000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `twitter` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `registration_date` date NOT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:未削除、1:削除済み',
  `create_date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'レコードの登録日時',
  `update_date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'レコードの更新日時'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- テーブルのデータのダンプ `news`
--

INSERT INTO `news` (`id`, `admin_id`, `title`, `article`, `picture`, `genre_id`, `url`, `twitter`, `registration_date`, `is_deleted`, `create_date_time`, `update_date_time`) VALUES
(2, 1, '幅わずか0.5mmで「塩粒サイズ」のカメラが開発される', '幅わずか0.5mmという「粗塩1粒サイズ」のカメラをアメリカのプリンストン大学とワシントン大学の共同研究チームが開発しました。この超小型カメラは、体積が55万倍のレンズを装着したカメラと変わらない画質の写真を撮影できます。', '/java_news/img/salt_size_camera.jpeg', 1, 'https://gigazine.net/news/20211201-shrink-camera-size-salt-grain/', '', '2021-12-02', 0, '2021-12-02 14:59:42', '2022-01-06 14:33:50'),
(3, 1, '「スーパーゼリー」が誕生、80％水なのに車にひかれても即座に完全復活する素材', 'イギリス・ケンブリッジ大学の研究チームが、80％が水にもかかわらず車にひかれてもたちどころに元の形状に戻るという「スーパーゼリー」を開発しました。このスーパーゼリーは、膝の軟骨の代替物として活用できると期待されています。', '/java_news/img/super_jelly.jpeg', 1, 'https://gigazine.net/news/20211126-super-jelly-can-survive-car-run-over/', '', '2021-12-02', 0, '2021-12-02 15:54:05', '2021-12-03 11:24:47'),
(7, 1, '20年逃亡していたマフィアがGoogleストリートビューに写ってしまい逮捕される', '2002年にイタリア・ローマの刑務所からの脱獄に成功し、逃亡していたマフィアのボスが逮捕されました。捜索の中では、Googleストリートビューが大きな役割を果たしていました。', '/java_news/img/00_m.jpg', 2, 'https://gigazine.net/news/20220107-italian-mafia-arrested-google-street-view/', '', '2022-01-17', 0, '2022-01-17 15:17:54', '2022-01-17 15:19:13'),
(9, 1, 'ブレイクスルー感染のデータから見る「ワクチンの有効性」とは？', '感染力が強い新型コロナウイルスのオミクロン株はワクチン接種完了者の間でブレイクスルー感染を引き起こしており、ワクチン有効率の低下が懸念されています。アメリカ・ニューヨーク州が公開する「ワクチン接種完了者やワクチン未接種者におけるブレイクスルー感染のデータ」からは、ワクチンの有効性がオミクロン株に受けた影響をうかがい知ることができます。', 'java_news/img/breakthrough.jpeg', 1, 'https://gigazine.net/news/20220117-covid-19-breakthrough-data-vaccine-effectiveness/', '', '2022-01-17', 0, '2022-01-17 15:35:19', '2022-01-17 15:35:19');

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- テーブルのインデックス `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- テーブルのインデックス `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `picture` (`picture`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- テーブルの AUTO_INCREMENT `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- テーブルの AUTO_INCREMENT `news`
--
ALTER TABLE `news`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
