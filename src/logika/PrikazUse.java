/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;


/*******************************************************************************
 *  Třída PrikazUse implementuje pro hru příkaz pouzij.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Stepan Chmel chms00 30/12/2016
 * @version   final
 */
public class PrikazUse implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "pouzij";
    private Hra hra;
    private HerniPlan herniPlan;
    private Batoh batoh;
    String vypis;
    String fotoHP ="fotografie";
    
    Prostor cil = null;
    Prostor cil2 = null;
   
    //== Konstruktory a tovární metody =============================================
    String nepouzitlne = "\nTento predmet se neda pouzit.";
    /***************************************************************************
     *  Konstruktor ....
     *  
     * @param herniPlan herní plán, ve kterém se bude ve hře "pouzivat veci" 
    *  @param hra hra, kterou budeme hrat
    *  @param batoh batoh, který mude hráč ve hře mít
    *  @param cil pomocna promena pro pouziti letadla
    *  @param cil2 pomocna promena pro pouziti kamenu
     */
    public PrikazUse(Hra hra)
    {
 
      this.hra = hra;
      this.herniPlan = hra.getHerniPlan();
      batoh = herniPlan.getBatoh();
    
   
      this.cil = herniPlan.getCil();
      this.cil2 = herniPlan.getCil2();
      
    }
    
    /**
     *  Provádí příkaz "pouzij". Zkouší pouzit vami zadanou vec. Pokud je
     *  vec jednim z casu switche teto metody dojde k  jejimu pouziti.
     *  
     *  Pokud zadana vec jednim z casu, tedy nema ve hre pouziti
     *  vypíše se oznamovaci hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno veci,
     *                         kterou chcete pouzit.
     *                         
     *@return vypis - zpráva, kterou vypíše hra hráči
     *
     *Z duvodu spravnosti pro PDM a jeho limitu 50 prikazu a 100 radku na metodu 
     *byly pro vetsinu casu vyvoreny omocne metody
     */ 
    public String proved(String... parametry){
        // pokud nezadate co chcete pouzit, tedy zadate pouze prikaz pouzij vypise se chybove hlaseni 
        if (parametry.length < 1) {
            return "nevím, co mám pouzit";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
       
        Vec vec = aktualniProstor.odeberVec(nazevVeci);
         
       // pokud neni vec v prostoru deojde ani k overeni jeji pouzitelnosti a je vypsana chybova hlaska 
        if( vec == null){
            return "věc '" + nazevVeci + "' neni v prostoru";     
        } 
        else{
            //V tomto switch prikazu dochazi ke zpracovani prikazu
            switch(nazevVeci){
                case "AviaB534":
                    vypis = helpAvia(aktualniProstor); //Vyuziva pomocnou metodu
                    break;
                    
                case "PrukazLetcu":
                    vypis = heplLetci(aktualniProstor);//Vyuziva pomocnou metodu
                    break;
                    
                case "ManualAvia":
                    vypis = helpManual(aktualniProstor);//Vyuziva pomocnou metodu
                    break;
                    
               case "PrukazMonteru":
                    vypis = helpMont(aktualniProstor);//Vyuziva pomocnou metodu
                    break;
                    
               case "pas":
                    vypis = helpPas(aktualniProstor);//Vyuziva pomocnou metodu
                    break;
                         
               case "kamen":
                    helpKamen(aktualniProstor);//Vyuziva pomocnou metodu
                    break;
                         
               case "telefonni_budka":
                    vypis = helpBud(aktualniProstor);//Vyuziva pomocnou metodu
                    break;
                    
              case "rukavice":
                    vypis = helpRukavice(aktualniProstor);//Vyuziva pomocnou metodu
                    break;
              
              case "kebab":
                    vypis = helpKebab(aktualniProstor);//Vyuziva pomocnou metodu
                    break;
                         
              case "formular":
                    vypis = "Muzete opustit mistnost(>jdi Istanbul)";//Jediny case bez pomocne metody
                    aktualniProstor.setVychod(cil);   //V mistnosti zachytne_pracoviste neni vychod zpet, ten se vytovri az po pouziti formulare
                    break;
                    
              case "tableta":
                    vypis = helpTab(aktualniProstor);//Vyuziva pomocnou metodu
                    break;
               
               
              default:
                vypis = "Pro tuto vec neni ve hre uplatneni"; //Pokud vami zadana vec nei pouzitelna, tedy neni jednim z casu je tato hlaska ulozena do vypisu
                aktualniProstor.vlozVec(vec);
                break;  
        }
       }
       /*//Nektere veci nechceme po jejich pouziti vratit do prostoru
       if( nazevVeci.equals("AviaB534") ||  nazevVeci.equals("kamen") || nazevVeci.equals("rukavice")|| nazevVeci.equals("formular") || nazevVeci.equals("tableta")){
          return "\nvec se neda znovu pouzit\n" + vypis;  
        }
        else{
            //vlozeni premetu zpet do prostoru po jeho pouziti
            aktualniProstor.vlozVec(vec);
            return vypis; //kazdy case ma svoji specifickou hlasku kterou vlozi do prom vypis
       }*/
       return vypis;  
    }
    
    /**
     * Pomocna metoda pro vec Avia
     * Prenese vas do prostoru Istanbul pokud umite letat
     */
    public String helpAvia(Prostor prostor){
        String vrac;
        Prostor cil = herniPlan.getCil();
       if(herniPlan.getUmisLitat()){
           System.out.println("\nNasedas do letadla a letis, pokusis se doletet co nejdal");
           System.out.println("Pristals\n");
           herniPlan.setAktualniProstor(cil);
           System.out.println(cil.dlouhyPopis());
           vrac = "letadlo bylo pouzito, na dalsi let vsak neni benzin";
           
           return vrac;
           
       }
       else{
            vrac = "je mi lito neumis letat";    
                 return vrac;
       } 
        
    }
    
    /**
     * Pomocna metoda pro case potvrzeniletcu
     * Pokud ativujete potvrzeni na uzemi nemeckeho vlivu zkoncite
     * pokud ho aktivujete jinde je nastaveno ze se prezentujete jako VB letec
     * 
     */
    public String heplLetci(Prostor prostor){
        String vrac;
        String pom = prostor.getNazev();
        if (pom.equals("Dubovec")||pom.equals("pobockaCKD")||pom.equals("kancelar")||pom.equals("do_Madarska")||pom.equals("vlak_do_Belehradu")){
            vrac ="\nV prostoru pod vlivem nacistu jste se prezentoval jako anglicky letec,\njako obyvatel protektoratu jste byl zastřelen pro velezradu";
            herniPlan.setHracVyhral(false);
            hra.setKonecHry(true);  
            
            return vrac;            
        }
        else {
        vrac = "Od této chvíle se prezentujete jako anglický letec ";
        herniPlan.setLet(true);
        
        return vrac;
    }
    }
    
    /**
     * Pomocna metoda pro case manualiAvia
     * po pouziti se nastavi hodnota umisletat na true
     */
    public String helpManual(Prostor prostor){
      String vrac;
      vrac = "\nOd teto chvile umite pilotovat AviiB534";
      herniPlan.setUmisLitat(true);
      
      return vrac;
        }
    
        /**
         * Pomocna metoda pro case prukazmonteru
         * pokud pouzijete tento predmet 
         * nastavi se hodnota mont na true a prezentuje te se jako monter
         */
     public String helpMont(Prostor prostor){
        String vrac;
        vrac = "\nOd teto chvile se prezentujete jako montér směřující do bělehradského závodu";
        herniPlan.setMont(true);
        return vrac;
    } 
    
    /**
     * Pomocna metoda pro case pas
     * pokud pouzijete tento predmet 
     * nastavi se hodnota pas na true a prezentuje se falesnym pasem
     */
    public String helpPas(Prostor prostor){
        String vrac;
        vrac = "\nOd teto chvíle Používáte tento pas";
        herniPlan.setPas(true);          
        return vrac;
    }
    
    /**
     * Pomocna metoda pro case kamen
     * pokud pouzijete tento predmet 
     * jste telepotovan do Haify
     * 
     */   
    public String helpKamen(Prostor prostor){
      String vrac;
      vrac = "\nByl to kouzelny kamen, presunul vas do ciloveho prostoru";
      herniPlan.setAktualniProstor(cil2);
      System.out.println(cil2.dlouhyPopis());
      return vrac;
        }
    
    /**
     * Pomocna metoda pro case telefoni_budka
     * pokud pouzijete tento predmet 
     * Ziskate fotografii Hercuila Poirota
     * tato foto je vam vlozena do batohu
     * 
     */  
    public String helpBud(Prostor prostor){
        String vrac;
        vrac = "\nNašel jsi fotografii Hercuila Poirota byla vlozena do batohu";
        Vec foto = new Vec(fotoHP,"Krasna fotografie Hercuila Poirota",true);
        batoh.pridejVec(foto);
        
        return vrac;
    }    
    
     /**
     * Pomocna metoda pro case rukavice
     * pokud pouzijete tento predmet 
     * Ziskate podmis na fotografii Hercuila Poirota(pokud ji mate) a radu
     * Pokud ne dostanete radu
     * 
     * pokd jste jinde nez v vlak_do_Istanbulu rukavice poze vypisi informaci
     */  
     public String helpRukavice(Prostor prostor){
        String vrac;
        String pom = prostor.getNazev();
        vrac = "\nZahral ste si ruce";
        if(batoh.getVec(fotoHP)==null&&pom.equals("vlak_do_Istanbulu")){
            vrac = "\nVelice dekuji, na oplatku vam doporucim aby jste ochutnal takzvany Kebab je to Turecka specialita";
        }
        else if(batoh.getVec(fotoHP)!= null&&pom.equals("vlak_do_Istanbulu")){
            batoh.remVec(fotoHP);
            Vec foto2 = new Vec("PodepsanaFotografie","Krasna fotografie Hercuila Poirota s jeho podpisem",true);
            vrac = "\nVelice dekuji, vidim ze jste muj fanousek, doporucji vam nejist kebab!!\n Hercula vam fotografii podepsal\nDo batohu byla vlozena vec PodepsanaFotografie";
            batoh.pridejVec(foto2);
            
        }
        return vrac;
    }
    
    /**
     * Pomocna metoda pro case kebab
     * pokud pouzijete tento predmet 
     * Zemrete a hra konci
     */
    public String helpKebab(Prostor prostor){
      String vrac;
      vrac ="\nRodice vas dozajita ucili, nebav se s cizimi lidmi a nejz jidlo ze zeme";
      System.out.println("\nPo snedeni kebabu jste umrel\n");
      herniPlan.setHracVyhral(false);
      hra.setKonecHry(true);
      return vrac;
        }
   
    /**
     * Pomocna metoda pro case tableta
     * pokud pouzijete tento predmet 
     * hodnota promene Vom se nastavi na true
     */
    public String helpTab(Prostor prostor){
      String vrac;
      vrac = "po tablete je vam lip";
      herniPlan.setVom(true);
      return vrac;
        }
     
     /**
     * Metoda vraci nazev prikazu pro dalsi pouziti
     * 
     * @ return nazev prikazu
     */
    public String getNazev() {
            return NAZEV;
            
        }
    //== Nesoukromé metody (instancí i třídy) ======================================


    //== Soukromé metody (instancí i třídy) ========================================

}
