package com.girlkun.models.player;

import com.girlkun.models.shop.ShopServiceNew;
import com.girlkun.services.MapService;
import com.girlkun.consts.ConstMap;
import com.girlkun.models.map.Map;
import com.girlkun.models.map.Zone;
import com.girlkun.server.Manager;
import com.girlkun.services.MapService;
import com.girlkun.services.PlayerService;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;
// đây
import java.util.ArrayList;
import java.util.List;

/**
 * @author BTH sieu cap vippr0
 */
public class Referee1 extends Player {

    private long lastTimeChat;
    private Player playerTarget;

    private long lastTimeTargetPlayer;
    private long timeTargetPlayer = 5000;
    private long lastZoneSwitchTime;
    private long zoneSwitchInterval;
    private List<Zone> availableZones;

    public void initReferee1() {
        init();
    }

    @Override
    public short getHead() {
        return 678;
    }

    @Override
    public short getBody() {
        return 679;
    }

    @Override
    public short getLeg() {
        return 680;
    }

    public void joinMap(Zone z, Player player) {
        MapService.gI().goToMap(player, z);
        z.load_Me_To_Another(player);
    }

    @Override
    public void update() {
        if (Util.canDoWithTime(lastTimeChat, 8000)) {
           Service.getInstance().chat(this, "Thằng nào có tiềnn");                   
         Service.getInstance().chat(this, "Nạp Tiền vào donate cho tao");
           Service.getInstance().chat(this, "ít thì 5 quả trứng" );
            Service.getInstance().chat(this, "nhiều thì 1 tên lửa ");
            Service.getInstance().chat(this, "Chúng mày hiểu chưa !!! ");        
            Service.getInstance().chat(this, "Hiểu chưa !!! "); 
            Service.getInstance().chat(this, "Anh chỉ nói một lần duy nhất!!");
            lastTimeChat = System.currentTimeMillis();
        }
    }

    private void init() {
        int id = -1000000;
        for (Map m : Manager.MAPS) {
            if (m.mapId == 0) {
                for (Zone z : m.zones) {
                    Referee1 pl = new Referee1();
                    pl.name = "CUI MEME";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 69696969696969L;
                    pl.nPoint.hpg = 69696969696969L;
                    pl.nPoint.hp = 69696969696969L;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 714;
                    pl.location.y = 432;
                    joinMap(z, pl);
                    z.setReferee(pl);
                }
            } else if (m.mapId == 7) {                      
                    for (Zone z : m.zones) {
                    Referee1 pl = new Referee1();
                    pl.name = "CUI MEME";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 69696969696969L;
                    pl.nPoint.hpg = 69696969696969L;
                    pl.nPoint.hp = 69696969696969L;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 761;
                    pl.location.y = 432;
                    joinMap(z, pl);
                    z.setReferee(pl);
                 }
              } 
//            else if (m.mapId == 14) {                      
//                    for (Zone z : m.zones) {
//                    Referee1 pl = new Referee1();
//                    pl.name = "BOT";
//                    pl.gender = 0;
//                    pl.id = id++;
//                    pl.nPoint.hpMax = 69696969696969L;
//                    pl.nPoint.hpg = 69696969696969L;
//                    pl.nPoint.hp = 69696969696969L;
//                    pl.nPoint.setFullHpMp();
//                    pl.location.x = 752;
//                    pl.location.y = 408;
//                    joinMap(z, pl);
//                    z.setReferee(pl);
//                 }
//            }
        }
    }
}

