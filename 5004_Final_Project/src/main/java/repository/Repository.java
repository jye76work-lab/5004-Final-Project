package repository;

import java.util.List;

import version.Commit;
import version.CommitHistory;
import diff.DiffEngine;
import diff.SimpleDiffEngine;
import diff.DiffResult;
import diff.RevertService;

/**
 * Central controller of the version control system.
 * Handles commit creation, history tracking, comparison, and revert operations.
 */
public class Repository {

    private CommitHistory history;
    private DiffEngine diffEngine;
    private RevertService revertService;
    private Commit current;
    private int maxHistory = 5;

    /**
     * Initializes repository with default components.
     */
    public Repository() {
        history = new CommitHistory();
        diffEngine = new SimpleDiffEngine();
        revertService = new RevertService();
        current = null;
    }

    /**
     * Creates a new commit.
     *
     * @param content file content
     * @param message commit message
     */
    public void commit(String content, String message) {

        if (content == null || content.isEmpty()) {
            System.out.println("[ERROR] empty content");
            return;
        }

        if (history.getHistory().size() >= maxHistory) {
            System.out.println("[ERROR] max history reached. Cannot commit.");
            return;
        }

        Commit c = history.createCommit(content, message);
        current = c;

        System.out.println("\n===== COMMIT =====");
        System.out.println("ID: " + c.getId());
        System.out.println("Message: " + c.getMessage());
        System.out.println("Time: " + c.getTimestamp());
        System.out.println("==================\n");
    }

    /**
     * Displays all commit history.
     */
    public void showHistory() {
        List<Commit> list = history.getHistory();

        System.out.println("\n===== HISTORY =====");
        for (Commit c : list) {
            System.out.println(c.getId() + " | " + c.getMessage());
        }
        System.out.println("===================\n");
    }

    /**
     * Displays the current commit.
     */
    public void showCurrent() {

        if (current == null) {
            System.out.println("No current version");
            return;
        }

        System.out.println("\n===== CURRENT =====");
        System.out.println("ID: " + current.getId());
        System.out.println("Message: " + current.getMessage());
        System.out.println("Time: " + current.getTimestamp());
        System.out.println("===================\n");
    }

    /**
     * Compares two commits by id.
     *
     * @param id1 first commit id
     * @param id2 second commit id
     */
    public void compare(String id1, String id2) {

        Commit c1 = history.getCommitById(id1);
        Commit c2 = history.getCommitById(id2);

        if (c1 == null || c2 == null) {
            System.out.println("[ERROR] invalid id");
            return;
        }

        DiffResult result = diffEngine.compare(c1, c2);

        System.out.println("\n===== DIFF =====");
        System.out.println(result);
        System.out.println("================\n");
    }

    /**
     * Reverts the system to a specific commit.
     *
     * @param id commit id
     */
    public void revert(String id) {

        Commit c = history.getCommitById(id);

        if (c == null) {
            System.out.println("[ERROR] commit not found");
            return;
        }

        current = c;
        String content = revertService.revertTo(c);

        System.out.println("\n===== REVERT =====");
        System.out.println("Now at: " + c.getId());
        System.out.println("Content:");
        System.out.println(content);
        System.out.println("==================\n");
    }

    /**
     * Returns current commit.
     *
     * @return current commit
     */
    public Commit getCurrent() {
        return current;
    }

    /**
     * Runs a full demo of the system.
     * Useful for presentation.
     */
    public void demoRun() {

        System.out.println("\n===== DEMO START =====");

        // step 1: commit
        System.out.println("\n[STEP 1] Create commits");
        commit("Hello World", "initial version");
        commit("Hello Java", "update content");

        // step 2: history
        System.out.println("\n[STEP 2] Show history");
        showHistory();

        // step 3: compare
        System.out.println("\n[STEP 3] Compare commits");
        compare("1", "2");

        // step 4: revert
        System.out.println("\n[STEP 4] Revert to commit 1");
        revert("1");

        // step 5: current
        System.out.println("\n[STEP 5] Show current version");
        showCurrent();

        System.out.println("\n===== DEMO END =====\n");
    }
}