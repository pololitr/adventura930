package logika;


/**
 * Třída Hra - třída představující logiku adventury.
 * 
 * Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan,
 * která inicializuje místnosti hry a vytváří seznam platných příkazů a instance tříd
 * provádějící jednotlivé příkazy. Vypisuje uvítací a ukončovací text hry. Také
 * vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * Dopracoval Stepan Chmel 30/12/2016
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    ZS 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private Batoh batoh;
    private boolean konecHry = false;
    private boolean konec = false;
    

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        batoh = new Batoh();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazVezmi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdhod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(this));
        platnePrikazy.vlozPrikaz(new PrikazObsah(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazUse(this));

    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte!\n" +
               "Tato adventura se inspiruje příběhem českosloveských\n" +
               "hrdinů, kteří se snažili dopravit na území neovládané\n"+
               "Německou 3. Říší a vstoupit do některé ze spojeneckých armád.\n" +
               "Tato adventura bude kopírovat takzvanou balkánskou cestu\n" +
               "Nikomu nevěřte. Zkoumejte předměty.\n" +
               "Dávejte si pozor kde použijete jaký předmět.\n\n" +
               "Napište 'napoveda', pokud si nevíte rady, jak hrát dál." +
               "\nBatoh ma kapacitu: " +batoh.getMaxCap()+"\n"+ //vypise kapacitu batohu
               "\n"+herniPlan.getAktualniProstor().dlouhyPopis()+ //popis aktualniho prostoru
               "\n-------------------------------------------";
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     *  mohou nastat 3 scenare
     *  
     *  1. Hrac ukoncil hru
     *  2. Hrac vyhral
     *  3. Hrac prohral
     */
    public String vratEpilog() {
        String rec = "Dík, že jste si zahráli.  Ahoj.";
        
        if (prkonec()){
            rec = "Je nam lito ze hru ukoncujete";
        }
        else if(herniPlan.hracVyhral()&&!prkonec()) {
            rec = "Gratuluji, hra vyhrana!!";    
            }
        else if(!herniPlan.hracVyhral()&&!prkonec()){
            rec = "Bohuzel hra byla prohrana, pokud chcete muzete zkusit stesti znovu";
        }
        return rec;
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     * 
     * @return parametr s boolean hodnotou
     */
    
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);

            if (herniPlan.hracVyhral()) {
                konecHry = true;
            }
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní IPrikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
     
     /**
      * Proto aby se mohl vypisovat epilog v zavislosti na vyhre ci nevyhre
      * Je nutno odlisit ukonceni hry prikazem konec
      * 
      * Tento setter se nastavi na true pokud hrac spusti prikaz konec
      */
     void setPrkonec(boolean konec) {
        this.konec = konec;
    }
    
    /**
     * Getter pro zjisteni spusteni prikazu konec
     * Pouzit ve tride hra v metode epilog
     * 
     * @return parametr s boolean hodnotou
     *
     */
     public boolean prkonec() {
        return konec;
    }
}
