/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estruturas.DoublyLinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Raúl Correia
 */
public class Sistema {

    DoublyLinkedList<Reparticao> dllReparticao;
    Set<Cidadao> setCidadao;

    public Sistema() {
        dllReparticao = new DoublyLinkedList<>();
        setCidadao = new HashSet<>();
    }
}
