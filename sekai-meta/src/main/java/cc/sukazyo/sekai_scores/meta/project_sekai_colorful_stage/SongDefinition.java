package cc.sukazyo.sekai_scores.meta.project_sekai_colorful_stage;

import cc.sukazyo.sekai_scores.Song;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class SongDefinition {
	
	public Song[] songs() {
		final List<Song> songs = new ArrayList<>();
		for (Field field : this.getClass().getFields()) {
			if (field.getType() == Song.class) {
				try {
					songs.add((Song)field.get(new Object()));
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return songs.toArray(Song[]::new);
	}
	
}
