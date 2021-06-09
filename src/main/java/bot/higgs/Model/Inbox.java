package bot.higgs.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "inboxes", schema = "bot_playgo", catalog = "")
public class Inbox {
    private int id;
    private String message;
    private String sender;
    private Integer status;
    private Date createDate;
    private String trxId;

    public Inbox(String message, String sender, Integer status, Date createDate, String trxId) {
        this.message = message;
        this.sender = sender;
        this.status = status;
        this.createDate = createDate;
        this.trxId = trxId;
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
    @Column(name = "message", nullable = true, length = 255)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "sender", nullable = true, length = 255)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    @Column(name = "trx_id", nullable = true, length = 255)
    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inbox inbox = (Inbox) o;
        return id == inbox.id &&
                Objects.equals(message, inbox.message) &&
                Objects.equals(sender, inbox.sender) &&
                Objects.equals(status, inbox.status) &&
                Objects.equals(createDate, inbox.createDate) &&
                Objects.equals(trxId, inbox.trxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, sender, status, createDate, trxId);
    }
}
