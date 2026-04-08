public class Commit {
  private String id;
  private String message;
  private FileSnapshot snapshot;

  public Commit(String id, String message, FileSnapshot snapshot) {
    this.id = id;
    this.message = message;
    this.snapshot = snapshot;
  }

  public String getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  public FileSnapshot getSnapshot() {
    return snapshot;
  }
}