package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * Doparcoval  Stepan Chmel chms00 30/12/2016
 *  
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Riha
 * @version    ZS 2016/2017
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param hra hra, kterou budeme hrat
    */    
    public PrikazJdi(Hra hra) {
        this.hra = hra;
        this.plan = hra.getHerniPlan();
        
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vejít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     *
     *Prikaz jdi take kontroluje zdali nechcete vejit do nektere ze 4 mistnosti
     *Ktera vyvolava nejakou reakci
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }
        
        //Do promene smer se ulozi vstupni parametr
        String smer = parametry[0];
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        //metoda vratSousedniProstor nam rekne zda muzete jit dovami zamyslene lokace

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        
        else {
            //Pri vstupu do mistnosti haifa se zjisti hodnodta parametru vom
            //pokud je false hra zkonci
            if(smer.equals("Haifa")&&!plan.getVom()){
                plan.setHracVyhral(false);
                hra.setKonecHry(true);  
                return "\nUdelalo se ti nevolno, jelkoz jsi nepouzil tabletu\nmusel jsi jit ven. Na palube lodi jsi vsak uklouzl a prepad do vody,\nkde jsi se utopil";
            }
            //Pri vstupu do mistnosti belehrad se zjisti hodnodta parametru mon
            //pokud je false hra zkonci
            else if(smer.equals("Belehrad")&&!plan.getMont()){
                plan.setHracVyhral(false);
                hra.setKonecHry(true);
                return "\nNeml jsi potrebny doklad a tak jsi byl poslan zpet do protekotratu,\nkde si te vzalo do parady gestapo";
                
            }
            //Pri vstupu do mistnosti vlak_do_Solune se zjisti hodnodta parametru let
            //pokud je false hra zkonci
            else if(smer.equals("vlak_do_Solune")&&!plan.getLet()){
                plan.setHracVyhral(false);
                hra.setKonecHry(true);
                return "\nByl jste povazovan za nemce, asi by vam byval pomohl falseny doklad anglickeho letce";
            }
            //Pri vstupu do mistnosti vlak_do_Istanbulu se zjisti hodnodta parametru pas
            //pokud je false hra zkonci
            else if(smer.equals("vlak_do_Istanbulu")&&!plan.getPas()){
                plan.setHracVyhral(false);
                hra.setKonecHry(true);
                return "\nNepouzil jste pas, s potrbnym dokladem vas do vlaku nepustili";
            }
            //pokdu se nepovede nektera ze 4 vyjimek dojde ke skutecnemu vstupu do mistnosti
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis()+ "\n-------------------------------------------";
        }
        
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
