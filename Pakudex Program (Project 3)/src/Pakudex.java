import static java.util.Arrays.sort;

public class Pakudex {
    Pakuri[] pakudex; //Array of pakuri that makes up the pakudex.

    public Pakudex(){ //Default Pakudex Constructor
        pakudex = new Pakuri[20];
    }

    public Pakudex(int capacity){ //Constructor that utilizes a given capacity to create the pakudex.
        pakudex = new Pakuri[capacity];
    }

    public int getSize(){ //Returns the amount of Pakuri currently in the Pakudex.
        int count = 0;
        for (int i = 0; i < this.pakudex.length; i++)
        {
            if (pakudex[i] != null) //Increases count if there is a Pakuri.
            {
                count++;
            }
        }
        return count;
    }

    public int getCapacity(){ //Returns the entire possible capacity of the pakudex.
        return this.pakudex.length;
    }

    public String[] getSpeciesArray(){ //Returns the name of all Pakuri in the Pakudex.
        String[] listOfSpecies;
        if (getSize() == 0)
            return null;
        else{
            listOfSpecies = new String[getSize()];
            for (int i = 0; i < getSize(); i++){
                listOfSpecies[i] = pakudex[i].getSpecies();
            }
        }
        return listOfSpecies;
    }

    public int[] getStats(String species){ //Returns the stats of the given species
        int[] stats = new int[3];
        for (int i = 0; i < getSize(); i++) {
            if (species.equals(pakudex[i].getSpecies())) {
                stats[0] = pakudex[i].getAttack();
                stats[1] = pakudex[i].getDefense();
                stats[2] = pakudex[i].getSpeed();
                return stats;
            }
        }
        System.out.println("Error: No such Pakuri!"); //Error message if Pakuri does not exist.
        return null;
    }

    public void sortPakuri(){ //Sorts Pakuri in lexicographical (alphabetical) order.
        if (getSize() != 0) {//Makes sure the is no NullPointerException error.
            String[] listOfSpecies = getSpeciesArray(); //Uses the getSpeciesArray to create a string of all Pakuris.
            sort(listOfSpecies); //Sorts using sort function (which works with Strings)
            for (int i = 0; i < getSize(); i++) {
                pakudex[i] = new Pakuri(listOfSpecies[i]); //Recreates every Pakuri in the Pakudex in lexicographical order.
            }
        }
    }

    public boolean addPakuri(String species){//Adds Pakuri if it is not already inside. (Test for if full is outside of the function)
        for (int i = 0; i < getSize();i++){
            if(species.equals(pakudex[i].getSpecies())) {
                System.out.println("Error: Pakudex already contains this species!");
                return false;
            }
        }
        pakudex[getSize()] = new Pakuri(species);
        return true;
    }

    public boolean evolveSpecies(String species){//Evolves the species
        for (int i = 0; i < getSize(); i++) {
            if (species.equals(pakudex[i].getSpecies())) {
                pakudex[i].evolve();
                return true;
            }
        }
        System.out.println("Error: No such Pakuri!");//Error message if Pakuri does not exist.
        return false;
    }

}

