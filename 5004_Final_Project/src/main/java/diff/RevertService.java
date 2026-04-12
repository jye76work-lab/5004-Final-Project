package diff;

import version.Commit;

public class RevertService {

  public String revertTo(Commit commit) {
    if (commit == null || commit.getSnapshot() == null) {
      throw new IllegalArgumentException("version.Commit or snapshot cannot be null.");
    }
    return commit.getSnapshot().getContent();
  }
}