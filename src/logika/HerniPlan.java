package logika;


/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * dopracoval Stepan Chmel chms00 30/12/2016
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    ZS 2016/2017
 */
public class HerniPlan {

    //private static final String CILOVY_PROSTOR = "Haifa";
    // inicializace prostoru a batohu
    private Prostor aktualniProstor;
    private Batoh batoh;
    private Prostor let;
    private Prostor kamenLET;
    //pomocne boolean promene pro pozdejsi uchovavani
    //informaci o plneni podminek
    private boolean litej = false;
    private boolean vom = false;
    private boolean mon = false;
    private boolean pas = false;
    private boolean vhr = false;
    private boolean letci = false;
    

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        this.batoh = new Batoh();
        
    }
    
    /**
     *  Vytváří jednotlivé prostory a postavy.
     *  Naplni pomocne promene let a kamenLet
     *  Vytvori pomocne pole mistnosti a postav
     *  Obsah poli vlozi do metod priradVychody() a zalozPrirad()
     *  Trida HerniPlan je takto rozdelna z duvodu spravnost vuci PMD
     *  Jako výchozí aktuální prostor nastaví Dubovec.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor Dubovec = new Prostor("Dubovec","Dubovec, slovenské město, ve kterém pracujete");
        Prostor DilnaA = new Prostor("pobockaCKD","Pobočka ČKD v této filiálce ČKD pracujete");
        Prostor DilnaB = new Prostor("kancelar","Kancelář vašeho nadřízeného");
        Prostor NelCestaM = new Prostor("do_Madarska","Nelegální přechod nové maďarské hranice");
        Prostor VlakBelehrad = new Prostor("vlak_do_Belehradu","Vlak, který vás odveze do Bělehradu");
        Prostor Belehrad = new Prostor("Belehrad","Bělehrad");
        Prostor BudovaSpojka = new Prostor("misto_setkani","Zde se máte sejít s vaší bělehradskou spojkou");
        Prostor VlakSolun = new Prostor("vlak_do_Solune","Vlak, který vás odveze do Soluně");
        Prostor Solun = new Prostor("Solun","Solun");
        Prostor VlakIstanbul = new Prostor("vlak_do_Istanbulu","Vlak, který vás odveze do Istanbulu");
        Prostor Instanbul = new Prostor("Istanbul","Istanbul");
        Prostor KancelarDustojniku = new Prostor("zachytne_pracoviste","Kancelář bývalých československých důstojníků");
        Prostor Pristav = new Prostor("pristav","Přístaviště Mersin");
        Prostor CestaHaifa = new Prostor("do_Haify","Cesta lodí do Haify, lodi cestujete poprve\ntrpite morskou nemoci.Pouzijte tabletu, pokud ji mate v batohu ");
        Prostor Haifa = new Prostor("Haifa","Haifa, místo vašeho připojení k jednotce");
        //vztvoreni postav
        Postava PanEmanuel = new Postava("PanEmanuel", "\nDobry den, tak tedy dnes ktomu dojde, az budes mit vse potrebne,\nvydej se na cestu dle PLANU");
        Postava Matej = new Postava( "Matej","\nNazdar, starej rikal, ze na tebe ma prijit rada, pokud si snim nemluvil,\nzajdi zanim je v kancelari pobocky");
        Postava Mvoj = new Postava("MadarskyVojak", "\nÖn letartóztatták!!!!");
        Postava pruvodci1 = new Postava("PruvodciMAD","\nNem tudok segíteni");
        Postava pruvodci2 = new Postava("PruvodciJUG","\nZa sada ćemo biti u Beogradu");
        Postava kamil = new Postava("Kamil","\nNazdar, tak uz mas kus zasebou. Skoc do mista_setkani,\ndaj ti tam PAS pro zbytek cesty");
        Postava ratko = new Postava("Ratko","\nTak ahoj ty od Kamil zejo,ja mam neco tobe dat");
        Postava pruvodci4 = new Postava("Pruvodci","\nEfcharistó ... Ach a fighter pilot, well we can sure use help");
        Postava senior = new Postava("senior", "\nO chrónos kylá san ta pouliá... time flies like a bird.. pick up and use that stone..trust me");
        Postava Hercule = new Postava("Hercule","\nDobrý den, me jmeno je Hercule Poirot, ztratil jsem rukavice");
        Postava csVojak = new Postava("csVojak","\nNazdar!! To sem rád že si konečně dorazil, jsi poslední zajdem do zachytneho_pracoviste");
        Postava dustojnik1 = new Postava("dustojnik","\nNuze, na nasem listu jste posledni, od teto chvile jste vojínem československé armády.\nVyplnte formular a\nOdchod!");
        Postava csVojak2 = new Postava("vojak", "\nAhoj, tak ty si přišel z čech? Já a další sme se konečně dostali z Ruska,\nkdyž jsme šli napomoc Polsku, tak nás rusáci zajali");
        Postava csVojak3 = new Postava("vojak","\nJe mi spatne asi budu ..fvbevuvkmw..to sem nejedl");
        Postava velitel = new Postava("veliciDustojnik","Dobrý den vojíne, vaše cesta zde končí a zárovň začíná");
        Postava poddustojnik = new Postava("poddustojnik","\nJe mi líto ale došlo k úniku seznamu jmen z kanceláře v Istanbulu,\nrodiče vaše i dalších byli zadrženi gestapem");
       
        
      // nasteveni prom 
       aktualniProstor = Dubovec; // hra začíná v dubovic
       let = Instanbul;
       kamenLET = Haifa;
       //pole mistnosti a postav
       Prostor[] poleMistnosti = {Dubovec, DilnaA, DilnaB, NelCestaM, VlakBelehrad, Belehrad, BudovaSpojka,
                                 VlakSolun, Solun, VlakIstanbul, Instanbul, KancelarDustojniku, Pristav, CestaHaifa, Haifa};
                                 
       Postava[] polePostavy = {PanEmanuel,Matej,Mvoj,pruvodci1,pruvodci2,kamil,ratko,pruvodci4,senior,Hercule,csVojak,dustojnik1,csVojak2,csVojak3,velitel,poddustojnik};
       
       //vlozeni hodnot poli do dalsich metod
       priradVychody(poleMistnosti);
       zalozPrirad(poleMistnosti, polePostavy);
     }
     
    /**
     * Po vykonani priradVychody(poleMistnosti) se hodnoty z poli pouziji pro prirazeni vychodu
     * Tato metoda je vytvorena z duvodu spravnost vuci PMD
     */
    private void priradVychody(Prostor prostor[]) {  
      // 0 = Dubovec, 1 = DilnaA, 2 = DilnaB, 3 = NelCestaM, 4 = VlakBelehrad, 5 = Belehrad,
      // 6 = BudovaSpojka, 7 = VlakSolun, 8 = Solun, 9 = VlakIstanbul, 10 = Instanbul, 
      //11 = KancelarDustojniku, 12 = Pristav, 13 = CestaHaifa, 14 = Haifa,
    
        prostor[0].setVychod(prostor[1]);
        prostor[0].setVychod(prostor[3]);
        prostor[1].setVychod(prostor[2]);
        prostor[1].setVychod(prostor[0]);
        prostor[2].setVychod(prostor[1]);
        prostor[3].setVychod(prostor[4]);
        prostor[4].setVychod(prostor[5]);
        prostor[5].setVychod(prostor[6]);
        prostor[5].setVychod(prostor[7]);
        prostor[6].setVychod(prostor[5]);
        prostor[7].setVychod(prostor[8]);
        prostor[8].setVychod(prostor[9]);
        prostor[9].setVychod(prostor[10]);
        prostor[10].setVychod(prostor[11]);
        prostor[10].setVychod(prostor[12]);
        prostor[12].setVychod(prostor[13]);
        prostor[13].setVychod(prostor[14]);
    }
    
    /**
     * Metoda zalozPrirad() po vykonai prikazu zalozPrirad(poleMistnosti, polePostavy) 
     * v metode zalozProstoryHry() ziska data pro vlozeni veci, ktere zde vytvarime do mistnosti
     * Stejne tak vlozi postavy do prostor
     */
    private void zalozPrirad(Prostor prostor[],Postava postava[]){
       prostor[1].vlozVec(new Vec("PrukazLetcu", "\nFalešný průkaz anglických letců, použij až v bělehradu", true));
       prostor[1].vlozVec(new Vec("ManualAvia", "\nManuál pro letadlo AviaB534", true));
       prostor[2].vlozVec(new Vec("PrukazMonteru","\nFalešný průkaz montéra z bělehradského továrního závodu", true));
       prostor[2].vlozVec(new Vec("PlanCesty","\nPlán cesty je zakódován v časopisu a vypadá následovně:"+
                                       "\n1)Poté co získáte vše potřebné vydejte se na nelegalni cestu do Maďarska"+
                                       "\n2)Po vstoupení do Maďarska se vydejte vlakem do Bělehradu"+
                                       "\n3)V Bělehradu vyhledejte Kamila, ten vám řekne co budete potřebovat pro další cestu"+
                                       "\n4)Z Bělehradu pokračujte vlakem do Soluni, budete opouštět jugoslávii,\nv řecku na vás mohou polížet jako na němce"+  
                                       "\n5)Ze Soluně se vydejte vlakem do Istanbulu, zde už naleznete zástupce čs. exilové armády"+
                                       "\n6)Z Istanbulu se musíte dostat do přístavu Mersin"+
                                       "\n7)Z přístavu putujete lodí do Haify"+
                                       "\n8)Haifa je vaše cílová destinace",true));
       prostor[4].vlozVec(new Vec("toaleta","Ve stanici nelze", false));
       prostor[5].vlozVec(new Vec("AviaB534", "Letadlo, ktere vas muze dopravit do Istanbulu", false));
       prostor[8].vlozVec(new Vec("kamen", "Obycejny kamen,ale je zajimavý...tím, že je z belehradu", true));
       prostor[8].vlozVec(new Vec("telefonni_budka", "Telefonni budka", false));
       prostor[6].vlozVec(new Vec("pas", "Tento pas budes potrebovat pro zbytek cesty", true));
       
       prostor[7].vlozVec(new Vec("noviny","Německo podpoří Italy v útoku na Řecko, britové posílají letce",true));
       prostor[8].vlozVec(new Vec("racek", "racek zelenonohý (Larus audouinii)",false));
       prostor[8].vlozVec(new Vec("lavicka", "Lavicka na nadraží, někdo na ní spí", false));
       prostor[9].vlozVec(new Vec("rukavice", "kožené rukavice s monogramem HP", true));
       prostor[10].vlozVec(new Vec("kebab", "mistni delikatesa...lezel na zemi....radeji nejsit", true));
       prostor[11].vlozVec(new Vec("formular","Zde se musíte formálně přihlásit do čs armády, vyplňte své pravé osobní udaje", false));
       prostor[12].vlozVec(new Vec("tableta", "Prášek proti mořské nemoci", true));
       prostor[12].vlozVec(new Vec("dalekohled", "Klasicky armadni dalekohled",true));
       
       prostor[2].vlozPostavu(postava[0]);
       prostor[0].vlozPostavu(postava[1]);
       prostor[3].vlozPostavu(postava[2]);
       prostor[4].vlozPostavu(postava[3]);
       prostor[4].vlozPostavu(postava[4]);
       prostor[5].vlozPostavu(postava[5]);
       prostor[6].vlozPostavu(postava[6]);
       prostor[7].vlozPostavu(postava[8]);
       
       prostor[9].vlozPostavu(postava[9]);
       prostor[10].vlozPostavu(postava[10]);
       prostor[11].vlozPostavu(postava[11]);
       prostor[12].vlozPostavu(postava[12]);
       prostor[13].vlozPostavu(postava[13]);
       prostor[14].vlozPostavu(postava[14]);
       prostor[14].vlozPostavu(postava[15]);
     
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     * pomocna metoda ktera nam pomuze uchovat hodnotu prostoru Istanbul
     * pro nasledny let letadlem pri pouziti prikazu pouzij
     * 
     * return prostor Istanbul
     */
    public Prostor getCil() {
        return let;
    }
    
    /**
     * pomocna metoda ktera nam pomuze uchovat hodnotu prostoru Haifa 
     * pro naslednou "teleporatci" pri pouziti prikazu pouzij
     * 
     * @return prostor haifa
     * 
     */
    public Prostor getCil2() {
        return kamenLET;
    }
    
    /**
     * Metoda ktera nam pouze ziskat Batoh pro herni plan 
     * 
     * @return objekt batoh
     */
    public Batoh getBatoh() {
        return this.batoh;
    }
    
    //Veskere boolean promene v teto metode jsou nastavene na hodnotu false
    
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    
    /** 
     * getter pro hodbnotu umisletat
     * Volanim teto metodu zjistim hodnotu boolenovske hodnoty parametru
     * pri pokusu o pouziti AvieB534
     * 
     * @return parametr s boolean hodnotou
     */
    public boolean getUmisLitat() {
        return litej;
    }
    
    /**
     * Setter pro hodnotu umisletat
     * Pokud pouzijete manualAvia ve tride prikazUse se nastavi
     * hodnota na true
     */
    void setUmisLitat(boolean litej) {
        this.litej = litej;
    }
    
    /** 
     * getter pro hodnotu vom
     * Volanim teto metodu zjistim hodnotu boolenovske hodnoty parametru
     * pri plaveni do Hafy se vam udela nevolno 
     * Po pruchdu do prostoru Haifa se zjistuje tato hodnota
     * 
     * @return parametr s boolean hodnotou
     */
    public boolean getVom() {
        return vom;
    }
    
    /** 
     * setter pro hodbnotu vom
     * k nastavovani tohoto setteru dochazi pri poziti tablety v prostrou do_Haify
     * pokud tapletu pouzijete nastavi se na true
     */
    void setVom(boolean vom) {
        this.vom = vom;
    }
    
    /**
     * 
     * Getter pro hodnotu parametru mon
     * Tato metoda nam pomuze zjistit zdali jste pouzil prukaz monteru 
     * 
     * @return parametr s boolean hodnotou
     */
    public boolean getMont() {
        return mon;
    }
    
    /**
     * 
     * Setter pro hodnotu parametru mon
     * pokud jste pouzili prukaz monteru dojde k nastaveni na true
     */
    void setMont(boolean mon) {
        this.mon = mon;
    }
    
    /**
     * Getter pro hodnotu parametru pas
     * Hodnota parametru rika zdali jste pouzil Pas
     * trua ano, false ne
     * 
     * @return parametr s boolean hodnotou
     */
    public boolean getPas() {
        return pas;
    }
    
    /**
     * Setter pro hodnotu parametru pas
     * pokud jste pouzili Pas dojde k nastaveni na true
     * trua ano, false ne
     */
    void setPas(boolean pas) {
        this.pas = pas;
    }
   
    /**
     * Getter pro hodnotu parametru letci
     * Hodnota parametru rika zdali jste pouzil falesny prukaz letcu
     * trua ano, false ne
     * 
     * @return parametr s boolean hodnotou
     */
    public boolean getLet() {
        return letci;
    }
    
    /**
     * Setter pro hodnotu parametru pas
     * pokud jste pouzili falesny prukaz letcu dojde k nastaveni na true
     */
    void setLet(boolean letci) {
        this.letci = letci;
    }
    
    /**
     * 
     * Setter pro informaci o vyhre ci nevyhre hry
     */
    void setHracVyhral(boolean vhr) {
        this.vhr = vhr;
    }
    
    /**
     * getter pro informai o vyhre ci nevyhre hry
     * vyhra true -nevyhra false
     * 
     * @return parametr s boolean hodnotou
     */
    public boolean hracVyhral() {
        return vhr;
    }
    
}
