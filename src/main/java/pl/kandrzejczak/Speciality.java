package pl.kandrzejczak;

enum Speciality {
    Offence(0.05),
    FirstAid(0);

    private final double value;

    Speciality(double value) {

        this.value = value;
    }

    public double getValue(int heroLevel) {
        return heroLevel * value;
    }
}
