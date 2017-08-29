package frenchforce.item_randomizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * Cette classe permet de modifier la rom en effectuant une copie.
 * Chaque modification implique la modification d'un ou de plusieurs octets.
 * Pour effectuer une modification : 
 *  - Instancier la classe avec le chemin de la rom
 *  - Ajouter une modification comme ceci instance.addModif(new Modification(0x786DE,0xEF27)); - Ici, on modifie la morph par un reserve tank
 *  - Appliquer les modifications : instance.applyModifications(../rom_rando.sfc);
 *  - ATTENTION : Une fois appliquées, vous ne pouvez plus ajouter de modification ni les réappliquer.
 * @author HélioDixy
 *
 */
public class RomManager {
	
	private File f;
	private boolean modifs_applied = false;
	private List<Modification> modifs;
	
	/**
	 * Default path : res/rom.sfc
	 * @param romPath
	 */
	public RomManager(String romPath) {
		try {
			this.f = new File(getClass().getResource(romPath).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		this.modifs = new ArrayList<Modification>();
	}
	
	public void addModif(Modification f) {
		if (modifs_applied) throw new IllegalStateException("Modifications are already applied.");
		if (!this.modifs.contains(f)) this.modifs.add(f);
		System.out.println("Nouvelle modif : " + f.getOffset() + " - " + Integer.toHexString(f.getValue()));
	}
	
	public Modification[] getModifs() {
		return this.modifs.toArray(new Modification[this.modifs.size()]);
	}
	
	public void applyModifications(String output) throws IOException {
		if (!modifs_applied) {
			File f2 = new File(output);
			f2.createNewFile();
			FileInputStream fis = new FileInputStream(f);
			FileOutputStream fos = new FileOutputStream(f2);
			byte[] b = new byte[8];
			while (fis.available() > 0) {
				fis.read(b);
				fos.write(b);
			}
			fis.close();
			fos.close();
			RandomAccessFile raf = new RandomAccessFile(f2, "rw");
			for (Modification m : this.modifs) {
				raf.seek(m.getOffset());
				byte r = raf.readByte();
				raf.writeByte(m.getValue());
				System.out.println("Wrote a byte at offset :" + raf.getFilePointer() + " - Replaced : " + Integer.toHexString(r) + " by " + Integer.toHexString(raf.readByte()));
			}
			raf.close();
		} else throw new IllegalStateException("Modifications are already applied.");
	}
	
	
	public static class Modification {
		
		private int offset;
		private byte value;
		
		public Modification(int offset, byte value) {
			this.offset = offset;
			this.value = value;
		}
		
		public int getOffset() {
			return this.offset;
		}
		
		public byte getValue() {
			return this.value;
		}
		
		public void setOffset(int offset) {
			this.offset = offset;
		}
		
		public void setValue(byte value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Modification)) return false;
			Modification m = (Modification) obj;
			if (m.getOffset() == this.getOffset() && m.getValue() == this.getValue()) return true;
			else return false;
		}
	}

}
