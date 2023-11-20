package Backend;

import java.util.ArrayList;

public class Player extends Character implements ActionsPlayer{

    private String name;
    private ArrayList<Weapon> weapons;

   
    public Player(String name) {
        this.name = name;
        this.money = 50;
        this.weapons = new ArrayList<Weapon>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Weapon> getWeapons() {
        return this.weapons;
    }
    

    
    @Override
    public int getStrength() {
        // Implémentation pour obtenir la force du joueur
        return strength;
    }

    @Override
    public int getHp() {
        // Implémentation pour obtenir les points de vie du joueur
        return hp;
    }
    
    @Override
    public void buyWeapon(Weapon weapon) {
        if (weapon.getPrice() <= this.money) {
            this.weapons.add(weapon);
            this.money -= weapon.getPrice();
            System.out.println("Weapon purchased: " + weapon.getName());
        } else {
            System.out.println("Not enough money to buy the weapon: " + weapon.getName());
        }
    }
    
}
