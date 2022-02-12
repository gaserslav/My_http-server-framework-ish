package joca.Servers;

import joca.channel;
import joca.selfsizearray;

import java.io.File;

public class LessOptimisedServer extends Server {

    private int refreshrate;
    public LessOptimisedServer() throws Exception {
    super();

    }

    public LessOptimisedServer(int port) throws Exception {
        super(port);
        refreshrate=10000;
    }

    public LessOptimisedServer(int port,int refreshrate) throws Exception {
        super(port);
        this.refreshrate=refreshrate;
    }


    @Override
    protected void foldermupping(){


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {

                        Thread.sleep(refreshrate);




                        selfsizearray<channel> replacement=new selfsizearray<>();

                        File[] list=new File("public").listFiles();

                        for(File f:list){

                            if(f.isDirectory()){
                                listfolders(replacement,f);
                            }else{
                                replacement.appand(makechannel(f));
                                mapplogging+=f;
                            }

                        }
                        printmapplogging();
                        mapplogging="";
                        mapping.get(0).changeinto(replacement);

                    }
                }catch (Exception ee){}


            }
        }).start();

    }

}
