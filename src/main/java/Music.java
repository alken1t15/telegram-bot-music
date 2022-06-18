import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

 public class Music {
    ArrayList<String> musicList = new ArrayList<>();
    {
        musicList.add("D:\\RockMusic\\Небо словян");
        musicList.add("D:\\RockMusic\\Голубые береты - Синева");
        musicList.add("D:\\RockMusic\\Пурген - Грозный хардкор");
        musicList.add("D:\\СhansonMusic\\Михаил Михайлов - Дискотека в деревне");
        musicList.add("D:\\СhansonMusic\\Петлюра - Дочь прокурора.mp3");
        musicList.add("D:\\СhansonMusic\\Сборная Союза - Позвони мне");
        musicList.add("D:\\RapMusic\\INSTASAMKA - ХЛОПАЙ");
        musicList.add("D:\\RapMusic\\Jason Swann, Eugene Demuckiy - ХАГИ ВАГИ");
        musicList.add("D:\\RapMusic\\Макс Корж - Малиновый закат");
    }


    public String getMusicList(int index){
        return musicList.get(index)+".mp3";
    }


}
