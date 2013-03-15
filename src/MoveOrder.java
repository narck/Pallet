public class MoveOrder {
    private Trainer first;
    private Trainer second;
    
    public MoveOrder(Trainer player, Trainer opponent){
        if (player.getPokemon().getSpeed() > opponent.getPokemon().getSpeed()) {
            this.first = player;
            this.second = opponent;
        } else {
            this.first = opponent;
            this.second = player;
        }
    }
    
    public Trainer getFirst() {
        return this.first;
    }
    
    public Trainer getSecond() {
        return this.second;
    }
            
}
