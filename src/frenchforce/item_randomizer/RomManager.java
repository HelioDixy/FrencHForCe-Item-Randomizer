package frenchforce.item_randomizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RomManager {
	
	private File f;
	private boolean modifs_applied = false;
	private List<Modification> modifs;
	
	/**
	 * Default path : res/rom.sfc
	 * @param romPath
	 */
	public RomManager(String romPath) {
		this.f = new File(romPath);
		this.modifs = new ArrayList<Modification>();
	}
	
	public void addModif(Modification f) {
		if (modifs_applied) throw new IllegalStateException("Modifications are already applied.");
		if (!this.modifs.contains(f)) this.modifs.add(f);
	}
	
	public Modification[] getModifs() {
		return this.modifs.toArray(new Modification[this.modifs.size()]);
	}
	
	public void applyModifications(String output) throws IOException {
		if (!modifs_applied) {
			FileInputStream fis = new FileInputStream(f);
			FileOutputStream fos = new FileOutputStream(new File(output));
			byte[] b = new byte[1];
			int offset = 0;
			while (fis.available() > 0) {
				fis.read(b);
				for (Modification m: this.modifs) {
					if (offset == m.getOffset()) b[0] = m.getValue();
				}
				fos.write(b);
			}
			fis.close();
			fos.close();
		} else throw new IllegalStateException("Modifications are already applied.");
	}
	
	
	public static class Modification {
		
		private byte offset;
		private byte value;
		
		public Modification(byte offset, byte value) {
			this.offset = offset;
			this.value = value;
		}
		
		public byte getOffset() {
			return this.offset;
		}
		
		public byte getValue() {
			return this.value;
		}
		
		public void setOffset(byte offset) {
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
