package estoque2000.com.br.estoque2000.modelo;

import java.io.Serializable;

public class Local implements Serializable {

    private Long idlocal;
    private String Localizacao;
    private String detalhes;
    private String quadrante;

    public Long getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(Long idlocal) {
        this.idlocal = idlocal;
    }

    public String getLocalizacao() {
        return Localizacao;
    }

    public void setLocalizacao(String localizacao) {
        Localizacao = localizacao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getQuadrante() {
        return quadrante;
    }

    public void setQuadrante(String quadrante) {
        this.quadrante = quadrante;
    }

    @Override
    public String toString() {
        return "IdLocal: " + idlocal +", Localizacao=" + Localizacao + '\n';
    }
}
