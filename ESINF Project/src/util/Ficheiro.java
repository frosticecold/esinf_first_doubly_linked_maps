/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.GestaoAtendimento;
import model.Reparticao;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Ficheiro {

    private String DELIMITADOR = ",";

    public Ficheiro() {
    }

    public List<String> lerFicheiro(String nomeFicheiro) {
        Scanner scn = null;
        List<String> lista = new ArrayList<>();
        try {
            scn = new Scanner(new FileReader(nomeFicheiro));
            while (scn.hasNext()) {
                lista.add(scn.next());
            }
        } catch (FileNotFoundException ex) {
            System.out.printf("Foi impossível ler o ficheiro %s\n", nomeFicheiro);
        } finally {
            if (scn != null) {
                scn.close();
            }
        }
        return lista;
    }

    public void lerReparticoes(GestaoAtendimento ga, String nomeFicheiro) {
        Reparticao r = null;
        final int C_CIDADE = 0, C_NUMREP = 1, C_CODPOSTAL = 2;
        List<String> lista = lerFicheiro(nomeFicheiro);
        for (int i = 0; i < lista.size(); i++) {
            String ls[] = lista.get(i).split(DELIMITADOR);
            r = new Reparticao(ls[C_CIDADE], Integer.parseInt(ls[C_NUMREP]), ls[C_CODPOSTAL]);
            if (ls.length > C_CODPOSTAL + 1) {
                for (int j = C_CODPOSTAL + 1; j < ls.length; j++) {
                    r.adicionarServiço(ls[j].charAt(0));
                }
            }
        }
        if (r != null) {
            System.out.println(r);
        }
    }
}
