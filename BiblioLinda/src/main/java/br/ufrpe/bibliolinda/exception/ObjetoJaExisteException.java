package br.ufrpe.bibliolinda.exception;

public class ObjetoJaExisteException extends Exception{
    private Object objeto;

    public ObjetoJaExisteException(Object obj){
        super("O objeto não existe no sistema");
        this.objeto = obj;
    }

    public Object getObjeto(){

        return objeto;
    }
    public void setObjeto(Object obj){
        this.objeto = obj;
    }
}
