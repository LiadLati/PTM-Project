package test;

import java.util.ArrayList;
import java.util.List;

public class Topic{

    public final String name;
    public List<Agent> subs = new ArrayList<>();
    public List<Agent> pubs = new ArrayList<>();
    public Message Message;

    Topic(String name){
        this.name=name;
    }
    public Message getMsg(){
        return Message;
    }

    public void subscribe(Agent a){
        if(!subs.contains(a)){
            subs.add(a);
        }
    }
    public void unsubscribe(Agent a){
        if(subs.contains(a)){
            subs.remove(a);
        }
     }

    public void publish(Message m){
        Message = m;
        for(Agent a : subs){
            a.callback(this.name,m);
        }
    }

    public void addPublisher(Agent a){
        if(!pubs.contains(a)) {
            pubs.add(a);
        }
    }

    public void removePublisher(Agent a){
        if(pubs.contains(a)) {
            pubs.remove(a);
        }
    }
    String getTopicName() {
        return name;
    }

    List<Agent> getSubs(){
        return subs;
    }

    List<Agent> getPubs(){
        return pubs;
    }


}

