/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 *  Třída PrikazMluv implementuje pro hru příkaz mluv.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Stepan Chmel chms00 30/12/2016
 * @version   final
 */
public class PrikazMluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "mluv";
    
    private HerniPlan hPlan;
    private Hra hra;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     *  
     *  @param hPlan herní plán, ve kterém se bude ve hře "mluvit" 
     */
    public PrikazMluv(Hra hra)
    {
        this.hra = hra;
        this.hPlan = hra.getHerniPlan();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "mluv". Zkouší mluvit s vami zadanou postavou.
     *  Pokud postava existuje a je v prostoru, dojde k "mluveni".
     *  Hraci je vracen retezec obsahujici proslov danne postavy.
     *  Pokud zadana postava neni v prostoru, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno postavy,
     *                         se kterou chcete mluvit.
     *                         
     *@return zpráva, kterou vypíše hra hráči
     *
     *Tento prikaz ma 2 vyjimky
     *Pokud je postava se kterou chcete mluvit madarskyVojak hra je ukoncena
     *s vypisem ze byl hrac byl zadrzen
     *
     *Pokud je postava se kterou chcete mluvit veliciDustojnik
     *hra se rovnez ukonci ale vypise se, ze jste hru vyhral
     */ 
    public String proved(String... parametry) {
       //pokud nezadate skym se ma mluvit, tedy pouze prikaz mluv -> vypise se tato chybova hlaska
        if (parametry.length < 1) {
            return "nevím, ským mám mluvit";
        }
        
        String jmenoPostavy = parametry[0];
        
        Postava postava = hPlan.getAktualniProstor().odeberPostavu(jmenoPostavy);
        //Pokud postava v prostoru neni -> vypise se tato chybova hlaska
        if (postava == null) {
            return "věc '" + jmenoPostavy + "' tu není";
            
        }
        
        hPlan.getAktualniProstor().vlozPostavu(postava);
        //Pokud je postava madarsky oslovena hra konci a prohravate
        if(jmenoPostavy.equals("MadarskyVojak")){
         hra.setKonecHry(true);
         System.out.println(jmenoPostavy + ": " + postava.getRec());
         hPlan.setHracVyhral(false);
         return "Pri nelegalnim prechodu hranice, jste promlvil s vojakem.\nDoslo k vasi neutralizaci";
        }
        //Pokud je postava veliciDustojnik oslovena hra konci a vyhravate
        else if(jmenoPostavy.equals("veliciDustojnik")){
         //hra.setKonecHry(true);
         System.out.println(jmenoPostavy + ": " + postava.getRec());
         hPlan.setHracVyhral(true);
         return "Mise splnena";
        }
        return jmenoPostavy + ": " + postava.getRec();
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
