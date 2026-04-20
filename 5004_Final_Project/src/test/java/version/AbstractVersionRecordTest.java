package version;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AbstractVersionRecordTest {

  static class TestVersionRecord extends AbstractVersionRecord {
    public TestVersionRecord(String id, String message) {
      super(id, message);
    }
  }

  @Test
  void testConstructorValidInput() {
    TestVersionRecord record = new TestVersionRecord("123", "Initial commit");

    assertEquals("123", record.getId());
    assertEquals("Initial commit", record.getMessage());
    assertTrue(record.getTimestamp() > 0);
  }

  @Test
  void testConstructorEmptyId() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new TestVersionRecord("", "msg");
    });

    assertEquals("ID cannot be empty.", exception.getMessage());
  }

  @Test
  void testConstructorNullId() {
    assertThrows(IllegalArgumentException.class, () -> {
      new TestVersionRecord(null, "msg");
    });
  }

  @Test
  void testConstructorEmptyMessage() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new TestVersionRecord("123", "");
    });

    assertEquals("Message cannot be empty.", exception.getMessage());
  }

  @Test
  void testConstructorNullMessage() {
    assertThrows(IllegalArgumentException.class, () -> {
      new TestVersionRecord("123", null);
    });
  }
}