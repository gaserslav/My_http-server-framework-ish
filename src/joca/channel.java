package joca;

public class channel implements what_should_i_do{

    private what_should_i_do controller;
    public String mupping;

    public channel(String mapping,what_should_i_do controller){
    this.controller=controller;
    this.mupping=mapping;
    }

    @Override
    public byte[] what_should_i_do(Request request) {
        return controller.what_should_i_do(request);
    }


}
