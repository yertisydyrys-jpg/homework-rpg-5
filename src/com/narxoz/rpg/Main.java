package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        AttackAction basic = new BasicAttack("Shadow Strike", 14);
        AttackAction fireAttack = new FireRuneDecorator(basic);
        AttackAction poisonThenCrit = new CriticalFocusDecorator(new PoisonCoatingDecorator(basic));
        AttackAction fullCombo = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(basic)
                )
        );
        AttackAction changedOrder = new CriticalFocusDecorator(
                new FireRuneDecorator(
                        new PoisonCoatingDecorator(basic)
                )
        );

        System.out.println("--- Decorator Preview ---");
        printActionPreview("Base action", basic);
        printActionPreview("Fire upgrade", fireAttack);
        printActionPreview("Poison then critical", poisonThenCrit);
        printActionPreview("Full combo", fullCombo);
        printActionPreview("Same decorators, different order", changedOrder);

        System.out.println("Observation: changing decorator order changes the final damage.\n");

        System.out.println("--- Facade Preview ---");
        HeroProfile hero = new HeroProfile("Arin the Ranger", 110);
        BossEnemy boss = new BossEnemy("Infernal Warden", 95, 15);

        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, fullCombo);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
        System.out.println("Battle log:");
        for (String line : result.getLog()) {
            System.out.println("- " + line);
        }

        System.out.println("\n=== Demo Complete ===");
    }

    private static void printActionPreview(String label, AttackAction action) {
        System.out.println(label + ":");
        System.out.println("  Name: " + action.getActionName());
        System.out.println("  Damage: " + action.getDamage());
        System.out.println("  Effects: " + action.getEffectSummary());
        System.out.println();
    }
}