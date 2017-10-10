/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 *  Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Stepan Chmel chms00 30/12/2016
 * @version   final
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    
    private HerniPlan hPlan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     *  
     *  @param hPlan herní plán, ve kterém se budou ve hře "prozkoumavat veci" 
     */
    public PrikazProzkoumej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "prozkoumej". Zkouší prozkoumat vami zadanou vec.
     *  Pokud vec existuje a je v prostoru, dojde k jejimu prozkoumani.
     *  Hraci je vracen retezec obsahujici popis veci.
     *  Pokud zadana vec neni v prostoru, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno predmetu (veci),
     *                         kterou chcete prozkoumat.
     *                         
     *@return zpráva, kterou vypíše hra hráči
     *
     *
     */ 
    public String proved(String... parametry) {
        //pokud nezadate co se ma prouzkoumat, tedy pouze prikaz prozkoumej -> vypise se tato chybova hlaska
        if (parametry.length < 1) {
            return "nevím, co mám prozkoumat";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = hPlan.getAktualniProstor().odeberVec(nazevVeci);
        //Pokud vec v prostoru neni -> vypise se tato chybova hlaska
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        hPlan.getAktualniProstor().vlozVec(vec);
        
        return nazevVeci + ": " + vec.getPopis();
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
