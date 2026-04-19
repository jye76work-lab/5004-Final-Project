package diff;

import org.junit.jupiter.api.Test;
import snapshot.FileSnapshot;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


import version.Commit;

class RevertServiceTest {

  @Test
  void testRevertToReturnsContent() {
    FileSnapshot snapshot = new FileSnapshot("test.txt",
        List.of("line1", "line2", "line3"));
    Commit commit = new Commit("1", "first",null, snapshot);

    RevertService service = new RevertService();
    String content = service.revertTo(commit);

    assertEquals("line1\nline2\nline3", content);
  }

  @Test
  void testRevertToNullCommitThrowsException() {
    RevertService service = new RevertService();

    assertThrows(IllegalArgumentException.class, () -> service.revertTo(null));
  }
}