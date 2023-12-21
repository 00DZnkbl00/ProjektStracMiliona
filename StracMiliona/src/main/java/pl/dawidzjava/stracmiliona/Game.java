package pl.dawidzjava.stracmiliona;

import java.util.List;
import java.util.Random;

public class Game {
    private int moneyToDivide = 1_000_000;
    private int firstDoor = 0;
    private int secondDoor = 0;
    private int thirdDoor = 0;
    private int fourthDoor = 0;
    private boolean debugMode;

    private int packageSize = 100_000;
    private List<Question> questionsPool;
    private int leftQuestions;

    private Question activeQuestion;


    private String playerName;

    public Game(boolean debugMode, List<Question> questionsPool, int questionsNumber, String playerName) {
        this.debugMode = debugMode;
        this.questionsPool = questionsPool;
        this.leftQuestions = questionsNumber;
        this.playerName = playerName;
    }

    public int changeDoorValue(int doorNumber, boolean isPlusButton) {
        int valueAdded = isPlusButton ? packageSize : packageSize * -1;

        if (moneyToDivide - valueAdded < 0 || moneyToDivide - valueAdded > 1_000_000) return -1;
        if (!isOneDoorEmpty(doorNumber)) return -1;
        if (isActiveQuestionNull()) return -1;

        if (doorNumber == 1) {
            if (firstDoor + valueAdded < 0) return -1;
            moneyToDivide -= valueAdded;
            firstDoor += valueAdded;
            return firstDoor;
        }
        if (doorNumber == 2) {
            if (secondDoor + valueAdded < 0) return -1;
            moneyToDivide -= valueAdded;
            secondDoor += valueAdded;
            return secondDoor;
        }
        if (doorNumber == 3) {
            if (thirdDoor + valueAdded < 0) return -1;
            moneyToDivide -= valueAdded;
            thirdDoor += valueAdded;
            return thirdDoor;
        }
        if (doorNumber == 4) {
            if (fourthDoor + valueAdded < 0) return -1;
            moneyToDivide -= valueAdded;
            fourthDoor += valueAdded;
            return fourthDoor;
        }
        return -1;
    }

    private boolean isOneDoorEmpty(int doorNumber) {
        if (doorNumber != 1 && firstDoor == 0) return true;
        if (doorNumber != 2 && secondDoor == 0) return true;
        if (doorNumber != 3 && thirdDoor == 0) return true;
        if (doorNumber != 4 && fourthDoor == 0) return true;
        return false;
    }

    public void resetScreens(){
        firstDoor=0;
        secondDoor=0;
        thirdDoor=0;
        fourthDoor=0;
    }

    public void loadNewQuestion() {
        Random random = new Random();
        int questionNum = random.nextInt(questionsPool.size());
        activeQuestion = questionsPool.get(questionNum);
        questionsPool.remove(activeQuestion);
        leftQuestions--;
        if(debugMode){
            System.out.println(questionNum);
            System.out.println(activeQuestion);
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String[] getActiveQuestionList() {
        if (activeQuestion == null) return null;
        String[] questions = new String[4];
        questions[0] = activeQuestion.getAnswer1();
        questions[1] = activeQuestion.getAnswer2();
        questions[2] = activeQuestion.getAnswer3();
        questions[3] = activeQuestion.getAnswer4();
        return questions;
    }

    public int checkAnswer() {
        int correct = activeQuestion.getCorrect();
        if (correct != 1) firstDoor = 0;
        if (correct != 2) secondDoor = 0;
        if (correct != 3) thirdDoor = 0;
        if (correct != 4) fourthDoor = 0;
        moneyToDivide = firstDoor + secondDoor + thirdDoor + fourthDoor;
        activeQuestion=null;
        if (moneyToDivide <= 0) return -1;
        if (leftQuestions <= 0) return 1;
        else return 0;
    }


    public boolean isActiveQuestionNull() {
        return activeQuestion == null;
    }

    public String getActiveQuestionString() {
        if (activeQuestion == null) return null;
        return activeQuestion.getQuestion();
    }

    public int getMoneyToDivide() {
        return moneyToDivide;
    }
}
