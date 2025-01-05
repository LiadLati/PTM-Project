package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Node {
    private String name;
    private List<Node> edges;
    private Message msg;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
        this.msg = null;
    }

    public String getName() {
        return name;
    }

    public List<Node> getEdges() {
        return edges;
    }

    public Message getMsg() {
        return msg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEdges(List<Node> edges) {
        this.edges = edges;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public void addEdge(Node node) {
        if (!edges.contains(node)) {
            edges.add(node);
        }
    }
    public void removeEdge(Node node) {
        if(edges.contains(node)) {
            edges.remove(node);
        }
    }

    public boolean hasCycles(){
        Set<Node> visited = new HashSet<>();
        Set<Node> stack = new HashSet<>();
        for(Node n : edges){
            if(!visited.contains(n)){
                if(recursiveNode(n, visited, stack)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean recursiveNode(Node current, Set<Node> visited, Set<Node> stack) {
        stack.add(current);
        visited.add(current);

        for (Node s : current.getEdges()) {
            if (!visited.contains(s)) {
                if (recursiveNode(s, visited, stack))
                    return true;
            }
            else if (stack.contains(s))
                return true;
        }
        stack.remove(current);
        return false;

    }
}

