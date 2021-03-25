package toy.numbergame1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import toy.numbergame1.numberGenerator.NumberGeneratorTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberGameV1Test {

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 5, 10})
    void modeSelectFail(int input) {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(1));
        numberGameV1.startGame(Integer.toString(input));

        String output = numberGameV1.getOutput();
        assertThat(output).contains("모드선택 명령어에 없는 숫자입니다.");
    }

    @Test
    void modeSelectSingleSuccess() {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(1));

        numberGameV1.startGame("1");
        String output = numberGameV1.getOutput();
        assertThat(output).contains("모드 선택 완료 : Single");
    }

    @Test
    void modeSelectMultiSuccess() {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(1));

        numberGameV1.startGame("2");

        String output = numberGameV1.getOutput();
        assertThat(output).contains("모드 선택 완료 : Multi");
    }

    @Test
    void modeSelectExit() {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(1));
        numberGameV1.startGame("3");

        String output = numberGameV1.getOutput();
        assertThat(output).contains("게임 종료");
    }

    @ParameterizedTest
    @CsvSource({"5, 4", "50, 10"})
    void singleModeInputTestLowInput(int answer, int input) {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(answer));

        numberGameV1.startGame("1");
        numberGameV1.startGame(Integer.toString(input));
        String output = numberGameV1.getOutput();
        assertThat(output).contains("정답은 입력보다 크다 tryCount");
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "50, 88"})
    void singleModeInputTestHighInput(int answer, int input) {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(1));

        numberGameV1.startGame("1");
        numberGameV1.startGame(Integer.toString(input));
        String output = numberGameV1.getOutput();
        assertThat(output).contains("정답은 입력보다 작다 tryCount");
    }

    @ParameterizedTest
    @CsvSource({"5, 5", "50, 50"})
    void singleModeInputTestSameInput(int answer, int input) {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(answer));

        numberGameV1.startGame("1");
        numberGameV1.startGame(Integer.toString(input));
        String output = numberGameV1.getOutput();
        assertThat(output).contains("일치 라운드 종료");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void singleModeInputTestFailTryCount(int inputs) {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(1));

        numberGameV1.startGame("1");
        for (int i = 0; i < inputs; i++) {
            numberGameV1.startGame(Integer.toString(10));
        }
        String output = numberGameV1.getOutput();
        assertThat(output).contains("tryCount : " + inputs);
    }

    @Test
    void selectMode_If_SingleModeFinished() {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(1));

        numberGameV1.startGame("1");
        numberGameV1.startGame("1");
        String output = numberGameV1.getOutput();
        assertThat(output).contains("일치 라운드 종료");
    }

    @Test
    void finishGame_If_SingleModeFinished() {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(1));

        numberGameV1.startGame("1");
        numberGameV1.startGame("1");
        numberGameV1.startGame("3");
        assertTrue(numberGameV1.isGameEnd());
    }


    @Test
    void multiModeSelect() {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(1));

        numberGameV1.startGame("2");
        String output = numberGameV1.getOutput();
        assertThat(output).contains("참여자 입력 (a,b,c) ,구분 :");
    }

    @ParameterizedTest
    @CsvSource({"a,b,c", "aa,bb,cc"})
    void multiModeInputTestUsers(String user1, String user2, String user3) {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(1));

        numberGameV1.startGame("2");
        numberGameV1.startGame(String.join(", ",user1, user2, user3 ));
        String output = numberGameV1.getOutput();
        assertThat(output).contains("사용자 입력 완료");
    }

    @ParameterizedTest
    @CsvSource({"a,b,c", "aa,bb,cc"})
    void multiModeInputTestUsersNameCheck(String user1, String user2, String user3) {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(1));

        numberGameV1.startGame("2");
        numberGameV1.startGame(String.join(",   ",user1, user2, user3 ));
        numberGameV1.getOutput();

        numberGameV1.startGame("2");
        String output = numberGameV1.getOutput();
        assertThat(output).contains("["+ user1 + "]" + " 정답은 입력보다 작다");

        numberGameV1.startGame("2");
        output = numberGameV1.getOutput();
        assertThat(output).contains("["+ user2 + "]" + " 정답은 입력보다 작다");

        numberGameV1.startGame("2");
        output = numberGameV1.getOutput();
        assertThat(output).contains("["+ user3 + "]" + " 정답은 입력보다 작다");
    }

    @ParameterizedTest
    @CsvSource({"a,b,c", "aa,bb,cc"})
    void multiModeInputTestUsersInputHigh(String user1, String user2, String user3) {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(50));

        numberGameV1.startGame("2");
        numberGameV1.startGame(String.join(",   ",user1, user2, user3 ));
        numberGameV1.getOutput();

        numberGameV1.startGame("85");
        String output = numberGameV1.getOutput();
        assertThat(output).contains("["+ user1 + "]" + " 정답은 입력보다 작다");

        numberGameV1.startGame("80");
        output = numberGameV1.getOutput();
        assertThat(output).contains("["+ user2 + "]" + " 정답은 입력보다 작다");

        numberGameV1.startGame("88");
        output = numberGameV1.getOutput();
        assertThat(output).contains("["+ user3 + "]" + " 정답은 입력보다 작다");
    }


    @ParameterizedTest
    @CsvSource({"a,b,c", "aa,bb,cc"})
    void multiModeInputTestUsersInputLow(String user1, String user2, String user3) {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(99));

        numberGameV1.startGame("2");
        numberGameV1.startGame(String.join(",   ",user1, user2, user3 ));
        numberGameV1.getOutput();

        numberGameV1.startGame("85");
        String output = numberGameV1.getOutput();
        assertThat(output).contains("["+ user1 + "]" + " 정답은 입력보다 크다");

        numberGameV1.startGame("80");
        output = numberGameV1.getOutput();
        assertThat(output).contains("["+ user2 + "]" + " 정답은 입력보다 크다");

        numberGameV1.startGame("88");
        output = numberGameV1.getOutput();
        assertThat(output).contains("["+ user3 + "]" + " 정답은 입력보다 크다");
    }

    @ParameterizedTest
    @CsvSource({"a,b,c", "aa,bb,cc"})
    void multiModeInputTestUsersInputCorrect(String user1, String user2, String user3) {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(99));

        numberGameV1.startGame("2");
        numberGameV1.startGame(String.join(",   ",user1, user2, user3 ));
        numberGameV1.getOutput();

        numberGameV1.startGame("99");
        String output = numberGameV1.getOutput();
        assertThat(output).contains(user1 + " 정답을 맞췄습니다\n승자는 "+user1+" 입니다.");
    }

    @Test
    void selectMode_If_MultiModeFinished() {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(99));

        numberGameV1.startGame("2");
        numberGameV1.startGame("a, b, c");
        numberGameV1.getOutput();

        numberGameV1.startGame("99");
        String output = numberGameV1.getOutput();
        assertThat(output).endsWith("[select mode 1(single), 2(multi), 3(exit)] :");
    }

    @Test
    void finishGame_If_MultiModeFinished() {
        NumberGameV1 numberGameV1 = new NumberGameV1(
                new NumberGeneratorTest(99));

        numberGameV1.startGame("2");
        numberGameV1.startGame("a, b, c");
        numberGameV1.getOutput();

        numberGameV1.startGame("99");
        numberGameV1.startGame("3");
        assertTrue(numberGameV1.isGameEnd());

        String output = numberGameV1.getOutput();
        assertThat(output).contains("게임 종료");
    }

}