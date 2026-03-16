package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || boss == null || action == null) {
            throw new IllegalArgumentException("Hero, boss and action must be provided.");
        }
        if (!hero.isAlive()) {
            throw new IllegalArgumentException("Hero must have positive health before the adventure starts.");
        }
        if (!boss.isAlive()) {
            throw new IllegalArgumentException("Boss must have positive health before the adventure starts.");
        }
        if (action.getDamage() <= 0) {
            throw new IllegalArgumentException("Action damage must be positive.");
        }

        return String.format(
                "Preparation: %s enters the dungeon with \"%s\" (%d damage). Boss: %s (%d HP, %d ATK). Effects: %s.",
                hero.getName(),
                action.getActionName(),
                action.getDamage(),
                boss.getName(),
                boss.getHealth(),
                boss.getAttackPower(),
                action.getEffectSummary()
        );
    }
}