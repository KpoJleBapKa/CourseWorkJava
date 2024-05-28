package com.example.wotstat.ui;

import com.example.wotstat.model.Clan;
import com.example.wotstat.model.Player;
import com.example.wotstat.service.ClanService;
import com.example.wotstat.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);

    private final ClanService clanService;
    private final PlayerService playerService;

    @Autowired
    public MainMenu(ClanService clanService, PlayerService playerService) {
        this.clanService = clanService;
        this.playerService = playerService;
    }

    public void start() {
        String option = System.getenv("MENU_OPTION");
        while (true) {
            System.out.println("1. Manage Clans");
            System.out.println("2. Manage Players");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    manageClans();
                    break;
                case 2:
                    managePlayers();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageClans() {
        while (true) {
            System.out.println("1. List all Clans");
            System.out.println("2. Get Clan by Name");
            System.out.println("3. Create Clan");
            System.out.println("4. Update Clan");
            System.out.println("5. Delete Clan");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    listAllClans();
                    break;
                case 2:
                    getClanByName();
                    break;
                case 3:
                    createClan();
                    break;
                case 4:
                    updateClan();
                    break;
                case 5:
                    deleteClan();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void managePlayers() {
        while (true) {
            System.out.println("1. List all Players");
            System.out.println("2. Get Player by Nickname");
            System.out.println("3. Create Player");
            System.out.println("4. Update Player");
            System.out.println("5. Delete Player");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    listAllPlayers();
                    break;
                case 2:
                    getPlayerByNickname();
                    break;
                case 3:
                    createPlayer();
                    break;
                case 4:
                    updatePlayer();
                    break;
                case 5:
                    deletePlayer();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllClans() {
        clanService.getAllClans().forEach(System.out::println);
    }

    private void getClanByName() {
        System.out.print("Enter Clan Name: ");
        String name = scanner.nextLine();
        Clan clan = clanService.getClanByName(name);
        if (clan != null) {
            System.out.println(clan);
        } else {
            System.out.println("Clan not found.");
        }
    }

    private void createClan() {
        System.out.print("Enter Clan Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Total Members: ");
        int members = scanner.nextInt();
        System.out.print("Enter Average Battles: ");
        int battles = scanner.nextInt();
        System.out.print("Enter Win Rate: ");
        float winRate = scanner.nextFloat();
        System.out.print("Enter Clan Rating: ");
        float rating = scanner.nextFloat();
        System.out.print("Enter Average Exp: ");
        float exp = scanner.nextFloat();
        System.out.print("Enter Average Damage: ");
        float damage = scanner.nextFloat();
        scanner.nextLine();  // Consume newline

        Clan clan = new Clan();
        clan.setClanName(name);
        clan.setTotalMembers(members);
        clan.setAverageBattles(battles);
        clan.setWinRate(winRate);
        clan.setClanRating(rating);
        clan.setAverageExp(exp);
        clan.setAverageDamage(damage);

        clanService.saveClan(clan);
        System.out.println("Clan created successfully.");
    }

    private void updateClan() {
        System.out.print("Enter Clan Name: ");
        String name = scanner.nextLine();
        Clan clan = clanService.getClanByName(name);
        if (clan != null) {
            System.out.print("Enter Total Members: ");
            clan.setTotalMembers(scanner.nextInt());
            System.out.print("Enter Average Battles: ");
            clan.setAverageBattles(scanner.nextInt());
            System.out.print("Enter Win Rate: ");
            clan.setWinRate(scanner.nextFloat());
            System.out.print("Enter Clan Rating: ");
            clan.setClanRating(scanner.nextFloat());
            System.out.print("Enter Average Exp: ");
            clan.setAverageExp(scanner.nextFloat());
            System.out.print("Enter Average Damage: ");
            clan.setAverageDamage(scanner.nextFloat());
            scanner.nextLine();  // Consume newline

            clanService.saveClan(clan);
            System.out.println("Clan updated successfully.");
        } else {
            System.out.println("Clan not found.");
        }
    }

    private void deleteClan() {
        System.out.print("Enter Clan Name: ");
        String name = scanner.nextLine();
        Clan clan = clanService.getClanByName(name);
        if (clan != null) {
            clanService.deleteClan(clan.getId());
            System.out.println("Clan deleted successfully.");
        } else {
            System.out.println("Clan not found.");
        }
    }

    private void listAllPlayers() {
        playerService.getAllPlayers().forEach(System.out::println);
    }

    private void getPlayerByNickname() {
        System.out.print("Enter Player Nickname: ");
        String nickname = scanner.nextLine();
        Player player = playerService.getPlayerByNickname(nickname);
        if (player != null) {
            System.out.println(player);
        } else {
            System.out.println("Player not found.");
        }
    }

    private void createPlayer() {
        System.out.print("Enter Player Nickname: ");
        String nickname = scanner.nextLine();
        System.out.print("Enter Total Battles: ");
        int battles = scanner.nextInt();
        System.out.print("Enter Win Rate: ");
        float winRate = scanner.nextFloat();
        System.out.print("Enter Average Damage: ");
        float damage = scanner.nextFloat();
        System.out.print("Enter Average Exp: ");
        float exp = scanner.nextFloat();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Clan Name: ");
        String clanName = scanner.nextLine();

        Player player = new Player();
        player.setNickname(nickname);
        player.setTotalBattles(battles);
        player.setWinRate(winRate);
        player.setAverageDamage(damage);
        player.setAverageExp(exp);
        player.setClanName(clanName);

        playerService.savePlayer(player);
        System.out.println("Player created successfully.");
    }

    private void updatePlayer() {
        System.out.print("Enter Player Nickname: ");
        String nickname = scanner.nextLine();
        Player player = playerService.getPlayerByNickname(nickname);
        if (player != null) {
            System.out.print("Enter Total Battles: ");
            player.setTotalBattles(scanner.nextInt());
            System.out.print("Enter Win Rate: ");
            player.setWinRate(scanner.nextFloat());
            System.out.print("Enter Average Damage: ");
            player.setAverageDamage(scanner.nextFloat());
            System.out.print("Enter Average Exp: ");
            player.setAverageExp(scanner.nextFloat());
            scanner.nextLine();  // Consume newline
            System.out.print("Enter Clan Name: ");
            String clanName = scanner.nextLine();

            player.setClanName(clanName);

            playerService.savePlayer(player);
            System.out.println("Player updated successfully.");
        } else {
            System.out.println("Player not found.");
        }
    }


    private void deletePlayer() {
        System.out.print("Enter Player Nickname: ");
        String nickname = scanner.nextLine();
        Player player = playerService.getPlayerByNickname(nickname);
        if (player != null) {
            playerService.deletePlayer(player.getId());
            System.out.println("Player deleted successfully.");
        } else {
            System.out.println("Player not found.");
        }
    }
}
