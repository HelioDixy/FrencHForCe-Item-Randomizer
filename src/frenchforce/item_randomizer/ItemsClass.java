package frenchforce.item_randomizer;



import frenchforce.item_randomizer.Requirement.BasicRequirement;

/**
 * Every item is here, in enumerations.
 * 
 * @author HélioDixy, Di10kong
 *
 */
public class ItemsClass {
	
	public static enum Items {
		MISSILES(0xeedb,0xef2f,0xef83), 
		SUPERS(0xeedf,0xef33,0xef87), 
		PB(0xeee3,0xef37,0xef8b), 
		ENERGY_TANK(0xeed7,0xEF2B,0xef7f), //14 in the game
		RESERVE_TANK(0xef27,0xef7b,0xefcf), //4 in the game
		//Miscellaneous
		MORPH_BALL(0xef23,0xef77,0xefcb),
		BOMBS(0xeee7,0xef3b,0xef8f),
		SPRING_BALL(0xef03,0xef57,0xefab),
		SCREW_ATTACK(0xef1f,0xef73,0xefc7),
		//Suits
		VARIA(0xef07,0xef5b,0xefaf),
		GRAVITY(0xef0b,0xef5f,0xefb3),
		//Beams
		CHARGE(0xeeeb,0xef3f,0xef93),
		ICE(0xeeef,0xef43,0xef97),
		WAVE(0xeefb,0xef4f,0xefa3),
		SPAZER(0xeeff,0xef53,0xefa7),
		PLASMA(0xef13,0xef67,0xefbb),
		//Boots
		HJB(0xeef3,0xef47,0xef9b),
		SPACE_JUMP(0xef1b,0xef6f,0xefc3),
		SPEED_BOOSTER(0xeef7,0xef4b,0xef9f),
		//Other -- HUD Items
		GRAPPLING_BEAM(0xef17,0xef6b,0xefbf),
		XRAY(0xef0f,0xef63,0xefb7);
		
		private int value,chozo,hidden;
		private Items(int value,int chozo,int hidden) {
			this.value = value;
			this.chozo = chozo;
			this.hidden = hidden;
		}
		
		public int getValue(Visibility v) {
			switch (v) {
			case VISIBLE:
				return value;
			case CHOZO_BALL:
				return chozo;
			case HIDDEN:
				return hidden;
			default:
				return value;
			}
			
		}
	}
	
	public static enum Visibility {
		VISIBLE,HIDDEN,CHOZO_BALL;
	}
	
	public static enum Locations {

    /*  Ensemble des items de crateria
     * 
     * */  
     
		MISSILE_CRATERIA_A(
      0x78248,
      Requirement.andMode(BasicRequirement.HAVE_SUPERS,BasicRequirement.HAVE_PB,BasicRequirement.HAVE_MORPH),
      Visibility.VISIBLE,
      "Crateria moat Missile"
    ),
        
		MISSILE_CRATERIA_B(
      0x783EE,
      Requirement.CAN_BREAK_BOMBS_BLOCKS,
      Visibility.VISIBLE,
      "Crateria missile bottom"
    ),
        
		MISSILE_CRATERIA_C(
      0x781F4,
      Requirement.CAN_GO_TO_WS,
      Visibility.VISIBLE,
      "Crateria, outside wrecked ship missile"
    ),
    
		MISSILE_CRATERIA_D(
      0x781EE,
      Requirement.CAN_GO_TO_WS,
      Visibility.HIDDEN,
      "Crateria - Outside Wrecked Ship top missile"
    ),
    
		MISSILE_CRATERIA_E(
      0x781E8,
      Requirement.CAN_GO_TO_WS,
      Visibility.VISIBLE,
      "Crateria - Outside Wrecked Ship bottom missile"
    ),
    
		MISSILE_CRATERIA_F(
      0x78464,
      Requirement.andMode(BasicRequirement.HAVE_MORPH,new Requirement(BasicRequirement.HAVE_10_PB,BasicRequirement.HAVE_BOMBS,Requirement.andMode(BasicRequirement.HAVE_SPEED_BOOSTER,BasicRequirement.HAVE_MORE_THAN_300PV),BasicRequirement.HAVE_SCREW_ATTACK)),
      Visibility.VISIBLE,
      "Crateria - Left Gauntlet right missile"
    ),
    
