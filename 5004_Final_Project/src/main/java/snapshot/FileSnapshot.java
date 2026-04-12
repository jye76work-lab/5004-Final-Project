package snapshot;

import java.util.ArrayList;
import java.util.List;

public class FileSnapshot {
  private String fileName;
  private List<String> lines;

  public FileSnapshot(String fileName, List<String> lines) {
    this.fileName = fileName;
    this.lines = new ArrayList<>(lines);
  }

  public String getFileName() {
    return fileName;
  }

  public List<String> getLines() {
    return new ArrayList<>(lines);
  }

  public String getContent() {
    return String.join("\n", lines);
  }

  @Override
  public String toString() {
    return "snapshot.FileSnapshot{" +
        "fileName='" + fileName + '\'' +
        ", lines=" + lines +
        '}';
  }
}