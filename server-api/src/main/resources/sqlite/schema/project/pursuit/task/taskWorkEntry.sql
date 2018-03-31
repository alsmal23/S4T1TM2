CREATE TABLE IF NOT EXISTS `TaskWorkEntry` (
  `task_pursuit_uuid` CHAR(36) NOT NULL,
  `user_uuid` CHAR(36) NOT NULL,
  `duration` INT NOT NULL, -- Time in milliseconds
  `reported_at` BIGINT NOT NULL, -- Unix Timestamp

  FOREIGN KEY(`task_pursuit_uuid`) REFERENCES `Pursuit`(`uuid`),
  FOREIGN KEY(`user_uuid`) REFERENCES `User`(`uuid`)
);

CREATE INDEX IX_TaskWorkEntry_task_pursuit_uuid ON `TaskWorkEntry`(`task_pursuit_uuid`);