		MISSILE_CRATERIA_G(
      0x7846A,
      MISSILE_CRATERIA_F.getRequirement(),
      Visibility.VISIBLE,
      "Crateria - Left Gauntlet left missile"
    ),
    
		MISSILE_CRATERIA_H(
      0x78486,
      Requirement.CAN_BREAK_BOMBS_BLOCKS,
      Visibility.VISIBLE,
      "Crateria - Middle missile"
    ),
    
		SUPER_CRATERIA(
      0x78478,
      new Requirement(Requirement.andMode(BasicRequirement.HAVE_MORPH,BasicRequirement.HAVE_PB,BasicRequirement.HAVE_SPEED_BOOSTER,new Requirement(BasicRequirement.HAVE_MORE_THAN_300PV,BasicRequirement.HAVE_VARIA,BasicRequirement.HAVE_GRAVITY))),
      Visibility.VISIBLE,
      "Crateria - Super missiles"
    ),
    
		PB_CRATERIA(
      0x781CC,
      Requirement.andMode(BasicRequirement.HAVE_MORPH,BasicRequirement.HAVE_PB,new Requirement(BasicRequirement.HAVE_BOMBS,BasicRequirement.HAVE_SPACE_JUMP,BasicRequirement.HAVE_SPEED_BOOSTER)),
      Visibility.VISIBLE,
      "Crateria - Right gauntlet Power bombs"
    ),
    
		ENERGY_TANK_CRATERIA_A(
      0x78264,
      new Requirement(BasicRequirement.HAVE_SCREW_ATTACK,Requirement.andMode(BasicRequirement.HAVE_MORPH,new Requirement(BasicRequirement.HAVE_BOMBS,BasicRequirement.HAVE_10_PB))),
      Visibility.VISIBLE,
      "Crateria - Energy tank gauntlet"
    ),
    
		ENERGY_TANK_CRATERIA_B(
      0x78432,
      Requirement.CAN_BREAK_BOMBS_BLOCKS,
      Visibility.VISIBLE,
      "Crateria - Energy tank Terminator"
    ),
    
		BOMBS(
      0x78404,
      Requirement.andMode(Requirement.CAN_OPEN_RED_DOORS,BasicRequirement.HAVE_MORPH),
      Visibility.CHOZO_BALL,
      "Crateria - Bombs"
    ),
		
    /*  Ensemble des items du wrecked ship
     * 
     * */
		MISSILE_WS_A(
      0x7C265,
      Requirement.CAN_GO_TO_WS,
      Visibility.VISIBLE,
      "Wrecked Ship - Middle Missile"
    ),
    
		MISSILE_WS_B(
      0x7C319,
      Requirement.CAN_GO_TO_WS,
      Visibility.VISIBLE,
      "Wrecked Ship - Top Missile"
    ),
    
		MISSILE_WS_C(
      0x7C2EF,
      Requirement.CAN_GO_TO_WS,
      Visibility.VISIBLE,
      "Wrecked Ship - Gravity Missile"
    ),
    
		SUPER_WS_A(
      0x7C357,
      Requirement.CAN_GO_TO_WS,
      Visibility.VISIBLE,
      "Wrecked Ship - Left Super Missile"
    ),
    
		SUPER_WS_B(
      0x7C365,
      Requirement.CAN_GO_TO_WS,Visibility.VISIBLE,
      "Wrecked Ship - Right Super Missile"
    ),
    
		ENERGY_TANK_WS(
      0x7C337,
      Requirement.andMode(Requirement.CAN_GO_TO_WS,new Requirement(BasicRequirement.HAVE_SPEED_BOOSTER,BasicRequirement.HAVE_GRAVITY,BasicRequirement.HAVE_BOMBS,BasicRequirement.HAVE_SPRING_BALL,BasicRequirement.HAVE_SPACE_JUMP,BasicRequirement.HAVE_HIGH_JUMP_BOOTS)),
      Visibility.VISIBLE,
      "Wrecked Ship - Energy Tank"
    ), 
    
