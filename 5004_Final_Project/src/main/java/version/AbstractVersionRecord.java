package version;

import repository.VersionedEntity;

/**
 * Abstract base class for all version records in the system.
 * <p>
 * This class stores the common information shared by concrete version objects,
 * including the unique id, timestamp, and commit message.
 * </p >
 */
public abstract class AbstractVersionRecord implements VersionedEntity {

  /** Unique identifier of this version record. */
  protected String id;

  /** Time when this version record was created. */
  protected long timestamp;

  /** Commit message describing this version. */
  protected String message;

  /**
   * Constructs an abstract version record with the given id and message.
   *
   * @param id the unique id of the version record
   * @param message the commit message
   * @throws IllegalArgumentException if id is null or empty
   * @throws IllegalArgumentException if message is null or empty
   */
  public AbstractVersionRecord(String id, String message) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("ID cannot be empty.");
    }
    if (message == null || message.isEmpty()) {
      throw new IllegalArgumentException("Message cannot be empty.");
    }

    this.id = id;
    this.message = message;
    this.timestamp = System.currentTimeMillis();
  }

  /**
   * Returns the id of this version record.
   *
   * @return the version id
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * Returns the creation timestamp of this version record.
   *
   * @return the timestamp
   */
  public long getTimestamp() {
    return timestamp;
  }

  /**
   * Returns the commit message of this version record.
   *
   * @return the commit message
   */
  public String getMessage() {
    return message;
  }
}