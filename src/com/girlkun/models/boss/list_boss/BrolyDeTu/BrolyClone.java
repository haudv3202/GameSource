package com.girlkun.models.boss.list_boss.BrolyDeTu;

import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.player.Player;
import com.girlkun.services.EffectSkillService;
import com.girlkun.utils.Util;


public class BrolyClone extends Boss {

    public BrolyClone() throws Exception {
        super(BossID.BROLYDETU, BossesData.BROLY_CLONE);
    }
    
    @Override
    public void active() {
        super.active();
        if(Util.canDoWithTime(st,300000)){
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }
    
    @Override
    public void joinMap() {
        super.joinMap();
        st= System.currentTimeMillis();
    }
    private long st;
    
        @Override
    public void moveTo(int x, int y) {
        if(this.currentLevel == 1){
            return;
        }
        super.moveTo(x, y);
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
