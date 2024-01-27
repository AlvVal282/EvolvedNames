import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author alvarovaldez-duran
 * @version Winter 2024
 */
public class Main {
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        Population population = new Population(100, 0.05);
        int counter = 0;
        while (population.getMostFit() > 0) {
            population.day();
            System.out.print("Gen " + counter + ": ");
            System.out.println(population.displayMostFit());
            counter++;
        }
        final long endTime = System.currentTimeMillis();
        final long executionTime = endTime - startTime;
        System.out.println("Generations: " + counter);
        System.out.println("Running time: " + executionTime + " milliseconds.");
    }
    @Test
    public void testGenome() {
        Genome myTest = new Genome(.05);
        assertEquals(45, myTest.fitness());

        assertEquals("\"A\", 45", myTest.toString());

        myTest.crossover(new Genome(.05));
        assertEquals("\"A\", 45", myTest.toString());

        myTest.mutate();
        assertTrue(myTest.toString().length() <= 8);
        assertTrue(myTest.fitness() <= 45);

        myTest.mutate();
        assertTrue(myTest.toString().length() <= 9);
        assertTrue(myTest.fitness() <= 45);

        myTest.crossover(new Genome(.05));
        assertTrue(myTest.toString().length() <= 9);
    }
    @Test
    public void testPopulation() {
        Population myTest = new Population(100, .05);
        assertEquals("(\"A\", 45)", myTest.displayMostFit());

        assertEquals(45, myTest.getMostFit());
        assertEquals("(\"A\", 45)",myTest.displayMostFit());

        myTest.day();
        assertTrue(myTest.getMostFit() <= 45);
    }
}