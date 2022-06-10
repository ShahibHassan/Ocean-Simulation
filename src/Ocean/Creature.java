package Ocean;

public abstract class Creature {
    protected Location location;
    public Creature(int x, int y){
        location = new Location(x, y);
    }
    public abstract void act(Field theField);
    public Location getLocation(){
        return location;
    }
}
