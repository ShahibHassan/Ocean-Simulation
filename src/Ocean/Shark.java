
package Ocean;

public class Shark extends Fish {
    public Shark(int x, int y){
        super(x,y);
        
    }
    @Override
    public void act(Field theField){
        Location loc = theField.freeAdjacentLocation(location);
        System.out.println(loc);
        if(!(loc==null)){
            theField.place(null, location);
            location = loc;
            theField.place(this, location);
        }
    }
}
