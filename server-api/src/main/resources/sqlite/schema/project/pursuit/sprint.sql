CREATE TABLE IF NOT EXISTS `Sprint` (
  `pursuit_uuid` CHAR(36),
  `name` TEXT NOT NULL,
  `size` CHAR(36), -- On the fence with this one, sprints likely will not inherit in the same way task sizes do
  `due_date` BIGINT, -- Not sure what data-type to put here, likely done by days rather than UNIX time
  `description` TEXT,

  PRIMARY KEY(`pursuit_uuid`),
  FOREIGN KEY(`pursuit_uuid`) REFERENCES `Pursuit`(`uuid`),
  FOREIGN KEY(`size`) REFERENCES `SizeAttribute`(`uuid`),
);