import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
/**
 * @author alvarovaldez-duran
 * @version Winter 2024
 */
public class Population {
    private final Random myRand;
    private List<Genome> myGenomes;
    private Genome myMostFit;
    public Population(Integer numGenomes, Double mutationRate) {
        myGenomes = new ArrayList<>();
        myRand = new Random();
        for (int i = 0; i < numGenomes; i++) {
            myGenomes.add(new Genome(mutationRate));
        }
        myMostFit = myGenomes.get(0);
    }
    public void day() {
        myGenomes.sort(Comparator.comparingInt(Genome::fitness));
        updateMostFit();
        removeLeastFit();
        breedNewGenomes();
    }
    private void updateMostFit() {
        myMostFit = myGenomes.get(0);
    }
    private void removeLeastFit(){
        final int size = myGenomes.size() / 2;
        myGenomes = myGenomes.subList(0, size);
    }
    private void breedNewGenomes() {
        final int inSize = myGenomes.size();
        for(int i = 0; i < inSize; i++) {
            final Genome inClone = new Genome(getRandomGenome());
            if( myRand.nextBoolean()) {
                inClone.mutate();
            } else {
                inClone.crossover(getRandomGenome());
                inClone.mutate();
            }
            myGenomes.add(inClone);
        }
    }
    private Genome getRandomGenome() {
        return myGenomes.get(myRand.nextInt(myGenomes.size()));
    }
    public String displayMostFit() {
        return "(" + myMostFit.toString() + ")";
    }
    public int getMostFit() {
        return myMostFit.fitness();
    }
}