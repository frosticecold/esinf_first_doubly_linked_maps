/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class MaquinaSenhas {

    Map<Assunto, PriorityQueue<Senha>> mapaSenhas;

    public MaquinaSenhas() {
        mapaSenhas = new HashMap<>();
    }

    public void criarAssuntos(Set<Assunto> setAssuntos) {
        for (Assunto a : setAssuntos) {
            if (!mapaSenhas.containsKey(a)) {
                mapaSenhas.put(a, new PriorityQueue<>());
            }
        }
    }
}
