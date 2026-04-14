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
    private int maxHistory = 5;

    public Repository() {
        history = new CommitHistory();
        diffEngine = new SimpleDiffEngine();
        revertService = new RevertService();
        current = null;
    }

    public void commit(String content, String message) {

        if (content == null || content.length() == 0) {
            System.out.println("[ERROR] empty content");
            return;
        }

        if (history.getHistory().size() >= maxHistory) {
            System.out.println("[INFO] max history reached");
        }

        Commit c = history.createCommit(content, message);
        current = c;

        System.out.println("\n========== COMMIT ==========");
        System.out.println("New Commit Created");
        System.out.println("ID: " + c.getId());
        System.out.println("Message: " + message);
        System.out.println("Time: " + System.currentTimeMillis());
        System.out.println("============================\n");
    }

    public void showHistory() {

        List<Commit> list = history.getHistory();

        System.out.println("\n===== VERSION HISTORY =====");
        for (Commit c : list) {
            System.out.println("Commit " + c.getId() + " | " + c.getMessage());
        }
        System.out.println("===========================\n");
    }

    public void showCurrent() {

        if (current == null) {
            System.out.println("No current version");
            return;
        }

        System.out.println("\n===== CURRENT VERSION =====");
        System.out.println("ID: " + current.getId());
        System.out.println("Message: " + current.getMessage());
        System.out.println("===========================\n");
    }

    public void compare(String id1, String id2) {

        Commit c1 = history.getCommitById(id1);
        Commit c2 = history.getCommitById(id2);

        if (c1 == null || c2 == null) {
            System.out.println("[ERROR] invalid id");
            return;
        }

        System.out.println("\n====== DIFF RESULT ======");
        DiffResult r = diffEngine.compare(c1, c2);
        System.out.println(r);
        System.out.println("=========================\n");
    }

    public void revert(String id) {

        Commit c = history.getCommitById(id);

        if (c == null) {
            System.out.println("[ERROR] commit not found");
            return;
        }

        String content = revertService.revertTo(c);
        current = c;

        System.out.println("\n====== REVERT ======");
        System.out.println("Switched to commit " + c.getId());
        System.out.println("Content:");
        System.out.println(content);
        System.out.println("====================\n");
    }

    public Commit getCurrent() {
        return current;
    }

    public void demoRun() {

        System.out.println("\n===== DEMO RUN =====");

        commit("Hello", "first version");
        commit("Hello World", "second version");

        showHistory();

        compare("1", "2");

        revert("1");

        showCurrent();

        System.out.println("====================\n");
    }
}