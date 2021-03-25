package toy.numbergame1.numberGenerator;

public class NumberGeneratorTest implements NumberGenerator{

    private int number;

    public NumberGeneratorTest(int number) {
        this.number = number;
    }

    @Override
    public int getRandomNumber() {
        return number++;
    }
}
