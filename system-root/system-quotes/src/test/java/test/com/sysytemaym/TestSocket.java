package test.com.sysytemaym;


import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.util.Scanner;

public class TestSocket {

    //
    static DataInputStream in;
    static DataOutputStream out;

    //
    static BufferedInputStream buffIN;
    static BufferedOutputStream buffOUT;


    // 行情服务IP地址
    static String quotes_host = "112.124.211.146";
    // 行情服务端口号
    static int quotes_port = 9101;

    static Socket socket = null;

    // 行情服务握手命令
    static String quotes_Login = "0=Login|1=1000|1000=chn-gd-zhongkuang|1001=chn-gd-zhongkuang\n";
    // 黄金报价
    static String quotes_XAUUSDOZ = "0=Subscribe|1=1001|10=XAUUSDOZ SC-FX\n";
    // 白银报价
    static String quotes_XAGUSDOZ = "0=Subscribe|1=1002|10=XAGUSDOZ SC-FXX\n";
    // 在岸人民币美金
    static String quotes_USDCNY = "0=Subscribe|1=1003|10=USDCNY SC-FX\n";
    // 离岸价人民币美金
    static String quotes_USDCNH= "0=Subscribe|1=1004|10=USDCNH SC-FX\n";

    public static void main(String[] args){
        try{
            socket = new Socket(quotes_host,quotes_port);
            // UDP(socket);
            TCP(socket);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void TCP(Socket socket){

        try{
            InputStreamReader in_reader = new InputStreamReader(socket.getInputStream());
            OutputStreamWriter out_reader = new OutputStreamWriter(socket.getOutputStream());

            BufferedReader buff_reader = new BufferedReader(in_reader);
            BufferedWriter buff_writer = new BufferedWriter(out_reader);

            buff_writer.write(quotes_Login);
            buff_writer.write(quotes_XAUUSDOZ);
            buff_writer.flush();

            while(true){
                System.out.println(buff_reader.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void UDP(Socket socket){
        try{
            in = new DataInputStream(socket.getInputStream());
            out =  new DataOutputStream(socket.getOutputStream());

            out.write(quotes_Login.getBytes());
            out.write(quotes_XAUUSDOZ.getBytes());

            while(in.readBoolean()){
                System.out.println(in.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
