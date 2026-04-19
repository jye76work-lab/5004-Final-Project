package diff;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of a diff comparison.
 * Stores formatted differences between two file versions.
 */
public class DiffResult {

  /** List of diff output lines */
  private List<String> diffLines;

  /**
   * Constructs an empty DiffResult.
   */
  public DiffResult() {
    this.diffLines = new ArrayList<>();
  }

  /**
   * Adds a formatted diff line.
   *
   * @param line the diff line to add
   */
  public void addLine(String line) {
    diffLines.add(line);
  }

  /**
   * Returns all diff lines.
   *
   * @return list of diff lines
   */
  public List<String> getDiffLines() {
    return new ArrayList<>(diffLines);
  }

  /**
   * Returns the formatted diff result as a string.
   *
   * @return formatted diff output
   */
  @Override
  public String toString() {
    if (diffLines.isEmpty()) {
      return "No differences found.";
    }
    return String.join("\n", diffLines);
  }
}