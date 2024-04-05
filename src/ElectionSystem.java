import java.util.Collections;
import java.util.List;

class ElectionSystem {
    Election election;

    public ElectionSystem() {
        election = new Election();
    }

    public void initializeElection(List<String> candidates, int electorateVotes) {
        election.initializeCandidates(candidates);
        System.out.println("Initialized Election with " + candidates.size() + " candidates.");
    }

    public void castVotes(int numberOfVotes) {
        System.out.println("Casting " + numberOfVotes + " votes:");
        for (int i = 0; i < numberOfVotes; i++) {
            election.castRandomVote();
        }
    }

    public void rigElection(String candidateName) {
        System.out.println("Rigging election for candidate: " + candidateName);
        election.rigElection(candidateName);
    }

    public void getTopKCandidates(int k) {
        List<String> topCandidates = election.getTopKCandidates(k);
        System.out.println("Top " + k + " candidates:");
        System.out.println(topCandidates);
    }

    public void auditElection() {
        election.auditElection();
    }
}
