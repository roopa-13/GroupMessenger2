package edu.buffalo.cse.cse486586.groupmessenger2;

import java.util.Comparator;

public class MessageComparator implements Comparator<MessagePacket> {


    @Override
    public int compare(MessagePacket lhs, MessagePacket rhs) {

        /* Comparing the values of the sequence no, if there is a tie then
        the process with the lower pid is chosen. */

        double seq1 = Double.valueOf(lhs.getServerSeqNo());
        double seq2 = Double.valueOf(rhs.getServerSeqNo());

        int cl1 = lhs.getProcessID();
        int cl2 = rhs.getProcessID();

        if(seq1 > seq2){
            return 1;
        }
        else if (seq1 < seq2){
            return -1;
        }
        else {
            if(cl1 < cl2){
                return 1;
            }
            else if(cl1 > cl2){
                return -1;
            }
            else {
                return 0;
            }
        }

    }
}
