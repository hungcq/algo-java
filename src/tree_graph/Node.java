package tree_graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by: HungCQ
 * Date: 10-Jul-20
 */
public class Node {
    public String name;
    public Set<Node> neighbors = new HashSet<>();
}
