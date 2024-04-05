import java.util.*;

class Election {
    PriorityQueue<Map.Entry<String, Integer>> maxHeap;
    Map<String, Integer> candidatesMap;

    public Election() {
        maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        candidatesMap = new HashMap<>();
    }

    public void initializeCandidates(List<String> candidates) {
        for (String candidate : candidates) {
            candidatesMap.put(candidate, 0);
        }
        maxHeap.addAll(candidatesMap.entrySet());
    }

    public void castVote(String candidateName) {
        Integer votes = candidatesMap.getOrDefault(candidateName, 0);
        candidatesMap.put(candidateName, votes + 1);
        refreshHeap();
    }

    public void castRandomVote() {
        List<String> candidatesList = new ArrayList<>(candidatesMap.keySet());
        Random random = new Random();
        String randomCandidate = candidatesList.get(random.nextInt(candidatesList.size()));
        castVote(randomCandidate);
    }

    public void rigElection(String candidateName) {
        Integer currentVotes = candidatesMap.getOrDefault(candidateName, 0);
        int totalVotes = candidatesMap.values().stream().mapToInt(Integer::intValue).sum();
        int votesNeeded = totalVotes + 1;
        candidatesMap.put(candidateName, votesNeeded);
        refreshHeap();
    }

    public List<String> getTopKCandidates(int k) {
        List<String> topCandidates = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            topCandidates.add(maxHeap.poll().getKey());
        }
        return topCandidates;
    }

    public void auditElection() {
        System.out.println("Audit of Election:");
        for (Map.Entry<String, Integer> entry : maxHeap) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    private void refreshHeap() {
        maxHeap.clear();
        maxHeap.addAll(candidatesMap.entrySet());
    }
}