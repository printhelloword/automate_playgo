package bot.playgo.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "inbox", schema = "dbo", catalog = "otomax")
public class InboxMsSql {
    private int kode;
    private Timestamp tglEntri;
    private String penerima;
    private String pengirim;
    private String tipePengirim;
    private String pesan;
    private byte status;
    private Integer kodeTerminal;
    private Timestamp tglStatus;
    private String kodeReseller;
    private Integer kodeTransaksi;
    private byte isJawaban;
    private String serviceCenter;
    private Byte isCs;
    private Integer kodeJawabanCs;

    @Id
    @Column(name = "kode", nullable = false)
    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    @Basic
    @Column(name = "tgl_entri", nullable = false)
    public Timestamp getTglEntri() {
        return tglEntri;
    }

    public void setTglEntri(Timestamp tglEntri) {
        this.tglEntri = tglEntri;
    }

    @Basic
    @Column(name = "penerima", nullable = true, length = 255)
    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    @Basic
    @Column(name = "pengirim", nullable = false, length = 255)
    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    @Basic
    @Column(name = "tipe_pengirim", nullable = false, length = 1)
    public String getTipePengirim() {
        return tipePengirim;
    }

    public void setTipePengirim(String tipePengirim) {
        this.tipePengirim = tipePengirim;
    }

    @Basic
    @Column(name = "pesan", nullable = false, length = 4000)
    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "kode_terminal", nullable = true)
    public Integer getKodeTerminal() {
        return kodeTerminal;
    }

    public void setKodeTerminal(Integer kodeTerminal) {
        this.kodeTerminal = kodeTerminal;
    }

    @Basic
    @Column(name = "tgl_status", nullable = true)
    public Timestamp getTglStatus() {
        return tglStatus;
    }

    public void setTglStatus(Timestamp tglStatus) {
        this.tglStatus = tglStatus;
    }

    @Basic
    @Column(name = "kode_reseller", nullable = true, length = 20)
    public String getKodeReseller() {
        return kodeReseller;
    }

    public void setKodeReseller(String kodeReseller) {
        this.kodeReseller = kodeReseller;
    }

    @Basic
    @Column(name = "kode_transaksi", nullable = true)
    public Integer getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(Integer kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    @Basic
    @Column(name = "is_jawaban", nullable = false)
    public byte getIsJawaban() {
        return isJawaban;
    }

    public void setIsJawaban(byte isJawaban) {
        this.isJawaban = isJawaban;
    }

    @Basic
    @Column(name = "service_center", nullable = true, length = 100)
    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    @Basic
    @Column(name = "is_cs", nullable = true)
    public Byte getIsCs() {
        return isCs;
    }

    public void setIsCs(Byte isCs) {
        this.isCs = isCs;
    }

    @Basic
    @Column(name = "kode_jawaban_cs", nullable = true)
    public Integer getKodeJawabanCs() {
        return kodeJawabanCs;
    }

    public void setKodeJawabanCs(Integer kodeJawabanCs) {
        this.kodeJawabanCs = kodeJawabanCs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InboxMsSql that = (InboxMsSql) o;
        return kode == that.kode &&
                status == that.status &&
                isJawaban == that.isJawaban &&
                Objects.equals(tglEntri, that.tglEntri) &&
                Objects.equals(penerima, that.penerima) &&
                Objects.equals(pengirim, that.pengirim) &&
                Objects.equals(tipePengirim, that.tipePengirim) &&
                Objects.equals(pesan, that.pesan) &&
                Objects.equals(kodeTerminal, that.kodeTerminal) &&
                Objects.equals(tglStatus, that.tglStatus) &&
                Objects.equals(kodeReseller, that.kodeReseller) &&
                Objects.equals(kodeTransaksi, that.kodeTransaksi) &&
                Objects.equals(serviceCenter, that.serviceCenter) &&
                Objects.equals(isCs, that.isCs) &&
                Objects.equals(kodeJawabanCs, that.kodeJawabanCs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kode, tglEntri, penerima, pengirim, tipePengirim, pesan, status, kodeTerminal, tglStatus, kodeReseller, kodeTransaksi, isJawaban, serviceCenter, isCs, kodeJawabanCs);
    }
}
