import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AirDS extends Thread{
    static String url = "jdbc:mysql://localhost:3306/gp";
    static String username = "root";
    static String password = "";
    static int a=0;
    public static final String BLACK = "\033[30m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String MAGENTA = "\033[35m";
    public static final String WHITE = "\033[37m";
    public static final String RESET = "\033[0m";
    public static final String GRAY = "\033[90m";

    public static final String BRIGHT_RED = "\033[91m";
    public static final String BRIGHT_GREEN = "\033[92m";
    public static final String BRIGHT_YELLOW = "\033[93m";

    public static final String BG_BLACK = "\033[40m";
    public static final String BG_RED = "\033[41m";
    public static final String BG_GREEN= "\033[42m";
    public static final String BG_YELLOW = "\033[43m";
    public static final String BG_BLUE = "\033[44m";
    public static final String BG_MAGENTA = "\033[45m";
    public static final String BG_WHITE = "\033[47m";
    public static final String BG_GRAY = "\033[100m"; 

    public static final String BG_BRIGHT_RED = "\033[101m";
    public static final String BG_BRIGHT_GREEN = "\033[102m";
    public static final String BG_BRIGHT_YELLOW = "\033[103m";


    class Node{
        String data;
        Node next;
    
        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }
    static Node startP;
    static Node endP;
    static Node flight;
    
    
    public void set(){
        try {
            CustomQueue queue = new CustomQueue();
            Connection con1= DriverManager.getConnection(url, username, password);
            String sql = "select * from all_flight";
            PreparedStatement pst = con1.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            AirDS ad=new AirDS();
            while (rs.next()) {
                ad.addS(rs.getString("start_place"));
                ad.addE(rs.getString("end_place"));
                queue.enqueue(rs.getString("flight_name"));   
            }
            ad.find();
            queue.display();
        } catch (Exception e) {

        }
        
    }

    public void addS(String data){
        Node n=new Node(data);
        if(startP==null){
            startP=n;
        }
        else{
            Node temp=startP;
            while (temp.next!=null) {
                temp=temp.next;
            }
            temp.next=n;
        }

    }
    
    public void addE(String data){
        Node n=new Node(data);
        if(endP==null){
            endP=n;
        }
        else{
            Node temp=endP;
            while (temp.next!=null) {
                temp=temp.next;
            }
            temp.next=n;
        }
    }

    public void find(){
        Node temp=startP;
        Node temp2=endP;
        while (temp != null && temp2 != null) {
            a++;
            if(temp.data.equals(AirTicket.startPlace) && temp2.data.equals(AirTicket.endPlace)){
                break;
            }
            temp=temp.next; 
            temp2=temp2.next;
        }
        if(a==6){
            a=10;
        }
    }

    class CustomQueue {

        class Node{
            String data;
            Node next;
        
            public Node(String data) {
                this.data = data;
                this.next = null;
            }
        }

        private Node front;
        private Node rear;
    
        public CustomQueue() {
            this.front = this.rear = null;
        }
    
        public void enqueue(String data) {
            Node newNode = new Node(data);
    
            if (this.rear == null) {
                this.front = this.rear = newNode;
                return;
            }
    
            this.rear.next = newNode;
            this.rear = newNode;
        }

    
        public void display() throws Exception{
            if (this.front == null) {
                return;
            }
            int i=1;
            Node temp = this.front;
            while (temp != null) {
                if(i==a){
                    System.out.print(GREEN+String.format("%55s", "")+temp.data + " ("+ AirTicket.startPlace +" --> "+ AirTicket.endPlace+")");
                    break;
                }
                i++;
                temp = temp.next;
            }
            System.out.println();
            AirTicket.menu();
        }
    }
    
    


}
