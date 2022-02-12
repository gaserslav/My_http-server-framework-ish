package joca;

public class Request {

    private String method_and_mapping;
    private String method;
    private String mapping;
    private String referer;
    private String s;
    private String platform;
    private String browser;
    private String cookies;
    public Request(byte[] b){
        s=new String(b);
        Main.logfine(s);
        build();

    }

    private void build(){
        method_and_mapping=s.substring(0,s.indexOf("\n"));
        try{
            int raz=method_and_mapping.indexOf(" ");
            method=s.substring(0,raz);
            mapping=s.substring(raz+1,method_and_mapping.indexOf(" ",raz+1));
        }catch (Exception fg){}

        try{
            int begin=s.indexOf("Referer: ")+9;
            int end=s.indexOf("\n",begin);
            if(end>begin && begin>9){
                referer=s.substring(begin,end);
            }
        }catch (Exception eee){}

        try{
            int begin=s.indexOf("sec-ch-ua-platform: ")+20;
            int end=s.indexOf("\n",begin);
            if(end>begin && begin>20) {
                platform = s.substring(begin, end);
            }
            }catch (Exception eee){}

        try{
            int begin=s.indexOf("User-Agent: ")+12;
            int end=s.indexOf("\n",begin);
            if(end>begin && begin>12) {
                browser = s.substring(begin, end);
            }
        }catch (Exception eee){}
        try{
            int begin=s.indexOf("Cookie: ")+8;
            int end=s.indexOf("\n",begin);
            Main.logimportant("found cookies "+begin+" end: "+end);
            if(end>begin && begin>8) {
                cookies = s.substring(begin, end);
            }
        }catch (Exception eee){}



    }


    public String getParametar(String name){


            int begin = s.indexOf(name);
            if (begin == -1) {
                return null;
            }

            int end = s.length();
            int end1 = s.indexOf(String.valueOf((char) 0), begin);
            int end2 = s.indexOf("&", begin);
            //int end3 = s.indexOf(" ", begin);

            //Main.logimportant("B:"+begin+" e1:"+end1+" e2:"+end2+" e3:"+end3);

        if(end1!=-1 && end2==-1){
            end =end1;
        }else if(end1==-1 && end2!=-1){
            end =end2;
        }
        else if(end2!=-1 && end1!=-1){
            end=Math.min(end1,end2);
        }else{
            return null;
        }


            String ret = s.substring(begin, end);

            // space is displayed as +
            ret=ret.replace("+"," ");


            Main.logimportant("ovo je parametar " + ret);

            return ret;

    }

    public String getCookie(String name){
        try {
            int begin = cookies.indexOf(name);
            if (begin == -1) {
                return null;
            }
            begin+=name.length()+1;

            int end=cookies.length();
            int end1 = cookies.indexOf(";", begin);
            int end2=cookies.indexOf("\n",begin);

            if(end1!=-1 && end2!=-1){
                end=Math.min(end1,end2);
            }else if (end1==-1 && end2!=-1){
                end=end2;
            }else if(end2==-1 && end1!=-1){
                end=end1;
            }

            return cookies.substring(begin, end);
        }catch (Exception ee){
            Main.logerror("error kod cookies-a\n"+ee);
        }

        return null;
    }


    public String getReferer(){return  referer;}
    public String getPlatform(){return  platform;}
    public String getBrowser(){return  browser;}
    public String getCookies(){return  cookies;}
    public String getMethod() {
        return method;
    }
    public String getMapping(){
        return mapping;
    }

}
