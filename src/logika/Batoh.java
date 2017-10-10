/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;

/*******************************************************************************
 * Trida Batoh - popisuje batoh, tedy misto do ktereho si muze hrac behem hry uladat veci
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Batoh" reprezentuje batoh ve scénáři hry.
 * Batoh ma fixni velikost, muzete vem pridat a odebirat veci
 *
 * @author    Stepan Chmel chms00 30/12/2016
 * @version   final
 */
public class Batoh
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    private static final int MAXobs = 7;
    private List<Vec> obsah;
    
    /***************************************************************************
     *  Konstruktor ....
     *  
     *  @param obsah je list veci v batohu
     */
    public Batoh()
    {
        obsah = new ArrayList<Vec>();
    }
    
    /**
     * 
     * Pokud je v batohu misto dojde k pridani veci jinak 
     * je vypsana chybova hlaska 
     * 
     * @return objekt vec pridana nebo null
     */
    public Vec pridejVec(Vec vec){
        if(isSpace()){
        obsah.add(vec);
        return vec;
        
        }
        
        System.out.println("vec nebyla vlozena do batohu, je plny");
        return null;
    }
    
   /**
    * Kontrola plnosti batohu, je-vraci true, neni vraci false
    * 
    * @return true nebo false
    */
    public boolean isSpace(){
        if(obsah.size() < MAXobs){
            return true;    
        }
        return false;
    }
    
   /**
    * 
    * Informace zda-li je vec v batohu, je-vraci true, neni vraci false
    * 
    * @return true po nalezeni veci v batohu
    * @return false pro nenalezeni
    */
    public boolean isIn(String hled){
        for(Vec pridavana: obsah){
            if(pridavana.getNazev().equals(hled)){
                return true;
            }
        } 
        return false;
    }
    
    /**
     * Tato metoda vypise veskery obsah batohu
     * Vraci tedy string
     * 
     */
    public String getObsah(){
     String seznam = "";
     for (Vec vyp: obsah) {
            if (!seznam.equals("")) {
                //pro větší přehlednost dáme čárku
                
                seznam += ",";
            }
            seznam += " " + vyp.getNazev();
        }
        return seznam;   
    }
   
    /**
     * 
     * Teto metode zadata jakou vec chcete z batohu
     * pokud ji najde vypise ji, pokud ne vrati null
     */
    public Vec getVec(String chtena) {
        Vec hledana = null;
        for (Vec porov: obsah) {
            if(porov.getNazev().equals(chtena)) {
                hledana = porov;
                break;
            }
        }
        return hledana;
    }
    
    /**
     * Metoda pro mazani veci z batohu
     * Na vstup zadate co chcete smazat,
     * Pokud je vec v batohu dojde ke smazani a je vraceo jmeno/nazev
     * smazane veci. Pokud vec neni v batohu vraci null
     */
     public Vec remVec(String rem) {
        Vec remed = null;
        for(Vec vec: obsah) {
            if(vec.getNazev().equals(rem)) {
                remed = vec;
                obsah.remove(vec);
                break;
            }
        }
        return remed;
    }
    
    /**
     * vraci cislo o maximalni kapacite batohu
     * 
     * @return intager o maximalni kapacite batohu
     */
    public int getMaxCap() {
        return MAXobs;
    }
    
    //== Nesoukromé metody (instancí i třídy) ======================================


    //== Soukromé metody (instancí i třídy) ========================================

}
