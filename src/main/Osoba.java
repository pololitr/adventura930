/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author chms00
 */
public class Osoba {
    private String jmeno;
    private String prijemni;
    private int vek;

    public Osoba(String jmeno, String prijemni, int vek) {
        this.jmeno = jmeno;
        this.prijemni = prijemni;
        this.vek = vek;
    }

    public Osoba(String jmeno, String prijemni) {
        this.jmeno = jmeno;
        this.prijemni = prijemni;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijemni() {
        return prijemni;
    }

    public int getVek() {
        return vek;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setPrijemni(String prijemni) {
        this.prijemni = prijemni;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }
    
    
    
}
