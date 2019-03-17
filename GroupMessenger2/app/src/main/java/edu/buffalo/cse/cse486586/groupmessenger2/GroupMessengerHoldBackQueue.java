package edu.buffalo.cse.cse486586.groupmessenger2;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class GroupMessengerHoldBackQueue {

    //The below reference has been used to understand and implement Priority Queue and Custom Comparator
    //https://howtodoinjava.com/java/collections/java-priorityqueue/
    PriorityQueue<MessagePacket> holdbackQueue = new PriorityQueue<MessagePacket>(5, new MessageComparator());

    public void add(MessagePacket messagePacket){
        holdbackQueue.add(messagePacket);
    }

    public MessagePacket poll(){
        if(holdbackQueue == null) throw new NoSuchElementException();
        return holdbackQueue.poll();
    }

    public MessagePacket peak(){
        if(holdbackQueue == null) throw new NoSuchElementException();
        return holdbackQueue.peek();
    }



}

