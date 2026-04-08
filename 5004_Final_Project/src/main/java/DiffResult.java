import java.util.ArrayList;
import java.util.List;

public class DiffResult {
  private List<String> diffLines;

  public DiffResult() {
    this.diffLines = new ArrayList<>();
  }

  public void addLine(String line) {
    diffLines.add(line);
  }

  public List<String> getDiffLines() {
    return new ArrayList<>(diffLines);
  }

  @Override
  public String toString() {
    if (diffLines.isEmpty()) {
      return "No differences found.";
    }
    return String.join("\n", diffLines);
  }
}