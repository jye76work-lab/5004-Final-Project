package diff;

import org.junit.jupiter.api.Test;
import snapshot.FileSnapshot;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import version.Commit;

class SimpleDiffEngineTest {

  private Commit makeCommit(String id, String message, String fileName, List<String> lines) {
    FileSnapshot snapshot = new FileSnapshot(fileName, lines);
    return new Commit(id, message, null, snapshot);
  }

  @Test
  void testCompareSameContent() {
    SimpleDiffEngine engine = new SimpleDiffEngine();

    Commit c1 = makeCommit("1", "first", "test.txt",
        List.of("line1", "line2"));
    Commit c2 = makeCommit("2", "second", "test.txt",
        List.of("line1", "line2"));

    DiffResult result = engine.compare(c1, c2);

    assertEquals(List.of(
        "Line 1: same",
        "Line 2: same"
    ), result.getDiffLines());
  }

  @Test
  void testCompareChangedLine() {
    SimpleDiffEngine engine = new SimpleDiffEngine();

    Commit c1 = makeCommit("1", "first", "test.txt",
        List.of("line1", "old line"));
    Commit c2 = makeCommit("2", "second", "test.txt",
        List.of("line1", "new line"));

    DiffResult result = engine.compare(c1, c2);

    assertEquals(List.of(
        "Line 1: same",
        "Line 2: changed",
        "   old: old line",
        "   new: new line"
    ), result.getDiffLines());
  }

  @Test
  void testCompareAddedLine() {
    SimpleDiffEngine engine = new SimpleDiffEngine();

    Commit c1 = makeCommit("1", "first", "test.txt",
        List.of("line1", "line2"));
    Commit c2 = makeCommit("2", "second", "test.txt",
        List.of("line1", "line2", "line3"));

    DiffResult result = engine.compare(c1, c2);

    assertEquals(List.of(
        "Line 1: same",
        "Line 2: same",
        "Line 3: added -> line3"
    ), result.getDiffLines());
  }

  @Test
  void testCompareRemovedLine() {
    SimpleDiffEngine engine = new SimpleDiffEngine();

    Commit c1 = makeCommit("1", "first", "test.txt",
        List.of("line1", "line2", "line3"));
    Commit c2 = makeCommit("2", "second", "test.txt",
        List.of("line1", "line2"));

    DiffResult result = engine.compare(c1, c2);

    assertEquals(List.of(
        "Line 1: same",
        "Line 2: same",
        "Line 3: removed -> line3"
    ), result.getDiffLines());
  }
}