package version;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommitHistoryTest {

  @Test
  void testCreateCommitValid() {
    CommitHistory history = new CommitHistory();

    Commit commit = history.createCommit("line1\nline2", "first commit");

    assertEquals("1", commit.getId());
    assertEquals("first commit", commit.getMessage());
    assertNull(commit.getParent());
    assertEquals(2, commit.getSnapshot().getLines().size());
  }

  @Test
  void testCreateCommitWithParent() {
    CommitHistory history = new CommitHistory();

    Commit c1 = history.createCommit("a", "first");
    Commit c2 = history.createCommit("b", "second");

    assertEquals(c1, c2.getParent());
    assertEquals("2", c2.getId());
  }

  @Test
  void testCreateCommitEmptyContent() {
    CommitHistory history = new CommitHistory();

    Exception e = assertThrows(IllegalArgumentException.class, () -> {
      history.createCommit("", "msg");
    });

    assertEquals("Content cannot be empty.", e.getMessage());
  }

  @Test
  void testCreateCommitNullContent() {
    CommitHistory history = new CommitHistory();

    assertThrows(IllegalArgumentException.class, () -> {
      history.createCommit(null, "msg");
    });
  }

  @Test
  void testCreateCommitEmptyMessage() {
    CommitHistory history = new CommitHistory();

    Exception e = assertThrows(IllegalArgumentException.class, () -> {
      history.createCommit("content", "");
    });

    assertEquals("Message cannot be empty.", e.getMessage());
  }

  @Test
  void testGetHistoryUnmodifiable() {
    CommitHistory history = new CommitHistory();
    history.createCommit("a", "msg");

    List<Commit> list = history.getHistory();

    assertThrows(UnsupportedOperationException.class, () -> {
      list.add(null);
    });
  }

  @Test
  void testGetCommitByIdFound() {
    CommitHistory history = new CommitHistory();

    Commit c1 = history.createCommit("a", "msg1");
    Commit c2 = history.createCommit("b", "msg2");

    Commit result = history.getCommitById("2");

    assertEquals(c2, result);
  }

  @Test
  void testGetCommitByIdNotFound() {
    CommitHistory history = new CommitHistory();
    history.createCommit("a", "msg");

    assertNull(history.getCommitById("999"));
  }

  @Test
  void testGetCommitByIdNullOrEmpty() {
    CommitHistory history = new CommitHistory();

    assertNull(history.getCommitById(null));
    assertNull(history.getCommitById(""));
  }
}