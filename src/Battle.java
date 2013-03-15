import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
        
public class Battle {
    private Trainer player;
    private Trainer opponent;
    private ArrayList<Pokemon> pokemons;
    private Scanner reader;
    private Random random;
    
    public Battle() {
        this.reader = new Scanner(System.in);
        this.pokemons = new ArrayList<Pokemon>();
        this.random =  new Random();
    }
    
    
    //main loop
    public void go() {
        intro();
        while (true) {
            if (!checkHp()) {
                break;
            }
            drawSituation();
            executeMoves();
        }
        printVerdict();
    }
    
    
    
    public void chooseCharacters(){
        String playerPokemonName;
        String opponentPokemonName;
        while (true) {
            System.out.print("Enter your pokemon: ");
            playerPokemonName = reader.nextLine();
            System.out.print("Enter opponent's pokemon: ");
            opponentPokemonName = reader.nextLine();
            // a bit hardcoded...
            if (!playerPokemonName.equals("bulbasaur") && !playerPokemonName.equals("charmander") && !playerPokemonName.equals("squirtle")) {
                System.out.println("Either name was invalid! Try lowercase?");
            } else if ((!opponentPokemonName.equals("bulbasaur") && !opponentPokemonName.equals("charmander") && !opponentPokemonName.equals("squirtle"))){
                System.out.println("Either name was invalid! Try lowercase?");
            } else {
                break;
            }
            
        }
        player = new Trainer("Ash", playerPokemonName);
        opponent = new Trainer("Gary", opponentPokemonName);
        
        pokemons.add(player.getPokemon());
        pokemons.add(opponent.getPokemon());
        wait(600);
        System.out.println("So it is - " + player.getName()+"'s " + player.getPokemonName() + " versus " + opponent.getName() + "'s " + opponent.getPokemonName() + "! ");
        wait(600);
    }
    
    public void intro() {
        System.out.println("Welcome to Pallet v0.01!\n");
        System.out.println("You will choose from the three classic starter pokemons"
                + "\nand battle against an AI construct called \"Gary\".\n"
                + "\nFor maximum comfort use terminal sizes larger than 80,25."
                + "\nCommands are listed whenever they are available. Use CTRL-C at any time"
                + "\n to exit the game."
                + "\n\n");
        
        System.out.println("Choose your combatants! (bulbasaur, charmander, squirtle) \n");
        chooseCharacters();
    }
    
