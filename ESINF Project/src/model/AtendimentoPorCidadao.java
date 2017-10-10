/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class AtendimentoPorCidadao {

    private Set<Cidadao> setCidadao;
    private Map<Cidadao, Set<Character>> mapaServicosPorCidadao;

    public AtendimentoPorCidadao() {
        setCidadao = new HashSet<>();
        mapaServicosPorCidadao = new HashMap<>();
    }

    public boolean adicionarAtendimento(Cidadao c, Senha s) {
        if (!setCidadao.contains(c)) {
            setCidadao.add(c);
            mapaServicosPorCidadao.put(c, new HashSet<>());
            mapaServicosPorCidadao.get(c).add(s.getCodServico());
        } else {
            if (!mapaServicosPorCidadao.get(c).contains(s.getCodServico())) {
                mapaServicosPorCidadao.get(c).add(s.getCodServico());
            }
        }
   return true; }

}
