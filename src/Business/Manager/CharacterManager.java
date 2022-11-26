package Business.Manager;

import Business.Entity.Character;
import Presentation.MainView;

public class CharacterManager {

    MainView mainView;

    public CharacterManager(MainView mainView) {
        this.mainView = mainView;

    }

    public boolean createCharacter(){
        Character character = new Character();

        character.setName(mainView.askUserForValidCharacterName());
        mainView.printLine("\nTavern keeper: “Hello, Finrod Felagund, be welcome.”");
        character.setPlayer(mainView.askUserForValidPlayerName());





        return true;
    }




}
