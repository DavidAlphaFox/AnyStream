CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255),
  displayName VARCHAR(255),
  passwordHash VARCHAR(255),
  permissions TEXT,
  PRIMARY KEY (id)
)