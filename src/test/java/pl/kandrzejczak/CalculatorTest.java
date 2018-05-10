package pl.kandrzejczak;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    //TODO: Interfaces TestCreatures, TestHeroes
    private final Creature ARCHANGEL = new Creature("Archangel", 30, 30, 50, 50);
    private final Creature DRAGON_FLY = new Creature("Dragon Fly", 8, 10, 2, 5);
    private final Creature NAGA = new Creature("Naga", 16, 13, 20, 20);
    private final CreaturesStack ARCHANGEL_STACK = new CreaturesStack(1, ARCHANGEL);
    private final CreaturesStack NINE_ARCHANGEL_STACK = new CreaturesStack(9, ARCHANGEL);
    private final Creature TITAN = new Creature("Titan", 24, 24, 40, 60);
    private final HeroSkill BASIC_ARCHERY = new HeroSkill(Skill.Archery, Level.Basic);
    private final HeroSkill BASIC_OFFENCE = new HeroSkill(Skill.Offence, Level.Basic);
    private final HeroSkill ADVANCED_ARCHERY = new HeroSkill(Skill.Archery, Level.Advanced);
    private final HeroSkill ADVANCED_OFFENCE = new HeroSkill(Skill.Offence, Level.Advanced);
    private final HeroSkill EXPERT_OFFENCE = new HeroSkill(Skill.Offence, Level.Expert);

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void shouldCalculateDamageForOneCreatureInStack() {
        DamageRange expectedDamage = new DamageRange(92, 92);
        assertEquals(expectedDamage, calculator.calculate(createHero(ARCHANGEL), NAGA, AttackType.MELEE));
    }

    @Test
    public void shouldCalculateDamageForOneCreatureInStackWithDamageRange() {
        Creature archDevil = new Creature("Arch Devil", 26, 28, 30, 40);
        Hero attacking = createHero(archDevil);
        DamageRange expectedDamage = new DamageRange(49, 66);
        assertEquals(expectedDamage, calculator.calculate(attacking, NAGA, AttackType.MELEE));
    }

    @Test
    public void shouldCalculateDamageForNineCreaturesInStack() {
        DamageRange expectedDamage = new DamageRange(832, 832);
        assertEquals(expectedDamage, calculator.calculate(createHero(NINE_ARCHANGEL_STACK), NAGA, AttackType.MELEE));
    }

    @Test
    public void shouldCalculateDamageWithBasicOffence() {
        Hero hero = new Hero(Speciality.FirstAid, Arrays.asList(BASIC_OFFENCE), ARCHANGEL_STACK, 1, 0);
        DamageRange expectedDamage = new DamageRange(97, 97);
        assertEquals(expectedDamage, calculator.calculate(hero, NAGA, AttackType.MELEE));
    }

    @Test
    public void shouldCalculateDamageWithAdvancedOffence() {
        Hero hero = new Hero(Speciality.FirstAid, Arrays.asList(ADVANCED_OFFENCE), ARCHANGEL_STACK, 1, 0);
        DamageRange expectedDamage = new DamageRange(102, 102);
        assertEquals(expectedDamage, calculator.calculate(hero, NAGA, AttackType.MELEE));
    }

    @Test
    public void shouldCalculateDamageWithBasicArchery() {
        CreaturesStack titansStack = new CreaturesStack(1, TITAN);
        Hero hero = new Hero(Speciality.FirstAid, Arrays.asList(BASIC_ARCHERY), titansStack, 1, 0);
        DamageRange expectedDamage = new DamageRange(66, 99);
        assertEquals(expectedDamage, calculator.calculate(hero, NAGA, AttackType.RANGED));
    }

    @Test
    public void shouldCalculateDamageWithAdvancedArchery() {
        CreaturesStack titansStack = new CreaturesStack(1, TITAN);
        Hero hero = new Hero(Speciality.FirstAid, Arrays.asList(ADVANCED_ARCHERY), titansStack, 1, 0);
        DamageRange expectedDamage = new DamageRange(72, 108);
        assertEquals(expectedDamage, calculator.calculate(hero, NAGA, AttackType.RANGED));
    }

    @Test
    public void shouldNotIncludeOffenceSkillWhenRangedAttack() {
        CreaturesStack titansStack = new CreaturesStack(1, TITAN);
        Hero hero = new Hero(Speciality.FirstAid, Arrays.asList(ADVANCED_OFFENCE), titansStack, 1, 0);
        DamageRange expectedDamage = new DamageRange(62, 93);
        assertEquals(expectedDamage, calculator.calculate(hero, NAGA, AttackType.RANGED));
    }

    @Test
    public void shouldCalculateDamageWithOffenceSpecialityForTwentyLevelHero() {
        Hero hero = new Hero(Speciality.Offence, Arrays.asList(EXPERT_OFFENCE), ARCHANGEL_STACK, 20, 8);
        DamageRange expectedDamage = new DamageRange(142, 142);
        assertEquals(expectedDamage, calculator.calculate(hero, NAGA, AttackType.MELEE));
    }

    @Test
    public void shouldCalculateDamageForHeroWithTenAttack() {
        Hero hero = new Hero(Speciality.FirstAid, Collections.emptyList(), ARCHANGEL_STACK, 1, 10);
        DamageRange expectedDamage = new DamageRange(117, 117);
        assertEquals(expectedDamage, calculator.calculate(hero, NAGA, AttackType.MELEE));
    }

    @Test
    public void shouldCalculateDamageWhenDefendingCreatureHasHigherDefence() {
        DamageRange expectedDamage = new DamageRange(1, 4);
        assertEquals(expectedDamage, calculator.calculate(createHero(DRAGON_FLY), NAGA, AttackType.MELEE));
    }

    private Hero createHero(Creature creature) {
        return createHero(new CreaturesStack(1, creature));
    }

    private Hero createHero(CreaturesStack creaturesStack) {
        return createHero(creaturesStack, Collections.emptyList());
    }

    private Hero createHero(CreaturesStack creaturesStack, List<HeroSkill> heroSkills) {
        return new Hero(Speciality.FirstAid, heroSkills, creaturesStack, 1, 0);
    }
}