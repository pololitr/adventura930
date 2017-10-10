/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Trida Vec - popisuje jednotlivé veci hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Vec" reprezentuje jedno vec ve scénáři hry.
 *  Vec ma sve jmeno, popis a take informaci otom, zdali je prenositelna
 *  
 *  @author Stepan Chmel chms00 30/12/2016
 *  @version final
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     *  nastavime, ze nazev popis a prenositelnost kterou ze budeme pouzivat 
     *  jsou stejne jako parametry objektu Vec
     */
    public Vec(String nazev, String popis, boolean prenositelna)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Getter pro nazev veci
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     * 
     * getter pro popis veci
     */
    public String getPopis() {
        return popis;
    }
    
    /**
     * Getter pro boolean informaci o prenositelnosti veci
     * 
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    

    //== Soukromé metody (instancí i třídy) ========================================

}
