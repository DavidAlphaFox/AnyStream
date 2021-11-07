CREATE TABLE IF NOT EXISTS inviteCodes (
  id INT NOT NULL AUTO_INCREMENT,
  secret VARCHAR(255),
  permissions TEXT,
  createdByUserId INT,
  PRIMARY KEY (id, secret)
)