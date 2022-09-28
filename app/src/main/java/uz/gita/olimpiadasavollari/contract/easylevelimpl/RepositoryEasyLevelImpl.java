package uz.gita.olimpiadasavollari.contract.easylevelimpl;

import java.util.ArrayList;
import java.util.Collections;

import uz.gita.olimpiadasavollari.contract.EasyLevelScreenContract;
import uz.gita.olimpiadasavollari.data.Database;
import uz.gita.olimpiadasavollari.model.Level;
import uz.gita.olimpiadasavollari.model.Question;

public class RepositoryEasyLevelImpl implements EasyLevelScreenContract.Repository {
    Database repository = Database.getInstance();
    ArrayList<Question> list = repository.initQuestions();

    @Override
    public ArrayList<Question> initQuestions(Level level) {
        list = Database.getInstance().initQuestions();
        switch (level) {
            case EASY: {
                list = levelCheck(list, Level.EASY);
            }
            break;
            case MEDIUM: {
                list = levelCheck(list, Level.MEDIUM);
            }
            break;
            case HARD: {
                list = levelCheck(list, Level.HARD);
            }
            break;
        }
        return list;
    }

    @Override
    public Question getQuestion(int index) {
        return list.get(index);
    }

    @Override
    public void shuffle() {

        Collections.shuffle(list);
    }

    private ArrayList<Question> levelCheck(ArrayList<Question> list, Level level) {
        ArrayList<Question> listLevel = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLevel().equals(level)) {
                listLevel.add(list.get(i));
            }
        }
        return listLevel;
    }
}
