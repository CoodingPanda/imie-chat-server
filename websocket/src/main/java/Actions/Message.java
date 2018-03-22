package Actions;

public class Message extends Action {

    private String Textmsg;
    private int Msg_id;
    private String MessageDate;



    public String getMessageDate() {
        return MessageDate;
    }

    public void setMessageDate(String messageDate) {
        MessageDate = messageDate;
    }

    public String getTextmsg() {
        return Textmsg;
    }

    public void setTextmsg(String textmsg) {
        Textmsg = textmsg;
    }

    public int getMsg_id() {
        return Msg_id;
    }

    public void setMsg_id(int msg_id) {
        Msg_id = msg_id;
    }
}
