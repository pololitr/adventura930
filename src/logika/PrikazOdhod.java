/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;


/*******************************************************************************
 *  Třída PrikazOdhod implementuje pro hru příkaz odhod.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Stepan Chmel chms00 30/12/2016
 * @version  final
 */
public class PrikazOdhod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "odhod";
    private HerniPlan herniPlan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     *  
     *  @param herniPlan herní plán, ve kterém se budou ve hře "odhazovat veci" 
     */
    public PrikazOdhod(HerniPlan herniPlan)
    {
        this.herniPlan = herniPlan;
    }
    
    /**
     *  Provádí příkaz "Odhod". Zkouší odhodit vami zadanou vec. Pokud je
     *  vec v batohu dojde k odhozeni tedy vlozeni do aktualniho prostoru.
     *  Pokud zadana vec neni v batohu
     *  vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno veci,
     *                         kterou chcete odhodit.
     *                         
     *@return zpráva, kterou vypíše hra hráči
     *
     *
     */ 
    public String proved(String... parametry){
        if (parametry.length == 0) {
            return "\nNevim co mam odhodit";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Batoh batoh = herniPlan.getBatoh();
        Vec mazana = batoh.getVec(nazevVeci);
        
        if (mazana == null) {
            // pokud mazana věc není v batohu vypise se chybova hlaska
            return "\nPozadovana vec neni v batohu:'" +nazevVeci+"'";            
        }
        else {
            // pokud je věc smazána z batohu, přesune se do aktualního prostoru
            mazana = batoh.remVec(nazevVeci);
            aktualniProstor.vlozVec(mazana);
            return "\nOdhozena vec: '" + nazevVeci + "'";
        }
    
    }
    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * 
     * Metoda ktera vrati nazev pro dalsi pouziti
     * 
     * @ return nazev prikazu
     */  
    public String getNazev() {
            return NAZEV;
        }

    //== Soukromé metody (instancí i třídy) ========================================

}
