package Graph;

import java.util.ArrayList;
import java.util.List;

public class Topic{

    public final String name;
    private List<Agent> subs;
    private List<Agent> pubs;

    Topic(String name){
        this.name=name;
        this.subs = new ArrayList<>();
        this.pubs = new ArrayList<>();
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
