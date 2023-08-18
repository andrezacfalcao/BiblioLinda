package br.ufrpe.bibliolinda.exception;

public class ObjetoInvalidoException extends Exception{
    private Object objeto;
    // Inválido pra busca, como um dos atributos nulos.
    public ObjetoInvalidoException() {
        super("Objeto inválido para busca");
    }
    //Não existe
    public ObjetoInvalidoException(Object obj) {
        super("O objeto não está presente no sistema");
        this.objeto = obj;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
}
