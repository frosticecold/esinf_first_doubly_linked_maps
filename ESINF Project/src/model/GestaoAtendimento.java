/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estruturas.RegistoCidadao;
import estruturas.RegistoReparticao;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class GestaoAtendimento {

    private String FX_REPARTICAO = "fx_repartições.txt";
    private String FX_CIDADAOS = "fx_cidadaos.txt";
    private String FX_SENHAS = "fx_senhas.txt";

    private Map<Integer, Reparticao> mapaReparticaoPorNumContribuinte;
    private RegistoReparticao registoReparticao;
    private RegistoCidadao registoCidadao;

    public GestaoAtendimento() {

        mapaReparticaoPorNumContribuinte = new HashMap<>();
        registoReparticao = new RegistoReparticao();
        registoCidadao = new RegistoCidadao();
    }

    public RegistoReparticao getRegistoReparticao() {
        return registoReparticao;
    }

    public void adicionarReparticao(Reparticao r) {
        boolean added = registoReparticao.adicionarReparticao(r, this);
        if (added == true) {
            passarCidadaosDeUmaReparticaoParaOutra(r);
        }
    }

    public void passarCidadaosDeUmaReparticaoParaOutra(Reparticao r) {
        List<Cidadao> listaCidadaosPorCodPostal = registoCidadao.obterCidadaosPorCodigoPostal(r);
        if (!listaCidadaosPorCodPostal.isEmpty()) {
            for (Cidadao c : listaCidadaosPorCodPostal) {
                c.setNumReparticao(r.getNumReparticao());
            }
        }
    }

    public void validarTodaEstruturaDeDados() {
        ListIterator<Reparticao> it = registoReparticao.listIterator();
        while (it.hasNext()) {
            Reparticao r = it.next();
            passarCidadaosDeUmaReparticaoParaOutra(r);
        }
    }
}
