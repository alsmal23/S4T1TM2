CREATE TABLE IF NOT EXISTS `SizeAttribute` (
  `uuid` CHAR(36),
  `project_uuid` CHAR(36) NOT NULL,
  `name` TEXT NOT NULL,
  `value` INT NOT NULL CHECK(0 <= `value`),

  PRIMARY KEY(`uuid`),
  FOREIGN KEY(`project_uuid`) REFERENCES `Project`(`uuid`)
);

CREATE INDEX IF NOT EXISTS IX_SizeAttribute_project_uuid ON `SizeAttribute`(`project_uuid`);