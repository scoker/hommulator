package pl.kandrzejczak;

class CreaturesStack {

    private final int quantity;
    private final Creature creature;

    CreaturesStack(int quantity, Creature creature) {
        this.quantity = quantity;
        this.creature = creature;
    }

    int getQuantity() {
        return quantity;
    }

    Creature getCreature() {
        return creature;
    }
}
