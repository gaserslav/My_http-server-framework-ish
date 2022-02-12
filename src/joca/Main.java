package joca;

import joca.Servers.LessOptimisedServer;
import joca.Servers.Server;

public class Main {



    public static void main(String[] args) throws Exception {
	// write your code here

        Server server=new Server(8081);


        channel channel=new channel("/", new what_should_i_do() {
            @Override
            public byte[] what_should_i_do(Request request) {

                String referer=" previous site is: "+request.getReferer();
                String browser=" client browser "+request.getBrowser();
                String platform=" client platform "+request.getPlatform();
                String method=" Method "+request.getMethod();
                String password=" mail of account "+request.getParametar("mail");
                String cooke=" Cooke "+request.getCookies();
                String s=referer+"\n"+browser+"\n"+platform+"\n"+method+"\n"+password+"\n"+cooke+"\n password "+request.getCookie("password");

                return s.getBytes();
            }
        });
        server.addChannel(channel);

        server.run();


    }



}
