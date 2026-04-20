package version;

import org.junit.jupiter.api.Test;
import snapshot.FileSnapshot;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommitTest {

  static class MockSnapshot extends FileSnapshot {
    public MockSnapshot() {
      super("test.txt", List.of("content"));
    }
  }

  @Test
  void testConstructorValid() {
    FileSnapshot snapshot = new MockSnapshot();
    Commit commit = new Commit("1", "first commit", null, snapshot);

    assertEquals("1", commit.getId());
    assertEquals("first commit", commit.getMessage());
    assertNotNull(commit.getTimestamp());
    assertNull(commit.getParent());
    assertEquals(snapshot, commit.getSnapshot());
  }

  @Test
  void testConstructorWithParent() {
    FileSnapshot snapshot1 = new MockSnapshot();
    FileSnapshot snapshot2 = new MockSnapshot();

    Commit parent = new Commit("1", "parent commit", null, snapshot1);
    Commit child = new Commit("2", "child commit", parent, snapshot2);

    assertEquals(parent, child.getParent());
    assertEquals(snapshot2, child.getSnapshot());
  }

  @Test
  void testNullSnapshotThrowsException() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Commit("1", "message", null, null)
    );

    assertEquals("Snapshot cannot be null.", exception.getMessage());
  }

  @Test
  void testToStringContainsImportantFields() {
    FileSnapshot snapshot = new MockSnapshot();
    Commit commit = new Commit("1", "test commit", null, snapshot);

    String result = commit.toString();

    assertTrue(result.contains("id='1'"));
    assertTrue(result.contains("message='test commit'"));
    assertTrue(result.contains("timestamp="));
  }
}