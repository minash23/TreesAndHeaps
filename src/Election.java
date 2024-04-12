import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

class Election {
    private Map<String, Integer> candidates;
    private PriorityQueue<Map.Entry<String, Integer>> maxHeap;
    private int totalVotes;

    Election() {
        candidates = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        totalVotes = 0;
    }

    public void initializeCandidates(LinkedList<String> candidatesList) {
        for (String candidate : candidatesList) {
            candidates.put(candidate, 0);
        }
    }

    public void castVote(String candidate) {
        if (candidates.containsKey(candidate)) {
            candidates.put(candidate, candidates.get(candidate) + 1);
            totalVotes++; // Increment total votes
            refreshHeap();
        } else {
            System.out.println("Candidate not found.");
        }
    }

    public void castRandomVote() {
        Random random = new Random();
        LinkedList<String> candidateList = new LinkedList<>(candidates.keySet());
        String randomCandidate = candidateList.get(random.nextInt(candidateList.size()));
        castVote(randomCandidate);
    }

    public void rigElection(String candidate, int p) {
        if (candidates.containsKey(candidate)) {
            int currentVotes = candidates.get(candidate);
            int additionalVotes = p;

            totalVotes += additionalVotes;

            candidates.put(candidate, currentVotes + p);
            refreshHeap();
        } else {
            System.out.println("Candidate not found.");
        }
    }

    public LinkedList<String> getTopKCandidates(int k) {
        LinkedList<String> topCandidates = new LinkedList<>();
        PriorityQueue<Map.Entry<String, Integer>> tempHeap = new PriorityQueue<>(maxHeap);

        for (int i = 0; i < k; i++) {
            Map.Entry<String, Integer> entry = tempHeap.poll();
            if (entry != null) {
                topCandidates.add(entry.getKey());
            }
        }
        return topCandidates;
    }

    public void auditElection() {
        for (Map.Entry<String, Integer> entry : maxHeap) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    private void refreshHeap() {
        maxHeap.clear();
        maxHeap.addAll(candidates.entrySet());
    }
}