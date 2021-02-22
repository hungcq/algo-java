package leetcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Test2 {
    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        line = br.readLine();
        Map<Integer, Set<Integer>> graphMap = new HashMap<>();
        while (!line.equals("")) {
            String[] nodeIds = line.split(" ");
            int sourceId = Integer.parseInt(nodeIds[0]);
            int destId = Integer.parseInt(nodeIds[1]);
            Set<Integer> dests = graphMap.get(sourceId);
            if (dests == null) {
                dests = new HashSet<>();
                graphMap.put(sourceId, dests);
            }
            dests.add(destId);
            line = br.readLine();
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Set<Integer> recursiveStackSet = new HashSet<>();
        for (Map.Entry<Integer, Set<Integer>> entry : graphMap.entrySet()) {
            int source = entry.getKey();
            if (visitedNodes.contains(source)) {
                continue;
            }
            if (DFS(source, visitedNodes, graphMap, recursiveStackSet)) {
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
    }

    static boolean DFS(int start, Set<Integer> visitedNodes, Map<Integer, Set<Integer>> graphMap,
                       Set<Integer> recursiveStackSet) {
        if (visitedNodes.contains(start)) {
            recursiveStackSet.remove(start);
            return false;
        }
        visitedNodes.add(start);
        recursiveStackSet.add(start);
        if (!graphMap.containsKey(start)) {
            recursiveStackSet.remove(start);
            return false;
        }
        for (int dest : graphMap.get(start)) {
            if (!visitedNodes.contains(dest) && DFS(dest, visitedNodes, graphMap, recursiveStackSet))
                return true;
            else if (recursiveStackSet.contains(dest))
                return true;
        }
        recursiveStackSet.remove(start);
        return false;
    }
}
