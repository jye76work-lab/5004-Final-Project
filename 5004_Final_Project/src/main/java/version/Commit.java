package version;

import snapshot.FileSnapshot;

/**
 * Represents a single commit in the version control system.
 * <p>
 * A commit stores its own metadata as well as a reference to its parent commit
 * and the file snapshot captured at that version.
 * </p >
 */
public class Commit extends AbstractVersionRecord {

  /** The previous commit in the history chain. */
  private final Commit parent;

  /** The snapshot stored in this commit. */
  private final FileSnapshot snapshot;

  /**
   * Constructs a commit with id, message, parent commit, and snapshot.
   *
   * @param id the commit id
   * @param message the commit message
   * @param parent the parent commit; may be null for the first commit
   * @param snapshot the snapshot stored in this commit
   * @throws IllegalArgumentException if snapshot is null
   */
  public Commit(String id, String message, Commit parent, FileSnapshot snapshot) {
    super(id, message);

    if (snapshot == null) {
      throw new IllegalArgumentException("Snapshot cannot be null.");
    }

    this.parent = parent;
    this.snapshot = snapshot;
  }

  /**
   * Returns the parent commit.
   *
   * @return the parent commit, or null if this is the first commit
   */
  public Commit getParent() {
    return parent;
  }

  /**
   * Returns the snapshot stored in this commit.
   *
   * @return the file snapshot
   */
  public FileSnapshot getSnapshot() {
    return snapshot;
  }

  /**
   * Returns a readable string representation of this commit.
   *
   * @return formatted commit information
   */
  @Override
  public String toString() {
    return "Commit{" +
        "id='" + id + '\'' +
        ", message='" + message + '\'' +
        ", timestamp=" + timestamp +
        '}';
  }
}