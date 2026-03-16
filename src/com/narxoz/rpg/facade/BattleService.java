package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private static final int MAX_ROUNDS = 6;
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        result.addLine("Battle started against " + boss.getName() + ".");

        int rounds = 0;
        while (hero.isAlive() && boss.isAlive() && rounds < MAX_ROUNDS) {
            rounds++;

            int heroDamage = action.getDamage() + random.nextInt(5);
            boss.takeDamage(heroDamage);
            result.addLine(String.format(
                    "Round %d: %s uses %s and deals %d damage. %s HP left: %d.",
                    rounds,
                    hero.getName(),
                    action.getActionName(),
                    heroDamage,
                    boss.getName(),
                    boss.getHealth()
            ));

            if (!boss.isAlive()) {
                break;
            }

            int bossDamage = boss.getAttackPower() + random.nextInt(4);
            hero.takeDamage(bossDamage);
            result.addLine(String.format(
                    "Round %d: %s strikes back for %d damage. %s HP left: %d.",
                    rounds,
                    boss.getName(),
                    bossDamage,
                    hero.getName(),
                    hero.getHealth()
            ));
        }

        result.setRounds(rounds);
        if (hero.isAlive() && !boss.isAlive()) {
            result.setWinner(hero.getName());
            result.setHeroVictory(true);
            result.addLine("Outcome: the hero defeats the boss.");
        } else if (!hero.isAlive() && boss.isAlive()) {
            result.setWinner(boss.getName());
            result.setHeroVictory(false);
            result.addLine("Outcome: the boss defeats the hero.");
        } else if (hero.isAlive()) {
            result.setWinner(hero.getName());
            result.setHeroVictory(true);
            result.addLine("Outcome: the hero survives the maximum number of rounds and wins by endurance.");
        } else {
            result.setWinner("Draw");
            result.setHeroVictory(false);
            result.addLine("Outcome: both fighters fall at the same time.");
        }

        return result;
    }
}