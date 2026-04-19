package snapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a snapshot of a file at a specific commit.
 * Stores file name and its content as a list of lines.
 */
public class FileSnapshot {

  /** Name of the file */
  private String fileName;

  /** Content of the file, stored line by line */
  private List<String> lines;

  /**
   * Constructs a FileSnapshot with given file name and content.
   *
   * @param fileName the name of the file
   * @param lines the content of the file as a list of lines
   */
  public FileSnapshot(String fileName, List<String> lines) {
    this.fileName = fileName;
    this.lines = new ArrayList<>(lines);
  }

  /**
   * Returns the file name.
   *
   * @return file name
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Returns a copy of file content lines.
   *
   * @return list of lines
   */
  public List<String> getLines() {
    return new ArrayList<>(lines);
  }

  /**
   * Returns the full content as a single string.
   *
   * @return file content joined by newline
   */
  public String getContent() {
    return String.join("\n", lines);
  }
}