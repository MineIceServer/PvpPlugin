package meteordevelopment.meteorpvp.chat;

import meteordevelopment.meteorpvp.kits.MaxKits;
import org.bukkit.ChatColor;

public class Msgs {
    // General
    public static String playerNotOnline() {
        return "Игрок не онлайн.";
    }

    public static String cantUseThisInPvp() {
        return "Ты не можешь испльзовать эту команду во время пвп.";
    }

    // Duels
    public static String dontHaveRequest() {
        return "У тебя нету запроса на дуель от этого игрока.";
    }

    public static String cancelledRequest() {
        return "Запрос на дуель отменен.";
    }

    public static String didntSendRequest() {
        return "Вы не отправляли ни одного запроса на дуель.";
    }

    public static String cantDuelYourself() {
        return "Вы не можете отправить запрос на дуель самому себе.";
    }

    public static String playerIsInDuel(String otherPlayer) {
        return "Игрок уже на дуели с " + otherPlayer + ".";
    }

    public static String duelRequestSent() {
        return "Запрос на дуель отправлен.";
    }

    public static String alreadySentRequest() {
        return "Вы уже отправили запрос на дуель.";
    }

    public static String cancelDuelHelp() {
        return "Введите /cancelduel для отмены.";
    }

    public static String duelRequest(String player, String arena) {
        return ChatColor.YELLOW + "" + ChatColor.BOLD + player + ChatColor.GRAY + " вызывает тебя на дуель на " + arena + " арене.";
    }

    public static String playerDeclinedDuel(String player) {
        return ChatColor.GRAY + player + ChatColor.WHITE + " отменил дуель.";
    }

    public static String youDeclinedDuel(String player) {
        return "Отменен запрос на дуель от " + ChatColor.GRAY + player + ChatColor.WHITE + ".";
    }

    public static String yourDuelExpired() {
        return "Срок запроса на дуель истек.";
    }

    public static String duelExpired(String player) {
        return "Срок запроса на дуель от " + ChatColor.GRAY + player + ChatColor.WHITE + " истек.";
    }

    public static String noAvailableArenas() {
        return "Нету свободной арены.";
    }

    // KITS
    public static String createdKit(String name) {
        return "Создан кит " + ChatColor.GRAY + "'" + name + "'" + ChatColor.WHITE + ".";
    }

    public static String maxKits(MaxKits maxKits) {
        return "У тебя может быть только " + maxKits.count + " китов.";
    }

    public static String kitAlreadyExists(String name) {
        return "Кит с названием " + ChatColor.GRAY + "'" + name + "' " + ChatColor.WHITE + "уже существует.";
    }

    public static String deletedKit(String name) {
        return "Кит с названием " + ChatColor.GRAY + "'" + name + "' " + ChatColor.WHITE + "успешно удален.";
    }

    public static String kitDoesntExist(String name) {
        return "Кита с названием " + ChatColor.GRAY + "'" + name + "' " + ChatColor.WHITE + "не существет.";
    }

    public static String kitCreatorToggled(boolean on) {
        return "Место для создания китов " + ChatColor.GRAY + (on ? "включен" : "выключен") + ChatColor.WHITE + ".";
    }

    public static String dontOwnKit() {
        return "Вы не владелец этого кита.";
    }
}
