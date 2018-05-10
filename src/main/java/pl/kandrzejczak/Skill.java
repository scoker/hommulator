package pl.kandrzejczak;

enum Skill {
    Archery(0.1, 0.25, 0.5),
    Offence(0.1, 0.2, 0.3);

    private final double basicLevelValue;
    private final double advancedLevelValue;
    private final double expertLevelValue;

    Skill(double basicLevelValue, double advancedLevelValue, double expertLevelValue) {

        this.basicLevelValue = basicLevelValue;
        this.advancedLevelValue = advancedLevelValue;
        this.expertLevelValue = expertLevelValue;
    }

    double getValue(Level level) {
        switch (level) {
            case Basic:
                return basicLevelValue;
            case Advanced:
                return advancedLevelValue;
            case Expert:
                return expertLevelValue;
        }
        return 0;
    }
}