		RESERVE_TANK_WS(
      0x7C2E9,
      Requirement.andMode(Requirement.CAN_GO_TO_WS,BasicRequirement.HAVE_SPEED_BOOSTER),
      Visibility.CHOZO_BALL,
      "Wrecked Ship - Reserve Tank"
    ),
    
		GRAVITY_SUIT(
      0x7C36D,
      Requirement.CAN_GO_TO_WS,
      Visibility.CHOZO_BALL,
      "Wrecked Ship - Gravity Suit"
    ),
		
     /*  Ensemble des items de Brinstar
     * 
     * */
                  
		MISSILES_BLUE_BRINSTAR_A(
      0x78802,
      BasicRequirement.HAVE_MORPH,
      Visibility.CHOZO_BALL,
      "Missiles Blue Brinstar - Bottom"
    ),
        
		MISSILES_BLUE_BRINSTAR_B(
      0x78798,
      Requirement.andMode(BasicRequirement.HAVE_MORPH,Requirement.CAN_OPEN_RED_DOORS),
      Visibility.VISIBLE,
      "Missiles Blue Brinstar - middle"
    ), 
		
    MISSILES_BLUE_BRINSTAR_C(
      0x78836,
      Requirement.andMode(BasicRequirement.HAVE_PB,BasicRequirement.HAVE_MORPH),
      Visibility.VISIBLE,
      "Missiles Blue Brinstar - Top"
    ),
        
		MISSILES_BLUE_BRINSTAR_D(
      0x7883C,
      Requirement.andMode(BasicRequirement.HAVE_PB,BasicRequirement.HAVE_MORPH),
      Visibility.HIDDEN,
      "Missies Blue Brinstar - Behind top missile"
    ),
    
    
		MISSILE_BRINSTAR_A(
      0x78518,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,Requirement.CAN_BREAK_BOMBS_BLOCKS),
      Visibility.VISIBLE,
      "Brinstar - Early Supers bottom missile"
    ),
    
