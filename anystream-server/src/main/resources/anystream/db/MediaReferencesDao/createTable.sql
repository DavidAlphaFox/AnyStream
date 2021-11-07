CREATE TABLE IF NOT EXISTS mediaReferences (
  id INT NOT NULL AUTO_INCREMENT,
  contentId INT NOT NULL,
  rootContentId INT NOT NULL,
  added LONG NOT NULL,
  addedByUserId INT NOT NULL,
  mediaKind VARCHAR NOT NULL,
  updatedAt LONG NOT NULL,
  PRIMARY KEY (id, contentId, mediaId)
)