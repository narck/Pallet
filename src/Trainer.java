public class Trainer {
    private Pokemon pokemon;
    private Move move;
    private String name;
    private int potions;
    
   public Trainer(String name, String pokemon) {
       this.name = name;
       this.pokemon = new Pokemon(pokemon);
       this.potions = 2;
}
   
   public Pokemon getPokemon() {
       return this.pokemon;
   }
   
   public int getPotionAmount() {
       return this.potions;
   }
   
   public void usePotion() {
       this.pokemon.addHp(20);
       this.potions--;
   }
   
   public String getName(){
       return this.name;
   }
   
   public String getPokemonName() {
       return pokemon.getName();
   }
   
   public String toString() {
       return this.name + "    " + this.pokemon.getName();
   }
   
   public void setChosenAction(Move action) {
       this.move= action;
   }
   
   public Move getChosenAction() {
       return this.move;
   }
   
   
}