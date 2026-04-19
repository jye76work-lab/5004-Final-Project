package diff;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiffResultTest {

  @Test
  void testAddLineAndGetDiffLines() {
    DiffResult result = new DiffResult();
    result.addLine("Line 1: same");
    result.addLine("Line 2: changed");

    assertEquals(List.of("Line 1: same", "Line 2: changed"), result.getDiffLines());
  }

  @Test
  void testGetDiffLinesReturnsCopy() {
    DiffResult result = new DiffResult();
    result.addLine("Line 1: same");

    var lines = result.getDiffLines();
    lines.add("fake line");

    assertEquals(1, result.getDiffLines().size());
  }

  @Test
  void testToStringWhenEmpty() {
    DiffResult result = new DiffResult();

    assertEquals("No differences found.", result.toString());
  }

  @Test
  void testToStringWhenNotEmpty() {
    DiffResult result = new DiffResult();
    result.addLine("Line 1: same");
    result.addLine("Line 2: changed");

    assertEquals("Line 1: same\nLine 2: changed", result.toString());
  }
}