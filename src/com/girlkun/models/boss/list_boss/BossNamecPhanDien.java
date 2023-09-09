/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girlkun.models.boss.list_boss;

import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;
import com.girlkun.models.item.Item;
import com.girlkun.services.ItemService;
import java.util.Random;
import com.girlkun.services.InventoryServiceNew;

/**
 * @@Stole By Arriety
 */
public class BossNamecPhanDien extends Boss {

    public BossNamecPhanDien() throws Exception {
        super(BossID.BossNamecPhanDien, BossesData.BOSS_Namec_Phan_Dien);
    }

    @Override
    public void reward(Player plKill) {
       int[] itemRewardTs = {1048, 1049, 1050, 1051, 1052, 1053, 1054, 1055, 1056, 1057, 1058, 1059, 1060, 1061, 1062};

       Random random = new Random();
       int sd = random.nextInt(5000) + 15000;
       int hp = random.nextInt(200,250);
       int ki = random.nextInt(200,250);
       int sdcm = random.nextInt(10)+ 10;
       int giap = random.nextInt(500)+3000;
       int cm = random.nextInt(10)+30;
       
        int randomIndex = random.nextInt(itemRewardTs.length);
        int selectedNumber = itemRewardTs[randomIndex];
        Item item = ItemService.gI().createNewItem((short)(selectedNumber));
        

        if (selectedNumber >= 1048 && selectedNumber <= 1050) {
             item.itemOptions.add(new Item.ItemOption(47,giap));
            item.itemOptions.add(new Item.ItemOption(5,sdcm));
              item.itemOptions.add(new Item.ItemOption(209,0));
        } else if (selectedNumber >= 1051 && selectedNumber <= 1053) {
             item.itemOptions.add(new Item.ItemOption(22,hp));
            item.itemOptions.add(new Item.ItemOption(5,sdcm));
            item.itemOptions.add(new Item.ItemOption(209,0));
        } else if (selectedNumber >= 1054 && selectedNumber <= 1056) {
               item.itemOptions.add(new Item.ItemOption(0,sd));
               item.itemOptions.add(new Item.ItemOption(5,sdcm));
               item.itemOptions.add(new Item.ItemOption(209,0));
        } else if (selectedNumber >= 1057 && selectedNumber <= 1059) {
             item.itemOptions.add(new Item.ItemOption(23,ki));
              item.itemOptions.add(new Item.ItemOption(5,sdcm));
              item.itemOptions.add(new Item.ItemOption(209,0));
        } else if (selectedNumber >= 1060 && selectedNumber <= 1062) {
            item.itemOptions.add(new Item.ItemOption(14,cm));
              item.itemOptions.add(new Item.ItemOption(5,sdcm));
              item.itemOptions.add(new Item.ItemOption(209,0));
        } 
        
        InventoryServiceNew.gI().addItemBag(plKill, item);
       Service.getInstance().sendThongBao(plKill, "Chúc mừng bạn vừa nhận được vật phẩm săn boss");
    }

    @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
        if (Util.canDoWithTime(st, 2500000)) {
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }

    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }
    private long st;

    @Override
    public double injured(Player plAtt, double damage, boolean piercing, boolean isMobAttack) {
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 1000)) {
                this.chat("Xí hụt");
                return 0;
            }
            damage = this.nPoint.subDameInjureWithDeff(damage);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = 1;
            }
            this.nPoint.subHP(damage);
            if (isDie()) {
                this.setDie(plAtt);
                die(plAtt);
            }
            return damage;
        } else {
            return 0;
        }
    }
}
