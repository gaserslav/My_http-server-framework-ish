package joca.Servers;

import joca.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class Server implements Runnable{
    private File folder=new File("public");
    protected selfsizearray<selfsizearray<channel>> mapping=new selfsizearray<>();
    protected String mapplogging="";

    protected void printmapplogging(){
        //System.out.println("logging map");
        try {
            FileOutputStream fos = new FileOutputStream("mappinglogging.txt");
            fos.write(mapplogging.getBytes());
            fos.flush();
            fos.close();
        }catch (Exception eee){}
    }

    protected ServerSocket ss;


    public byte[] _404="404".getBytes();

    public Server() throws Exception {
        ss=new ServerSocket(8080);
        prepere();
    }
    public Server(int port) throws Exception {
        ss=new ServerSocket(port);
        prepere();
    }

    protected void prepere(){
        if(!folder.exists()){
            folder.mkdir();
            try{
            FileOutputStream fos =new FileOutputStream(folder+"/favicon.ico");

            InputStream is=Server.class.getResourceAsStream("/logo.png");
            byte[] b=is.readAllBytes();
            is.close();


            fos.write(b);
            fos.flush();
            fos.close();
            }catch (Exception eee){System.out.println("problem sa public prepere stvarima \n"+eee);}
        }

        mapping.appand(new selfsizearray<channel>());
        mapping.appand(new selfsizearray<channel>());
    }


    protected byte[] getcontentbyname(Request req){

        for(int i=0;i<mapping.length();i++){

            for(int o=0;o<mapping.get(i).length();o++){

               // System.out.println("|"+mapping.get(i).get(o).mupping+"|<-->|"+req.getMapping()+"|");
                if(mapping.get(i).get(o).mupping.equals(req.getMapping())){
                    return mapping.get(i).get(o).what_should_i_do(req);
                }

            }


        }
            return _404;
    }



    protected channel makechannel(File f){
        String path=f.getAbsolutePath().replace(folder.getAbsolutePath(),"");
        mapplogging+=path+"\n";
        path=path.replace("\\","/");


        //System.out.println("mappiranje fajla na pathu "+path);
        return new channel(path, new what_should_i_do() {
            @Override
            public byte[] what_should_i_do(Request request) {
                byte[] b=_404;
                try {
                    FileInputStream fis=new FileInputStream(f);
                    b=fis.readAllBytes();
                    fis.close();

                } catch (Exception e) {
                }
                return b;
            }
        });

    }

    protected void listfolders(selfsizearray<channel> mapping,File f){
        File[] list=f.listFiles();

        for(File ff:list){

            if(ff.isDirectory()){
                listfolders(mapping,ff);
            }else{
                mapping.appand(makechannel(ff));
            }


        }

    }

    protected void foldermupping(){


        selfsizearray<channel> replacement=new selfsizearray<>();
        File[] list=new File("public").listFiles();

        for(File f:list){

            if(f.isDirectory()){
                listfolders(replacement,f);
            }else{
                replacement.appand(makechannel(f));
                printmapplogging();
            }

        }

        for(int i=0;i<replacement.length();i++){
            logging.logfine(replacement.get(i).mupping);
        }
        logging.logfine(String.valueOf(replacement.hashCode()));
        mapping.get(0).changeinto(replacement);


    }

    public void addChannel(channel channel){
        mapping.get(1).appand(channel);
    }

    protected byte[] responseHeader(){
        String res="HTTP/1.0 200 OK\r\n"+
                "Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n"+
                "Server: JocaServer/0.1\r\n"+
                "\r\n";

        return res.getBytes();
    }


    protected void runServer() {

        if(!folder.exists()){
            folder.mkdir();
        }
        mapping.appand(new selfsizearray<channel>());
        mapping.appand(new selfsizearray<channel>());
        foldermupping();




        while (true){

            try {

                Socket s = ss.accept();
                byte[] b=new byte[1000];
                InputStream is=s.getInputStream();
                is.read(b);
                Request req=new Request(b);

                OutputStream os=s.getOutputStream();
                os.write(responseHeader());
                os.write(getcontentbyname(req));
                os.flush();
                os.close();


                try {
                    s.close();
                }catch (Exception eee){}


            }catch (Exception eee){}
        }


    }

    @Override
    public void run() {
        runServer();
    }
}
