package frenchforce.item_randomizer;

import java.util.ArrayList;
import java.util.List;

public class Requirement {
	
	
	public static class BasicRequirement extends Requirement {
		
		//Default values.
		public static final BasicRequirement 
		NONE = new BasicRequirement(						0b0000000000000000000000000),
		HAVE_VARIA = new BasicRequirement(					0b0000000000000000000000001),
		HAVE_GRAVITY = new BasicRequirement(				0b0000000000000000000000010),
		HAVE_MORPH = new BasicRequirement(					0b0000000000000000000000100),
		HAVE_BOMBS = new BasicRequirement(					0b0000000000000000000001000),
		HAVE_SPRING_BALL = new BasicRequirement(			0b0000000000000000000010000),
		HAVE_SCREW_ATTACK = new BasicRequirement(			0b0000000000000000000100000),
		HAVE_HIGH_JUMP_BOOTS = new BasicRequirement(		0b0000000000000000001000000),
		HAVE_SPACE_JUMP = new BasicRequirement(				0b0000000000000000010000000),
		HAVE_SPEED_BOOSTER = new BasicRequirement(			0b0000000000000000100000000),
		HAVE_CHARGE_BEAM = new BasicRequirement(			0b0000000000000001000000000),
		HAVE_ICE_BEAM = new BasicRequirement(				0b0000000000000010000000000),
		HAVE_WAVE_BEAM = new BasicRequirement(				0b0000000000000100000000000),
		HAVE_SPAZER_BEAM = new BasicRequirement(			0b0000000000001000000000000),
		HAVE_PLASMA_BEAM = new BasicRequirement(			0b0000000000010000000000000),
		HAVE_GRAPPLING = new BasicRequirement(				0b0000000000100000000000000),
		HAVE_MORE_THAN_300PV = new BasicRequirement(		0b0000000001000000000000000),
		HAVE_MORE_THAN_400PV = new BasicRequirement(		0b0000000010000000000000000),
		HAVE_MORE_THAN_600PV = new BasicRequirement(		0b0000000100000000000000000),
		HAVE_MORE_THAN_900PV = new BasicRequirement(		0b0000001000000000000000000),
		HAVE_MISSILES = new BasicRequirement(				0b0000010000000000000000000),
		HAVE_10_MISSILES = new BasicRequirement(			0b0000110000000000000000000),
		HAVE_SUPERS = new BasicRequirement(					0b0001000000000000000000000),
		HAVE_10_SUPERS = new BasicRequirement(				0b0011000000000000000000000),
		HAVE_PB = new BasicRequirement(						0b0100000000000000000000000),
		HAVE_10_PB = new BasicRequirement(					0b1100000000000000000000000);
		
		private BasicRequirement(int rq) {
			super(rq);
		}
		
		public int getValue() {
			return this.getValues().get(0).intValue();
		}
		
	}
	
	public static Requirement CAN_USE_PB,CAN_BREAK_BOMBS_BLOCKS,CAN_GO_TO_RED_TOWER,CAN_GO_TO_BRINSTAR,CAN_GO_TO_MARIDIA,CAN_GO_TO_WS,CAN_OPEN_RED_DOORS,CAN_SLO_MARIDIA,CAN_SLI_MARIDIA,CAN_DEFEAT_BOTWOON,CAN_COMPLETE_NORFAIR,CAN_GO_TO_LN;
	