		MISSILE_BRINSTAR_B(
      0x78538,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,Requirement.CAN_OPEN_RED_DOORS,BasicRequirement.HAVE_MORPH),
      Visibility.VISIBLE,
      "Brinstar - Behind Reserve Tank"
    ),
		
    MISSILE_BRINSTAR_C(
      0x78532,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,Requirement.CAN_OPEN_RED_DOORS,BasicRequirement.HAVE_MORPH,Requirement.CAN_BREAK_BOMBS_BLOCKS),
      Visibility.HIDDEN,
      "Brinstar - Behind missile (Reserve tank)"
    ),
		
    MISSILE_BRINSTAR_D(
      0x78608,
      new Requirement(Requirement.CAN_GO_TO_RED_TOWER,Requirement.andMode(Requirement.CAN_OPEN_RED_DOORS,Requirement.CAN_GO_TO_BRINSTAR,new Requirement(Requirement.CAN_BREAK_BOMBS_BLOCKS,BasicRequirement.HAVE_SCREW_ATTACK,BasicRequirement.HAVE_SPEED_BOOSTER))),
      Visibility.VISIBLE,
      "Brinstar - Pink brinstar top missile"
    ),
    
		MISSILE_BRINSTAR_E(
      0x7860E,
      MISSILE_BRINSTAR_D.getRequirement(),
      Visibility.VISIBLE,
      "Brinstar - Pink brinstar bottom missile"
    ),
    
		MISSILE_BRINSTAR_F(
      0x78676,
      Requirement.andMode(Requirement.CAN_GO_TO_RED_TOWER,BasicRequirement.HAVE_MORPH),
      Visibility.VISIBLE,
      "Brinstar - Missile pipe"
    ),
    
		MISSILE_BRINSTAR_G(
      0x78914,
      new Requirement(Requirement.CAN_GO_TO_RED_TOWER, Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,BasicRequirement.HAVE_PB,BasicRequirement.HAVE_SUPERS)),
      Visibility.VISIBLE,
      "Red Tower - Behind Power bombs"
    ),
    
		MISSILE_BRINSTAR_H(
      0x789EC,
      new Requirement(Requirement.CAN_GO_TO_RED_TOWER, Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,BasicRequirement.HAVE_PB,BasicRequirement.HAVE_SUPERS)),
      Visibility.HIDDEN,
      "Kraid Zone - Hidden missile"
    ),

     
		SUPER_BRINSTAR_A(
      0x784E4,
      new Requirement(Requirement.andMode(Requirement.CAN_OPEN_RED_DOORS,Requirement.CAN_GO_TO_BRINSTAR,Requirement.CAN_BREAK_BOMBS_BLOCKS,BasicRequirement.HAVE_SUPERS),Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,Requirement.CAN_OPEN_RED_DOORS,BasicRequirement.HAVE_MORPH)),
      Visibility.CHOZO_BALL,
      "Brinstar - Spore Spawn Super Missile"
    ),
    
		SUPER_BRINSTAR_B(
      0x7851E,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,new Requirement(BasicRequirement.HAVE_MORPH,BasicRequirement.HAVE_SPEED_BOOSTER)),
      Visibility.VISIBLE,
      "Brinstar - Early Super"
    ),
    
		SUPER_BRINSTAR_C(
      0x787D0,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,BasicRequirement.HAVE_PB,BasicRequirement.HAVE_SUPERS),
      Visibility.VISIBLE,
      "Brinstar - Etecoons Super"
    ),
    
    PB_BLUE_BRINSTAR_A(
      0x7879E,
      Requirement.andMode(BasicRequirement.HAVE_PB,BasicRequirement.HAVE_MORPH),
      Visibility.VISIBLE,
      "Power bombs - Blue Brinstar"
    ),  
      
    PB_BRINSTAR_A(
      0x784AC,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,BasicRequirement.HAVE_PB),
      Visibility.CHOZO_BALL,
      "Brinstar - Etecoons PB"
    ),
    
		PB_BRINSTAR_B(
      0x7865C,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,BasicRequirement.HAVE_PB,Requirement.CAN_OPEN_RED_DOORS),
      Visibility.VISIBLE,
      "Brinstar - Pink brinstar PB"
    ),
    
		PB_BRINSTAR_C(
      0x788CA,
      Requirement.andMode(Requirement.CAN_GO_TO_RED_TOWER,BasicRequirement.HAVE_SUPERS,BasicRequirement.HAVE_PB),
      Visibility.VISIBLE,
      "Red Tower - Sidehopper room PB"
    ),
    
		PB_BRINSTAR_D(
      0x7890E,
      Requirement.andMode(Requirement.CAN_GO_TO_RED_TOWER,BasicRequirement.HAVE_SUPERS,BasicRequirement.HAVE_PB),
      Visibility.CHOZO_BALL,
      "Red Tower - Power bombs"
    ),
		          
		ENERGY_TANK_BLUE_BRINSTAR(
      0x7879E,
      BasicRequirement.CAN_OPEN_RED_DOORS,
      Visibility.HIDDEN,
      "Energy tank - Blue brinstar ceiling"
    ), 
    
    ENERGY_TANK_BRINSTAR_A(
      0x787C2,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,BasicRequirement.HAVE_PB),
      Visibility.VISIBLE,
      "Brinstar - Etecoons e tank"
    ),
    
		ENERGY_TANK_BRINSTAR_B(
      0x787FA,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,BasicRequirement.HAVE_SPEED_BOOSTER,BasicRequirement.HAVE_PB,BasicRequirement.CAN_OPEN_RED_DOORS),
      Visibility.VISIBLE,
      "Brinstar - Waterway"
    ),
		
    ENERGY_TANK_BRINSTAR_C(
      0x78824,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,BasicRequirement.HAVE_PB,new Requirement(BasicRequirement.HAVE_WAVE_BEAM,BasicRequirement.HAVE_SUPERS)),
      Visibility.VISIBLE,
      "Brinstar - Pink brinstar gate Energy Tank"
    ),
    
		ENERGY_TANK_BRINSTAR_D(
      0x7899C,
      Requirement.CAN_GO_TO_RED_TOWER,
      Visibility.HIDDEN,
      "Brinstar - Kraid E tank"
    ),
		
    RESERVE_TANK_BRINSTAR(
      0x7852C,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,Requirement.CAN_OPEN_RED_DOORS,new Requirement(BasicRequirement.HAVE_MORPH,BasicRequirement.HAVE_SPEED_BOOSTER)),
      Visibility.CHOZO_BALL,
      "Brinstar - Reserve tank"
    ),
         
    MORPHING_BALL(
      0x786DE,
      BasicRequirement.NONE,
      Visibility.VISIBLE,
      "Morphing ball"
    ),
    
    CHARGE_BEAM(
      0x78614,
      Requirement.andMode(Requirement.CAN_GO_TO_BRINSTAR,Requirement.CAN_OPEN_RED_DOORS,Requirement.CAN_BREAK_BOMBS_BLOCKS),
      Visibility.CHOZO_BALL,
      "Charge beam"
    ),
    
		SPAZER(
      0x7896E,
      Requirement.andMode(Requirement.CAN_GO_TO_RED_TOWER,Requirement.CAN_BREAK_BOMBS_BLOCKS),
      Visibility.CHOZO_BALL,
      "Spazer"
    ),
    
		XRAY(
      0x78876,
      Requirement.andMode(Requirement.CAN_GO_TO_RED_TOWER,BasicRequirement.HAVE_PB,new Requirement(BasicRequirement.HAVE_SPACE_JUMP,BasicRequirement.HAVE_GRAPPLING)),
      Visibility.CHOZO_BALL,
      "Xray"
    ), 
    
		VARIA_SUIT(
      0x78ACA,
      Requirement.CAN_GO_TO_RED_TOWER,
      Visibility.CHOZO_BALL,
      "Brinstar - Varia Suit"
    ),

    /*  Ensemble des items de maridia
    * 
    * */
		MISSILE_MARIDIA_A(
      0x7C437,
      Requirement.andMode(Requirement.CAN_GO_TO_MARIDIA,BasicRequirement.HAVE_SPEED_BOOSTER,BasicRequirement.HAVE_GRAVITY),
      Visibility.VISIBLE,
      "Maridia - Green maridia shinespark missile"
    ),
    
		MISSILE_MARIDIA_B(
      0x7c483,
      Requirement.andMode(Requirement.CAN_GO_TO_MARIDIA,Requirement.CAN_OPEN_RED_DOORS,new Requirement(BasicRequirement.HAVE_GRAVITY,Requirement.CAN_SLO_MARIDIA)),
      Visibility.HIDDEN,
      "Maridia - Mama turtle missile"
    ),

		MISSILE_MARIDIA_C(
      0x7C4B5,
      Requirement.andMode(Requirement.CAN_GO_TO_MARIDIA,new Requirement(Requirement.CAN_SLI_MARIDIA,BasicRequirement.HAVE_GRAVITY)),
      Visibility.VISIBLE,
      "Maridia - Yellow maridia missile"
    ),
    
		MISSILE_MARIDIA_D(
      0x7C533,
      MISSILE_MARIDIA_C.getRequirement(),
      Visibility.VISIBLE,
      "Maridia - Fake wall missile"
    ),
    
		MISSILE_MARIDIA_E(
      0x7C5DD,
      Requirement.andMode(Requirement.CAN_GO_TO_MARIDIA,BasicRequirement.HAVE_SUPERS,new Requirement(BasicRequirement.HAVE_GRAVITY,Requirement.CAN_SLI_MARIDIA)),
      Visibility.CHOZO_BALL,
      "Maridia - Left sand pit missile"
    ),
    
		MISSILE_MARIDIA_F(
      0x7C5EB,
      MISSILE_MARIDIA_E.getRequirement(),
      Visibility.VISIBLE,
      "Maridia - Right sand pit missile"
    ),
    
		MISSILE_MARIDIA_G(
      0x7C74D,
      Requirement.CAN_DEFEAT_BOTWOON,
      Visibility.HIDDEN,
      "Maridia - Draygon missile"
    ),
    
		MISSILE_MARIDIA_H(
      0x7C603,
      Requirement.andMode(Requirement.CAN_GO_TO_MARIDIA,BasicRequirement.HAVE_GRAVITY,BasicRequirement.HAVE_SPEED_BOOSTER),
      Visibility.VISIBLE,
      "Maridia - Pink maridia missile"
    ),
    
		SUPER_MARIDIA_A(
      0x7C43D,
      Requirement.CAN_SLO_MARIDIA,
      Visibility.VISIBLE,
      "Maridia - Green maridia super"
    ),
    
		SUPER_MARIDIA_B(
      0x7C4AF,
      Requirement.CAN_SLI_MARIDIA,
      Visibility.VISIBLE,
      "Maridia - Yellow Maridia super Missile"
    ),
    
		SUPER_MARIDIA_C(
      0x7C609,
      Requirement.andMode(Requirement.CAN_GO_TO_MARIDIA,BasicRequirement.HAVE_GRAVITY,BasicRequirement.HAVE_SPEED_BOOSTER),
      Visibility.VISIBLE,
      "Maridia - Pink Maridia Super Missile"
    ),
    
		PB_MARIDIA_A(
      0x7C5F1,
      Requirement.CAN_SLI_MARIDIA,
      Visibility.VISIBLE,
      "Maridia - Right sand pit Power Bomb"
    ),
    
		ENERGY_TANK_MARIDIA_A(
      0x7C47D,
      Requirement.andMode(Requirement.CAN_GO_TO_MARIDIA,Requirement.CAN_SLO_MARIDIA),
      Visibility.VISIBLE,
      "Maridia - Mama turtle eTank"
    ),
    
		ENERGY_TANK_MARIDIA_B(
      0x7C755,
      Requirement.CAN_DEFEAT_BOTWOON,
      Visibility.VISIBLE,
      "Maridia - Botwoon e-tank"
    ),
    
		RESERVE_TANK_MARIDIA(
      0x7C5E3,
      Requirement.CAN_SLI_MARIDIA,
      Visibility.CHOZO_BALL,
      "Maridia - Reserve Tank"
    ),
    
		PLASMA_BEAM(
      0x7C559,
      Requirement.andMode(Requirement.CAN_SLI_MARIDIA,new Requirement(BasicRequirement.HAVE_CHARGE_BEAM,BasicRequirement.HAVE_SPEED_BOOSTER,BasicRequirement.HAVE_SCREW_ATTACK,BasicRequirement.HAVE_PLASMA_BEAM)),
      Visibility.CHOZO_BALL,
      "Maridia - Plasma beam"
    ),
    
		SPACE_JUMP(
      0x7C7A7,
      Requirement.andMode(Requirement.CAN_GO_TO_MARIDIA,Requirement.CAN_SLI_MARIDIA,new Requirement(BasicRequirement.HAVE_CHARGE_BEAM,BasicRequirement.HAVE_SPEED_BOOSTER,BasicRequirement.HAVE_GRAPPLING)),
      Visibility.CHOZO_BALL,
      "Maridia - Space jump"
    ),
    
		SPRING_BALL(
      0x7C6E5,
      Requirement.andMode(Requirement.CAN_SLO_MARIDIA,new Requirement(BasicRequirement.HAVE_GRAVITY,Requirement.andMode(BasicRequirement.HAVE_HIGH_JUMP_BOOTS,BasicRequirement.HAVE_SPRING_BALL,BasicRequirement.HAVE_ICE_BEAM),BasicRequirement.HAVE_ICE_BEAM,BasicRequirement.HAVE_GRAPPLING)),
      Visibility.CHOZO_BALL,
      "Maridia - Spring ball"
    ),
		
    /*  Ensemble des items de maridia
    * 
    * */
		MISSILE_NORFAIR_A(
      0x78AE4,
      Requirement.CAN_COMPLETE_NORFAIR,
      Visibility.HIDDEN,
      "Norfair - Lava room missile"
    ),
    
		MISSILE_NORFAIR_B(
      0x78B46,
      Requirement.andMode(BasicRequirement.HAVE_PB,new Requirement(BasicRequirement.HAVE_VARIA,BasicRequirement.HAVE_MORE_THAN_300PV)),
      Visibility.HIDDEN,
      "Norfair - Below ice beam missile"
    ),
    
		MISSILE_NORFAIR_C(
      0x78BC0,
      Requirement.andMode(Requirement.CAN_COMPLETE_NORFAIR,new Requirement(Requirement.andMode(BasicRequirement.HAVE_HIGH_JUMP_BOOTS,BasicRequirement.HAVE_SPEED_BOOSTER),BasicRequirement.HAVE_SPACE_JUMP,BasicRequirement.HAVE_BOMBS,Requirement.andMode(BasicRequirement.HAVE_SPRING_BALL,BasicRequirement.HAVE_HIGH_JUMP_BOOTS),BasicRequirement.HAVE_GRAPPLING)),
      Visibility.VISIBLE,
      "Norfair - Above Crocomire Missile"
    ),
    
		MISSILE_NORFAIR_D(
      0x78Be6,
      Requirement.andMode(Requirement.CAN_GO_TO_RED_TOWER,Requirement.CAN_BREAK_BOMBS_BLOCKS),
      Visibility.VISIBLE,
      "Norfair - HJB Missile"
    ),
    
		MISSILE_NORFAIR_E(
      0x78C14,
      Requirement.CAN_COMPLETE_NORFAIR,
      Visibility.VISIBLE,
      "Norfair - Below Crocomire missile"
    ),
    
		MISSILE_NOFAIR_F(
      0x78C2A,
      Requirement.andMode(Requirement.CAN_COMPLETE_NORFAIR,new Requirement(BasicRequirement.HAVE_BOMBS,BasicRequirement.HAVE_SPACE_JUMP,BasicRequirement.HAVE_SPEED_BOOSTER)),
      Visibility.VISIBLE,
      "Norfair - Grappling beam missile"
    ),
    
		MISSILE_NORFAIR_G(
      0x78C3E,
      Requirement.CAN_COMPLETE_NORFAIR,
      Visibility.HIDDEN,
      "Norfair - Reserve tank missile"
    ),
    
		MISSILE_NORFAIR_H(
      0x78C52,
      Requirement.CAN_COMPLETE_NORFAIR,
      Visibility.VISIBLE,
      "Norfair - Bubble mountain green door missile"
    ),
    
		MISSILE_NORFAIR_I(
      0x78C66,
      Requirement.CAN_COMPLETE_NORFAIR,
      Visibility.VISIBLE,
      "Norfair - Bubble mountain missile"
    ),
    
		MISSILE_NORFAIR_J(
      0x78C74,
      Requirement.CAN_COMPLETE_NORFAIR,
      Visibility.HIDDEN,
      "Norfair - Speed booster missile"
    ),
    
		MISSILE_NORFAIR_K(
      0x78CBC,
      Requirement.CAN_COMPLETE_NORFAIR,
      Visibility.VISIBLE,
      "Norfair - Wave beam missile"
    ),
    
		PB_NORFAIR_A(
      0x78C04,
      Requirement.andMode(Requirement.CAN_COMPLETE_NORFAIR,new Requirement(BasicRequirement.HAVE_SPACE_JUMP,BasicRequirement.HAVE_HIGH_JUMP_BOOTS,BasicRequirement.HAVE_SPEED_BOOSTER,BasicRequirement.HAVE_BOMBS)),
      Visibility.VISIBLE,
      "Norfair - Crocomire Power bomb"
    ),
    
		ENERGY_TANK_NORFAIR_A(
      0x78BA4,
      Requirement.CAN_COMPLETE_NORFAIR,
      Visibility.VISIBLE,
      "Norfair - Crocomire Energy Tank"
    ),
    
		ENERGY_TANK_NORFAIR_B(
      0x78BEC,
      Requirement.CAN_GO_TO_RED_TOWER,
      Visibility.VISIBLE,
      "Norfair - High Jump Boots Energy Tank"
    ),
    
		RESERVE_TANK_NORFAIR(
      0x78C3E,
      Requirement.CAN_COMPLETE_NORFAIR,
      Visibility.CHOZO_BALL,
      "Norfair - Reserve Tank"
    ),
    
		HIGH_JUMP_BOOTS(
      0x78BAC,
      Requirement.andMode(Requirement.CAN_COMPLETE_NORFAIR,Requirement.CAN_BREAK_BOMBS_BLOCKS)
      ,Visibility.CHOZO_BALL,
      "Norfair - High Jump Boots"
    ),
    
		ICE_BEAM(
      0x78B24,
      Requirement.andMode(Requirement.CAN_GO_TO_RED_TOWER,BasicRequirement.HAVE_MORE_THAN_300PV),
      Visibility.VISIBLE,
      "Norfair - Ice beam"
    ),
    
		SPEED_BOOSTER(
      0x78C82,
      Requirement.CAN_COMPLETE_NORFAIR,
      Visibility.CHOZO_BALL,
      "Norfair - Speed booster"
    ),
    
		WAVE_BEAM(
      0x78CCA,
      Requirement.CAN_COMPLETE_NORFAIR,
      Visibility.CHOZO_BALL,
      "Norfair - Wave beam"
    ),
    
		GRAPPLING_BEAM(
      0x78C36,
      Requirement.andMode(Requirement.CAN_COMPLETE_NORFAIR,new Requirement(Requirement.andMode(BasicRequirement.HAVE_SPEED_BOOSTER,BasicRequirement.HAVE_PB),BasicRequirement.HAVE_SPACE_JUMP,BasicRequirement.HAVE_BOMBS)),
      Visibility.CHOZO_BALL,
      "Norfair - Grappling beam"
    ),
		
    /*  Ensemble des items de maridia
    * 
    * */    
		MISSILE_LN_A(
      0x78E6E,
      Requirement.andMode(Requirement.CAN_GO_TO_LN,BasicRequirement.HAVE_SPACE_JUMP), //Ou tu t'appelle TAS et tu cf clip Kappa
      Visibility.VISIBLE,
      "Lower Norfair - Golden Torizo Missile"
    ),
    
		MISSILE_LN_B(
      0x78F30,
      Requirement.andMode(Requirement.CAN_GO_TO_LN,new Requirement(BasicRequirement.HAVE_SCREW_ATTACK,Requirement.andMode(BasicRequirement.HAVE_CHARGE_BEAM,new Requirement(BasicRequirement.HAVE_PLASMA_BEAM,BasicRequirement.HAVE_SPAZER_BEAM,BasicRequirement.HAVE_ICE_BEAM,BasicRequirement.HAVE_WAVE_BEAM)))),
      Visibility.VISIBLE,
      "Lower Norfair - Mickey mouse missile"
    ),
    
		MISSILE_LN_C(
      0x78FCA,
      MISSILE_LN_B.getRequirement(),
      Visibility.VISIBLE,
      "Lower Norfair - Above Fire flea room missile"
    ),
    
		MISSILE_LN_D(
      0x79100,
      MISSILE_LN_B.getRequirement(),
      Visibility.VISIBLE,
      "Lower Norfair - Near exit missile"
    ),
    
		SUPER_LN_A(
      0x78E74,
      Requirement.CAN_GO_TO_LN,
      Visibility.HIDDEN,
      "Lower Norfair - Golden Torizo Super missiles"
    ),
    
		PB_LN_A(
      0x78FD2,
      MISSILE_LN_C.getRequirement(),
      Visibility.VISIBLE,
      "Lower Norfair - Above Fire flea room Power bombs"
    ),
    
		PB_LN_B(
      0x790C0
      MISSILE_LN_C.getRequirement(),
      Visibility.VISIBLE,
      "Lower Norfair - Power bombs of shame"
    ),
    
		ENERGY_TANK_LN_A(
      0x79108,
      MISSILE_LN_C.getRequirement(),
      Visibility.HIDDEN,
      "Lower Norfair - Ridley Energy Tank"
    ),
    
		ENERGY_TANK_LN_B(
      0x79184,
      MISSILE_LN_C.getRequirement(),
      Visibility.VISIBLE,
      "Lower Norfair - Firefleas E tank"
    ),
    
		SCREW_ATTACK(
      0x79110,
      Requirement.andMode(Requirement.CAN_GO_TO_LN,new Requirement(BasicRequirement.HAVE_BOMBS,BasicRequirement.HAVE_SPEED_BOOSTER,BasicRequirement.HAVE_SPACE_JUMP)),
      Visibility.CHOZO_BALL,
      "Lower Norfair - Screw attack"
    );

		
		private int address;
		private Requirement rq;
		private Visibility visible;
		private String name;
		private Locations(int address,Requirement rq,Visibility visible,String name) {
			this.address = address;
			this.rq = rq;
			this.visible = visible;
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
		
		public Requirement getRequirement() {
			return this.rq;
		}
		
		public int getAddress() {
			return this.address;
		}
		
		public Visibility getVisibility() {
			return this.visible;
		}
	}

}
