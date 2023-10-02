package models;

import java.util.Date;

public class Bookings {

    private Integer id;
    private Date entryDate;
    private Date exitDate;
    private Double amount;
    private String payment;

    public Bookings(Date entryDate, Date exitDate, Double amount, String payment){
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.amount = amount;
        this.payment = payment;
    }

    public Bookings(Integer id, Date entryDate, Date exitDate, Double amount, String payment){
        this.id = id;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.amount = amount;
        this.payment = payment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public Date getEntryDate() {
        return this.entryDate;
    }

    public Date getExitDate() {
        return this.exitDate;
    }

    public Double getAmount() {
        return this.amount;
    }

    public String getPayment() {
        return this.payment;
    }

    public String[] toListString() {
        String[] string = {id.toString(), entryDate.toString(), exitDate.toString(), amount.toString(), payment.toString()};
        return string;
    }
}
