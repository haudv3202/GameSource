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
import com.girlkun.services.InventoryServiceNew;
import com.girlkun.services.ItemService;
import com.girlkun.services.PetService;
import com.girlkun.services.Service;
import com.girlkun.services.TaskService;
import com.girlkun.utils.Util;
import java.util.Random;

/**
 * @@Stole By Arriety
 */
public class BossMaBuNoel extends Boss {

    public BossMaBuNoel() throws Exception {
        super(BossID.MABUNOEL, BossesData.MABUNOEL);
    }

    @Override
    public void reward(Player plKill) {
//            Item item = ItemService.gI().createNewItem((short)(937));
//            Random random = new Random();
//            int sd = random.nextInt(46) + 40;
//            int hp = random.nextInt(46) + 40;
//            int ki = random.nextInt(46) + 40;
//             item.itemOptions.add(new Item.ItemOption(49,sd));
//            item.itemOptions.add(new Item.ItemOption(77,hp));
//            item.itemOptions.add(new Item.ItemOption(103,ki));
//             if (Util.isTrue(100, 100)) {
//                    int randomMoth = random.nextInt(30) + 1;
////               randomMoth
//                    item.itemOptions.add(new Item.ItemOption(93, randomMoth));
//            }
//            item.itemOptions.add(new Item.ItemOption(207,0));
//            item.itemOptions.add(new Item.ItemOption(33,0));

        int[] NRs = new int[]{15, 16};
        int randomDo = new Random().nextInt(Manager.itemIds_TL.length);
        int randomNR = new Random().nextInt(NRs.length);
        if (Util.isTrue(5, 10)) {
            if (Util.isTrue(5, 10)) {
                ItemMap it = new ItemMap(this.zone, 937, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), plKill.id);

                Random random = new Random();

                it.options.add(new Item.ItemOption(49, Util.nextInt(40,82)));
                it.options.add(new Item.ItemOption(77, Util.nextInt(40,82)));
                it.options.add(new Item.ItemOption(103, Util.nextInt(40,82)));
                it.options.add(new Item.ItemOption(5, Util.nextInt(5, 20)));

                if (Util.isTrue(99, 100)) {
                    int randomMoth = random.nextInt(30) + 1;
//               randomMoth
                    it.options.add(new Item.ItemOption(93, randomMoth));
                }
                it.options.add(new Item.ItemOption(207, 0));
                it.options.add(new Item.ItemOption(33, 0));
                it.options.add(new Item.ItemOption(209, 1));

                Service.getInstance().dropItemMap(this.zone, it);
                return;
                
            }
            Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.location.y, plKill.id));
        } else if (Util.isTrue(2, 10)) {
            Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, 1079, 2, this.location.x, this.location.y, plKill.id));
            return;
        } else {
            Service.getInstance().dropItemMap(this.zone, new ItemMap(zone, NRs[randomNR], 1, this.location.x, zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
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
