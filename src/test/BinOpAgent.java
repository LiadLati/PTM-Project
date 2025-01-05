package test;

import java.util.function.BinaryOperator;
import test.TopicManagerSingleton.TopicManager;

public class BinOpAgent {
    private Agent agent;
    private String agentName;
    private String firstTopicName;
    private String secondTopicName;
    private String outputTopicName;
    private BinaryOperator<Double> operator;

    public BinOpAgent(String agentName, String FirstTopicName, String SecondTopicName, String OutputTopicName, BinaryOperator<Double> op){

        this.agentName = agentName;
        this.firstTopicName = FirstTopicName;
        this.secondTopicName = SecondTopicName;
        this.outputTopicName = OutputTopicName;
        this.operator = op;

        TopicManager tm = TopicManagerSingleton.get();
        Topic firstTopic = tm.getTopic(FirstTopicName);
        Topic secondTopic = tm.getTopic(SecondTopicName);
        Topic outputTopic = tm.getTopic(OutputTopicName);

        this.agent = new Agent() {
            @Override
            public String getName() {
                return agentName;
            }

            @Override
            public void reset() {
                firstTopic.publish(new Message(0.0));
                secondTopic.publish(new Message(0.0));
            }

            @Override
            public void callback(String topic, Message msg) {
                if (topic.equals(firstTopicName)) {
                    double x = msg.asDouble;
                    double y = (secondTopic.getMsg() != null) ? secondTopic.getMsg().asDouble : 0;
                    double result = operator.apply(x, y);
                    outputTopic.publish(new Message(result));
                } else if (topic.equals(secondTopicName)) {
                    double y = msg.asDouble;
                    double x = (firstTopic.getMsg() != null) ? firstTopic.getMsg().asDouble : 0;
                    double result = operator.apply(x, y);
                    outputTopic.publish(new Message(result));
                }
            }

            @Override
            public void close() {

            }
        };
        firstTopic.subscribe(this.agent);
        secondTopic.subscribe(this.agent);
        outputTopic.addPublisher(this.agent);

    }
}
