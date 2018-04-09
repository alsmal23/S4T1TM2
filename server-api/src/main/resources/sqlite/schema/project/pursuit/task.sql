CREATE TABLE IF NOT EXISTS `Task` (
  `pursuit_uuid` CHAR(36),
  `name` TEXT NOT NULL,
  `size` CHAR(36),
  `assignee` CHAR(36),
  `description` TEXT,

  PRIMARY KEY(`pursuit_uuid`),
  FOREIGN KEY(`pursuit_uuid`) REFERENCES `Pursuit`(`uuid`),
  FOREIGN KEY(`size`) REFERENCES `SizeAttribute`(`uuid`),
  FOREIGN KEY(`assignee`) REFERENCES `User`(`uuid`)
);