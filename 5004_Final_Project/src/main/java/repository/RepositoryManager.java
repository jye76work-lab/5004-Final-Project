package repository;

public class RepositoryManager {

    private Repository repo;

    public RepositoryManager() {
        repo = new Repository();
    }

    public Repository getRepository() {
        return repo;
    }
}