package cn.yyb.creational.singleton01;

/**
 * @author yueyubo <br>
 * @date 2024-05-30 20:59
 */
public class TicketMarker {
    private int ticket = 1000;

    public static TicketMarker ticketMarker = null;

    private TicketMarker(){}

    public int getNextTicketNumber(){
        return ticket++;
    }

    public static TicketMarker getInstance(){
        if (ticketMarker == null){
            ticketMarker = new TicketMarker();
        }
        return ticketMarker;
    }
}
