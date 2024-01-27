import java.util.Random;
/**
 * @author alvarovaldez-duran
 * @version Winter 2024
 */
public class Genome {
    final char[] myChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ','-','’'};
    private final String myTarget;
    private StringBuilder myCurrentString;
    private final double myMutationRate;
    public Genome(final double theMutationRate) {
        myTarget = "PAULO LICCIARDI BARRETO";
        myCurrentString = new StringBuilder("A");
        if(theMutationRate > 0 || theMutationRate < 1) {
            myMutationRate = theMutationRate * 100;
        } else throw new IllegalArgumentException("the mutation rate is not between 0 and 1");
    }
    public Genome(final Genome gene) {
        myTarget = gene.myTarget;
        myCurrentString = new StringBuilder(gene.myCurrentString);
        myMutationRate = gene.myMutationRate;
    }
    public void mutate() {
        final Random random = new Random();
        if(random.nextInt(100) + 1 <= myMutationRate) {
            myCurrentString.insert(random.nextInt(myCurrentString.length()), myChars[random.nextInt(29)]);
        }
        if (myCurrentString.length() >= 2 && random.nextInt(100) + 1 <= myMutationRate) {
            myCurrentString.deleteCharAt(random.nextInt(myCurrentString.length()));
        }
        for(int i = 0; i < myCurrentString.length(); i++) {
            if (random.nextInt(100) + 1 <= myMutationRate) {
                myCurrentString.deleteCharAt(i);
                myCurrentString.insert(i, myChars[random.nextInt(29)]);
            }
        }
    }
    public void crossover(final Genome other) {
        final Random rand = new Random();
        StringBuilder newString = new StringBuilder();
        StringBuilder inString;
        final int inMax = Math.max(myCurrentString.length(), other.myCurrentString.length());
        for (int i = 0; i < inMax; i++) {
            inString = rand.nextBoolean() ? myCurrentString : other.myCurrentString;
            if (i < inString.length()) {
                newString.append(inString.charAt(i));
            } else {
                break;
            }
        }
        myCurrentString = newString;
    }
    public Integer fitness() {
        int n = myCurrentString.length();
        int m = myTarget.length();
        int inMax = Math.max(n, m);
        int f = Math.abs(myTarget.length() - myCurrentString.length());
        for (int i = 0; i < inMax; i++) {
            if (i >= n || i >= m || myCurrentString.charAt(i) != myTarget.charAt(i)) {
                f++;
            }
        }
        return f;
    }
    /*
  Integer fitness() {
      int n = myCurrentString.length();
      int m = myTarget.length();

      int[][] inED = new int[n + 1][m + 1];
      for(int i  = 1; i <= n;i++) {
           inED[i][0] = i;
      }
      for(int i  = 1; i <= m;i++) {
          inED[0][i] = i;
      }
      // Initialization
      for (int i = 1; i <= n; i++) {
          for (int j = 1; j <= m; j++) {
               if (myCurrentString.charAt(i - 1) == myTarget.charAt(j - 1)) {
                  inED[i][j] = inED[i - 1][j - 1];
              } else {
                  inED[i][j] = Math.min(inED[i - 1][j - 1], Math.min(inED[i][j - 1], inED[i - 1][j])) + 1;
              }
          }
      }
      return inED[n][m] + (Math.abs(m - n) + 1) / 2;
  }

     */
    public String toString() {
        return "\"" + myCurrentString.toString() + "\", " + fitness();
    }
}