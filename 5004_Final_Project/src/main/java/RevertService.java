public class RevertService {

  public String revertTo(Commit commit) {
    if (commit == null || commit.getSnapshot() == null) {
      throw new IllegalArgumentException("Commit or snapshot cannot be null.");
    }
    return commit.getSnapshot().getContent();
  }
}