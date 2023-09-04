package com.girlkun.models.boss.list_boss.BrolyDeTu;

import com.girlkun.consts.ConstPlayer;
import com.girlkun.models.boss.*;
import static com.girlkun.models.boss.BossStatus.ACTIVE;
import static com.girlkun.models.boss.BossStatus.JOIN_MAP;
import static com.girlkun.models.boss.BossStatus.RESPAWN;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.map.challenge.MartialCongressService;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.PetService;
import com.girlkun.services.PlayerService;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;



public class BrolySuper extends Boss {
  
    private long lastUpdate = System.currentTimeMillis();
    private long timeJoinMap;
    protected Player playerAtt;
    private int timeLive = 2000000;
    
    
    public BrolySuper(Zone zone , int dame, long hp,int id) throws Exception {
        super(id, new BossData(
                "Thần vũ Trụ", //name
                ConstPlayer.TRAI_DAT, //gender
                new short[]{98, 99, 100, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
                ((50000 + dame) ), //dame
                new long[]{((400000000 + hp) )}, //hp
                new int[]{29,33,20}, //map join
                new int[][]{
                 {Skill.KAMEJOKO, 7, 500},
                {Skill.ANTOMIC, 7, 1000},
                 {Skill.TAI_TAO_NANG_LUONG, 1, 10000},
                {Skill.KHIEN_NANG_LUONG, 1, 10000}},
            new String[]{
                    "|-1|Gaaaaaa",
                    "|-2|Tới đây đi!"
            }, //text chat 1
            new String[]{"|-1|Các ngươi tới số rồi mới gặp phải ta",
                    "|-1|Gaaaaaa",
                    "|-2|Không ngờ..Hắn mạnh cỡ này sao..!!"
            }, //text chat 2
            new String[]{"|-1|Gaaaaaaaa!!!"}, //text chat 3
                60
        ));
        this.zone = zone;
    }
    @Override
    public void reward(Player plKill) {
               if (plKill.pet == null) {
                    PetService.gI().createNormalPet(plKill);
                    Service.getInstance().sendThongBao(plKill, "Bạn Vừa Nhận được đệ tử! Hãy chăm sóc tốt nhé");
                }else {
                    Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 2050, 1, this.location.x, this.location.y, plKill.id));
                }
    }
    @Override
    public void active() {
     if (this.typePk == ConstPlayer.NON_PK) {
            this.changeToTypePK();
        } 
       try {
            switch (this.bossStatus) {
                case RESPAWN:
                    this.respawn();
                    this.changeStatus(BossStatus.JOIN_MAP);
                case JOIN_MAP:
                    joinMap();
                    if (this.zone != null) {
                        changeStatus(BossStatus.ACTIVE);
                        timeJoinMap = System.currentTimeMillis();
                        this.typePk = 3;
                        MartialCongressService.gI().sendTypePK(playerAtt, this);
                        PlayerService.gI().changeAndSendTypePK(playerAtt, ConstPlayer.PK_PVP);
                        this.changeStatus(BossStatus.ACTIVE);
                    }
                    break;
                case ACTIVE:
                    if (this.playerSkill.prepareTuSat || this.playerSkill.prepareLaze || this.playerSkill.prepareQCKK) {
                        break;
                    } else {
                        this.attack();
                    }
                    break;
            }
            if (Util.canDoWithTime(lastUpdate, 1000)) {
                lastUpdate = System.currentTimeMillis();
                if (timeLive > 0) {
                    timeLive--;
                } else {
                    super.leaveMap();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     @Override
    public double injured(Player plAtt, double damage, boolean piercing, boolean isMobAttack) {
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 1000)) {
                this.chat("Xí hụt");
                return 0;
            }
            damage = this.nPoint.subDameInjureWithDeff(damage/2);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = damage/2;
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


