package toy.numbergame1.numberGenerator;

import java.util.Random;

public class NumberGeneratorRandom implements NumberGenerator{

    private Random random;

    public NumberGeneratorRandom() {
        random = new Random();
    }

    @Override
    public int getRandomNumber() {
        return random.nextInt(100) + 1;
    }
}
