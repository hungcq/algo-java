package tree_graph;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by: HungCQ
 * Date: 10-Jul-20
 */
public class TopologicalSortTest {
    @Test
    public void topologicalSort() {
        Graph graph = new Graph();
        Node a = new Node();
        a.name = "a";
        Node b = new Node();
        b.name = "b";
        Node c = new Node();
        c.name = "c";
        Node d = new Node();
        d.name = "d";
        Node e = new Node();
        e.name = "e";
        Node f = new Node();
        f.name = "f";
        a.neighbors.add(d);
        f.neighbors.add(b);
        b.neighbors.add(d);
        f.neighbors.add(a);
        d.neighbors.add(c);
//        d.neighbors.add(f);
        graph.nodeList.add(a);
        graph.nodeList.add(b);
        graph.nodeList.add(c);
        graph.nodeList.add(d);
        graph.nodeList.add(e);
        graph.nodeList.add(f);
        List<Node> list = TopologicalSort.sort(graph);
        for (Node node : list) {
            System.out.println(node.name);
        }
    }
}