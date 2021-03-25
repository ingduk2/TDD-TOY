package toy.numbergame1;


import toy.numbergame1.numberGenerator.NumberGenerator;

/**
 * - 1 ~ 100 까지 임의의 정수를 맞추는 게임
 * - 플레이어가 숫자를 입력하면
 * - 입력한 숫자가 정답보다 작으면 작다고 출력
 * - 입력한 숫자가 정답보다 크면 크다고 출력
 * - 입력한 숫자와 정답이 일치하면 라운드 종료
 * - 단일 모드와 다중 모드 지원
 * - 단일 모드가 종료되면 총 시도를 출력
 * - 다중 모드가 종료되면 승자를 출력 (사용자는 , 로 입력)
 */
public class NumberGameV1 {

    private int mode;
    private boolean isEnd;
    private int answer;
    private StringBuffer output;

    private final String SELECT_MODE = "[select mode 1(single), 2(multi), 3(exit)] :";
    private final String NUMBER_INPUT = "[숫자 입력] : ";

    private SingleGame singleGame;
    private MultiGame multiGame;
    private NumberGenerator numberGenerator;

    public NumberGameV1(NumberGenerator numberGenerator) {
        this.output = new StringBuffer();

        this.singleGame = new SingleGame(output);
        this.multiGame = new MultiGame(output);

        this.numberGenerator = numberGenerator;

        print(SELECT_MODE);
    }

    public boolean isGameEnd() {
        return isEnd;
    }

    public String getOutput() {
        String out = output.toString();
        output.setLength(0);
        System.out.println(out);
        return out;
    }

    public void startGame(String input) {
        if (mode == 0) {
            answer = numberGenerator.getRandomNumber();
            selectMode(input);
        } else {
            if (mode == 1) {
                singleGame(input);
            } else if (mode == 2) {
                multiGame(input);
            }
        }
    }

    private void selectMode(String input) {
        int in = Integer.parseInt(input);

        if (in == 1) {
            mode = in;
            println("모드 선택 완료 : Single");
            print(NUMBER_INPUT);

        } else if (in == 2) {
            mode = in;
            println("모드 선택 완료 : Multi");
            print("참여자 입력 (a,b,c) ,구분 :");

        } else if (in == 3) {
            isEnd = true;
            println("게임 종료");

        } else {
            println("모드선택 명령어에 없는 숫자입니다.");
            print(NUMBER_INPUT);

        }
    }

    private void multiGame(String input) {
        if (!multiGame.isSettingUser()) {
            multiGame.setUser(input);
            println("사용자 입력 완료");
            print(NUMBER_INPUT);
            return;
        }

        int in = Integer.parseInt(input);
        if (multiGame.isGameEnd(in, answer)) {
            mode = 0;
        }

        printNextGame();
    }

    private void singleGame(String input) {
        int in = Integer.parseInt(input);
        if (singleGame.isGameEnd(in, answer)) {
            mode = 0;
        }

        printNextGame();
    }


    private void print(String msg) {
        output.append(msg);
    }

    private void println(String msg) {
        output.append(msg).append("\n");
    }

    private void printNextGame() {
        if (mode != 0) print(NUMBER_INPUT);
        else print(SELECT_MODE);
    }


}
