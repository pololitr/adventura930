/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;


/*******************************************************************************
 *  Třída PrikazVezmi implementuje pro hru příkaz vezmi.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Stepan Chmel chms00 30/12/2016
 * @version   final
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi"; 
    private HerniPlan herniPlan;
    private Batoh batoh;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     *  
     *  @param herniPlan herní plán, ve kterém se budou ve hře "brat veci" 
     *  @param batoh batoh, ve kterém se budou ve hře "prenaset veci" 
     */
    public PrikazVezmi(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
        this.batoh = herniPlan.getBatoh();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "vezmi". Zkouší vzit vami zadanou vec. Pokud vec
     *  existuje a je prenositelna, dojde k vlozeni do batohu. 
     *  Pokud neexistuje nebo neni prenositelna
     *  Vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno veci
     *                         kteru chcete vzit
     *                         
     *@return zpráva, kterou vypíše hra hráči
     *
     *Prikaz vezmii take kontroluje zdali je v batohu misto pokud
     *neni tak nedojde k vlozeni veci do batohu
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "nevím, co mám sebrat";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Vec vec = aktualniProstor.odeberVec(nazevVeci);
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        else{
            if (vec.isPrenositelna()) {
                if(batoh.isSpace()) {
                   batoh.pridejVec(vec);
                   // pokud se podaří věc vložit do batohu
                   // vypise se hlaskvv7a o vlozeni veci do batohu
                   return "věc '" + nazevVeci + "' jsi uložil do batohu";
                    
                }
                      
                else{
                   // vrátí věc do prostoru, pokud je batoh plný
                    aktualniProstor.vlozVec(vec);
                    return "batoh je plný"; 
                    
                }    
            }
            else{
                //Pokud se vec neda prenaset tedy hodnota isprensitelna je false nedojde k jejimu volzeni do batohu
               aktualniProstor.vlozVec(vec);
               return "věc '" + nazevVeci + "' není určená k přenášení";   
            }
            
        }
       }
       
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
