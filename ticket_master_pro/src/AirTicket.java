import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AirTicket extends App {
    static String mainChoice;
    static String s[]={".","..","..."};
    static String startPlace;
    static String endPlace;
    static String flightChoice;
    static String flightName;
    static String userName="utsav";
    static String []seats=new String[50];
    static int price[]={10000 , 12000 , 60000 , 30000 , 7000 , 11000};
    static boolean flight[][]=new boolean[10][6];
    static AirDS ad=new AirDS();
    static int x=0;
    static String payment;

    public static void flightBooking() throws Exception{
        menu();
    }

     public static void menu() throws Exception{
     
        System.out.println(String.format("%53s", "")+YELLOW+" ╔═════════════════════════════════════════════╗ ");
        System.out.println(String.format("%53s", "")+" ║        "+RED+"WELCOME TO AIR TICKET BOOKING"+RED+"        "+YELLOW+"║ ");
        System.out.println(String.format("%53s", "")+" ╚═════════════════════════════════════════════╝ "+RESET);
        System.out.println();
        System.out.println(String.format("%55s", "")+MAGENTA+"1."+GREEN+" Find Flights");
        System.out.println(String.format("%55s", "")+MAGENTA+"2."+GREEN+" View All Flights");
        System.out.println(String.format("%55s", "")+MAGENTA+"3."+GREEN+" Book a Flight");
        System.out.println(String.format("%55s", "")+MAGENTA+"4."+GREEN+" Cancle Flight");
        System.out.println(String.format("%55s", "")+MAGENTA+"5."+GREEN+" Exit");
        System.out.print(String.format("%55s", "")+YELLOW+"Enter Your Choice -->");
        mainChoice=sc.nextLine();
        switch (mainChoice) {
            case "1":
                findFlight();
                break;
            
            case "2":
                viewAllFlight(); 
                flightBooking(); 
                break;
                
            case "3":
                bookFlight();
                break;
                
            case "4":
                cancleFlight();
                break;
                
            case "5":
                App.mainMenu();
                break;

            case "":
                System.out.println("\033[H\033[2J");
                flightBooking();
                break;
        
            default:
                System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET); 
                menu();
                break;
        }
    }

    private static void findFlight(){
        System.out.print(MAGENTA+String.format("%55s", "")+"enter start place --> "+RED);
        startPlace=sc.nextLine();
        System.out.print(MAGENTA+String.format("%55s", "")+"enter end place -->"+RED);
        endPlace=sc.next();
        finding();
        ad.set();
    }

    private static void finding(){
        for (int i = 0; i < 25; i++) {
            System.out.print(String.format("%55s", "")+"Finding");
            System.out.print(s[i%3]);
            try {
                Thread.sleep(200);
            } catch (Exception e) {

            }
            System.out.println("\033[H\033[2J");
        }   
    }


    
    private static void viewAllFlight() throws Exception{
        System.out.println(GREEN);
        System.out.println(String.format("%55s", "")+"1. indigo (mumbai -> kolkata)");
        System.out.println(String.format("%55s", "")+"2. indigo (delhi -> Madras)");
        System.out.println(String.format("%55s", "")+"3. singapore_Go (delhi -> singapore)");
        System.out.println(String.format("%55s", "")+"4. air_india (surat -> dubai)");
        System.out.println(String.format("%55s", "")+"5. super_surat (mumbai -> surat)");
        System.out.println(String.format("%55s", "")+"6. Madras_int (delhi -> Madras)"+RESET);
        System.out.println();
    }
    
    private static void bookFlight(){
        try {
            viewAllFlight();
            setChoice();
            boolean upiCheak=true;
            set();
            displaySeat();
            System.out.print(RED+String.format("%55s", "")+"How Many Seat You Want to Book -->"+YELLOW);
            String s=sc.next();
            int a=Integer.parseInt(s);
            int i=1;
            while (i<=a) {
                System.out.print(GREEN+String.format("%55s", "")+"Enter Row Number (Exit) --> ");
                String row=sc.next();
                if(row.equalsIgnoreCase("Exit")){
                    menu();
                }
                System.out.print(GREEN+String.format("%55s", "")+"Enter Col. Number --> ");
                String col=sc.next();
                if(isValidSeat(row,col)){
                    int r=Integer.parseInt(row);
                    int c=Integer.parseInt(col);
                    seats[i-1]=row+col;
                    flight[r][c]=true;
                    i++;
                }
                else{
                    System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET); 
                }
            }
            payment=String.valueOf(a*price[Integer.parseInt(flightChoice)-1]);
            System.out.println(String.format("%55s", "")+"Your Payment -->"+payment);
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
            x=1;
            flightBooking();
        } 
        catch (Exception e) {
            System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET); 
            bookFlight();

        }
        
        
    }

    private static void setChoice() throws Exception{
        System.out.print(String.format("%55s", "")+"Enter your choice --> ");
        flightChoice=sc.nextLine();
        switch (flightChoice) {
            case "1":
                flightName="indigo";
                startPlace="mumbai";
                endPlace="kolkata";
                break;

            case "2":
                flightName="indigo";
                startPlace="delhi";
                endPlace="madras";
                break;

            case "3":
                flightName="singapore_Go";
                startPlace="delhi";
                endPlace="singapore";
                break;

            case "4":
                flightName="air_india";
                startPlace="surat";
                endPlace="dubai";
                break;

            case "5":
                flightName="super_surat";
                startPlace="mumbai";
                endPlace="surat";
                break;

            case "6":
                flightName="Madras_int";
                startPlace="delhi";
                endPlace="madras";
                break;
        
            default:
                System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET); 
                setChoice();
                break;
        }
    }

    private static void cancleFlight() throws Exception{
        try {
            viewAllFlight();
            setChoice();
            set();
            displaySeat();
            System.out.println();
            System.out.print(RED+String.format("%55s", "")+"How Many Seat You Want to Cancle -->"+YELLOW);
            String s=sc.next();
            int a=Integer.parseInt(s);
            int i=1;
            while (i<=a) {
                System.out.print(GREEN+String.format("%55s", "")+"Enter Row Number (Exit) --> ");
                String row=sc.next();
                if(row.equalsIgnoreCase("Exit")){
                    menu();
                }
                System.out.print(GREEN+String.format("%55s", "")+"Enter Col. Number --> ");
                String col=sc.next();
                if(isCancle(row,col)){ 
                    int r=Integer.parseInt(row);
                    int c=Integer.parseInt(col);
                    seats[i-1]=row+col;
                    flight[r][c]=true;
                    i++;
                }
                else{
                    System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET); 
                }
            }
            i=0;
            x=1;
            while (i<a) {
                databaseCancle(i);
                seats[i]="";
                i++;
            }
            flightBooking();
        } 
        catch (Exception e) {
            flightBooking();
            System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET); 
        }
        
        
    }

    private static void displaySeat(){
        System.out.println(String.format("%55s", "" )+YELLOW+"----- Seating chart -----");
        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.print(String.format("%55s", "" ));
            for (int j = 0; j < 6; j++) {
                if(i==0 && j!=0){
                    System.out.print(MAGENTA+" "+j);
                }
                else if(j==0 && i!=0){
                    System.out.print(MAGENTA+i);
                }
                else if(i==0 && j==0){
                    System.out.print(" ");
                }
                else if(!flight[i][j]){
                    System.out.print(GREEN+" O");
                }
                else if(flight[i][j]){
                    System.out.print(RED+" X");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void set() throws Exception{
        Connection con= DriverManager.getConnection(url, username, password);
        String sql = "select * from flight";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        while (rs.next()) {
            if(rs.getString("flight_name").equalsIgnoreCase(flightName) && rs.getString("start_place").equalsIgnoreCase(AirTicket.startPlace) && rs.getString("end_place").equalsIgnoreCase(AirTicket.endPlace)){
                String a=rs.getString("seat");
                char c[]=a.toCharArray();
                int r=(int)c[0]-48;
                int col=(int)c[1]-48;
                flight[r][col]=true;
            }
        }
    }

    private static void databaseCancle(int i) throws Exception {
        Connection con= DriverManager.getConnection(url, username, password);
        String f="'"+flightName+"'";
        String s="'"+seats[i]+"'";
        String sql = "DELETE FROM flight WHERE (flight.seat = "+s+" and flight_name = "+f+")";
        System.out.println(sql);
        PreparedStatement statement = con.prepareStatement(sql);
        statement.executeUpdate();
    }

    private static void database(int i){
        try {
            Connection con= DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO flight (user_name , flight_name , start_place , end_place , seat , payment , date_and_time) VALUES (? , ? , ? , ? , ? , ? , ? )";
            PreparedStatement statement = con.prepareStatement(sql);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            statement.setString(1, userName);
            statement.setString(2, flightName);
            statement.setString(3, startPlace);
            statement.setString(4, endPlace);
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

    private static boolean isCancle(String row,String col){
        int r=Integer.parseInt(row);
        int c=Integer.parseInt(col);
    
        return r > 0 && c > 0 && c < 6 && r < 10 && flight[r][c];
    }

    private static boolean isValidSeat(String row,String col){
        int r=Integer.parseInt(row);
        int c=Integer.parseInt(col);
    
        return r > 0 && c > 0 && c < 6 && r < 10 && !flight[r][c];
    }

}
