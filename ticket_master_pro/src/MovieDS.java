import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MovieDS extends Thread {
    static String url = "jdbc:mysql://localhost:3306/gp";
    static String username = "root";
    static String password = "";
    static Scanner sc=new Scanner(System.in);

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
    static Node first;
    static Node temp;
    static Node time;
    static Node temp2;
    
    public void start(){
        try {
            int i=0;
            Connection con1= DriverManager.getConnection(url, username, password);
            String sql = "select * from all_movie";
            PreparedStatement pst = con1.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            MovieDS md=new MovieDS();
        while (rs.next()) {
            if(i%2==0){
                md.add(rs.getString("movie_name"));

            }
            md.addTIme(rs.getString("show_time"));
            i++;
        }
        } catch (Exception e) {

        }
        
    }

    public void add(String data){
        Node n=new Node(data);
        if(first==null){
            first=n;
        }
        else{
            Node temp=first;
            while (temp.next!=null) {
                temp=temp.next;
            }
            temp.next=n;
        }

    }

    public void addTIme(String data){
        Node n=new Node(data);
        if(time==null){
            time=n;
        }
        else{
            Node temp2=time;
            while (temp2.next!=null) {
                temp2=temp2.next;
            }
            temp2.next=n;
        }
    }

    public void movies() throws Exception{
        int a=1;
        temp=first;
        while (temp!=null) {
            System.out.println(String.format("%55s", "")+MAGENTA+a+"."+GREEN+" "+temp.data);
            temp=temp.next;
            a++;
        }
        System.out.println(String.format("%55s", "")+MAGENTA+a+"."+GREEN+" Exit");
        System.out.println(RESET);
        System.out.print(YELLOW+String.format("%55s", "")+"ENTER YOUR CHOICE --> "+RED);
        MovieTicket mt=new MovieTicket();
        mt.movieChoice();
        temp=first;
    }

    public void displayTIme() throws Exception{
        int a=1;
        temp2=time;
        int c=Integer.parseInt(MovieTicket.mChoice);
        while (a!=c) {
            a++;
            temp2=temp2.next.next;
        }
        System.out.println("                                       -------------                                   -------------");
        System.out.println("                                      | "+YELLOW+"1. "+temp2.data+" "+RED+"|                                 | "+YELLOW+"2. "+temp2.next.data+" "+RED+"|");
        System.out.println("                                       -------------                                   -------------");
        MovieTicket.hs.put("1", temp2.data);
        MovieTicket.hs.put("2", temp2.next.data);
        MovieTicket.input();
    }

    public void setSeat() throws Exception{
        Connection con1= DriverManager.getConnection(url, username, password);
        String sql = "select * from movie";
        PreparedStatement pst = con1.prepareStatement(sql);
        ResultSet rs= pst.executeQuery();
        MyStack stack=new MyStack(50);
        while (rs.next()) {
            if(rs.getString("movie_name").equalsIgnoreCase(MovieTicket.moviesChoice) && rs.getString("show_time").equalsIgnoreCase(MovieTicket.hs.get(MovieTicket.tChoice))){
                stack.push(rs.getString("seat"));
            }
        }
        stack.display();
    }    
}

class MyStack extends MovieDS{
    private int maxSize;
    private String[] stackArray;
    private int top;

    public MyStack(int size) {
        this.maxSize = size;
        this.stackArray = new String[maxSize];
        this.top = -1;
    }

    public void push(String data) {
        if (top == maxSize - 1) {

        } else {
            stackArray[++top] = data;
        }
    }

    

    public void display(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                MovieTicket.seat[i][j]=false;
            }
        }
        if (top == -1) {
            return;
        }
        for (int i = 0; i <= top; i++) {
            String a=stackArray[i];
            char c[]=a.toCharArray();
            int r=(int)c[0]-48;
            int col=(int)c[1]-48;
            MovieTicket.seat[r][col]=true;
        }
    }

}