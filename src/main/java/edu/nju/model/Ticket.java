package edu.nju.model;

import edu.nju.model.embedable.TicketPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Shenmiu
 * @date 2018/03/04
 *
 * 票
 */
@Entity
@Table(name = "ticket")
public class Ticket {

    /**
     * 座位的行、列及所属订单号组成联合主键
     */
    @EmbeddedId
    private TicketPK ticketPK;

    /**
     * 票的单价
     */
    private int price;

    public Ticket() {
    }

    public TicketPK getTicketPK() {
        return ticketPK;
    }

    public void setTicketPK(TicketPK ticketPK) {
        this.ticketPK = ticketPK;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
