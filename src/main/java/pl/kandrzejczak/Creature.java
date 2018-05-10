package pl.kandrzejczak;

class Creature {
    private String name;
    private int attack;
    private int defence;
    private DamageRange damage;

    Creature(String name, int attack, int defence, int minDamage, int maxDamage) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.damage = new DamageRange(minDamage, maxDamage);
    }

    String getName() {
        return name;
    }

    int getAttack() {
        return attack;
    }

    int getDefence() {
        return defence;
    }

    DamageRange getDamage() {
        return damage;
    }
}