    public boolean checkHp() {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getHp() <= 0) {
                return false;
            }
            if (pokemon.getHp() > pokemon.getFullHp())
                pokemon.setHp(pokemon.getFullHp());
            
        }
        
            return true;
        
    }
    
    
    public Move chooseAttack(Trainer trainer) {
        
        //player attackchooser
        if (trainer == player) {
            ArrayList<Move> availableMoves = player.getPokemon().getMoveSet();
            for (int i = 0; i < availableMoves.size(); i++) {
                System.out.println(i + ") " + availableMoves.get(i));

            }
            System.out.println("2) Use Potion (" + player.getPotionAmount() + " left)");

            System.out.print("\n Choose action (number): ");
            String command = reader.nextLine();
            Move chosenMove;
            while (true) {
                if (command.equals("0")) {
                    chosenMove = availableMoves.get(0);
                    System.out.println(player.getPokemonName() + " will use: " + availableMoves.get(0).getName());
                    break;
                } else if (command.equals("1")) {
                    chosenMove = availableMoves.get(1);
                    System.out.println(player.getPokemonName() + " will use: " + availableMoves.get(1).getName());
                    break;
                } else if (command.equals("2")) { // use potion as a move, check for it in method attack()
                    chosenMove = new Move("usepotion", "", "", 0, 0, 0);
                    break;
                } else {
                    System.out.println("Invalid move!");
                    System.out.print("\n Choose action (number): ");
                    command = reader.nextLine();
                }
            }
            return chosenMove;
        } 
        //AI control
        else if (trainer == opponent) { 
            Move chosenMove;
            int randomized;
            randomized = random.nextInt((2 - 0 + 1) + 0);
            ArrayList<Move> availableMoves = opponent.getPokemon().getMoveSet();
            for (int i = 0; i < availableMoves.size(); i++) {
                if (randomized == 2) {
                    if (opponent.getPokemon().isFullHp()) {
                        //System.out.println("Gary tried to use a potion, but his pokemon is already at Full HP!");
                        wait(200);
                        chosenMove = availableMoves.get(0);
                    }
                    
                    if (opponent.getPokemon().getHp() < 40 && opponent.getPotionAmount() != 0) {
                        chosenMove = new Move("usepotion", "", "", 0, 0, 0);
                    } else { chosenMove = availableMoves.get(0);
                    System.out.println("Gary's " + opponent.getPokemonName() + " will use: " + chosenMove.getName());
                    wait(600);
                    }
                    
                    return chosenMove;
                } else {
                    wait(200);
                    chosenMove = availableMoves.get(randomized);
                    System.out.println("Gary's " + opponent.getPokemonName() + " will use: " + chosenMove.getName());
                    wait(600);
                    return chosenMove;
                }
                
            }
            System.out.println("Critical error! Exiting... \"chooseAttack()\", AI controlled");
            return null;
        } else {
            System.out.println("Critical error! Exiting... \"chooseAttack()\"");
            return null;
        }
        
        }
    
    public MoveOrder resolveTurnOrder(Trainer player, Trainer opponents) {
        MoveOrder moveOrder = new MoveOrder(player, opponent);
        return moveOrder;
        
    }
    
    
    public int calculateDamageDone(Pokemon attacker, Move move, Pokemon target) {
    int damage;
    damage = (((2 * 5 / 5 + 2) * move.getPower() * attacker.getAtk() / target.getDef()) / 50 + 2) * 5 * (random.nextInt(100 - 10) + 10) / 100;
    // Damage = ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100
    // some unnecessary variables omitted
    return damage;
    }
    
    
    public void executeMoves() {
        MoveOrder attackOrder = resolveTurnOrder(player, opponent);
        
        
        //choose attacks
        player.setChosenAction(chooseAttack(player));
        opponent.setChosenAction(chooseAttack(opponent));
        
        
        // get order for always correct sequential attacking
        Trainer firstP = (attackOrder.getFirst());
        Move firstPM = (attackOrder.getFirst().getChosenAction());
        Trainer secondP = (attackOrder.getSecond());
        Move secondPM = (attackOrder.getSecond().getChosenAction());
        
        if (firstPM.getCat().toUpperCase().equals("PHYSICAL")) {
            attack(firstP.getPokemon(), firstPM, secondP.getPokemon());
        } else if(firstPM.getCat().toUpperCase().equals("STATUS")) {
            stat(firstPM, secondP.getPokemon());
        } else if (firstPM.getName().toUpperCase().equals("USEPOTION")) {
            heal(firstP);
        }
        
        if (secondPM.getCat().toUpperCase().equals("PHYSICAL")) {
            attack(secondP.getPokemon(), secondPM, firstP.getPokemon());
        } else if(secondPM.getCat().toUpperCase().equals("STATUS")) {
            stat(secondPM, firstP.getPokemon());
        } else if (secondPM.getName().toUpperCase().equals("USEPOTION")) {
            heal(secondP);
        }
        
        
    }
    
    public void heal(Trainer trainer) {
        System.out.println(trainer.getName()+ " is using a potion!");
        wait(600);
        if (trainer.getPotionAmount() == 0) {
            System.out.println("Tried to use a potion but didn't have any!");
            wait(600);
        } else {
            trainer.usePotion();
        }
    }
    
    
    public void attack(Pokemon attacker, Move move, Pokemon target) {
        if (move.getPP() <= 0) {
            System.out.println("Not enough PP!");
            wait(600);
        } else {
            int damageDone = calculateDamageDone(attacker, move, target);
            wait(1200);
            System.out.println(attacker.getName() + "'s " + move.getName() + " did " + damageDone + " damage!!!");
            if (damageDone > 20) {
                System.out.println("Critical hit!!!");
                wait(600);
            }
            wait(1200);
            target.decreaseHp(damageDone);
            move.decreasePP(1);
        }
    }
    
    public void stat(Move move, Pokemon target) {
        wait(200);
        if (move.getPP() <= 0) {
            System.out.println("Not enough PP!");
        } else { 
            if (move.getStat().equals("def")) {
                target.decreaseDef(move.getStatusDecrement());
                System.out.println(target.getName() + "'s DEF decreased by " + move.getStatusDecrement()+"!");
                wait(600);
            } else if (move.getStat().equals("atk")) {
                target.decreaseAtk(move.getStatusDecrement());
                System.out.println(target.getName() + "'s ATK decreased by " + move.getStatusDecrement()+"!");
                wait(600);
            }
        }
        move.decreasePP(1);
    }
    
    public void drawSituation() {
        wait(600);
        System.out.println("\n");
        wait(50);
        System.out.println("|---------------------------------------|");
        wait(50);
        System.out.println("|    " + opponent.getName()+"'s "+opponent.getPokemonName() + " HP:" + opponent.getPokemon().getHp() + " \t\t|");
        wait(50);
        System.out.println("|\t\t\t\t\t|");
        wait(50);
        System.out.println("|\t\t(-o-)\t\t        |");
        wait(50);
        System.out.println("|\t\t\t\t\t|");
        wait(50);
        System.out.println("|\t" + player.getName()+"'s "+player.getPokemonName() + " HP:" + player.getPokemon().getHp() + " \t\t|");
        wait(50);
        System.out.println("|---------------------------------------|");
        wait(50);
        System.out.println("\n");
        wait(50);
    }
    
    public void printVerdict() {
        if (player.getPokemon().getHp() > 0 && opponent.getPokemon().getHp() < 0) {
            System.out.println("Gary's pokemon fainted!");
            System.out.println("You win!");
        } else if (player.getPokemon().getHp() < 0 && opponent.getPokemon().getHp() > 0) {
            System.out.println("Your pokemon fainted!");
            System.out.println("You lost! I hope you saved your game...");
        }
    }
    
    public void wait(int milliseconds) {
        try {
        Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
        }
    }   
}