
package Ocean;

public class Sardine extends Fish {

    public Sardine(int x, int y){
        super(x, y);
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
