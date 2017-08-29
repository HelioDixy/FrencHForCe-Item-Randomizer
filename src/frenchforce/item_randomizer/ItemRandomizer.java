package frenchforce.item_randomizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import frenchforce.item_randomizer.ItemsClass.Items;
import frenchforce.item_randomizer.ItemsClass.Locations;
import frenchforce.item_randomizer.Requirement.BasicRequirement;
import frenchforce.item_randomizer.RomManager.Modification;

public class ItemRandomizer {

	private static final String[] IPS = {"frenchforce_intro.ips","gravity_heat.ips","suit_patch.ips"};
	
	public static void main(String[] args) {
		Requirement.init();
		/*System.out.println(Requirement.CAN_GO_TO_RED_TOWER.getValues().size());
		for (int a : Requirement.CAN_GO_TO_RED_TOWER.getValues()) {
			System.out.println("0b" + Integer.toBinaryString(a));
		}/
		/*for (Locations l : Locations.values()) {
			System.out.println("Location : " + l.getName());
			for (int r : l.getRequirement().getValues()) {
				if ((r & 0b0100) == 0) {
					System.err.println(l.getName() + " - 0b" + Integer.toBinaryString(r));
				}
			}
		}*/
		
		
		
		generateRom("seed", 0);
	}
	
	
	public static void generateRom(String target,long seed) {
		if (seed == 0) {
			seed = new Random().nextLong() % 99999999;
		}
		RomManager rm = new RomManager("res" + System.getProperty("file.separator") + "rom.sfc");
		Random r = new Random(seed);
		List<Items> items = new ArrayList<Items>();
		items.addAll(Arrays.asList(Items.values())); //Ajout tout une fois
		//Ajouter 13 e tanks
		for (int i = 0;i<13;i++) items.add(Items.ENERGY_TANK);
		//Ajouter 3 reserves
		for (int i = 0;i<3;i++) items.add(Items.RESERVE_TANK);
		//Répartir aléatoirement le reste des mineurs en respectant les espérances suivantes : 3/3/1
		for (int i = 0;i<63;i++) {
			double d = r.nextDouble();
			if ( d <= 3.0/7.0) items.add(Items.MISSILES);
			else if (d > 3.0/7.0 && d <= 6.0/7.0) items.add(Items.SUPERS);
			else if (d > 6.0/7.0 && d <= 1.0) items.add(Items.PB);
			System.out.println(i +  " " + d + " Added : " + items.get(items.size()-1).name());
		}
		List<Locations> locations = new ArrayList<Locations>();
		locations.addAll(Arrays.asList(Locations.values()));
		List<Locations> possible = new ArrayList<Locations>();
		HashMap<Locations,Items> map = new HashMap<Locations,Items>();	
		Locations l;
		Items i;
		int act = 0;
		possible.addAll(updatePossibleLocations(0, locations));
		while (!locations.isEmpty()) {
			//Début de la boucle
			while (true) {
			List<Items> test_i = new ArrayList<Items>();
			do {
			l= possible.get(r.nextInt(possible.size()));
			i = items.get(r.nextInt(items.size()));
			test_i.clear();
			test_i.addAll(items);
			test_i.remove(i);
			} while(!Requirement.hasRequired(updateItems(act, i,test_i), l.getRequirement()));
			List<Locations> test_pos = new ArrayList<Locations>();
			test_pos.addAll(locations);
			test_pos.remove(l); 
			//On crée une liste sans la location et une sans l'item choisis, et on regarde si, en updatant les items acquis on peut aller quelque part. SI oui, on update tout, sinon, on recommence.
			if (!updatePossibleLocations(updateItems(act, i, test_i), test_pos).isEmpty() || locations.size() == 1) {
				break;
			} else {
				System.out.println("Résolution impossible. En attente d'une réponse.");
				System.out.println("Location choisie : " + l.name());
				System.out.println("Item choisi : " + i.name());
				System.out.println("Nouvel etat : " + Integer.toBinaryString(updateItems(act, i, test_i)));
				for (Locations l1 : possible) System.out.println("Location possible : " + l1.name());
			}
			}
			items.remove(i);
			locations.remove(l);
			possible.remove(l);
			map.put(l, i);
			act = updateItems(act, i, items);
			possible = updatePossibleLocations(act, locations);
			System.err.println(items.size() + " - " + locations.size() + " - Location :" + l.getName() + " - Item : " + i.name());
		}		
		System.out.println("Génération terminée.\nDémarrage de la préparation à l'écriture.");
		for (Iterator<Map.Entry<Locations, Items>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<Locations, Items> e = it.next();
			byte a = 0, b = 0;
			int c = e.getValue().getValue(e.getKey().getVisibility());
			a = (byte)c;
			b = (byte)(c>>8);
			rm.addModif(new Modification(e.getKey().getAddress(), b));
			rm.addModif(new Modification(e.getKey().getAddress()-1, a));
		}
		System.out.println("Préparation terminée.");
		try {
			System.out.println("Démarrage de l'écriture...");
			rm.applyModifications(target + "_" + seed + ".sfc");
			System.out.println("Ecriture terminée.");
			//APPLY IPS
			System.out.println("Application des patchs...");
			for (int j = 0;j<IPS.length;j++) {
				System.out.println("Application du patch : " + IPS[j]);
				applyIPS(target + "_" + seed + ".sfc","res\\ips\\" + IPS[j]);
				System.out.println(	"Application terminée.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Génération terminée. ROM prête à l'utilisation.");
	}
	
	private static void applyIPS(String rom,String ips) {
		try {
			Runtime run = Runtime.getRuntime();
			System.err.println("res\\uips.exe a " + ips + " " + rom);
			Process p = run.exec("res\\uips.exe a " + ips + " " + rom);
			p.waitFor();
			}catch(Exception e) {
			e.printStackTrace();
			} 
	}
	
	private static int updateItems(int act,Items s, List<Items> items_temp) {
		switch (s) {
		case MORPH_BALL:
			act = act | BasicRequirement.HAVE_MORPH.getValue();
			break;
		case MISSILES:
			if ((act&BasicRequirement.HAVE_MISSILES.getValue()) > 0) act = act | BasicRequirement.HAVE_10_MISSILES.getValue();
			else act = act | BasicRequirement.HAVE_MISSILES.getValue();
			break;
		case SUPERS:
			if ((act&BasicRequirement.HAVE_SUPERS.getValue()) > 0) act = act | BasicRequirement.HAVE_10_SUPERS.getValue();
			else act = act | BasicRequirement.HAVE_SUPERS.getValue();
			break;
		case PB:
			if ((act&BasicRequirement.HAVE_PB.getValue()) > 0) act = act | BasicRequirement.HAVE_10_PB.getValue();
			else act = act | BasicRequirement.HAVE_PB.getValue();
			break;
		case BOMBS:
			act = act | BasicRequirement.HAVE_BOMBS.getValue();
			break;
		case ENERGY_TANK:
			int count = 0;
			for (Items item : items_temp) {
				if (item.equals(Items.ENERGY_TANK)) count++;
			}
			count = 14 - count;
			if (count >= 3) act = act | BasicRequirement.HAVE_MORE_THAN_300PV.getValue();
			if (count >= 4) act = act | BasicRequirement.HAVE_MORE_THAN_400PV.getValue();
			if (count >= 6) act = act | BasicRequirement.HAVE_MORE_THAN_600PV.getValue();
			if (count >= 9) act = act | BasicRequirement.HAVE_MORE_THAN_900PV.getValue();
			break;
		case CHARGE:
			act = act | BasicRequirement.HAVE_CHARGE_BEAM.getValue();
			break;
		case GRAPPLING_BEAM:
			act = act | BasicRequirement.HAVE_GRAPPLING.getValue();
			break;
		case GRAVITY:
			act = act | BasicRequirement.HAVE_GRAVITY.getValue();
			break;
		case HJB:
			act = act | BasicRequirement.HAVE_HIGH_JUMP_BOOTS.getValue();
			break;
		case ICE:
			act = act | BasicRequirement.HAVE_ICE_BEAM.getValue();
			break;
		case PLASMA:
			act = act | BasicRequirement.HAVE_PLASMA_BEAM.getValue();
			break;
		case RESERVE_TANK:
			break;
		case SCREW_ATTACK:
			act = act | BasicRequirement.HAVE_SCREW_ATTACK.getValue();
			break;
		case SPACE_JUMP:
			act = act | BasicRequirement.HAVE_SPACE_JUMP.getValue();
			break;
		case SPAZER:
			act = act | BasicRequirement.HAVE_SPAZER_BEAM.getValue();
			break;
		case SPEED_BOOSTER:
			act = act | BasicRequirement.HAVE_SPEED_BOOSTER.getValue();
			break;
		case SPRING_BALL:
			act = act | BasicRequirement.HAVE_SPRING_BALL.getValue();
			break;
		case VARIA:
			act = act | BasicRequirement.HAVE_VARIA.getValue();
			break;
		case WAVE:
			act = act | BasicRequirement.HAVE_WAVE_BEAM.getValue();
			break;
		case XRAY:
			break;
		}
		return act;
	}
	
	private static List<Locations> updatePossibleLocations(int act,List<Locations> staying) {
		List<Locations> rtn = new ArrayList<Locations>();
		for (Locations l : staying) {
			if (Requirement.hasRequired(act, l.getRequirement())) rtn.add(l);
		}
		return rtn;
	}
}
