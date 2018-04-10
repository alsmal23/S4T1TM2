CREATE TABLE IF NOT EXISTS `Permission` (
  `project_uuid` CHAR(36) NOT NULL,
  `user_uuid` CHAR(36) NOT NULL,
  `permission_node` TEXT NOT NULL,

  FOREIGN KEY(`project_uuid`) REFERENCES `Project`(`uuid`),
  FOREIGN KEY(`user_uuid`) REFERENCES `User`(`uuid`)
);

CREATE INDEX IF NOT EXISTS IX_Permission_project_uuid ON `Permission`(`project_uuid`);
CREATE INDEX IF NOT EXISTS IX_Permission_user_uuid ON `Permission`(`user_uuid`);