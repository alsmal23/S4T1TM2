CREATE TABLE IF NOT EXISTS `Task` (
  `pursuit_uuid` CHAR(36),
  `size_uuid` CHAR(36),
  `assignee_uuid` CHAR(36),
  `sprint_uuid` CHAR(36),


  PRIMARY KEY(`pursuit_uuid`),
  FOREIGN KEY(`pursuit_uuid`) REFERENCES `Pursuit`(`uuid`),
  FOREIGN KEY(`size_uuid`) REFERENCES `SizeAttribute`(`uuid`),
  FOREIGN KEY(`assignee_uuid`) REFERENCES `User`(`uuid`),
  FOREIGN KEY('sprint_uuid') REFERENCES `Sprint`(`uuid`)
);