package diff;

import version.Commit;

public interface DiffEngine {
  DiffResult compare(Commit c1, Commit c2);
}