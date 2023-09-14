/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girlkun.models.boss.list_boss;

import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.server.Manager;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.PetService;
import com.girlkun.services.Service;
import com.girlkun.services.TaskService;
import com.girlkun.utils.Util;
import java.util.Random;

/**
 * @@Stole By Arriety
 */
public class gokuvocuc extends Boss {

    public gokuvocuc() throws Exception {
        super(BossID.Gokuvocuc, BossesData.gokuvocuc);
    }

    @Override
    public void reward(Player plKill) {
        int[] itemDos = new int[]{15, 16};
        int[] NRs = new int[]{1132, 1245};
        int randomDo = new Random().nextInt(itemDos.length);
        int randomNR = new Random().nextInt(NRs.length);
        int[] itemRewardTs = {1048, 1049, 1050, 1051, 1052, 1053, 1054, 1055, 1056, 1057, 1058, 1059, 1060, 1061, 1062};
        if (Util.isTrue(1, 100)) {
            if (Util.isTrue(10, 10)) {
                Random random = new Random();
                int sd = random.nextInt(15000,20000) ;
                int hp = Util.nextInt(200,300);
                int ki = Util.nextInt(200,300);
                int sdcm = random.nextInt(10,20);
                int giap = random.nextInt(3000,3500);
                int cm = random.nextInt(30,40);
                int randomIndex = random.nextInt(itemRewardTs.length);
                int selectedNumber = itemRewardTs[randomIndex];
                ItemMap it = new ItemMap(this.zone, selectedNumber, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), plKill.id);
    
                if (selectedNumber >= 1048 && selectedNumber <= 1050) {
                    it.options.add(new Item.ItemOption(47, giap));
                    it.options.add(new Item.ItemOption(5, sdcm));
                    it.options.add(new Item.ItemOption(209, 0));
                    it.options.add(new Item.ItemOption(21, 1000));
                } else if (selectedNumber >= 1051 && selectedNumber <= 1053) {
                    it.options.add(new Item.ItemOption(22, hp));
                    it.options.add(new Item.ItemOption(5, sdcm));
                    it.options.add(new Item.ItemOption(209, 0));
                    it.options.add(new Item.ItemOption(21, 1000));
                } else if (selectedNumber >= 1054 && selectedNumber <= 1056) {
                    it.options.add(new Item.ItemOption(0, sd));
                    it.options.add(new Item.ItemOption(5, sdcm));
                    it.options.add(new Item.ItemOption(209, 0));
                    it.options.add(new Item.ItemOption(21, 1000));
                } else if (selectedNumber >= 1057 && selectedNumber <= 1059) {
                    it.options.add(new Item.ItemOption(23, ki));
                    it.options.add(new Item.ItemOption(5, sdcm));
                    it.options.add(new Item.ItemOption(209, 0));
                    it.options.add(new Item.ItemOption(21, 1000));
                } else if (selectedNumber >= 1060 && selectedNumber <= 1062) {
                    it.options.add(new Item.ItemOption(14, cm));
                    it.options.add(new Item.ItemOption(5, sdcm));
                    it.options.add(new Item.ItemOption(209, 0));
                    it.options.add(new Item.ItemOption(21, 1000));
                }

                Service.getInstance().dropItemMap(this.zone, it);
                return;
            }
            Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, itemDos[randomDo], Util.nextInt(5, 30), this.location.x, this.location.y, plKill.id));
        } else if (Util.isTrue(80, 100)) {
            Service.gI().dropItemMap(this.zone, new ItemMap(zone, NRs[randomNR], Util.nextInt(5, 30), this.location.x, zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
        }
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }

    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }
    private long st;

}
