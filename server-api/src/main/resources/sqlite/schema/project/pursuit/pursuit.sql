CREATE TABLE IF NOT EXISTS `Pursuit` (
  `uuid` CHAR(36),
  `project` CHAR(36) NOT NULL,
  `name` TEXT NOT NULL,
  `type` TEXT NOT NULL CHECK(`type` = 'TASK' OR `type` = 'SPRINT'),

  PRIMARY KEY(`uuid`),
  FOREIGN KEY(`project`) REFERENCES `Project`(`uuid`)
);
