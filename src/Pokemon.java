import java.util.ArrayList;


public class Pokemon {
    private String name;
    private String type;
    private int hp;
    private int fullhp;
    private int atk;
    private int spatk;
    private int def;
    private int spdef;
    private int spd;
    private ArrayList<Move> moves;
    private Move chosenMove;
    
    
    public Pokemon(String name) {
        this.moves = new ArrayList<Move>();
        if(name.toUpperCase().equals("BULBASAUR")) {
             bulbasaur();
        } else if (name.toUpperCase().equals("CHARMANDER")) {
            charmander();
        } else if (name.toUpperCase().equals("SQUIRTLE")) {
            squirtle();
        } 
    
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public int getAtk() {
        return this.atk;
    }
    
    public int getDef() {
        return this.def;
    }
    
    public void addHp(int amount) {
        this.hp+=amount;
    }
    
    public void decreaseHp(int amount){
        this.hp-=amount;
    }
    
    public String getName(){
        return this.name;
    }
    public int getSpeed() {
        return this.spd;
    }
    
    public void decreaseDef(int number) {
        this.def-=number;
    }
    
    public void decreaseAtk(int number) {
        this.atk-=number;
    }
    
    public ArrayList<Move> getMoveSet() {
        return this.moves;
    }
    
    
    public String toString() {
        return this.name + " HP: " + this.hp + "";
    }
    
    //pokemons
    
    private void bulbasaur(){
        this.name = "Bulbasaur";
        this.type = "grass";
        this.hp = 45;
        this.fullhp =45;
        this.atk = 49;
        this.spatk = 65;
        this.def= 49;
        this.spdef= 65;
        this.spd = 45;
        this.moves.add(new Move("Tackle","normal","physical",50,0,25));
        this.moves.add(new Move("Growl","normal","status",0,0,15,"atk",7));
    }
    
    

    private void charmander() {
        this.name = "Charmander";
        this.type = "fire";
        this.hp = 39;
        this.fullhp =39;
        this.atk = 52;
        this.spatk = 60;
        this.def= 43;
        this.spdef= 50;
        this.spd = 65;
        this.moves.add(new Move("Scratch","normal","physical",40,0,25));
        this.moves.add(new Move("Growl","normal","status",0,0,15,"atk",10));
                
    }
    
    private void squirtle() {
        this.name = "Squirtle";
        this.type = "water";
        this.hp = 44;
        this.fullhp =44;
        this.atk = 48;
        this.spatk = 50;
        this.def= 65;
        this.spdef= 65;
        this.spd = 43;
        this.moves.add(new Move("Tackle","normal","physical",50,0,25));
        this.moves.add(new Move("Tail Whip","normal","status",0,0,15,"def",10));
        
    }  
    public void setChosenAction(Move action) {
       this.chosenMove= action;
   }
    
    public boolean isFullHp(){
        if (this.hp == this.fullhp) {
            return true;
        }
        return false;
    }
    
    public void setHp(int amount){
        this.hp=amount;
        }
    
    public int getFullHp() {
        return this.fullhp;
    }
   
   public Move getChosenAction() {
       return this.chosenMove;
   }
}
