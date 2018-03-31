CREATE TABLE IF NOT EXISTS `TagAttribute` (
  `uuid` CHAR(36),
  `project_uuid` CHAR(36) NOT NULL,
  `name` TEXT NOT NULL,
  `color` INT, -- Stored as a RGB int

  PRIMARY KEY(`uuid`),
  FOREIGN KEY(`project`) REFERENCES `Project`(`uuid`)
);

CREATE INDEX IX_TagAttribute_project_uuid ON `TagAttribute`(`project_uuid`);