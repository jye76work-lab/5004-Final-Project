package repository;

import java.util.List;

import version.Commit;
import version.CommitHistory;
import diff.DiffEngine;
import diff.SimpleDiffEngine;
import diff.DiffResult;
import diff.RevertService;

public class Repository {

    private CommitHistory history;
    private DiffEngine diffEngine;
    private RevertService revertService;
    private Commit current;

    public Repository() {
        history = new CommitHistory();
        diffEngine = new SimpleDiffEngine();
        revertService = new RevertService();
        current = null;
    }

    public void commit(String content, String message) {

        if (content == null || content.length() == 0) {
            throw new IllegalArgumentException("empty content");
        }

        Commit c = history.createCommit(content, message);
        current = c;

        System.out.println("commit " + c.getId());
    }

    public void showHistory() {

        List<Commit> list = history.getHistory();

        System.out.println("history:");
        for (Commit c : list) {
            System.out.println(c.getId() + " : " + c.getMessage());
        }
    }

    public void compare(String id1, String id2) {

        Commit c1 = history.getCommitById(id1);
        Commit c2 = history.getCommitById(id2);

        if (c1 == null || c2 == null) {
            System.out.println("invalid id");
            return;
        }

        DiffResult r = diffEngine.compare(c1, c2);
        System.out.println(r);
    }

    public void revert(String id) {

        Commit c = history.getCommitById(id);

        if (c == null) {
            System.out.println("not found");
            return;
        }

        String content = revertService.revertTo(c);
        current = c;

        System.out.println("revert to " + c.getId());
        System.out.println(content);
    }

    public Commit getCurrent() {
        return current;
    }
}