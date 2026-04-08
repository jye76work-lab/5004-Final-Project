public interface DiffEngine {
  DiffResult compare(Commit c1, Commit c2);
}