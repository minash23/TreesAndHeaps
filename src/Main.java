import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ElectionSystem system = new ElectionSystem();
        List<String> candidates = Arrays.asList("Marcus Fenix", "Dominic Santiago", "Damon Baird", "Cole Train", "Anya Stroud");
        int p = 5;

        system.initializeElection(candidates, p);
        system.castVotes(p);
        system.getTopKCandidates(3);
        system.rigElection("Marcus Fenix");
        system.getTopKCandidates(3);
        system.auditElection();
    }
}