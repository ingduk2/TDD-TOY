package toy.numbergame1;

public class SingleGame {

    private int tryCount;
    private StringBuffer output;

    public SingleGame(StringBuffer output) {
        this.output = output;
    }

    private void print(String msg) {
        output.append(msg).append(" tryCount : ").append(tryCount).append("\n");
    }

    public boolean isGameEnd(int in, int answer) {
        tryCount++;
        if (answer == in) {
            print("일치 라운드 종료");
            tryCount = 0;
            return true;

        } else if (answer > in) {
            print("정답은 입력보다 크다");

        } else if (answer < in) {
            print("정답은 입력보다 작다");

        }

        return false;
    }
}
