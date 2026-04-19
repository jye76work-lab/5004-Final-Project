package snapshot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileSnapshotTest {

  @Test
  void testGetFileName() {
    FileSnapshot snapshot = new FileSnapshot("test.txt",
        List.of("line1", "line2"));

    assertEquals("test.txt", snapshot.getFileName());
  }

  @Test
  void testGetLinesReturnsCopy() {
    FileSnapshot snapshot = new FileSnapshot("test.txt",
        List.of("line1", "line2"));

    var lines = snapshot.getLines();
    lines.add("line3");

    assertEquals(2, snapshot.getLines().size());
    assertEquals(List.of("line1", "line2"), snapshot.getLines());
  }

  @Test
  void testGetContent() {
    FileSnapshot snapshot = new FileSnapshot("test.txt",
        List.of("line1", "line2", "line3"));

    assertEquals("line1\nline2\nline3", snapshot.getContent());
  }
}