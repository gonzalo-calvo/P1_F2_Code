package Business.Entity;

public class MyCharacter {

    String name;
    String player;
    int experience;
    int body;
    int mind;
    int spirit;
    String type;

    public MyCharacter(String name, String player, int experience, int body, int mind, int spirit, String type) {
        this.name = name;
        this.player = player;
        this.experience = experience;
        this.body = body;
        this.mind = mind;
        this.spirit = spirit;
        this.type = type;
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
}
