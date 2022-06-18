import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.management.Query;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    Bot bot;
    Music music;
    public String c;
    String wordForClass;//
    int count;//

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }


    public void sendMsg(Message message, String text) { // Метод отвечает за отправку сообщения
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendPhoto(Message message) {
        File soundFile1 = new File("C:\\gg\\Timati.jpg");
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());
        sendPhoto.setReplyToMessageId(message.getMessageId());
        sendPhoto.setPhoto(new InputFile(soundFile1));
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMusic(Message message, String file) {
        File soundFile = new File(file); //Звуковой файл
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(message.getChatId().toString());
        sendAudio.setReplyToMessageId(message.getMessageId());
        sendAudio.setAudio(new InputFile(soundFile));
        try {
            execute(sendAudio);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    public void onUpdateReceived(Update update) { // Метод который слуашет сообщения в чате
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/hello":
                    sendMsg(message, "Привет я музыкальный бот я могу предложить тебе популярную музыку по жанрам, введи команду /help для отображение моих команд");
                    break;
                case "/help":
                    sendMsg(message, "Список команд: /downloadMusic, /rock, /rap,/chanson");
                    break;
                case "/downloadMusic":
                    sendMsg(message, "Введите /downloadMusic и номер песни чтобы скачать ее");
                    break;
                case "/rock":
                    sendMsg(message, "Топ 3 популярных рок песен: \n Небо словян id 0\n Голубые береты - Синева id 1 \n Пурген - Грозный хардкор id 2 \n");
                    break;
                case "/chanson":
                    sendMsg(message, "Топ 3 популярных шансон песен: \n Михаил Михайлов - Дискотека в деревне id 3\n Петлюра - Дочь прокурора id 4 \n Сборная Союза - Позвони мне id 5 \n");
                    break;
                case "/rap":
                    sendMsg(message, "Топ 3 популярных рэп песен: \n INSTASAMKA - ХЛОПАЙ id 6\n Jason Swann, Eugene Demuckiy - ХАГИ ВАГИ id 7 \n Макс Корж - Малиновый закат id 8 \n");
                    break;
                case "/regist":
                    System.out.println("case");
                    count = 1;
                    break;
                default:
                    wordForClass = message.getText();
                    System.out.println(wordForClass);
                    if (count == 1) {
                       Register register = new Register(wordForClass,count);
                       count = register.count;
                    }
                    sendMsg(message, status(message));
            }
        }

    }


    public void setButtons(SendMessage sendMessage) { // Метод отвечает за кнопки
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardFirstRow2 = new KeyboardRow();


        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/downloadMusic"));
        keyboardFirstRow2.add(new KeyboardButton("/rock"));
        keyboardFirstRow2.add(new KeyboardButton("/chanson"));
        keyboardFirstRow2.add(new KeyboardButton("/rap"));
        keyboardFirstRow2.add(new KeyboardButton("/regist"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardFirstRow2);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }

    public String status(Message message) { // Функционал комманд
        String b = message.getText().toString();
        String arr[] = b.split(" ", 2);
        String firstWord = arr[0];
        String secondWord = arr[1];
        if (firstWord.equals("/downloadMusic")) {
            music = new Music();
            bot = new Bot();
            String musicFile = music.getMusicList(Integer.parseInt(secondWord));
            bot.sendMusic(message, musicFile);
        }
        return c;


    }


    @Override
    public String getBotUsername() {
        return "Marcho";
    }

    @Override
    public String getBotToken() {
        return "";
    }

}
