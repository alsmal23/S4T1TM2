CREATE TABLE IF NOT EXISTS `Pursuit` (
  `uuid` CHAR(36),
  `project_uuid` CHAR(36) NOT NULL,
  `name` TEXT NOT NULL,
  `type` TEXT NOT NULL CHECK(`type` = 'TASK' OR `type` = 'SPRINT'),
  `description` TEXT,

  PRIMARY KEY(`uuid`),
  FOREIGN KEY(`project_uuid`) REFERENCES `Project`(`uuid`)
);

CREATE INDEX IF NOT EXISTS IX_Pursuit_project_uuid ON `Project`(`uuid`);