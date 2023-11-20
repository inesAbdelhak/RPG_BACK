package Backend;

import java.util.Scanner;

public class MainGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a name :");
        String name = scanner.nextLine();
        Player p = new Player(name);
        System.out.println("Player name : " + p.getName());

        WeaponStore store = new WeaponStore();
        store.printWeaponsList();

        System.out.println("Choose a weapon (enter the weapon number): ");
        int weaponChoice = scanner.nextInt();
        p.buyWeapon(store.getWeapon(weaponChoice));

        System.out.println("Available characters: ");
        printCharacterOptions();

        System.out.println("Choose a character (enter the character number): ");
        int characterChoice = scanner.nextInt();
        createAndPrintCharacter(characterChoice, p, store, scanner);

        // Creation et initialisation de la map
        GameMap gameMap = new GameMap(5, 5);

        // Boucle du jeu
        while (gameMap.getPlayerX() != gameMap.getPlayerY() && gameMap.getPlayerX() != gameMap.getMap().length - 1) {
            // affiche la carte et la position du joueur
            displayMap(gameMap);

            // demande la direction 
            System.out.println("Choose a direction (UP, DOWN, LEFT, RIGHT): ");
            String directionInput = scanner.next().toUpperCase();
            Direction direction = Direction.valueOf(directionInput);

            // Deplace le joueur
            gameMap.movePlayer(direction);

            // Verifie les rencontres 
            Destructible currentObject = gameMap.getObjectAtPosition(gameMap.getPlayerX(), gameMap.getPlayerY());
            if (currentObject instanceof Monster || currentObject instanceof Obstacle) {
                System.out.println("You encountered something!");
                
            }
        }

        System.out.println("Congratulations! You reached the destination!");
        scanner.close();
    }

    private static void printCharacterOptions() {
        System.out.println("[1] Warrior - HP: 100, Strength: 20");
        System.out.println("[2] Mage - HP: 80, Strength: 15");
        System.out.println("[3] Archer - HP: 90, Strength: 18");
    }

    private static void createAndPrintCharacter(int characterChoice, Player p, WeaponStore store, Scanner scanner) {
        switch (characterChoice) {
            case 1:
                Warrior warrior = new Warrior();
                System.out.println("Warrior selected: " + warrior.toString());
                break;
            case 2:
                Mage mage = new Mage();
                System.out.println("Mage selected: " + mage.toString());
                break;
            case 3:
                archer archer = new archer();
                System.out.println("Archer selected: " + archer.toString());
                break;
            default:
                System.out.println("Invalid character choice.");
        }

        System.out.println("Do you want to change your inventory? (YES/NO): ");
        String changeInventory = scanner.next().toUpperCase();

        if (changeInventory.equals("YES")) {
            // Changer l'inventaire 
            do {
                store.printWeaponsList();

                System.out.println("Choose a weapon (enter the weapon number): ");
                int weaponChoice = scanner.nextInt();
                p.buyWeapon(store.getWeapon(weaponChoice));

                System.out.println("Do you want to change your inventory again? (YES/NO): ");
                changeInventory = scanner.next().toUpperCase();
            } while (changeInventory.equals("YES"));
        }

        
        System.out.println("Player's final inventory: ");
        for (Weapon w : p.getWeapons()) {
            System.out.println(w.toString());
        }
    }

    private static void displayMap(GameMap gameMap) {
        Destructible[][] map = gameMap.getMap();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == gameMap.getPlayerY() && j == gameMap.getPlayerX()) {
                    System.out.print(" P ");
                } else {
                    System.out.print(" X ");
                }
            }
            System.out.println(); 
        }
    }
}
