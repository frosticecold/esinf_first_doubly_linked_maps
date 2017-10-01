package model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Reparticao {

    /**
     * Cidade da Repartição
     */
    private String cidade;
    /**
     * Número da repartição
     */
    private int n_reparticao;
    /**
     * Código postal da partição
     */
    private CodigoPostal cod_postal;

    /**
     * Lista com assuntos únicos;
     */
    private Set<Assunto> stAssnt;

    private static final String VALOR_OMISSAO = "Sem valor";
    private static int NUMERO_REPARTICAO_CRIADAS = 0;

    public Reparticao() {
        cidade = VALOR_OMISSAO;
        n_reparticao = NUMERO_REPARTICAO_CRIADAS + 1;
        cod_postal = new CodigoPostal();
        stAssnt = new HashSet<>();
        NUMERO_REPARTICAO_CRIADAS++;
    }

    public Reparticao(String cidade, int n_reparticao, CodigoPostal cod_postal, Set<Assunto> setAss) {
        this.cidade = cidade;
        this.n_reparticao = n_reparticao;
        this.cod_postal = cod_postal;
        this.stAssnt = setAss;
    }

    public String getCidade() {
        return cidade;
    }

    public int getNumReparticao() {
        return n_reparticao;
    }

    public CodigoPostal getCodPostal() {
        return cod_postal;
    }

    public Set<Assunto> getSetAssunto() {
        return stAssnt;
    }

    public boolean adicionarAssunto(Assunto a) {
        return stAssnt.add(a);
    }

    public boolean removerAssunto(Assunto a) {
        return stAssnt.remove(a);
    }

    public int quantosAssuntos() {
        return stAssnt.size();
    }
}
