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
    private String toid = "";
    private String fromid = "";
    private String timestamp = null;
    private String sequence = "";

    public Message (String message, String fromid, String toid, String sequence, String timestamp) {
        this.message = message;
        this.fromid = fromid;
        this.toid = toid;
        this.sequence = sequence;
        this.timestamp = timestamp;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", toId='" + toid + '\'' +
                ", fromId='" + fromid + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", seqId='" + sequence + '\'' +
                '}';
    }

    public int compareTo(Object o) {
        return this.sequence.compareTo(((Message) o).getSequence());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSequence() {
        return sequence;
    }
}