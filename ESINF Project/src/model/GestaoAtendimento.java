/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estruturas.RegistoReparticao;
import java.util.HashMap;
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

    public GestaoAtendimento() {

        mapaReparticaoPorNumContribuinte = new HashMap<>();
        registoReparticao = new RegistoReparticao();
    }

    public RegistoReparticao getRegistoReparticao() {
        return registoReparticao;
    }
}
