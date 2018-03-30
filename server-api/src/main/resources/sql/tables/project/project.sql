CREATE TABLE IF NOT EXISTS `Project` (
  `uuid` CHAR(36),
  `creator_uuid` CHAR(36) NOT NULL,
  `name` TEXT NOT NULL,

  PRIMARY KEY(`uuid`),
  FOREIGN KEY(`creator_uuid`) REFERENCES `User`(`uuid`)
);