CREATE TABLE IF NOT EXISTS `Task` (
  `pursuit_uuid` CHAR(36),
  `name` TEXT NOT NULL,
  `pursuit_parent_uuid` CHAR(36),
  `size` CHAR(36),
  `assignee` CHAR(36),
  `due_date` BIGINT, -- Not sure what data-type to put here, likely done by days rather than UNIX time
  `description` TEXT,

  PRIMARY KEY(`pursuit_uuid`),
  FOREIGN KEY(`pursuit_uuid`) REFERENCES `Pursuit`(`uuid`),
  FOREIGN KEY(`pursuit_parent_uuid`) REFERENCES `Pursuit`(`uuid`),
  FOREIGN KEY(`size`) REFERENCES `SizeAttribute`(`uuid`),
  FOREIGN KEY(`assignee`) REFERENCES `User`(`uuid`)
);