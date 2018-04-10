CREATE TABLE IF NOT EXISTS `TaskWorkEntry` (
  `task_pursuit_uuid` CHAR(36) NOT NULL,
  `user_uuid` CHAR(36) NOT NULL,
  `start_timestamp` INT NOT NULL, -- Unix Timestamp
  `stop_timestamp` INT NOT NULL, -- Unix Timestamp
  `reported_at_timestamp` BIGINT NOT NULL, -- Unix Timestamp

  PRIMARY KEY(`task_pursuit_uuid`),
  FOREIGN KEY(`task_pursuit_uuid`) REFERENCES `Pursuit`(`uuid`),
  FOREIGN KEY(`user_uuid`) REFERENCES `User`(`uuid`)
);

CREATE INDEX IF NOT EXISTS IX_TaskWorkEntry_task_pursuit_uuid ON `TaskWorkEntry`(`task_pursuit_uuid`);