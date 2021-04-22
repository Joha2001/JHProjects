public class Pakuri {
    private String species;//Name of the Pakuri species.
    private int attack, defense, speed;//Integer values that hold the attack, defense, and speed of the Pakuri.

    public Pakuri(String species){//Constructor that creates a species and creates values based on the name.
        this.species = species;
        attack = (species.length() * 7) + 9;
        defense = (species.length() * 5) + 17;
        speed = (species.length() * 6) + 13;
    }

    public String getSpecies(){//Returns the species name
        return this.species;//Could just use species, but more comfortable with this.species.
    }

    public int getAttack(){//Returns the attack value
        return this.attack;
    }

    public int getDefense() {//Returns the defense value
        return this.defense;
    }

    public int getSpeed() {//Returns the speed value
        return this.speed;
    }

    public void setAttack(int newAttack){//Sets the attack to a new value
        this.attack = newAttack;
    }

    public void evolve(){//Changes all 3 stats by a specific value to 'evolve' the Pakuri.
        this.attack = this.attack*2;
        this.defense = this.defense*4;
        this.speed = this.speed*3;
    }
}
