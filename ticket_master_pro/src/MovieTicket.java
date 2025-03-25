import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class MovieTicket extends App{
    static String url = "jdbc:mysql://localhost:3306/gp";
    static String username = "root";
    static String password = "";
    static String userName="utsav";
    static String []seats=new String[50];
    static boolean seat[][]=new boolean[6][10];
    static String mChoice;
    static String tChoice;
    static String moviesChoice="";
    static int screen[][]={{2,1},{1,3},{2,2},{3,3},{4,4}};
    static int price[]={350,300,300,350,250};
    static HashMap<String,String> hs=new HashMap<>();
    static MovieDS md2=new MovieDS();
    static String payment="";

    public static void MovieBooking() throws Exception{

        // multy threading
        Thread md=new MovieDS();
        Thread b=new Banner();
        b.start();
        if(flag){
            md.start();
            md.join();
            flag=false;
        }
        b.join();

        System.out.println(RESET);

        md2.movies();

    }

    public static void movieChoice() throws Exception{
        mChoice=sc.next();    
        switch (mChoice) {
            case "1":
                md2.displayTIme();
                break;
                
            case "2":
                md2.displayTIme();
                break;
                
            case "3":
                md2.displayTIme();
                break;
                
            case "4":
                md2.displayTIme();
                break;
                
            case "5":
                md2.displayTIme();
                break;
                
            case "6":
                App.mainMenu();
                break;

            default:
                System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET);
                System.out.println();
                md2.movies();
                break;
        }
    } 

    public static void input() throws Exception{
        System.out.print(GREEN+String.format("%55s", "")+"Enter Your Choice -->"+MAGENTA);
        tChoice = sc.next();
        switch (tChoice) {
            case "1":
            case "2":
                displayMenu();
                break;
        
            default:
                System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET);
                System.out.println();
                input();
                break;
        }
    }

    public static void displayMenu() throws Exception{
        movieBanner();
        while (true) {
            System.out.println(MAGENTA+String.format("%55s", "")+"1. Display Seats");
            System.out.println(String.format("%55s", "")+"2. Book Seats");
            System.out.println(String.format("%55s", "")+"3. Cancle Seats");
            System.out.println(String.format("%55s", "")+"4. Exit");
            System.out.print(String.format("%55s", "")+BLUE+"Enter Your Choice -->"+GREEN);
            String menuChoice=sc.nextLine();
            switch (menuChoice) {
                case "1":
                    displaySeat();
                    break;
                    
                case "2":
                    bookSeat();
                    break;
                    
                case "3":
                    cancleSeat();
                    break;
                    
                case "4":
                    MovieBooking();
                    break;
            
                default:
                    System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET);
                    displayMenu();
                    break;
            }
        }
        
    }

    private static void movieBanner(){
        System.out.print("\033[H\033[2J");
        System.out.println();
        if(mChoice.equalsIgnoreCase("1")){
            moviesChoice="Fakt Purusho Maate";
            System.out.println( "  " + String.format("%55s", "" )+RED+" -------------------------------------"+RESET+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+"|"+YELLOW+"       !! Fakt Purusho Maate !!     "+RESET+RED+"|"+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+" -------------------------------------"+RESET+String.format("%38s",""));
        }
        else if(mChoice.equalsIgnoreCase("2"))
        {            
            moviesChoice="Stree 2";
            System.out.println( "  " + String.format("%55s", "" )+RED+"-------------------------------------"+RESET+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+"|"+YELLOW+"         !!    Stree 2    !!       "+RESET+RED+"|"+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+"-------------------------------------"+RESET+String.format("%38s",""));
        }
        else if(mChoice.equalsIgnoreCase("3")){
            moviesChoice="Vedaa";
            System.out.println( "  " + String.format("%55s", "" )+RED+"-------------------------------------"+RESET+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+"|"+YELLOW+"             !! Vedaa !!           "+RESET+RED+"|"+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+"-------------------------------------"+RESET+String.format("%38s",""));
        }
        else if(mChoice.equalsIgnoreCase("4")){
            moviesChoice="Deadpool & Wolverin";
            System.out.println( "  " + String.format("%55s", "" )+RED+"-------------------------------------"+RESET+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+"|"+YELLOW+"     !! Deadpool & Wolverin !!    "+RESET+RED+"|"+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+"-------------------------------------"+RESET+String.format("%38s",""));
        }
        else if(mChoice.equalsIgnoreCase("5")){
            moviesChoice="Alien: Romulus";
            System.out.println( "  " + String.format("%55s", "" )+RED+"-------------------------------------"+RESET+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+"|"+YELLOW+"        !! Alien: Romulus !!       "+RESET+RED+"|"+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+"-------------------------------------"+RESET+String.format("%38s",""));
        }
    }

    

    private static void displaySeat() throws Exception{
        System.out.print("\033[H\033[2J");

        md2.setSeat();
        System.out.println(String.format("%55s", "" )+YELLOW+"----- Seating chart -----");
        for (int i = 0; i < 6; i++) {
            System.out.println();
            System.out.print(String.format("%55s", "" ));
            for (int j = 0; j < 10; j++) {
                if(i==0 && j!=0){
                    System.out.print(MAGENTA+" "+j);
                }
                else if(j==0 && i!=0){
                    System.out.print(MAGENTA+i);
                }
                else if(i==0 && j==0){
                    System.out.print(" ");
                }
                else if(!seat[i][j] && i==5){
                    System.out.print(YELLOW+" O");
                }
                else if(!seat[i][j]){
                    System.out.print(GREEN+" O");
                }
                else if(seat[i][j]){
                    System.out.print(RED+" X");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void bookSeat() throws Exception{
        System.out.print("\033[H\033[2J");
        displaySeat();
        System.out.println();
        System.out.print(String.format("%55s", "")+"How Many Seat You Want to Book -->");
        String s=sc.next();
        int a=Integer.parseInt(s);
        int i=1;
        while (i<=a) {
            System.out.print(String.format("%55s", "")+"Enter Row Number (Exit) --> ");
            String row=sc.next();
            if(row.equalsIgnoreCase("Exit")){
                displayMenu();
            }
            System.out.print(String.format("%55s", "")+"Enter Col. Number --> ");
            String col=sc.next();
            if(isValidSeat(row,col)){
                int r=Integer.parseInt(row);
                int c=Integer.parseInt(col);
                seats[i-1]=row+col;
                seat[r][c]=true;
                i++;
            }
            else{
                System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET); 
            }
        }
        getpayment(a);
        System.out.println(String.format("%55s", "")+"Your total payment -->"+payment);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        i=0;
        while (i<a) {
            database(i);
            seats[i]="";
            i++;
        }


    }

    private static void getpayment(int a){
        payment = String.valueOf(a*price[Integer.parseInt(mChoice)]);
    }

    private static void cancleSeat() throws Exception{
        System.out.print("\033[H\033[2J");
        displaySeat();
        System.out.println();
        System.out.print(String.format("%55s", "")+"How Many Seat You Want to Cancle -->");
        String s=sc.next();
        int a=Integer.parseInt(s);
        int i=1;
        while (i<=a) {
            System.out.print(String.format("%55s", "")+"Enter Row Number (Exit) --> ");
            String row=sc.next();
            if(row.equalsIgnoreCase("Exit")){
                displayMenu();
            }
            System.out.print(String.format("%55s", "")+"Enter Col. Number --> ");
            String col=sc.next();
            if(isCancle(row,col)){
                int r=Integer.parseInt(row);
                int c=Integer.parseInt(col);
                seats[i-1]=row+col;
                seat[r][c]=true;
                i++;
            }
            else{
                System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET); 
            }
        }
        i=0;
        while (i<a) {
            databaseCancle(i);
            seats[i]="";
            i++;
        }
    }

    private static void databaseCancle(int i) throws Exception {
        Connection con= DriverManager.getConnection(url, username, password);
        String m="'"+moviesChoice+"'";
        String s="'"+seats[i]+"'";
        String sql = "DELETE FROM movie WHERE (movie.seat = "+s+" and movie_name = "+m+")";
        System.out.println(sql);
        PreparedStatement statement = con.prepareStatement(sql);
        statement.executeUpdate();
    }

    private static void database(int i){
        try {
            Connection con= DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO movie (user_name , movie_name , show_time , screen_no , seat , payment , date_and_time) VALUES (? , ? , ? , ? , ? , ? , ? )";
            PreparedStatement statement = con.prepareStatement(sql);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            statement.setString(1, userName);
            statement.setString(2, moviesChoice);
            statement.setString(3, hs.get(tChoice));
            statement.setInt(4, screen[Integer.parseInt(mChoice)-1][Integer.parseInt(tChoice)-1]);
            statement.setString(5, seats[i]);
            statement.setString(6, payment);
            statement.setString(7, formattedDateTime);
            statement.executeUpdate();
            statement.close();
            con.close();
            System.out.println();
            System.out.println(RESET);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(BG_RED+String.format("%45s", "")+"Error: Duplicate entry or integrity constraint violation."+String.format("%50s", "") + RESET);
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(BG_RED+String.format("%45s", "")+"Error storing booking information in database."+String.format("%56s", "") + RESET);
            e.printStackTrace();
        }
    }

    private static void setSeat(){
            
        
    }

    private static boolean isCancle(String row,String col){
        int r=Integer.parseInt(row);
        int c=Integer.parseInt(col);
    
        return r > 0 && c > 0 && r < 6 && c < 10 && seat[r][c];
    }

    private static boolean isValidSeat(String row,String col){
        int r=Integer.parseInt(row);
        int c=Integer.parseInt(col);
    
        return r > 0 && c > 0 && r < 6 && c < 10 && !seat[r][c];
    }

}




class Banner extends Thread{

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

    public void run(){
        System.out.println(String.format("%53s", "")+YELLOW+" ╔═════════════════════════════════════════════╗ ");
        System.out.println(String.format("%53s", "")+" ║       "+RED+"WELCOME TO MOVIE TICKET BOOKING"+RED+"       "+YELLOW+"║ ");
        System.out.println(String.format("%53s", "")+" ╚═════════════════════════════════════════════╝ "+RESET);
    }


}