package diff;

import java.util.List;
import snapshot.FileSnapshot;
import version.Commit;

/**
 * A simple implementation of DiffEngine.
 * Performs line-by-line comparison between two commits.
 */
public class SimpleDiffEngine implements DiffEngine {

  /**
   * Compares two commits line by line and identifies differences.
   *
   * @param c1 the first commit (older version)
   * @param c2 the second commit (newer version)
   * @return a DiffResult containing the differences
   */
  @Override
  public DiffResult compare(Commit c1, Commit c2) {
    DiffResult result = new DiffResult();

    FileSnapshot s1 = c1.getSnapshot();
    FileSnapshot s2 = c2.getSnapshot();

    List<String> lines1 = s1.getLines();
    List<String> lines2 = s2.getLines();

    int max = Math.max(lines1.size(), lines2.size());

    for (int i = 0; i < max; i++) {
      int lineNumber = i + 1;

      if (i >= lines1.size()) {
        result.addLine("Line " + lineNumber + ": added -> " + lines2.get(i));
      } else if (i >= lines2.size()) {
        result.addLine("Line " + lineNumber + ": removed -> " + lines1.get(i));
      } else if (lines1.get(i).equals(lines2.get(i))) {
        result.addLine("Line " + lineNumber + ": same");
      } else {
        result.addLine("Line " + lineNumber + ": changed");
        result.addLine("   old: " + lines1.get(i));
        result.addLine("   new: " + lines2.get(i));
      }
    }

    return result;
  }
}