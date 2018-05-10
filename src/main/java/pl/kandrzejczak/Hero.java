package pl.kandrzejczak;

import java.util.List;

class Hero {
    private final Speciality speciality;
    private final List<HeroSkill> skills;
    private final CreaturesStack creaturesStack;
    private final int heroLevel;
    private final int heroAttack;

    //TODO: Consider builder pattern
    Hero(Speciality speciality, List<HeroSkill> skills, CreaturesStack creaturesStack, int heroLevel, int heroAttack) {
        this.speciality = speciality;
        this.skills = skills;
        this.creaturesStack = creaturesStack;
        this.heroLevel = heroLevel;
        this.heroAttack = heroAttack;
    }

    CreaturesStack getCreaturesStack() {
        return creaturesStack;
    }

    //TODO: code duplication
    boolean hasSkill(Skill skill) {
        for(HeroSkill hs : skills) {
            if(hs.getSkill().equals(skill)) {
                return true;
            }
        }
        return false;
    }

    Level skillLevelFor(Skill skill) {
        for(HeroSkill hs : skills) {
            if(hs.getSkill().equals(skill)) {
                return hs.getLevel();
            }
        }
        return null;
    }

    int getHeroLevel() {
        return heroLevel;
    }

    boolean hasSpeciality(Speciality speciality) {
        return this.speciality.equals(speciality);
    }

    int getHeroAttack() {
        return heroAttack;
    }
}

class HeroSkill {
    private Skill skill;
    private Level level;

    HeroSkill(Skill skill, Level level) {
        this.skill = skill;
        this.level = level;
    }

    Skill getSkill() {
        return skill;
    }

    Level getLevel() {
        return level;
    }
}