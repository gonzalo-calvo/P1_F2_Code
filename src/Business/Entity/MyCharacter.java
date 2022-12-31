package Business.Entity;

public class MyCharacter {

    String name;
    String player;
    int experience;
    int body;
    int mind;
    int spirit;
    String type;
    int healthPoints;
    int initiative;

    public MyCharacter(String name, String player, int experience, int body, int mind, int spirit, String type, int healthPoints, int initiative) {
        this.name = name;
        this.player = player;
        this.experience = experience;
        this.body = body;
        this.mind = mind;
        this.spirit = spirit;
        this.type = type;
        this.healthPoints = healthPoints;
        this.initiative = initiative;
    }

    public MyCharacter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int getMind() {
        return mind;
    }

    public void setMind(int mind) {
        this.mind = mind;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHealthPoints() {return healthPoints;}
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getInitiative() {return initiative;}

    public void setInitiative(int initiative) {this.initiative = initiative;}

    public String myToString() {
        return name + ", " + player + ", " + experience + ", " + body + ", " + mind + ", " + spirit + ", " + type + ", " + healthPoints + ", " + initiative;
    }
}
