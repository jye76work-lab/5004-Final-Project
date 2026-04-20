package repository;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryManagerTest {

  @Test
  void testConstructorInitializesRepository() {
    RepositoryManager manager = new RepositoryManager();
    assertNotNull(manager.getRepository(), "Repository should be initialized");
  }

  @Test
  void testGetRepositoryReturnsSameInstance() {
    RepositoryManager manager = new RepositoryManager();
    Repository repo1 = manager.getRepository();
    Repository repo2 = manager.getRepository();

    assertSame(repo1, repo2, "Should return the same Repository instance");
  }
}