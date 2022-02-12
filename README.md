# My_http-server-framework-ish
### http server written in java
This is simple http server/framework~ish



# DONT USE IT WITH VIDEOS,IT WILL SEND ALL BYTES AT ONCE 




### Q: how do i use it ?

you see "out/artifacts/JocaWeb_jar/JocaWeb.jar" ?
add it in your project and you will have access to object 'Server' in 

`
import joca.Servers.*;
`


when you construct object server 

```java
//defolt is 8080
Server server=new Server();

Server server=new Server(8081);
```

when Server object is created program will generate (if it doenst exist) "public" folder in your project ,what ever you put inside will be "online" but you first need to restart program unless you use 'LessOptimisedServer`

```java
Server server=new LessOptimisedServer();
  
  //first one is port (defolt is 8080) and refreshrate(defolt is 10000)
  Server server2=new LessOptimisedServer(8080,100);
```
so with pure Server u can edit files after initializing object but YOU CAN NOT ADD NEW ONES (if you do program wont register them) while with lessOptimisedServer you can, just you need to wait Server to refresh itself


Server starts on function run() it has Runnable so you can make it into Thread

```java
Server s=new Server(8080);
//this way 
s.run();
// or
new Thread(s).start();
//this way as a thread
```

### Q: you said its framework~ish what can it do


you can add extra mapping over "channel" objects 

```java
channel can=new channel("/",new new what_should_i_do() {
            @Override
            public byte[] what_should_i_do(Request request) {
            return "<div> hello world</div>".getBytes();}
            });
```
as you can see cannel object has 2 parametars one is mapping(String) and what_should_i_do interface in which you have function with same name that you specify logic.
What that function returns thats Response and via Request object you access Request

after you initialized channel you can add it to Server

`
server.addChannel(channel);
`


![pic](https://i.ibb.co/vkWnSmN/Bez-naslova.png)

# I DO NOT RECOMENT USING IT IN SERIUS PROJECTS 


