package br.ufrpe.bibliolinda.exception;

public class SenhaIncorretaException extends Exception{
    public SenhaIncorretaException(){
        super("A senha informada está incorreta");
    }

}
