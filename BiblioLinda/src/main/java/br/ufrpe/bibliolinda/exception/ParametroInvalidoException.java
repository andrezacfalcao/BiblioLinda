package br.ufrpe.bibliolinda.exception;

public class ParametroInvalidoException extends Exception{
    private Object objeto;

    public ParametroInvalidoException() {
        super("Parâmetro inválido para realizar busca");
    }

    public ParametroInvalidoException(Object obj){
        this.objeto = obj;
    }

    public ParametroInvalidoException(String message) {
        super(message);
    }

    public Object getObjeto(){
        return objeto;
    }

    public void setObjeto(Object obj){
        this.objeto = obj;
    }



}
