package version;

import snapshot.FileSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Manages the commit history of the repository.
 * <p>
 * This class is responsible for creating commits, storing them in order,
 * and retrieving commits by id.
 * </p >
 */
public class CommitHistory {

  /** Ordered list of all commits in the repository. */
  private final List<Commit> commits;

  /** The next numeric id to assign to a new commit. */
  private int nextId;

  /**
   * Constructs an empty commit history.
   */
  public CommitHistory() {
    this.commits = new ArrayList<>();
    this.nextId = 1;
  }

  /**
   * Creates a new commit from the given content and message.
   *
   * @param content the file content to save
   * @param message the commit message
   * @return the newly created commit
   * @throws IllegalArgumentException if content is null or empty
   * @throws IllegalArgumentException if message is null or empty
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
   * Returns an unmodifiable view of the full commit history.
   *
   * @return the commit history
   */
  public List<Commit> getHistory() {
    return Collections.unmodifiableList(commits);
  }

  /**
   * Returns the commit with the given id.
   *
   * @param id the commit id to search for
   * @return the matching commit, or null if not found
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