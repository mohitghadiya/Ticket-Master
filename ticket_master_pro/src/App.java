import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.*;
import java.security.MessageDigest;

public class App {
    static Scanner sc=new Scanner(System.in);
    static String url = "jdbc:mysql://localhost:3306/gp";
    static String username = "root";
    static String password = "";
    static String userName;
    static boolean flag=true;
    static String phoneNumber;

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


    public static void main(String[] args) throws Exception {
        welcome();
        cheakUserId();
        mainMenu();
    }

    private static void welcome() throws Exception{
        System.out.println("\033[H\033[2J");
        for (int i = 0; i<5; i++) 
        {  
            ticketMasterBanner(i % 7, (i + 1) % 7);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        resetColor();
    }

    private static void ticketMasterBanner(int textColorCode, int bgColorCode) throws Exception{
        System.out.print("\u001B[3" + textColorCode + ";4" + bgColorCode + "m");
        System.out.print("\033[5A");
        System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------------------------- ");
        System.out.println("|"+String.format("%62s", "")+"      TICKET MASTER        "+String.format("%62s", "")+"|");
        System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------------------------- ");
    }

    private static void resetColor() throws Exception{
        System.out.print(RESET);
    }

    private static void cheakUserId() throws Exception{
        Boolean flag=true;
        String address=getAddress();
        Connection con = DriverManager.getConnection(url, username, password);
        String sql="SELECT * FROM USER";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs= pst.executeQuery();
        while (rs.next()) {
            if(address.equals(rs.getString("device_id"))){
                userName=rs.getString("user_name");
                phoneNumber=rs.getString("phone_number");
                flag=false;
                break;
            }
        }
        if(flag){
            addUser();
        }   
    }

    private static String getAddress() {
        try 
        {
        	String deviceId = "";
        	Properties properties = System.getProperties();
        	deviceId += properties.getProperty("os.name");
        	deviceId += properties.getProperty("os.arch");
        	deviceId += properties.getProperty("os.version");

        	InetAddress ip = InetAddress.getLocalHost();
        	NetworkInterface network = NetworkInterface.getByInetAddress(ip);

        	if (network == null) {
        	    Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
        	    while (networks.hasMoreElements()) {
        	        network = networks.nextElement();
        	        if (network.getHardwareAddress() != null) {
        	            break;
        	        }
        	    }
        	}

        	byte[] macAddress = network.getHardwareAddress();
        	if (macAddress != null) {
        	    for (byte b : macAddress) {
        	        deviceId += String.format("%02X", b);
        	    }
        	}

        	MessageDigest md = MessageDigest.getInstance("SHA-256");
        	byte[] hash = md.digest(deviceId.getBytes());

        	String hexString = "";
        	for (byte b : hash) {
        	    hexString += String.format("%02x", b);
        	}
        	return hexString;
        } 
        catch (Exception e) {
            throw new RuntimeException("Failed to get device ID", e);
        }
    }

    private static void addUser() throws Exception{
        Connection con = DriverManager.getConnection(url, username, password);
        String address = getAddress();
        setDetails();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String sql="INSERT INTO user (user_name, phone_number, device_id, date_and_time) VALUES ( ? , ? , ? , ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, userName);
        pst.setString(2, phoneNumber);
        pst.setString(3, address);
        pst.setString(4, formattedDateTime);
        pst.executeUpdate();
        mainMenu();
    }

    private static void setDetails(){
        while (true) {
            System.out.println();
            System.out.print(YELLOW+String.format("%21s", "")+"Enter Your Name -->"+RED);
            userName = sc.nextLine();
            System.out.print(YELLOW+String.format("%21s", "")+"Enter Your Phone Number -->"+RED);
            phoneNumber = sc.nextLine();
            if(isValidNUmber()){
                break;
            }
            System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET);
        }   
    }

    private static boolean isValidNUmber(){
        if(phoneNumber.length()==10){
            for(int i = 0 ; i < 10 ;i++){
                if (phoneNumber.charAt(i) >= '0' && phoneNumber.charAt(i) <= '9'){

                }
                else{
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void mainMenu() throws Exception{
        System.out.print("\033[H\033[2J");
        System.out.println(BG_WHITE+BLACK+String.format("%62s", "")+"WELCOME IN OUR TICKET MASTER "+userName.toUpperCase()+String.format("%"+(62-userName.length())+"s", "")+RESET);
        System.out.println();
        System.out.println(YELLOW+"                               ------------------                  ----------------                  ------------");
        System.out.println("                              |"+RED+" 1. MOVIE BOOKING "+YELLOW+"|                |"+RED+" 2. AIR BOOKING "+YELLOW+"|                |"+RED+" 3. LOG OUT "+YELLOW+"|");
        System.out.println(YELLOW+"                               ------------------                  ----------------                  ------------");
        System.out.println("\n");
        System.out.print(MAGENTA+String.format("%21s", "")+"Enter Your Choice -->"+GREEN);
        String choice=sc.nextLine();
        switch (choice) {
            case "1":
                System.out.print("\033[H\033[2J");
                MovieTicket.MovieBooking();
                break;

            case "2":
                System.out.print("\033[H\033[2J");
                AirTicket.flightBooking();
                break;

            case "3":
                addUser();
                break;

        
            default:
                System.out.println(BG_RED+WHITE+String.format("%61s", "")+" INVALID INPUT PLEASE TRY AGAIN "+String.format("%60s", "")+RESET);
                mainMenu();
                break;
        }
    }
    
}
