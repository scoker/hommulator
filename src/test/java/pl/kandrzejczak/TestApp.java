package pl.kandrzejczak;

import java.util.Arrays;

public class TestApp {

    private static final Creature NAGA = new Creature("Naga", 16, 13, 20, 20);
    private static final Creature ARCHANGEL = new Creature("Archangel", 30, 30, 50, 50);
    private static final Creature TITAN = new Creature("Titan", 24, 24, 40, 60);
    private static final Creature WOOD_ELF = new Creature("Wood Elf", 9, 5, 3, 5);
    private static final Creature GRAND_ELF = new Creature("Grand Elf", 9, 5, 3, 5);
    private static final Creature MARKSMAN = new Creature("Marksman", 6, 3, 2, 3);
    private static final Creature MASTER_GRAMLIN = new Creature("Master Gremlin", 4, 4, 1, 2);
    private static final Creature MAGOG = new Creature("Magog", 7, 4, 2, 4);
    private static final Creature EVIL_EYE = new Creature("Evil Eye", 10, 8, 3, 5);
    private static final Creature GRIFFIN = new Creature("Griffin", 8, 8, 3, 6);

    private static final HeroSkill BASIC_OFFENCE = new HeroSkill(Skill.Offence, Level.Basic);
    private static final HeroSkill BASIC_ARCHERY = new HeroSkill(Skill.Archery, Level.Basic);
    private static final HeroSkill ADVANCED_OFFENCE = new HeroSkill(Skill.Offence, Level.Advanced);


    public static void main(String[] args) {
        calculateAndPrint(createHero(BASIC_OFFENCE, GRAND_ELF, 15), GRIFFIN);
    }

    private static void calculateAndPrint(Hero hero, Creature defending) {
        Calculator calculator = new Calculator();
        DamageRange damage = calculator.calculate(hero, defending, AttackType.MELEE);
        System.out.println("Hero attacking");
        System.out.println("  with " + hero.getCreaturesStack().getCreature().getName());
        System.out.println("  will do " + damage);
        System.out.println("  to " + defending.getName());
        System.out.println();
    }

    private static Hero createHero(HeroSkill heroSkill, Creature creature, int quantity) {
        CreaturesStack stack = new CreaturesStack(quantity, creature);
        return new Hero(Speciality.FirstAid, Arrays.asList(heroSkill), stack, 1, 0);
    }
}
