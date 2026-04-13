package version;

import snapshot.FileSnapshot;

/**
 * Represents a single commit in the version control system.
 * Demonstrates inheritance and composition.
 */
public class Commit extends AbstractVersionRecord {

  private final Commit parent;
  private final FileSnapshot snapshot;

  public Commit(String id, String message, Commit parent, FileSnapshot snapshot) {
    super(id, message);

    if (snapshot == null) {
      throw new IllegalArgumentException("Snapshot cannot be null.");
    }

    this.parent = parent;
    this.snapshot = snapshot;
  }

  public Commit getParent() {
    return parent;
  }

  public FileSnapshot getSnapshot() {
    return snapshot;
  }

  @Override
  public String toString() {
    return "Commit{" +
        "id='" + id + '\'' +
        ", message='" + message + '\'' +
        ", timestamp=" + timestamp +
        '}';
  }
}