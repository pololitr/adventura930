/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;


/*******************************************************************************
 *  Třída PrikazObsah implementuje pro hru příkaz vbatohu.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Stepan Chmel 30/12/2016
 * @version   final
 */
public class PrikazObsah implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
   private static final String NAZEV = "vbatohu";
   private HerniPlan herniPlan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     *  
     *  @param herniPlan herní plán, ve kterém se bude ve hře "divat do batohu" 
     */
    public PrikazObsah(HerniPlan herniPlan)
    {
       this.herniPlan = herniPlan; 
    }
    
    /**
     *  Provádí příkaz "vbatohu". Trida zpracovava vraceni obsah batohu.
     *  Pokud je batoh prazdny dojde k vypsani hlasky o prazdnosti batohu.
     *  V opacnem pripade vypise seznam veci.
     *  
     *
     *
     *                         
     *@return zpráva, kterou vypíše hra hráči
     *
     *
     */ 
    public String proved(String... parametr) {
        if (parametr.length > 0) {
            return "\nPrikaz vbatohu nema dasli vstup, staci zadat pouze vbatohu";
        }
        else if (herniPlan.getBatoh().getObsah().isEmpty()) {
            // pokud je batoh prázdný
            return "\nBatoh je prazdny.";
        }
        else {
            // pokud je v batohu jedna a více věcí.
            return "\nV batohu je:" + herniPlan.getBatoh().getObsah() + ".";
        }
    }
    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda vraci nazev prikazu pro dalsi pouziti
     * 
     * @ return nazev prikazu
     */
    public String getNazev() {
            return NAZEV;
        }

    //== Soukromé metody (instancí i třídy) ========================================

}
