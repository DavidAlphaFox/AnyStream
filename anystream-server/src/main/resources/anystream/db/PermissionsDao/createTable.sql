CREATE TABLE IF NOT EXISTS permissions (
  userId INT NOT NULL,
  name VARCHAR(255),
  PRIMARY KEY (userId, name)
)