	public static final void init() {
	CAN_USE_PB = Requirement.andMode(BasicRequirement.HAVE_MORPH,BasicRequirement.HAVE_PB);
	CAN_BREAK_BOMBS_BLOCKS = Requirement.andMode(BasicRequirement.HAVE_MORPH,new Requirement(BasicRequirement.HAVE_BOMBS,BasicRequirement.HAVE_PB));
	CAN_GO_TO_RED_TOWER = Requirement.andMode(BasicRequirement.HAVE_MORPH,BasicRequirement.HAVE_SUPERS,new Requirement(BasicRequirement.HAVE_PB,BasicRequirement.HAVE_BOMBS,BasicRequirement.HAVE_SCREW_ATTACK,BasicRequirement.HAVE_SPEED_BOOSTER));
	CAN_GO_TO_BRINSTAR = new Requirement(Requirement.CAN_BREAK_BOMBS_BLOCKS,BasicRequirement.HAVE_SCREW_ATTACK,BasicRequirement.HAVE_SPEED_BOOSTER,Requirement.CAN_GO_TO_RED_TOWER);
	CAN_GO_TO_MARIDIA = Requirement.andMode(Requirement.CAN_GO_TO_RED_TOWER,BasicRequirement.HAVE_PB);
	CAN_GO_TO_WS = new Requirement(andMode(CAN_GO_TO_RED_TOWER,BasicRequirement.HAVE_PB));
	CAN_OPEN_RED_DOORS = new Requirement(BasicRequirement.HAVE_SUPERS,BasicRequirement.HAVE_MISSILES);
	CAN_SLO_MARIDIA = Requirement.andMode(Requirement.CAN_GO_TO_MARIDIA,new Requirement( BasicRequirement.HAVE_GRAVITY,andMode(Requirement.CAN_GO_TO_MARIDIA,BasicRequirement.HAVE_HIGH_JUMP_BOOTS,new Requirement(BasicRequirement.HAVE_ICE_BEAM,BasicRequirement.HAVE_SPRING_BALL))));
	CAN_SLI_MARIDIA = Requirement.andMode(Requirement.CAN_GO_TO_MARIDIA,new Requirement( BasicRequirement.HAVE_GRAVITY,andMode(Requirement.CAN_GO_TO_MARIDIA,BasicRequirement.HAVE_GRAPPLING,BasicRequirement.HAVE_HIGH_JUMP_BOOTS, new Requirement(BasicRequirement.HAVE_ICE_BEAM,BasicRequirement.HAVE_SPRING_BALL))));
	CAN_DEFEAT_BOTWOON = Requirement.andMode(Requirement.CAN_GO_TO_MARIDIA,new Requirement(BasicRequirement.HAVE_CHARGE_BEAM,BasicRequirement.HAVE_10_SUPERS),new Requirement(BasicRequirement.HAVE_GRAVITY,Requirement.CAN_SLI_MARIDIA));
	CAN_COMPLETE_NORFAIR = Requirement.andMode(
			Requirement.CAN_GO_TO_RED_TOWER,
			new Requirement(BasicRequirement.HAVE_VARIA,BasicRequirement.HAVE_MORE_THAN_600PV),
			new Requirement(BasicRequirement.HAVE_HIGH_JUMP_BOOTS,BasicRequirement.HAVE_SPEED_BOOSTER));
	CAN_GO_TO_LN = Requirement.andMode(Requirement.CAN_GO_TO_RED_TOWER,BasicRequirement.HAVE_PB,BasicRequirement.HAVE_VARIA, new Requirement(BasicRequirement.HAVE_GRAVITY,andMode(BasicRequirement.HAVE_HIGH_JUMP_BOOTS,BasicRequirement.HAVE_MORE_THAN_300PV)));
	}
	
	private List<Integer> rqs = new ArrayList<Integer>();
	
	protected Requirement(int rq) {
		this.rqs.add(rq);
	}
	
	protected Requirement(List<Integer> rqs) {
		this.rqs = rqs;
	}
	
	public Requirement(Requirement... requirements) {
		for (Requirement r : requirements) {
			for (Integer t : r.getValues()) this.rqs.add(t);
		}
	}
	
	public List<Integer> getValues() {
		return this.rqs;
	}
	
	public boolean requires(Requirement r,int actual) {
		for (int a : r.getValues()) {
			if ((a&actual) == a) return true; 
		}
		return false;
	}
	
	/*public static Requirement andMode(Requirement... rqs) {
		List<Integer> rtn = new ArrayList<Integer>();
		List<Integer> local = new ArrayList<Integer>();
		List<Integer> first = new ArrayList<Integer>();
		for (Requirement rq : rqs) {
			if (rtn.isEmpty()) { //Premier ou deuxieme
				if (first.isEmpty()) { //Le premier
					first.addAll(rq.getValues());
				} else {
					for (int a : rq.getValues()) {
						for (int b : first) {
							rtn.add(a|b);
						}
					}
					continue;
				}
			}
			local.clear();
			for (int a : rq.getValues()) {
				for (int b : rtn) {
					local.add(a|b);
				}
			}
			rtn.addAll(local);
		}
		return new Requirement(rtn);
	}*/
	
	public static Requirement andMode(Requirement... rqs) {
		List<Integer> rtn = new ArrayList<Integer>();
		Requirement r = null;
		for (int i = 0; i<rqs.length;i++) {
			if (i == 0) r = rqs[0];
			if (i == 1) {
				for (int a : r.getValues()) {
					for (int b : rqs[1].getValues()) {
						rtn.add(a|b);
					}
				}
			}
			if (i >= 2) {
				List<Integer> to_add = new ArrayList<Integer>();
				for (int a : rqs[i].getValues()) {
					for (int b : rtn) {
						to_add.add(a|b);
					}
				}
				rtn.addAll(to_add);
			}
		}
		return new Requirement(rtn);
	}
	
	public static boolean hasRequired(int have,Requirement rq) {
		for (int b : rq.getValues()) {
			//System.out.println(Integer.toBinaryString(have) + " - " + Integer.toBinaryString(b));
			if (rq.equals(BasicRequirement.NONE)) return true;
			if ((b & have) == b) return true;
		}
		return false;
	}
}
