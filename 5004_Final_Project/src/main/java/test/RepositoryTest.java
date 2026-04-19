package test;

import org.junit.jupiter.api.Test;
import repository.Repository;
import version.Commit;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest {

    @Test
    public void testCommit() {
        Repository repo = new Repository();
        repo.commit("Hello", "first");

        Commit c = repo.getCurrent();
        assertNotNull(c);
        assertEquals("first", c.getMessage());
    }

    @Test
    public void testShowHistory() {
        Repository repo = new Repository();
        repo.commit("A", "1");
        repo.commit("B", "2");

        // 这里只测试不报错（console方法）
        repo.showHistory();
        assertNotNull(repo.getCurrent());
    }

    @Test
    public void testShowCurrent() {
        Repository repo = new Repository();
        repo.commit("A", "1");

        repo.showCurrent();
        assertEquals("1", repo.getCurrent().getMessage());
    }

    @Test
    public void testCompare() {
        Repository repo = new Repository();
        repo.commit("A", "1");
        repo.commit("B", "2");

        // 不报错即可（输出型方法）
        repo.compare("1", "2");
        assertNotNull(repo.getCurrent());
    }

    @Test
    public void testRevert() {
        Repository repo = new Repository();
        repo.commit("A", "1");
        repo.commit("B", "2");

        repo.revert("1");

        assertEquals("1", repo.getCurrent().getMessage());
    }

    @Test
    public void testGetCurrent() {
        Repository repo = new Repository();
        repo.commit("X", "msg");

        Commit c = repo.getCurrent();
        assertNotNull(c);
        assertEquals("msg", c.getMessage());
    }

    @Test
    public void testEmptyCommit() {
        Repository repo = new Repository();
        repo.commit("", "fail");

        assertNull(repo.getCurrent());
    }

    @Test
    public void testInvalidRevert() {
        Repository repo = new Repository();
        repo.commit("A", "1");

        repo.revert("999"); // 不存在

        // current 应该不变
        assertEquals("1", repo.getCurrent().getMessage());
    }

    @Test
    public void testMaxHistory() {
        Repository repo = new Repository();

        for (int i = 0; i < 5; i++) {
            repo.commit("v" + i, "m" + i);
        }

        repo.commit("overflow", "fail");

        // 最后一个不能是 overflow
        assertNotEquals("fail", repo.getCurrent().getMessage());
    }
}