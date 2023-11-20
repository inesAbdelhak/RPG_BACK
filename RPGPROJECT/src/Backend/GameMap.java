
package Backend;
import java.util.Random;
import java.util.Scanner;

public class GameMap {
    private Destructible[][] map;
    private int playerX;
    private int playerY;

    public GameMap(int sizeX, int sizeY) {
        map = new Destructible[sizeX][sizeY];
        initializeMap();
        playerX = 0; // Le joueur commence en bas à gauche
        playerY = sizeY - 1;
    }

    private void initializeMap() {
        Random random = new Random();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                // Remplis la carte avec des monstres et des obstacles de manière aléatoire
                if (random.nextBoolean()) {
                    map[i][j] = new Monster();
                } else {
                    map[i][j] = new Obstacle();
                }
            }
        }
    }

    public Destructible getObjectAtPosition(int x, int y) {
        return map[x][y];
    }

    public void movePlayer(Direction direction) {
        switch (direction) {
            case UP:
                if (playerY > 0) {
                    playerY--;
                }
                break;
            case DOWN:
                if (playerY < map[0].length - 1) {
                    playerY++;
                }
                break;
            case LEFT:
                if (playerX > 0) {
                    playerX--;
                }
                break;
            case RIGHT:
                if (playerX < map.length - 1) {
                    playerX++;
                }
                break;
        }
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public Destructible[][] getMap() {
        return map;
    }

    // Gérer les rencontres avec les monstres et les obstacles
    public void handleEncounter(Player player, Scanner scanner) {
        Destructible currentObject = getObjectAtPosition(playerX, playerY);

        if (currentObject instanceof Monster || currentObject instanceof Obstacle) {
            System.out.println("You encountered something!");

            // Déclenche automatiquement le combat ou la fuite
            if (currentObject instanceof Monster) {
                Monster monster = (Monster) currentObject;
                if (combat(player, monster)) {
                    // Le joueur gagne le combat
                    System.out.println("You defeated the monster and gained XP!");
                    
                } else {
                    // Le joueur perd le combat
                    System.out.println("You were defeated by the monster. Game over!");
                   
                }
            } else {
                // Combat avec l'obstacle
                Obstacle obstacle = (Obstacle) currentObject;
                if (combat(player, obstacle)) {
                    // Le joueur a surmonté l'obstacle
                    System.out.println("You overcame the obstacle!");
                } else {
                    // Le joueur a perdu contre l'obstacle
                    System.out.println("You were unable to overcome the obstacle. Game over!");
                    
                }
            }
        }
    }

    // Combat contre les monstres
    private boolean combat(Player player, Monster monster) {
        // Force du joueur
        int playerStrength = player.getStrength();

        // Force aléatoire du monstre entre 1 et 20
        Random random = new Random();
        int monsterStrength = random.nextInt(20) + 1; // Génère une force entre 1 et 20

        System.out.println("Player strength: " + playerStrength);
        System.out.println("Monster strength: " + monsterStrength);

        // Comparaison des forces
        if (monsterStrength > playerStrength) {
            System.out.println("The monster is too strong! You were defeated.");
            return false;
        } else if (monsterStrength < playerStrength) {
            System.out.println("You defeated the monster!");

            // Déplace le joueur vers la prochaine case
            movePlayer(Direction.RIGHT);
            System.out.println("Moved to the next position: (" + getPlayerX() + ", " + getPlayerY() + ")");

            return true;
        } else {
            // En cas d'égalité de force, la victoire dépend des points de vie (HP)
            if (player.getHp() > monster.getHp()) {
                System.out.println("You defeated the monster!");

                // Déplace le joueur vers la prochaine case
                movePlayer(Direction.RIGHT);
                System.out.println("Moved to the next position: (" + getPlayerX() + ", " + getPlayerY() + ")");

                return true;
            } else {
                System.out.println("The battle ended in a draw. You were not able to defeat the monster.");
                return false;
            }
        }
    }

    // Combat contre les obstacles
    private boolean combat(Player player, Obstacle obstacle) {
      
    	int playerHp = player.getHp();
    	int obstacleHp = (int) obstacle.getLife();


        System.out.println("Player HP: " + playerHp);
        System.out.println("Obstacle HP: " + obstacleHp);

        if (playerHp > obstacleHp) {
            System.out.println("You successfully overcame the obstacle!");

            // Déplace le joueur vers la prochaine case
            movePlayer(Direction.RIGHT);
            System.out.println("Moved to the next position: (" + getPlayerX() + ", " + getPlayerY() + ")");
            
            return true;
        } else {
            System.out.println("The obstacle is too strong! You were defeated.");
            return false;
        }
    }
}
