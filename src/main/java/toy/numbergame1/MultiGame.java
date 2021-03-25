package toy.numbergame1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MultiGame {

    private List<String> users = new ArrayList<>();
    private int index;

    private StringBuffer output;

    public MultiGame(StringBuffer output) {
        this.output = output;
    }

    public boolean isSettingUser() {
        return users.size() != 0;
    }

    public void setUser(String input) {
        String[] split = input.split(",");
        users = Arrays.stream(split).map(String::trim).collect(Collectors.toList());
    }

    private void clearUser() {
        index = 0;
        users.clear();
    }

    private void print(String msg) {
        output.append(msg).append("\n");
    }

    public boolean isGameEnd(int in, int answer) {
        String userName = users.get(index);
        index = (index + 1) % users.size();

        if (answer == in) {
            clearUser();
            print(userName +  " 정답을 맞췄습니다");
            print("승자는 " + userName + " 입니다.");
            return true;
        } else if (answer > in){
            print("[" + userName + "]" + " 정답은 입력보다 크다");
        } else if (answer < in) {
            print("[" + userName + "]" + " 정답은 입력보다 작다");
        }


        return false;
    }
}
