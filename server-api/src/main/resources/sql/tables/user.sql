CREATE TABLE IF NOT EXISTS `User` (
  `uuid` CHAR(36),
  `username` TEXT NOT NULL,
  `hashword` BLOB NOT NULL, -- I'm more proud of this name than I should be

  `display_name` TEXT, -- An alternative name to display, rather than a user's username

  PRIMARY KEY(`uuid`),
  CONSTRAINT `UNIQUE_USERNAME` UNIQUE(`username`)
);