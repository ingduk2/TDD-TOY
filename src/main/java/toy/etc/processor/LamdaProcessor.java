package toy.etc.processor;

public class LamdaProcessor {

    private Processor processor;

    interface Processor {
        Processor go(String input);
    }

    public LamdaProcessor() {
        processor = this::one;
    }

    private void start(String input) {
        System.out.println("App.start " + input);
        processor = processor.go(input);
    }

    private Processor one(String input) {
        System.out.println("App.one " + input);
        return two(input);
    }

    private Processor two(String in) {
        System.out.println("App.two " + in);
        return input -> {
            System.out.println("Three " + input);
            return two(input);
        };
    }

    public static void main(String[] args) {
        LamdaProcessor processor = new LamdaProcessor();
        processor.start("11");
        System.out.println();
        processor.start("ww");
        System.out.println();
        processor.start("asdf");
    }
}
