package configs;

import java.util.*;

import configs.TopicManagerSingleton.TopicManager;

public class Graph extends ArrayList<Node> {
    private HashMap<String, Node> nodesMap;

    public Graph() {
        nodesMap = new HashMap<>();
    }

    public void createFromTopics() {
        this.clear();
        nodesMap.clear();

        TopicManager tm = TopicManagerSingleton.get();
        Collection<Topic> topics = tm.getTopics();

        for (Topic topic : topics) {
            String topicNodeName = "T" + topic.name;
            Node topicNode = nodesMap.get(topicNodeName);
            if(topicNode == null) {
                topicNode = new Node(topicNodeName);
                this.add(topicNode);
                nodesMap.put(topicNodeName, topicNode);
            }
        }
        for (Topic topic : topics) {
            String topicNodeName = "T" + topic.name;
            Node topicNode = nodesMap.get(topicNodeName);
            for(Agent subAgent : topic.getSubs())
            {
                Node subAgentNode = findNode(subAgent);
                topicNode.addEdge(subAgentNode);

            }
            for (Agent pubAgent : topic.getPubs())
            {
                Node pubAgentNode = findNode(pubAgent);
                pubAgentNode.addEdge(topicNode);
            }
        }

    }
    public Node findNode(Agent agent) {
        String agentNodeName = "A" + agent.getName();
        Node agentNode = nodesMap.get(agentNodeName);
        if(agentNode == null)
        {
            agentNode = new Node(agentNodeName);
            this.add(agentNode);
            nodesMap.put(agentNodeName, agentNode);
        }
        return agentNode;
    }

    public boolean hasCycles() {
        for(Node n : this)
        {
            if(n.hasCycles())
            {
                return true;
            }
        }
        return false;
    }

}


