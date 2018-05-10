package pl.kandrzejczak;

import static pl.kandrzejczak.Skill.Archery;
import static pl.kandrzejczak.Skill.Offence;

class Calculator {

//  Formula for calculating damage from http://heroes.thelazy.net/wiki/Damage
//  DMGf = DMGb × (1 + I1 + I2 + I3 + I4 + I5) × (1 - R1) × (1 - R2 - R3) × (1 - R4) × (1 - R5) × (1 - R6) × (1 - R7) × (1 - R8)
    DamageRange calculate(Hero attacking, Creature defending, AttackType attackType) {
        DamageRange damage = calculateDamage(attacking.getCreaturesStack());
        double positiveModifiers = calculatePositiveModifiers(attacking, defending, attackType);
        double negativeModifiers = calculateNegativeModifiers(attacking, defending);
        //TODO
        return new DamageRange(
                (int)((double)damage.getMin() * positiveModifiers * negativeModifiers),
                (int)((double)damage.getMax() * positiveModifiers * negativeModifiers));
    }

    //TODO: Law of demeter
    private DamageRange calculateDamage(CreaturesStack creaturesStack) {
        int damageMin = creaturesStack.getCreature().getDamage().getMin() * creaturesStack.getQuantity();
        int damageMax = creaturesStack.getCreature().getDamage().getMax() * creaturesStack.getQuantity();
        return new DamageRange(damageMin, damageMax);
    }

//  Positive part of formula: (1 + I1 + I2 + I3 + I4 + I5)
    private double calculatePositiveModifiers(Hero hero, Creature defendingCreature, AttackType attackType) {
        double modifier = 1;
        modifier += calculateI1(hero, defendingCreature.getDefence());
        double i2 = calculateI2(hero, attackType);
        modifier += i2;
        modifier += calculateI3(hero, i2);
        return modifier;
    }

    private double calculateI1(Hero attackingHero, int defendingCreatureDefense) {
        int attackingCreatureAttack = attackingHero.getCreaturesStack().getCreature().getAttack();
        int difference = (attackingHero.getHeroAttack() + attackingCreatureAttack) - defendingCreatureDefense;
        if(difference > 0) {
            return 0.05 * difference;
        }
            return 0;
    }

    private double calculateI2(Hero hero, AttackType attackType){
        if(hero.hasSkill(Offence) && AttackType.MELEE.equals(attackType)) {
            return Offence.getValue(hero.skillLevelFor(Offence));
        }
        if(hero.hasSkill(Archery) && AttackType.RANGED.equals(attackType)) {
            return Archery.getValue(hero.skillLevelFor(Archery));
        }
        return 0;
    }

    //TODO: To implement 0.03 × (hero ÷ creature level) for Adela's bless
    private double calculateI3(Hero hero, double i2) {
        if(hero.hasSpeciality(Speciality.Offence)) {
            return i2 * Speciality.Offence.getValue(hero.getHeroLevel());
        }
        return 0;
    }

    //  Negative part of formula: (1 - R1) × (1 - R2 - R3) × (1 - R4) × (1 - R5) × (1 - R6) × (1 - R7) × (1 - R8)
    private double calculateNegativeModifiers(Hero attacking, Creature defending) {
        double modifier = 1;
        modifier = modifier * calculateFirstNegativePart(attacking, defending.getDefence());
        return modifier;
    }

    private double calculateFirstNegativePart(Hero attackingHero, int defendingCreatureDefense) {
        int attackingCreatureAttack = attackingHero.getCreaturesStack().getCreature().getAttack();
        int difference = defendingCreatureDefense - (attackingHero.getHeroAttack() + attackingCreatureAttack);
        if(difference > 0) {
            return 1 - (0.025 * difference);
        }
        return 1;
    }
}