public class Move {
    private String name;
    private String type;
    private String category;
    private String stat; // if the move has a status decrementing effect
    private int power;
    private int speed;
    private int pp;
    private int statusDecrement;
    
    
    // paste: name, type, baseDMG, speed, pp
    public Move(String name, String type, String cat, int power, int speed, int pp) {
        this.name = name;
        this.type = type;
        this.category = cat;
        this.power=power;
        this.speed = speed;
        this.pp = pp;
    }
    public Move(String name, String type, String cat, int power, int speed, int pp, String stat, int decrement) {
        this.name = name;
        this.type = type;
        this.category = cat;
        this.power=power;
        this.speed = speed;
        this.pp = pp;
        this.statusDecrement = decrement;
        this.stat = stat;
    }
    
    
    public int getPower(){
        return this.power;
    }
    
    public int getPP(){
        return this.pp;
    }
    
    public void decreasePP(int number){
        this.pp-=number;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String getName(){
        return this.name;
    }
    
    
    public void setStatusDecrement(int number) {
        this.statusDecrement=number;
    }
    public int getStatusDecrement() {
        return this.statusDecrement;
    }
    
    public String getStat() {
        return this.stat;
    }
    
    public String getCat() {
        return this.category;
    }
        
    
    public String toString() {
        return ""+this.name+" Type: "+this.type+" Category: "+this.category+" PP: "+this.pp;
    }
}
