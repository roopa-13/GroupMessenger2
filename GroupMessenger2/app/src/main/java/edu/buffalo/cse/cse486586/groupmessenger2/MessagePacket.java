package edu.buffalo.cse.cse486586.groupmessenger2;

public class MessagePacket {
    private String message;
    private int processID;
    private String sequenceNo;
    private String serverSeqNo;
    private boolean devliveryFlag;

    MessagePacket(){
        this.message = "";
        this.processID = 0;
        this.sequenceNo = "";
        this.serverSeqNo = "";
        this.devliveryFlag = false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getProcessID() {
        return processID;
    }

    public void setProcessID(int processID) {
        this.processID = processID;
    }

    public String getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public boolean isDevliveryFlag() {
        return devliveryFlag;
    }

    public void setDevliveryFlag(boolean devliveryFlag) {
        this.devliveryFlag = devliveryFlag;
    }

    public String getServerSeqNo() {
        return serverSeqNo;
    }

    public void setServerSeqNo(String serverSeqNo) {
        this.serverSeqNo = serverSeqNo;
    }
}
