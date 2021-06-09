package bot.higgs.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "outboxes", schema = "bot_playgo", catalog = "")
public class Outbox {
    private int id;
    private String message;
    private String receiver;
    private Date createDate;
    private Integer inboxId;

    public Outbox(String message, String receiver, Date createDate, Integer inboxId) {
        this.message = message;
        this.receiver = receiver;
        this.createDate = createDate;
        this.inboxId = inboxId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message", nullable = true, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "receiver", nullable = true, length = 255)
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Basic
    @Column(name = "create_date", nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "inbox_id", nullable = true)
    public Integer getInboxId() {
        return inboxId;
    }

    public void setInboxId(Integer inboxId) {
        this.inboxId = inboxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outbox outbox = (Outbox) o;
        return id == outbox.id &&
                Objects.equals(message, outbox.message) &&
                Objects.equals(receiver, outbox.receiver) &&
                Objects.equals(createDate, outbox.createDate) &&
                Objects.equals(inboxId, outbox.inboxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, receiver, createDate, inboxId);
    }
}
