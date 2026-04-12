package main;

import repository.Repository;

public class Main {

    public static void main(String[] args) {

        Repository repo = new Repository();

        repo.commit("Hello", "first");
        repo.commit("Hello World", "second");
        repo.commit("Hello World!!!", "third");

        System.out.println();

        repo.showHistory();

        System.out.println();

        repo.compare("1", "2");

        System.out.println();

        repo.revert("1");
    }
}