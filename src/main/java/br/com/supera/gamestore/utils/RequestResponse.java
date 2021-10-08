package br.com.supera.gamestore.utils;

import org.springframework.http.StreamingHttpOutputMessage;

public class RequestResponse<P> {

    private boolean Erro;

    private String Mensagem;

    public RequestResponse(boolean Erro, String mensagem) {
        this.Erro = Erro;
        Mensagem = mensagem;
    }

    public RequestResponse() {
    }

    public boolean isErro() {
        return Erro;
    }

    public void setErro(boolean erro) {
        Erro = erro;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String mensagem) {
        Mensagem = mensagem;
    }
}
