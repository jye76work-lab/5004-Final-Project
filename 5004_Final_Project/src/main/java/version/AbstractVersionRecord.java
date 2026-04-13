package version;

/**
 * Abstract base class for version records.
 * Demonstrates abstraction by storing common fields.
 */
public abstract class AbstractVersionRecord {

  protected String id;
  protected long timestamp;
  protected String message;

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

  public String getId() {
    return id;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }
}