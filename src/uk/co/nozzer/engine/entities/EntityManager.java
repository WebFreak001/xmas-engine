package uk.co.nozzer.engine.entities;

import java.util.ArrayList;
import java.util.List;

import uk.co.nozzer.engine.gfx.Bitmap;

public class EntityManager {

	protected ArrayList<Entity> entities = new ArrayList<Entity>();
	
	protected int maxEntities = 500;
	
	public EntityManager() {
		
	}
	public EntityManager(int maxEntities) {
		this.maxEntities = maxEntities;
	}
	
	public void update(double delta) {
		for (Entity e : entities) {
			e.update(delta);
		}
	}
	
	public void render(Bitmap screen) {
		for (Entity e : entities) {
			e.render(screen);
		}
	}
	
	public void addEntity(Entity e) {
		if (entities.size() < maxEntities) entities.add(e);
	}

	public void removeEntity(int index) {
		if (index >= 0 || index < entities.size()) entities.remove(index);
	}
	
	// --- getters and setters
	public int getMaxEntities() {
		return this.maxEntities;
	}
	public List<Entity> getEntities() {
		return this.entities;
	}
	public Entity getEntity(int index) {
		return this.entities.get(index);
	}
	
	public void setMaxEntities(int maxEntities) {
		this.maxEntities = maxEntities;
	}
}
