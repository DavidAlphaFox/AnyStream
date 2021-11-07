CREATE TABLE IF NOT EXISTS playbackStates (
  id INT NOT NULL AUTO_INCREMENT,
  mediaReferenceId INT NOT NULL,
  mediaId INT NOT NULL,
  userId INT NOT NULL,
  position DOUBLE NOT NULL,
  runtime DOUBLE NOT NULL,
  updatedAt LONG NOT NULL,
  PRIMARY KEY (id, userId, mediaId)
)