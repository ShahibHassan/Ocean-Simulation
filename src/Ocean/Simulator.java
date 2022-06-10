
package Ocean;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Simulator {
    private static Field field;
    private static SimulatorView simView;
    private static ArrayList<Creature> creatures = new ArrayList<Creature>();
    private static int currentStep;
    
    
    public Simulator(int x, int y){
        RandomGenerator.initialiseWithSeed(44);
        if(x>0 && y>0){
            field = new Field(x, y);
            simView = new SimulatorView(x, y);
        }
        else{
            x = ModelConstants.DEFAULT_DEPTH;
            y = ModelConstants.DEFAULT_WIDTH;
            field = new Field(x, y);
            simView = new SimulatorView(x, y);
        }
        simView.getState();
        simView.setColor(Plankton.class, Color.black);
        simView.setColor(Shark.class, Color.blue);
        simView.setColor(Sardine.class, Color.red);
        
    }
    public static void main(String[] args){
        start();
    }
    
    public static void populate(){
        Random rand = RandomGenerator.getRandom();
        double sharkPop = ModelConstants.SHARK_CREATION;
        double sardinePop = ModelConstants.SARDINE_CREATION;
        double planktonPop = ModelConstants.PLANKTON_CREATION;
        
        for(int i=0; i < field.getWidth(); i++){
            for(int j=0; j < field.getDepth(); j++){
                double randomNum = rand.nextDouble();
                if(randomNum <=sharkPop){
                    Shark sh = new Shark(i, j);
                    field.place(sh, i, j);
                    creatures.add(sh);
                }
                else if (randomNum > sharkPop && randomNum <= (sharkPop+sardinePop)){
                    Sardine s = new Sardine(i, j);
                    field.place(s, i, j);
                    creatures.add(s);
                }
                else if(randomNum>(sharkPop+sardinePop) && randomNum <=(sharkPop+sardinePop+planktonPop)) {
                    Plankton p = new Plankton(i, j);
                    field.place(p, i, j);
                    creatures.add(p);
                }

            }
        }
    }
    
    public static void start(){
        Simulator sim = new Simulator(100, 100);
        populate();
        simView.showStatus(1, field);
        simulate(1000);
    }
    
    public static void simulate(int steps){
        for(int i=0; i<steps; i++){
            simulateOneStep();
        }
        
    }
    
    public static void simulateOneStep(){
        Collections.shuffle(creatures, RandomGenerator.getRandom());
        for(Creature c:creatures){
            if(c instanceof Shark){
                Shark sh = (Shark)c;
                sh.act(field);
                
            }
            else if(c instanceof Sardine){
                Sardine s = (Sardine)c;
                s.act(field);
            }
            else if(c instanceof Plankton){
                Plankton p = (Plankton)c;
                p.act(field);
            }
        }
       simView.getState();
       currentStep++;
       simView.showStatus(currentStep, field);
    }

}
