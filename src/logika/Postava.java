/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Trida Postava - popisuje jednotlivé postavy  hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Postava" reprezentuje jedno postavu ve scénáři hry.
 *  Postava ma sve jmeno a svuj proslov
 *
 * @author    Stepan Chmel chms00 30/12/2016
 * @version   final
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================
    private String jmeno;
    private String rec;
    
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     *  nastavime, ze jmeno a rec ktere ze budeme pouzivat 
     *  jsou stejne jako parametry objektu Postava
     *  
     */
    public Postava(String jmeno, String rec)
    {
        this.jmeno = jmeno;
        this.rec = rec;
        
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Getter pro jmeno postavy
     */
    public String getJmeno() {
        return jmeno;
    }
    /**
     * Getter pro rec postavy
     */
    public String getRec() {
        return rec;
    }
    
   

    // Možná bude potřeba přidat settery pro atributy 'popis' a 'prenositelna'.
    // Atribut 'nazev' by se měnit neměl.
    
    //== Soukromé metody (instancí i třídy) ========================================

}
