package version;

import snapshot.FileSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Manages the full commit history.
 */
public class CommitHistory {

  private final List<Commit> commits;
  private int nextId;

  public CommitHistory() {
    this.commits = new ArrayList<>();
    this.nextId = 1;
  }

  /**
   * Creates a new commit from content and message.
   * This matches Repository.commit(content, message).
   */
  public Commit createCommit(String content, String message) {
    if (content == null || content.isEmpty()) {
      throw new IllegalArgumentException("Content cannot be empty.");
    }
    if (message == null || message.isEmpty()) {
      throw new IllegalArgumentException("Message cannot be empty.");
    }

    Commit parent = commits.isEmpty() ? null : commits.get(commits.size() - 1);

    List<String> lines = Arrays.asList(content.split("\\R", -1));
    FileSnapshot snapshot = new FileSnapshot("tracked_file.txt", lines);

    Commit newCommit = new Commit(
        String.valueOf(nextId),
        message,
        parent,
        snapshot
    );

    commits.add(newCommit);
    nextId++;

    return newCommit;
  }

  /**
   * Returns an unmodifiable view of commit history.
   */
  public List<Commit> getHistory() {
    return Collections.unmodifiableList(commits);
  }

  /**
   * Finds a commit by its id.
   * Returns null if not found, to match Repository's current logic.
   */
  public Commit getCommitById(String id) {
    if (id == null || id.isEmpty()) {
      return null;
    }

    for (Commit c : commits) {
      if (c.getId().equals(id)) {
        return c;
      }
    }

    return null;
  }
}