CREATE TABLE `hospital` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `name_index` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4