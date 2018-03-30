CREATE TABLE IF NOT EXISTS `PursuitTag` (
  `pursuit_uuid` CHAR(36) NOT NULL,
  `tag` CHAR(36) NOT NULL,

  FOREIGN KEY(`pursuit_uuid`) REFERENCES `Pursuit`(`uuid`),
  FOREIGN KEY(`tag`) REFERENCES `TagAttribute`(`uuid`)
);

CREATE INDEX IX_PursuitTag_pursuit_uuid ON `PursuitTag`(`pursuit_uuid`);