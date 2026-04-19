package diff;

import version.Commit;

/**
 * Service class responsible for reverting file content
 * to a specified commit.
 */
public class RevertService {

  /**
   * Restores the file content from a given commit.
   *
   * @param commit the commit to revert to
   * @return the restored file content as a string
   * @throws IllegalArgumentException if commit or snapshot is null
   */
  public String revertTo(Commit commit) {
    if (commit == null || commit.getSnapshot() == null) {
      throw new IllegalArgumentException("Commit or snapshot cannot be null.");
    }
    return commit.getSnapshot().getContent();
  }
}