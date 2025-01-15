package domain2;

import java.util.Random;

public class MatchSimulator {
    public MatchResult simulateMatch(Team team1, Team team2) {
        Random random = new Random();

        int team1Skill = team1.getPlayers().stream().mapToInt(Player::getSkill).sum();
        int team2Skill = team2.getPlayers().stream().mapToInt(Player::getSkill).sum();

        int team1Score = random.nextInt(3) + (team1Skill / 100);
        int team2Score = random.nextInt(3) + (team2Skill / 100);

        return new MatchResult(team1, team2, team1Score, team2Score);
    }
}
