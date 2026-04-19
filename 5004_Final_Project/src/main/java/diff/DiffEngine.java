package diff;

import version.Commit;

/**
 * Interface for comparing differences between two commits.
 * Allows different diff strategies to be implemented.
 */
public interface DiffEngine {

  /**
   * Compares two commits and returns the difference result.
   *
   * @param c1 the first commit (older version)
   * @param c2 the second commit (newer version)
   * @return a DiffResult containing differences
   */
  DiffResult compare(Commit c1, Commit c2);
}