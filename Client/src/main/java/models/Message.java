package models;



/* 
 * POJO for an Message object
 *
 *   {
    "sequence": "-",
    "timestamp": "_",
    "fromid": "xt0fer",
    "toid": "kristofer",
    "message": "Hello, Kristofer!"
  },

*
 */

public class Message implements Comparable {

    private String message = "";
    private String toId = "";
    private String fromId = "";
    private String timestamp = "";
    private String seqId = "";

    public Message (String message, String fromId, String toId, String seqId, String timestamp) {
        this.message = message;
        this.fromId = fromId;
        this.toId = toId;
        this.seqId = seqId;
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", toId='" + toId + '\'' +
                ", fromId='" + fromId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", seqId='" + seqId + '\'' +
                '}';
    }

    public int compareTo(Object o) {
        return this.seqId.compareTo(((Message) o).getSeqId());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSeqId() {
        return seqId;
    }
}