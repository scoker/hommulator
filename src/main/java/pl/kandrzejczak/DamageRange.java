package pl.kandrzejczak;

public class DamageRange {
    private final int minDamage;
    private final int maxDamage;

    DamageRange(int minDamage, int maxDamage) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    int getMin() {
        return minDamage;
    }

    int getMax() {
        return maxDamage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DamageRange that = (DamageRange) o;

        return minDamage == that.minDamage && maxDamage == that.maxDamage;
    }

    @Override
    public int hashCode() {
        int result = minDamage;
        result = 31 * result + maxDamage;
        return result;
    }

    @Override
    public String toString() {
        return "DamageRange(" + minDamage + ", " + maxDamage + ')';
    }
}
